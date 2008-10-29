/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors. 
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.dna.common.monitor;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.jboss.dna.common.collection.Problems;
import org.jboss.dna.common.collection.ThreadSafeProblems;
import org.jboss.dna.common.i18n.I18n;
import org.jboss.dna.common.util.CheckArg;

/**
 * A basic activity monitor.
 * 
 * @author Randall Hauch
 * @author John Verhaeg
 */
@ThreadSafe
public class SimpleActivityMonitor implements ActivityMonitor {

    @GuardedBy( "lock" )
    private I18n taskName;
    @GuardedBy( "lock" )
    private Object[] taskNameParams;
    @GuardedBy( "lock" )
    private double totalWork;
    @GuardedBy( "lock" )
    private double worked;

    private final I18n activityName;
    private final Object[] activityNameParams;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final AtomicBoolean cancelled = new AtomicBoolean(false);
    private final Problems problems = new ThreadSafeProblems();

    public SimpleActivityMonitor( I18n activityName,
                                  Object... params ) {
        CheckArg.isNotNull(activityName, "activityName");
        this.activityName = activityName;
        this.activityNameParams = params;
        this.taskName = null;
        this.taskNameParams = null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.jboss.dna.common.monitor.ActivityMonitor#beginTask(double, org.jboss.dna.common.i18n.I18n, java.lang.Object[])
     */
    public void beginTask( double totalWork,
                           I18n name,
                           Object... params ) {
        CheckArg.isGreaterThan(totalWork, 0.0, "totalWork");
        try {
            this.lock.writeLock().lock();
            this.taskName = name;
            this.taskNameParams = params;
            this.totalWork = totalWork;
            this.worked = 0.0d;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.jboss.dna.common.monitor.ActivityMonitor#createSubtask(double)
     */
    public ActivityMonitor createSubtask( double subtaskWork ) {
        return new SubActivityMonitor(this, subtaskWork);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.jboss.dna.common.monitor.ActivityMonitor#done()
     */
    public void done() {
        boolean alreadyDone = false;
        try {
            this.lock.writeLock().lock();
            if (this.worked < this.totalWork) {
                this.worked = this.totalWork;
            } else {
                alreadyDone = true;
            }
        } finally {
            this.lock.writeLock().unlock();
        }
        if (!alreadyDone) notifyProgress();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.jboss.dna.common.monitor.ActivityMonitor#getActivityName()
     */
    public String getActivityName() {
        return getActivityName(null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.jboss.dna.common.monitor.ActivityMonitor#getActivityName(java.util.Locale)
     */
    public String getActivityName( Locale locale ) {
        return activityName.text(locale == null ? Locale.getDefault() : locale, activityNameParams);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Problems must only be added by the {@link ActivityMonitor <strong>Updater</strong>}, and accessed by
     * {@link ActivityMonitor Observers} only after the activity has been {@link #done() completed}.
     * </p>
     * 
     * @see org.jboss.dna.common.monitor.ActivityMonitor#getProblems()
     */
    public Problems getProblems() {
        return problems;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.jboss.dna.common.monitor.ActivityMonitor#getStatus(java.util.Locale)
     */
    public ActivityStatus getStatus( Locale locale ) {
        try {
            this.lock.readLock().lock();
            String localizedTaskName = this.taskName == null ? "" : this.taskName.text(locale, this.taskNameParams);
            return new ActivityStatus(this.getActivityName(locale), localizedTaskName, this.worked, this.totalWork,
                                      this.isCancelled());
        } finally {
            this.lock.readLock().unlock();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.jboss.dna.common.monitor.ActivityMonitor#isCancelled()
     */
    public boolean isCancelled() {
        return this.cancelled.get();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.jboss.dna.common.monitor.ActivityMonitor#isDone()
     */
    public boolean isDone() {
        lock.readLock().lock();
        try {
            return worked >= totalWork;
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Method that is called in {@link #worked(double)} (which is called by {@link #createSubtask(double) subtasks}) when there
     * has been some positive work, or when the monitor is first marked as {@link #done()}.
     * <p>
     * This method implementation does nothing, but subclasses can easily override this method if they want to be updated with the
     * latest progress.
     * </p>
     */
    protected void notifyProgress() {
        // do nothing
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.jboss.dna.common.monitor.ActivityMonitor#setCancelled(boolean)
     */
    public void setCancelled( boolean value ) {
        this.cancelled.set(value);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.jboss.dna.common.monitor.ActivityMonitor#worked(double)
     */
    public void worked( double work ) {
        if (work > 0) {
            try {
                this.lock.writeLock().lock();
                if (this.worked < this.totalWork) {
                    this.worked += work;
                    if (this.worked > this.totalWork) this.worked = this.totalWork;
                }
            } finally {
                this.lock.writeLock().unlock();
            }
            notifyProgress();
        }
    }
}
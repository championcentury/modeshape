/*
 * ModeShape (http://www.modeshape.org)
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 * See the AUTHORS.txt file in the distribution for a full listing of 
 * individual contributors.
 *
 * ModeShape is free software. Unless otherwise indicated, all code in ModeShape
 * is licensed to you under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 * 
 * ModeShape is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.modeshape.jdbc.metadata;

/* <p> This class contains constants indicating names of the columns in the
 *  result sets returned by methods on JcrMetaData. Each inner class represents
 *  a particular method and the class attributes give the names of the columns on
 *  methods ResultSet.</p>
 */

public interface JDBCColumnNames {

    /**
     * This class contains constants representing column names on ResultSet
     * returned by getCatalogs method on DatabaseMetaData. These constant values
     * are be used for the column names used in constructing the ResultSet obj.
     */
    interface CATALOGS {
        //  name of the column containing catalog or Virtual database name.
        static final String TABLE_CAT = "TABLE_CAT"; //$NON-NLS-1$
    }

    /**
     * This class contains constants representing column names on ResultSet
     * returned by getColumns method on DatabaseMetaData. These constant values
     * are be used to hardcode the column names used in constructin the ResultSet obj.
     */
    interface COLUMNS {

        //  name of the column containing catalog or Virtual database name.
        static final String TABLE_CAT = "TABLE_CAT"; //$NON-NLS-1$

        //  name of the column containing schema or Virtual database version.
        static final String TABLE_SCHEM = "TABLE_SCHEM"; //$NON-NLS-1$

        //  name of the column containing table or group name.
        static final String TABLE_NAME = "TABLE_NAME"; //$NON-NLS-1$

        //  name of the column containing column or element name.
        static final String COLUMN_NAME = "COLUMN_NAME"; //$NON-NLS-1$

        /** name of column that contains SQL type from java.sql.Types for column's data type. */
        static final String DATA_TYPE = "DATA_TYPE"; //$NON-NLS-1$

        /** name of column that contains local type name used by the data source. */
        static final String TYPE_NAME = "TYPE_NAME"; //$NON-NLS-1$

        //  name of the column containing column size.
        static final String COLUMN_SIZE = "COLUMN_SIZE"; //$NON-NLS-1$

        /** name of column that is not used will contain nulls */
        static final String BUFFER_LENGTH = "BUFFER_LENGTH"; //$NON-NLS-1$

        //  name of the column containing number of digits to right of decimal
        static final String DECIMAL_DIGITS = "DECIMAL_DIGITS"; //$NON-NLS-1$

        //  name of the column containing column's Radix.
        static final String NUM_PREC_RADIX = "NUM_PREC_RADIX"; //$NON-NLS-1$

        /** name of column that has an String value indicating nullablity */
        static final String NULLABLE = "NULLABLE"; //$NON-NLS-1$

        /** name of column containing explanatory notes. */
        static final String REMARKS = "REMARKS"; //$NON-NLS-1$

        /** name of column which contails default value for the column. */
        static final String COLUMN_DEF = "COLUMN_DEF"; //$NON-NLS-1$

        /** name of column that not used will contain nulls */
        static final String SQL_DATA_TYPE = "SQL_DATA_TYPE"; //$NON-NLS-1$

        /** name of column that not used will contain nulls */
        static final String SQL_DATETIME_SUB = "SQL_DATETIME_SUB"; //$NON-NLS-1$

        /** name of column that stores the max number of bytes in the column */
        static final String CHAR_OCTET_LENGTH = "CHAR_OCTET_LENGTH"; //$NON-NLS-1$

        /** name of column that stores the index of a column in the table */
        static final String ORDINAL_POSITION = "ORDINAL_POSITION"; //$NON-NLS-1$

        /** name of column that has an String value indicating nullablity */
        static final String IS_NULLABLE = "IS_NULLABLE"; //$NON-NLS-1$

        /** name of column that is the scope of a reference attribute (null if DATA_TYPE isn't REF)*/ 
        static final String SCOPE_CATLOG = "SCOPE_CATLOG"; //$NON-NLS-1$
        
        /** name of column that is the scope of a reference attribute (null if the DATA_TYPE isn't REF) */ 
        static final String SCOPE_SCHEMA = "SCOPE_SCHEMA"; //$NON-NLS-1$
        
        /** name of column that is the scope of a reference attribure (null if the DATA_TYPE isn't REF) */
        static final String SCOPE_TABLE = "SCOPE_TABLE"; //$NON-NLS-1$
        
        /** 
         * name of column that is source type of a distinct type or user-generated Ref type, SQL type
         * from java.sql.Types (null if DATA_TYPE isn't DISTINCT or user-generated REF)
         */ 
        static final String SOURCE_DATA_TYPE = "SOURCE_DATA_TYPE"; //$NON-NLS-1$
        
        /** name of column that has an String value indicating format */
        static final String FORMAT = "FORMAT"; //$NON-NLS-1$

        /** name of column that has an String value indicating minimum range */
        static final String MIN_RANGE = "MIN_RANGE"; //$NON-NLS-1$

        /** name of column that has an String value indicating maximum range */
        static final String MAX_RANGE = "MAX_RANGE"; //$NON-NLS-1$
     }


    /**
     * This class contains constants representing column names on ResultSet
     * returned by getSchemas method on DatabaseMetaData. These constant values
     * are be used to hardcode the column names used in constructin the ResultSet obj.
     */
    interface SCHEMAS {

        // name of the column containing procedure catalog or Virtual database name.
        static final String TABLE_SCHEM = "TABLE_SCHEM"; //$NON-NLS-1$

        // name of the column containing schema or Virtual database version.
        static final String TABLE_CATALOG = "TABLE_CATALOG"; //$NON-NLS-1$

    }

    /**
     * This class contains constants representing column names on ResultSet
     * returned by getTables and getTableTypes methods on DatabaseMetaData. These
     * constant values are be used to hardcode the column names used in construction
     * the ResultSet obj.
     */
    interface TABLES {

        // name of the column containing catalog or Virtual database name.
        static final String TABLE_CAT = "TABLE_CAT"; //$NON-NLS-1$

        // name of the column containing schema or Virtual database version.
        static final String TABLE_SCHEM = "TABLE_SCHEM"; //$NON-NLS-1$

        // name of the column containing table or group name.
        static final String TABLE_NAME = "TABLE_NAME"; //$NON-NLS-1$

        // name of the column containing table or group type.
        static final String TABLE_TYPE = "TABLE_TYPE"; //$NON-NLS-1$

        /** name of column containing explanatory notes. */
        static final String REMARKS = "REMARKS"; //$NON-NLS-1$
        static final String TYPE_CAT = "TYPE_CAT"; //$NON-NLS-1$
        static final String TYPE_SCHEM = "TYPE_SCHEM"; //$NON-NLS-1$
        static final String TYPE_NAME = "TYPE_NAME"; //$NON-NLS-1$
        static final String SELF_REFERENCING_COL_NAME = "SELF_REFERENCING_COL_NAME"; //$NON-NLS-1$
        static final String REF_GENERATION = "REF_GENERATION"; //$NON-NLS-1$
        static final String ISPHYSICAL = "ISPHYSICAL"; //$NON-NLS-1$
    
    }

    /**
     * This class contains constants representing column names on ResultSet
     * returned by getTables and getTableTypes methods on DatabaseMetaData. These
     * constant values are be used to hardcode the column names used in construction
     * the ResultSet obj.
     */
    interface TABLE_TYPES {

        // name of the column containing table or group type.
        static final String TABLE_TYPE = "TABLE_TYPE"; //$NON-NLS-1$
    }

    /**
     * This class contains constants representing column names on ResultSet
     * returned by getTypeInfo method on DatabaseMetaData. These constant values
     * are be used to hardcode the column names used in constructin the ResultSet obj.
     */
    interface TYPE_INFO {

        /** name of column that contains local type name used by the data source. */
        static final String TYPE_NAME = "TYPE_NAME"; //$NON-NLS-1$

        /** name of column that contains SQL type from java.sql.Types for column's data type. */
        static final String DATA_TYPE = "DATA_TYPE"; //$NON-NLS-1$

        // name of the column containing number of digits to right of decimal
        static final String PRECISION = "PRECISION"; //$NON-NLS-1$

        // name of the column containing prefix used to quote a literal
        static final String LITERAL_PREFIX = "LITERAL_PREFIX"; //$NON-NLS-1$

        // name of the column containing suffix used to quote a literal
        static final String LITERAL_SUFFIX = "LITERAL_SUFFIX"; //$NON-NLS-1$

        // name of the column containing params used in creating the type
        static final String CREATE_PARAMS = "CREATE_PARAMS"; //$NON-NLS-1$

        /** name of column that has an String value indicating nullablity */
        static final String NULLABLE = "NULLABLE"; //$NON-NLS-1$

        /** name of column that has an String value indicating case sensitivity */
        static final String CASE_SENSITIVE = "CASE_SENSITIVE"; //$NON-NLS-1$

        /** name of column that has an String value indicating searchability */
        static final String SEARCHABLE = "SEARCHABLE"; //$NON-NLS-1$

        /** name of column that has an String value indicating searchability */
        static final String UNSIGNED_ATTRIBUTE = "UNSIGNED_ATTRIBUTE"; //$NON-NLS-1$

        /** name of column that contains info if the column is a currency value */
        static final String FIXED_PREC_SCALE = "FIXED_PREC_SCALE"; //$NON-NLS-1$

        /** name of column that contains info whether the column is autoincrementable */
        static final String AUTOINCREMENT = "AUTO_INCREMENT"; //$NON-NLS-1$

        /** name of column that localised version of type name */
        static final String LOCAL_TYPE_NAME = "LOCAL_TYPE_NAME"; //$NON-NLS-1$

        /** name of column that gives the min scale supported */
        static final String MINIMUM_SCALE = "MINIMUM_SCALE"; //$NON-NLS-1$

        /** name of column that gives the max scale supported */
        static final String MAXIMUM_SCALE = "MAXIMUM_SCALE"; //$NON-NLS-1$

        /** name of column that not used will contain nulls */
        static final String SQL_DATA_TYPE = "SQL_DATA_TYPE"; //$NON-NLS-1$

        /** name of column that not used will contain nulls */
        static final String SQL_DATETIME_SUB = "SQL_DATETIME_SUB"; //$NON-NLS-1$

        // constant indiacting column's Radix.
        static final String NUM_PREC_RADIX = "NUM_PREC_RADIX"; //$NON-NLS-1$
    }

}

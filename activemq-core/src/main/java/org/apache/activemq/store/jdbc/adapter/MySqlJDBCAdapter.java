/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.store.jdbc.adapter;

import org.apache.activemq.store.jdbc.Statements;

/**
 * 
 * @version $Revision$
 */
public class MySqlJDBCAdapter extends DefaultJDBCAdapter {

    public void setStatements(Statements statements) {
        statements.setLockCreateStatement("LOCK TABLE " + statements.getFullLockTableName() + " WRITE");
        statements.setBinaryDataType("LONGBLOB");
        
        // Use INNODB table since we need transaction support.
        String[] s = statements.getCreateSchemaStatements();
        for (int i = 0; i < s.length; i++) {
            if( s[i].startsWith("CREATE TABLE")) {
                s[i] = s[i]+" TYPE=INNODB";
            }
        }
        
        super.setStatements(statements);
    }    
}

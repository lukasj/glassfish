/*
 * Copyright (c) 2003, 2018 Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.jdbcra.spi;

import com.sun.jdbcra.common.DataSourceObjectBuilder;
import com.sun.jdbcra.common.DataSourceSpec;
import com.sun.jdbcra.util.SecurityUtils;

import jakarta.resource.ResourceException;
import jakarta.resource.spi.ConnectionRequestInfo;
import jakarta.resource.spi.ResourceAllocationException;
import jakarta.resource.spi.security.PasswordCredential;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <code>ManagedConnectionFactory</code> implementation for Generic JDBC Connector.
 * This class is extended by the DataSource specific <code>ManagedConnection</code> factories
 * and the <code>ManagedConnectionFactory</code> for the <code>DriverManager</code>.
 *
 * @version        1.0, 02/08/03
 * @author        Evani Sai Surya Kiran
 */
public abstract class ManagedConnectionFactory implements jakarta.resource.spi.ManagedConnectionFactory,
    java.io.Serializable {

    protected DataSourceSpec spec = new DataSourceSpec();
    protected transient DataSourceObjectBuilder dsObjBuilder;

    protected java.io.PrintWriter logWriter = null;
    protected jakarta.resource.spi.ResourceAdapter ra = null;

    private static Logger _logger;
    static {
        _logger = Logger.getAnonymousLogger();
    }
    private final boolean debug = false;
    /**
     * Creates a Connection Factory instance. The <code>ConnectionManager</code> implementation
     * of the resource adapter is used here.
     *
     * @return        Generic JDBC Connector implementation of <code>javax.sql.DataSource</code>
     */
    @Override
    public Object createConnectionFactory() {
        if(logWriter != null) {
            logWriter.println("In createConnectionFactory()");
        }
        com.sun.jdbcra.spi.DataSource cf = new com.sun.jdbcra.spi.DataSource(
            this, null);

        return cf;
    }

    /**
     * Creates a Connection Factory instance. The <code>ConnectionManager</code> implementation
     * of the application server is used here.
     *
     * @param        cxManager        <code>ConnectionManager</code> passed by the application server
     * @return        Generic JDBC Connector implementation of <code>javax.sql.DataSource</code>
     */
    @Override
    public Object createConnectionFactory(jakarta.resource.spi.ConnectionManager cxManager) {
        if(logWriter != null) {
            logWriter.println("In createConnectionFactory(jakarta.resource.spi.ConnectionManager cxManager)");
        }
        com.sun.jdbcra.spi.DataSource cf = new com.sun.jdbcra.spi.DataSource(
            this, cxManager);
        return cf;
    }

    /**
     * Creates a new physical connection to the underlying EIS resource
     * manager.
     *
     * @param        subject        <code>Subject</code> instance passed by the application server
     * @param        cxRequestInfo        <code>ConnectionRequestInfo</code> which may be created
     *                                    as a result of the invocation <code>getConnection(user, password)</code>
     *                                    on the <code>DataSource</code> object
     * @return        <code>ManagedConnection</code> object created
     * @throws        ResourceException        if there is an error in instantiating the
     *                                         <code>DataSource</code> object used for the
     *                                       creation of the <code>ManagedConnection</code> object
     * @throws        SecurityException        if there ino <code>PasswordCredential</code> object
     *                                         satisfying this request
     * @throws        ResourceAllocationException        if there is an error in allocating the
     *                                                physical connection
     */
    @Override
    public abstract jakarta.resource.spi.ManagedConnection createManagedConnection(javax.security.auth.Subject subject,
        ConnectionRequestInfo cxRequestInfo) throws ResourceException;

    /**
     * Check if this <code>ManagedConnectionFactory</code> is equal to
     * another <code>ManagedConnectionFactory</code>.
     *
     * @param        other        <code>ManagedConnectionFactory</code> object for checking equality with
     * @return        true        if the property sets of both the
     *                        <code>ManagedConnectionFactory</code> objects are the same
     *                false        otherwise
     */
    @Override
    public abstract boolean equals(Object other);

    /**
     * Get the log writer for this <code>ManagedConnectionFactory</code> instance.
     *
     * @return        <code>PrintWriter</code> associated with this <code>ManagedConnectionFactory</code> instance
     * @see        <code>setLogWriter</code>
     */
    @Override
    public java.io.PrintWriter getLogWriter() {
        return logWriter;
    }

    /**
     * Get the <code>ResourceAdapter</code> for this <code>ManagedConnectionFactory</code> instance.
     *
     * @return        <code>ResourceAdapter</code> associated with this <code>ManagedConnectionFactory</code> instance
     * @see        <code>setResourceAdapter</code>
     */
    public jakarta.resource.spi.ResourceAdapter getResourceAdapter() {
        if(logWriter != null) {
            logWriter.println("In getResourceAdapter");
        }
        return ra;
    }

    /**
     * Returns the hash code for this <code>ManagedConnectionFactory</code>.
     *
     * @return        hash code for this <code>ManagedConnectionFactory</code>
     */
    @Override
    public int hashCode(){
        if(logWriter != null) {
                logWriter.println("In hashCode");
        }
        return spec.hashCode();
    }

    /**
     * Returns a matched <code>ManagedConnection</code> from the candidate
     * set of <code>ManagedConnection</code> objects.
     *
     * @param        connectionSet        <code>Set</code> of  <code>ManagedConnection</code>
     *                                objects passed by the application server
     * @param        subject         passed by the application server
     *                        for retrieving information required for matching
     * @param        cxRequestInfo        <code>ConnectionRequestInfo</code> passed by the application server
     *                                for retrieving information required for matching
     * @return        <code>ManagedConnection</code> that is the best match satisfying this request
     * @throws        ResourceException        if there is an error accessing the <code>Subject</code>
     *                                        parameter or the <code>Set</code> of <code>ManagedConnection</code>
     *                                        objects passed by the application server
     */
    @Override
    public jakarta.resource.spi.ManagedConnection matchManagedConnections(java.util.Set connectionSet,
        javax.security.auth.Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        if(logWriter != null) {
            logWriter.println("In matchManagedConnections");
        }

        if(connectionSet == null) {
            return null;
        }

        PasswordCredential pc = SecurityUtils.getPasswordCredential(this, subject, cxRequestInfo);

        java.util.Iterator iter = connectionSet.iterator();
        com.sun.jdbcra.spi.ManagedConnection mc = null;
        while(iter.hasNext()) {
            try {
                mc = (com.sun.jdbcra.spi.ManagedConnection) iter.next();
            } catch(java.util.NoSuchElementException nsee) {
                _logger.log(Level.SEVERE, "jdbc.exc_iter");
                throw new ResourceException(nsee.getMessage());
            }
            if(pc == null && this.equals(mc.getManagedConnectionFactory())) {
                //GJCINT
                try {
                    isValid(mc);
                    return mc;
                } catch(ResourceException re) {
                    _logger.log(Level.SEVERE, "jdbc.exc_re", re);
                    mc.connectionErrorOccurred(re, null);
                }
            } else if(SecurityUtils.isPasswordCredentialEqual(pc, mc.getPasswordCredential()) == true) {
                //GJCINT
                try {
                    isValid(mc);
                    return mc;
                } catch(ResourceException re) {
                    _logger.log(Level.SEVERE, "jdbc.re");
                    mc.connectionErrorOccurred(re, null);
                }
            }
        }
        return null;
    }

    //GJCINT
    /**
     * Checks if a <code>ManagedConnection</code> is to be validated or not
     * and validates it or returns.
     *
     * @param        mc        <code>ManagedConnection</code> to be validated
     * @throws        ResourceException        if the connection is not valid or
     *                                          if validation method is not proper
     */
    void isValid(com.sun.jdbcra.spi.ManagedConnection mc) throws ResourceException {

        if(mc.isTransactionInProgress()) {
            return;
        }

        boolean connectionValidationRequired =
            (new Boolean(spec.getDetail(DataSourceSpec.CONNECTIONVALIDATIONREQUIRED).toLowerCase())).booleanValue();
        if( connectionValidationRequired == false || mc == null) {
            return;
        }


        String validationMethod = spec.getDetail(DataSourceSpec.VALIDATIONMETHOD).toLowerCase();

        mc.checkIfValid();
        /**
         * The above call checks if the actual physical connection
         * is usable or not.
         */
        java.sql.Connection con = mc.getActualConnection();

        if(validationMethod.equals("auto-commit") == true) {
            isValidByAutoCommit(con);
        } else if(validationMethod.equalsIgnoreCase("meta-data") == true) {
            isValidByMetaData(con);
        } else if(validationMethod.equalsIgnoreCase("table") == true) {
            isValidByTableQuery(con, spec.getDetail(DataSourceSpec.VALIDATIONTABLENAME));
        } else {
            throw new ResourceException("The validation method is not proper");
        }
    }

    /**
     * Checks if a <code>java.sql.Connection</code> is valid or not
     * by checking its auto commit property.
     *
     * @param        con        <code>java.sql.Connection</code> to be validated
     * @throws        ResourceException        if the connection is not valid
     */
    protected void isValidByAutoCommit(java.sql.Connection con) throws ResourceException {
        if(con == null) {
            throw new ResourceException("The connection is not valid as "
                + "the connection is null");
        }

        try {
           // Notice that using something like
           // dbCon.setAutoCommit(dbCon.getAutoCommit()) will cause problems with
           // some drivers like sybase
           // We do not validate connections that are already enlisted
           //in a transaction
           // We cycle autocommit to true and false to by-pass drivers that
           // might cache the call to set autocomitt
           // Also notice that some XA data sources will throw and exception if
           // you try to call setAutoCommit, for them this method is not recommended

           boolean ac = con.getAutoCommit();
           if (ac) {
                con.setAutoCommit(false);
           } else {
                con.rollback(); // prevents uncompleted transaction exceptions
                con.setAutoCommit(true);
           }

           con.setAutoCommit(ac);

        } catch(Exception sqle) {
            _logger.log(Level.SEVERE, "jdbc.exc_autocommit");
            throw new ResourceException(sqle.getMessage());
        }
    }

    /**
     * Checks if a <code>java.sql.Connection</code> is valid or not
     * by checking its meta data.
     *
     * @param        con        <code>java.sql.Connection</code> to be validated
     * @throws        ResourceException        if the connection is not valid
     */
    protected void isValidByMetaData(java.sql.Connection con) throws ResourceException {
        if(con == null) {
            throw new ResourceException("The connection is not valid as "
                + "the connection is null");
        }

        try {
            java.sql.DatabaseMetaData dmd = con.getMetaData();
        } catch(Exception sqle) {
            _logger.log(Level.SEVERE, "jdbc.exc_md");
            throw new ResourceException("The connection is not valid as "
                + "getting the meta data failed: " + sqle.getMessage());
        }
    }

    /**
     * Checks if a <code>java.sql.Connection</code> is valid or not
     * by querying a table.
     *
     * @param        con        <code>java.sql.Connection</code> to be validated
     * @param        tableName        table which should be queried
     * @throws        ResourceException        if the connection is not valid
     */
    protected void isValidByTableQuery(java.sql.Connection con,
        String tableName) throws ResourceException {
        if(con == null) {
            throw new ResourceException("The connection is not valid as "
                + "the connection is null");
        }

        try {
            java.sql.Statement stmt = con.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
        } catch(Exception sqle) {
            _logger.log(Level.SEVERE, "jdbc.exc_execute");
            throw new ResourceException("The connection is not valid as "
                + "querying the table " + tableName + " failed: " + sqle.getMessage());
        }
    }

    /**
     * Sets the isolation level specified in the <code>ConnectionRequestInfo</code>
     * for the <code>ManagedConnection</code> passed.
     *
     * @param        mc        <code>ManagedConnection</code>
     * @throws        ResourceException        if the isolation property is invalid
     *                                        or if the isolation cannot be set over the connection
     */
    protected void setIsolation(com.sun.jdbcra.spi.ManagedConnection mc) throws ResourceException {

            java.sql.Connection con = mc.getActualConnection();
            if(con == null) {
                return;
            }

            String tranIsolation = spec.getDetail(DataSourceSpec.TRANSACTIONISOLATION);
            if(tranIsolation != null && tranIsolation.equals("") == false) {
                int tranIsolationInt = getTransactionIsolationInt(tranIsolation);
                try {
                    con.setTransactionIsolation(tranIsolationInt);
                } catch(java.sql.SQLException sqle) {
                _logger.log(Level.SEVERE, "jdbc.exc_tx_level");
                    throw new ResourceException("The transaction isolation could "
                        + "not be set: " + sqle.getMessage());
                }
            }
    }

    /**
     * Resets the isolation level for the <code>ManagedConnection</code> passed.
     * If the transaction level is to be guaranteed to be the same as the one
     * present when this <code>ManagedConnection</code> was created, as specified
     * by the <code>ConnectionRequestInfo</code> passed, it sets the transaction
     * isolation level from the <code>ConnectionRequestInfo</code> passed. Else,
     * it sets it to the transaction isolation passed.
     *
     * @param        mc        <code>ManagedConnection</code>
     * @param        tranIsol        int
     * @throws        ResourceException        if the isolation property is invalid
     *                                        or if the isolation cannot be set over the connection
     */
    void resetIsolation(com.sun.jdbcra.spi.ManagedConnection mc, int tranIsol) throws ResourceException {

            java.sql.Connection con = mc.getActualConnection();
            if(con == null) {
                return;
            }

            String tranIsolation = spec.getDetail(DataSourceSpec.TRANSACTIONISOLATION);
            if(tranIsolation != null && tranIsolation.equals("") == false) {
                String guaranteeIsolationLevel = spec.getDetail(DataSourceSpec.GUARANTEEISOLATIONLEVEL);

                if(guaranteeIsolationLevel != null && guaranteeIsolationLevel.equals("") == false) {
                    boolean guarantee = (new Boolean(guaranteeIsolationLevel.toLowerCase())).booleanValue();

                    if(guarantee) {
                        int tranIsolationInt = getTransactionIsolationInt(tranIsolation);
                        try {
                            if(tranIsolationInt != con.getTransactionIsolation()) {
                                con.setTransactionIsolation(tranIsolationInt);
                            }
                        } catch(java.sql.SQLException sqle) {
                        _logger.log(Level.SEVERE, "jdbc.exc_tx_level");
                            throw new ResourceException("The isolation level could not be set: "
                                + sqle.getMessage());
                        }
                    } else {
                        try {
                            if(tranIsol != con.getTransactionIsolation()) {
                                con.setTransactionIsolation(tranIsol);
                            }
                        } catch(java.sql.SQLException sqle) {
                        _logger.log(Level.SEVERE, "jdbc.exc_tx_level");
                            throw new ResourceException("The isolation level could not be set: "
                                + sqle.getMessage());
                        }
                    }
                }
            }
    }

    /**
     * Gets the integer equivalent of the string specifying
     * the transaction isolation.
     *
     * @param        tranIsolation        string specifying the isolation level
     * @return        tranIsolationInt        the <code>java.sql.Connection</code> constant
     *                                        for the string specifying the isolation.
     */
    private int getTransactionIsolationInt(String tranIsolation) throws ResourceException {
            if(tranIsolation.equalsIgnoreCase("read-uncommitted")) {
                return java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
            } else if(tranIsolation.equalsIgnoreCase("read-committed")) {
                return java.sql.Connection.TRANSACTION_READ_COMMITTED;
            } else if(tranIsolation.equalsIgnoreCase("repeatable-read")) {
                return java.sql.Connection.TRANSACTION_REPEATABLE_READ;
            } else if(tranIsolation.equalsIgnoreCase("serializable")) {
                return java.sql.Connection.TRANSACTION_SERIALIZABLE;
            } else {
                throw new ResourceException("Invalid transaction isolation; the transaction "
                    + "isolation level can be empty or any of the following: "
                        + "read-uncommitted, read-committed, repeatable-read, serializable");
            }
    }

    /**
     * Set the log writer for this <code>ManagedConnectionFactory</code> instance.
     *
     * @param        out        <code>PrintWriter</code> passed by the application server
     * @see        <code>getLogWriter</code>
     */
    @Override
    public void setLogWriter(java.io.PrintWriter out) {
        logWriter = out;
    }

    /**
     * Set the associated <code>ResourceAdapter</code> JavaBean.
     *
     * @param        ra        <code>ResourceAdapter</code> associated with this
     *                        <code>ManagedConnectionFactory</code> instance
     * @see        <code>getResourceAdapter</code>
     */
    public void setResourceAdapter(jakarta.resource.spi.ResourceAdapter ra) {
        this.ra = ra;
    }

    /**
     * Sets the user name
     *
     * @param        user        <code>String</code>
     */
    public void setUser(String user) {
        spec.setDetail(DataSourceSpec.USERNAME, user);
    }

    /**
     * Gets the user name
     *
     * @return        user
     */
    public String getUser() {
        return spec.getDetail(DataSourceSpec.USERNAME);
    }

    /**
     * Sets the user name
     *
     * @param        user        <code>String</code>
     */
    public void setuser(String user) {
        spec.setDetail(DataSourceSpec.USERNAME, user);
    }

    /**
     * Gets the user name
     *
     * @return        user
     */
    public String getuser() {
        return spec.getDetail(DataSourceSpec.USERNAME);
    }

    /**
     * Sets the password
     *
     * @param        passwd        <code>String</code>
     */
    public void setPassword(String passwd) {
        spec.setDetail(DataSourceSpec.PASSWORD, passwd);
    }

    /**
     * Gets the password
     *
     * @return        passwd
     */
    public String getPassword() {
        return spec.getDetail(DataSourceSpec.PASSWORD);
    }

    /**
     * Sets the password
     *
     * @param        passwd        <code>String</code>
     */
    public void setpassword(String passwd) {
        spec.setDetail(DataSourceSpec.PASSWORD, passwd);
    }

    /**
     * Gets the password
     *
     * @return        passwd
     */
    public String getpassword() {
        return spec.getDetail(DataSourceSpec.PASSWORD);
    }

    /**
     * Sets the class name of the data source
     *
     * @param        className        <code>String</code>
     */
    public void setClassName(String className) {
        spec.setDetail(DataSourceSpec.CLASSNAME, className);
    }

    /**
     * Gets the class name of the data source
     *
     * @return        className
     */
    public String getClassName() {
        return spec.getDetail(DataSourceSpec.CLASSNAME);
    }

    /**
     * Sets the class name of the data source
     *
     * @param        className        <code>String</code>
     */
    public void setclassName(String className) {
        spec.setDetail(DataSourceSpec.CLASSNAME, className);
    }

    /**
     * Gets the class name of the data source
     *
     * @return        className
     */
    public String getclassName() {
        return spec.getDetail(DataSourceSpec.CLASSNAME);
    }

    /**
     * Sets if connection validation is required or not
     *
     * @param        conVldReq        <code>String</code>
     */
    public void setConnectionValidationRequired(String conVldReq) {
        spec.setDetail(DataSourceSpec.CONNECTIONVALIDATIONREQUIRED, conVldReq);
    }

    /**
     * Returns if connection validation is required or not
     *
     * @return        connection validation requirement
     */
    public String getConnectionValidationRequired() {
        return spec.getDetail(DataSourceSpec.CONNECTIONVALIDATIONREQUIRED);
    }

    /**
     * Sets if connection validation is required or not
     *
     * @param        conVldReq        <code>String</code>
     */
    public void setconnectionValidationRequired(String conVldReq) {
        spec.setDetail(DataSourceSpec.CONNECTIONVALIDATIONREQUIRED, conVldReq);
    }

    /**
     * Returns if connection validation is required or not
     *
     * @return        connection validation requirement
     */
    public String getconnectionValidationRequired() {
        return spec.getDetail(DataSourceSpec.CONNECTIONVALIDATIONREQUIRED);
    }

    /**
     * Sets the validation method required
     *
     * @param        validationMethod        <code>String</code>
     */
    public void setValidationMethod(String validationMethod) {
            spec.setDetail(DataSourceSpec.VALIDATIONMETHOD, validationMethod);
    }

    /**
     * Returns the connection validation method type
     *
     * @return        validation method
     */
    public String getValidationMethod() {
        return spec.getDetail(DataSourceSpec.VALIDATIONMETHOD);
    }

    /**
     * Sets the validation method required
     *
     * @param        validationMethod        <code>String</code>
     */
    public void setvalidationMethod(String validationMethod) {
            spec.setDetail(DataSourceSpec.VALIDATIONMETHOD, validationMethod);
    }

    /**
     * Returns the connection validation method type
     *
     * @return        validation method
     */
    public String getvalidationMethod() {
        return spec.getDetail(DataSourceSpec.VALIDATIONMETHOD);
    }

    /**
     * Sets the table checked for during validation
     *
     * @param        table        <code>String</code>
     */
    public void setValidationTableName(String table) {
        spec.setDetail(DataSourceSpec.VALIDATIONTABLENAME, table);
    }

    /**
     * Returns the table checked for during validation
     *
     * @return        table
     */
    public String getValidationTableName() {
        return spec.getDetail(DataSourceSpec.VALIDATIONTABLENAME);
    }

    /**
     * Sets the table checked for during validation
     *
     * @param        table        <code>String</code>
     */
    public void setvalidationTableName(String table) {
        spec.setDetail(DataSourceSpec.VALIDATIONTABLENAME, table);
    }

    /**
     * Returns the table checked for during validation
     *
     * @return        table
     */
    public String getvalidationTableName() {
        return spec.getDetail(DataSourceSpec.VALIDATIONTABLENAME);
    }

    /**
     * Sets the transaction isolation level
     *
     * @param        trnIsolation        <code>String</code>
     */
    public void setTransactionIsolation(String trnIsolation) {
        spec.setDetail(DataSourceSpec.TRANSACTIONISOLATION, trnIsolation);
    }

    /**
     * Returns the transaction isolation level
     *
     * @return        transaction isolation level
     */
    public String getTransactionIsolation() {
        return spec.getDetail(DataSourceSpec.TRANSACTIONISOLATION);
    }

    /**
     * Sets the transaction isolation level
     *
     * @param        trnIsolation        <code>String</code>
     */
    public void settransactionIsolation(String trnIsolation) {
        spec.setDetail(DataSourceSpec.TRANSACTIONISOLATION, trnIsolation);
    }

    /**
     * Returns the transaction isolation level
     *
     * @return        transaction isolation level
     */
    public String gettransactionIsolation() {
        return spec.getDetail(DataSourceSpec.TRANSACTIONISOLATION);
    }

    /**
     * Sets if the transaction isolation level is to be guaranteed
     *
     * @param        guaranteeIsolation        <code>String</code>
     */
    public void setGuaranteeIsolationLevel(String guaranteeIsolation) {
        spec.setDetail(DataSourceSpec.GUARANTEEISOLATIONLEVEL, guaranteeIsolation);
    }

    /**
     * Returns the transaction isolation level
     *
     * @return        isolation level guarantee
     */
    public String getGuaranteeIsolationLevel() {
        return spec.getDetail(DataSourceSpec.GUARANTEEISOLATIONLEVEL);
    }

    /**
     * Sets if the transaction isolation level is to be guaranteed
     *
     * @param        guaranteeIsolation        <code>String</code>
     */
    public void setguaranteeIsolationLevel(String guaranteeIsolation) {
        spec.setDetail(DataSourceSpec.GUARANTEEISOLATIONLEVEL, guaranteeIsolation);
    }

    /**
     * Returns the transaction isolation level
     *
     * @return        isolation level guarantee
     */
    public String getguaranteeIsolationLevel() {
        return spec.getDetail(DataSourceSpec.GUARANTEEISOLATIONLEVEL);
    }

}

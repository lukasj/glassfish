/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
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

/*
 * WebTagNames.java
 *
 * Created on February 20, 2002, 5:15 PM
 */

package org.glassfish.web.deployment.xml;

import com.sun.enterprise.deployment.xml.TagNames;

/**
 * Holds all tag names for the web application dtd
 *
 * @author  Jerome Dochez
 * @version
 */
public interface WebTagNames extends TagNames {

    public static final String COMMON_NAME = "name";
    public static final String WEB_BUNDLE = "web-app";
    public static final String WEB_FRAGMENT = "web-fragment";
    public static final String SERVLET = "servlet";
    public static final String SERVLET_NAME = "servlet-name";
    public static final String SERVLET_CLASS = "servlet-class";
    public static final String JSP_FILENAME = "jsp-file";
    public static final String LOAD_ON_STARTUP = "load-on-startup";

    public static final String FILTER = "filter";
    public static final String FILTER_MAPPING = "filter-mapping";
    public static final String FILTER_CLASS = "filter-class";
    public static final String FILTER_NAME = "filter-name";
    public static final String DISPATCHER = "dispatcher";

    public static final String INIT_PARAM = "init-param";
    public static final String PARAM_NAME = "param-name";
    public static final String PARAM_VALUE = "param-value";
    public static final String CONTEXT_PARAM = "context-param";
    public static final String ENABLED = "enabled";
    public static final String ASYNC_SUPPORTED = "async-supported";

    public static final String SECURITY_CONSTRAINT = "security-constraint";
    public static final String WEB_RESOURCE_COLLECTION = "web-resource-collection";
    public static final String AUTH_CONSTRAINT = "auth-constraint";
    public static final String USERDATA_CONSTRAINT = "user-data-constraint";
    public static final String TRANSPORT_GUARANTEE = "transport-guarantee";
    public static final String WEB_RESOURCE_NAME = "web-resource-name";
    public static final String URL_PATTERN = "url-pattern";
    public static final String HTTP_METHOD = "http-method";
    public static final String HTTP_METHOD_OMISSION = "http-method-omission";
    public static final String DISTRIBUTABLE = "distributable";
    public static final String SESSION_CONFIG = "session-config";
    public static final String SESSION_TIMEOUT = "session-timeout";
    public static final String COOKIE_CONFIG = "cookie-config";
    public static final String DOMAIN = "domain";
    public static final String PATH = "path";
    public static final String COMMENT = "comment";
    public static final String HTTP_ONLY = "http-only";
    public static final String SECURE = "secure";
    public static final String MAX_AGE = "max-age";
    public static final String TRACKING_MODE = "tracking-mode";
    public static final String WELCOME_FILE_LIST = "welcome-file-list";
    public static final String WELCOME_FILE = "welcome-file";
    public static final String SERVLET_MAPPING = "servlet-mapping";

    public static final String MIME_MAPPING  = "mime-mapping";
    public static final String EXTENSION = "extension";
    public static final String MIME_TYPE  = "mime-type";

    public static final String LISTENER = "listener";
    public static final String LISTENER_CLASS = "listener-class";

    public static final String ERROR_PAGE = "error-page";
    public static final String ERROR_CODE = "error-code";
    public static final String EXCEPTION_TYPE = "exception-type";
    public static final String LOCATION = "location";

    public static final String LOGIN_CONFIG = "login-config";
    public static final String AUTH_METHOD = "auth-method";
    public static final String REALM_NAME = "realm-name";
    public static final String FORM_LOGIN_CONFIG = "form-login-config";
    public static final String FORM_LOGIN_PAGE = "form-login-page";
    public static final String FORM_ERROR_PAGE = "form-error-page";

    public static final String JSPCONFIG = "jsp-config";
    public static final String TAGLIB = "taglib";
    public static final String TAGLIB_URI = "taglib-uri";
    public static final String TAGLIB_LOCATION = "taglib-location";
    public static final String JSP_GROUP = "jsp-property-group";
    public static final String EL_IGNORED = "el-ignored";
    public static final String PAGE_ENCODING = "page-encoding";
    public static final String SCRIPTING_INVALID = "scripting-invalid";
    public static final String INCLUDE_PRELUDE = "include-prelude";
    public static final String INCLUDE_CODA = "include-coda";
    public static final String IS_XML = "is-xml";
    public static final String DEFERRED_SYNTAX_ALLOWED_AS_LITERAL =
        "deferred-syntax-allowed-as-literal";
    public static final String TRIM_DIRECTIVE_WHITESPACES =
        "trim-directive-whitespaces";
    public static final String DEFAULT_CONTENT_TYPE = "default-content-type";
    public static final String BUFFER = "buffer";
    public static final String ERROR_ON_UNDECLARED_NAMESPACE = "error-on-undeclared-namespace";

    public static final String LOCALE_ENCODING_MAPPING_LIST = "locale-encoding-mapping-list";
    public static final String LOCALE_ENCODING_MAPPING = "locale-encoding-mapping";
    public static final String LOCALE = "locale";
    public static final String ENCODING = "encoding";
    public static final String DEFAULT_CONTEXT_PATH = "default-context-path";
    public static final String REQUEST_CHARACTER_ENCODING = "request-character-encoding";
    public static final String RESPONSE_CHARACTER_ENCODING = "response-character-encoding";

    //ordering
    public static final String ABSOLUTE_ORDERING = "absolute-ordering";
    public static final String OTHERS = "others";
    public static final String ORDERING = "ordering";
    public static final String AFTER = "after";
    public static final String BEFORE = "before";

    public static final String MULTIPART_CONFIG = "multipart-config";
    public static final String MAX_FILE_SIZE = "max-file-size";
    public static final String MAX_REQUEST_SIZE = "max-request-size";
    public static final String FILE_SIZE_THRESHOLD = "file-size-threshold";

    public static final String DENY_UNCOVERED_HTTP_METHODS = "deny-uncovered-http-methods";
}
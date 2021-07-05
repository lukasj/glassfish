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

package com.sun.enterprise.admin.remote;

/**
 *
 * @author bnevins
 */
public abstract class RemoteException extends Exception {
    // save the cause string from server
    private String remoteCause = "";

    RemoteException(String msg) {
        super(msg);
    }

    RemoteException(String msg, String cause) {
        super(msg);
        if (cause != null) {
            remoteCause = cause;
        }
    }

    RemoteException(Throwable t) {
        super(t.getMessage(), t);
    }

    public String getRemoteCause() {
        return remoteCause;
    }
}
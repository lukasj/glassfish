/*
 * Copyright (c) 2022 Eclipse Foundation and/or its affiliates. All rights reserved.
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

package org.glassfish.common.util.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Input stream which doesn't contain anything.
 *
 * @author David Matejcek
 */
public class EmptyInputStream extends InputStream {

    @Override
    public int read() throws IOException {
        return -1;
    }


    @Override
    public int available() throws IOException {
        return 0;
    }


    @Override
    public int read(final byte[] b) throws IOException {
        return -1;
    }


    @Override
    public int read(final byte[] b, final int off, final int len) throws IOException {
        return -1;
    }
}

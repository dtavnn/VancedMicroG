/*
 * SPDX-FileCopyrightText: 2020, microG Project Team
 * SPDX-License-Identifier: Apache-2.0
 */

package org.microg.gms.common.api;

import android.annotation.SuppressLint;

public class GoogleApiManager {
    @SuppressLint("StaticFieldLeak")
    private static GoogleApiManager instance;

    private GoogleApiManager() {
    }

    public synchronized static void getInstance() {
        if (instance == null) instance = new GoogleApiManager();
    }

}

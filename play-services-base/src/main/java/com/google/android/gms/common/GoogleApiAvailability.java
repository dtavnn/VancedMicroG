/*
 * Copyright (C) 2013-2017 microG Project Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.gms.common;

import android.app.Dialog;

import org.microg.gms.common.PublicApi;

@PublicApi
public class GoogleApiAvailability {

    private GoogleApiAvailability() {
    }

    private static final class InstanceHolder {
        static final GoogleApiAvailability instance = new GoogleApiAvailability();
    }

    /**
     * Returns the singleton instance of GoogleApiAvailability.
     */
    public static GoogleApiAvailability getInstance() {
        return InstanceHolder.instance;
    }


    /**
     * Returns a dialog to address the provided errorCode. The returned dialog displays a localized
     * message about the error and upon user confirmation (by tapping on dialog) will direct them
     * to the Play Store if Google Play services is out of date or missing, or to system settings
     * if Google Play services is disabled on the device.
     *
     */
    public Dialog getErrorDialog() {
        // TODO
        return null;
    }

}

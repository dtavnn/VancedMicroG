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
import android.app.PendingIntent;
import android.util.Log;

import org.microg.gms.common.Constants;
import org.microg.gms.common.PublicApi;

/**
 * Utility class for verifying that the Google Play services APK is available and up-to-date on
 * this device. The same checks are performed if one uses {@link AdvertisingIdClient} or
 * {@link GoogleAuthUtil} to connect to the service.
 * <p/>
 * TODO: methods :)
 */
@PublicApi
public class GooglePlayServicesUtil {
    private static final String TAG = "GooglePlayServicesUtil";

    /**
     * Package name for Google Play services.
     */
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = Constants.GMS_PACKAGE_NAME;

    /**
     * Google Play services client library version (declared in library's AndroidManifest.xml android:versionCode).
     */
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = Constants.GMS_VERSION_CODE;

    /**
     * Returns a dialog to address the provided errorCode. The returned dialog displays a localized
     * message about the error and upon user confirmation (by tapping on dialog) will direct them
     * to the Play Store if Google Play services is out of date or missing, or to system settings
     * if Google Play services is disabled on the device.
     *
     */
    @Deprecated
    public static Dialog getErrorDialog() {
        return GoogleApiAvailability.getInstance().getErrorDialog();
    }

    /**
     * Returns a PendingIntent to address the provided errorCode. It will direct them to one of the
     * following places to either the Play Store if Google Play services is out of date or missing,
     * or system settings if Google Play services is disabled on the device.
     *
     */
    @Deprecated
    public static PendingIntent getErrorPendingIntent() {
        return null; // TODO
    }

    /**
     * Returns a human-readable string of the error code returned from {@link #isGooglePlayServicesAvailable()}.
     */
    @Deprecated
    public static String getErrorString() {
        return null; // TODO
    }

    /**
     * Returns the open source software license information for the Google Play services
     * application, or null if Google Play services is not available on this device.
     */
    @Deprecated
    public static String getOpenSourceSoftwareLicenseInfo() {
        return null; // TODO
    }

    /**
     * Verifies that Google Play services is installed and enabled on this device, and that the
     * version installed on this device is no older than the one required by this client.
     *
     * @return status code indicating whether there was an error. Can be one of following in
     * {@link ConnectionResult}: SUCCESS, SERVICE_MISSING, SERVICE_VERSION_UPDATE_REQUIRED,
     * SERVICE_DISABLED, SERVICE_INVALID
     */
    @Deprecated
    public static int isGooglePlayServicesAvailable() {
        Log.d(TAG, "As we can't know right now if the later desired feature is available, " +
                "we just pretend it to be.");
        return ConnectionResult.SUCCESS;
    }

    @Deprecated
    public static boolean isGoogleSignedUid() {
        return false; // TODO
    }

    /**
     * Determines whether an error is user-recoverable. If true, proceed by calling
     * {@link #getErrorDialog()} and showing the dialog.
     *
     * @return true if the error is recoverable with {@link #getErrorDialog()}
     */
    @Deprecated
    public static boolean isUserRecoverableError() {
        return false; // TODO
    }

    @Deprecated
    public static boolean showErrorDialogFragment() {
        return false; // TODO
    }

    /**
     * Displays a notification relevant to the provided error code. This method is similar to
     * {@link #getErrorDialog()}, but is provided for background
     * tasks that cannot or shouldn't display dialogs.
     *
     */
    @Deprecated
    public static void showErrorNotification() {
        // TODO
    }
}

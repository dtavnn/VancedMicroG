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

package com.google.android.gms.common.api;

import android.app.PendingIntent;

import org.microg.gms.common.PublicApi;
import org.microg.safeparcel.AutoSafeParcelable;
import org.microg.safeparcel.SafeParceled;

/**
 * Represents the results of work.
 */
@PublicApi
public final class Status extends AutoSafeParcelable implements Result {
    @PublicApi(exclude = true)
    public static final Status INTERNAL_ERROR = new Status(CommonStatusCodes.INTERNAL_ERROR, "Internal error");
    @PublicApi(exclude = true)
    public static final Status CANCELED = new Status(CommonStatusCodes.CANCELED, "Cancelled");
    @PublicApi(exclude = true)
    public static final Status SUCCESS = new Status(CommonStatusCodes.SUCCESS, "Success");

    @SafeParceled(1000)
    private final int versionCode = 1;

    @SafeParceled(1)
    private final int statusCode;

    private Status() {
        statusCode = 0;
    }

    /**
     * Creates a representation of the status resulting from a GoogleApiClient operation.
     *
     * @param statusCode The status code.
     */
    public Status(int statusCode) {
        this(statusCode, null);
    }

    /**
     * Creates a representation of the status resulting from a GoogleApiClient operation.
     *
     * @param statusCode    The status code.
     * @param statusMessage The message associated with this status, or null.
     */
    public Status(int statusCode, String statusMessage) {
        this(statusCode, statusMessage, null);
    }

    /**
     * Creates a representation of the status resulting from a GoogleApiClient operation.
     *
     * @param statusCode    The status code.
     * @param statusMessage The message associated with this status, or null.
     * @param resolution    A pending intent that will resolve the issue when started, or null.
     */
    public Status(int statusCode, String statusMessage, PendingIntent resolution) {
        this.statusCode = statusCode;
    }

    /**
     * Returns the status of this result. Use {@link #isSuccess()} to determine whether the call
     * was successful, and {@link #getStatusCode()} to determine what the error cause was.
     * <p>
     * Certain errors are due to failures that can be resolved by launching a particular intent.
     * The resolution intent is available via {@link #getResolution()}.
     */
    @Override
    public Status getStatus() {
        return this;
    }

    /**
     * Returns true if the operation was canceled.
     */
    public boolean isCanceled() {
        return statusCode == CommonStatusCodes.CANCELED;
    }

    public static final Creator<Status> CREATOR = new AutoCreator<>(Status.class);
}

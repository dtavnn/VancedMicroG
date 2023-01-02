/*
 * SPDX-FileCopyrightText: 2020, microG Project Team
 * SPDX-License-Identifier: Apache-2.0
 */

package com.google.android.gms.common.api;

import android.content.Context;

import org.microg.gms.common.PublicApi;
import org.microg.gms.common.api.GoogleApiManager;

@PublicApi
public abstract class GoogleApi<O extends Api.ApiOptions> implements HasApiKey {
    @PublicApi(exclude = true)
    public Api<O> api;

    @PublicApi(exclude = true)
    protected GoogleApi(Context context, Api<O> api) {
        this.api = api;
        GoogleApiManager.getInstance();
    }

    @PublicApi(exclude = true)
    public O getOptions() {
        return null;
    }
}

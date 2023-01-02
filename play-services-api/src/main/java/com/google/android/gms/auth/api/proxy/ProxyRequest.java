/*
 * SPDX-FileCopyrightText: 2022 microG Project Team
 * SPDX-License-Identifier: Apache-2.0
 */

package com.google.android.gms.auth.api.proxy;

import androidx.annotation.NonNull;

import org.microg.safeparcel.AutoSafeParcelable;

public class ProxyRequest extends AutoSafeParcelable {

    @Field(1)
    public String url;

    @NonNull
    @Override
    public String toString() {
        return url;
    }

    public static final Creator<ProxyRequest> CREATOR = new AutoCreator<>(ProxyRequest.class);
}

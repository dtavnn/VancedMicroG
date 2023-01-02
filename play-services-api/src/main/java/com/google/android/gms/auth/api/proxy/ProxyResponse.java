/*
 * SPDX-FileCopyrightText: 2022 microG Project Team
 * SPDX-License-Identifier: Apache-2.0
 */

package com.google.android.gms.auth.api.proxy;

import org.microg.safeparcel.AutoSafeParcelable;

public class ProxyResponse extends AutoSafeParcelable {
    @Field(1)
    public int gmsStatusCode;

    public static final Creator<ProxyResponse> CREATOR = new AutoCreator<>(ProxyResponse.class);
}

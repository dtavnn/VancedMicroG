/*
 * SPDX-FileCopyrightText: 2022 microG Project Team
 * SPDX-License-Identifier: Apache-2.0
 */

package com.google.android.gms.auth;

import org.microg.safeparcel.AutoSafeParcelable;

public class GetHubTokenInternalResponse extends AutoSafeParcelable {
    @Field(2)
    public String status;
    public static final Creator<GetHubTokenInternalResponse> CREATOR = new AutoCreator<>(GetHubTokenInternalResponse.class);
}

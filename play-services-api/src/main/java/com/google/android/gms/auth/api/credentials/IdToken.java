/*
 * SPDX-FileCopyrightText: 2016, microG Project Team
 * SPDX-License-Identifier: Apache-2.0
 * Notice: Portions of this file are reproduced from work created and shared by Google and used
 *         according to terms described in the Creative Commons 4.0 Attribution License.
 *         See https://developers.google.com/readme/policies for details.
 */

package com.google.android.gms.auth.api.credentials;

import org.microg.gms.common.PublicApi;
import org.microg.safeparcel.AutoSafeParcelable;

@PublicApi
public class IdToken extends AutoSafeParcelable {

    @Field(1)
    private final String accountType;

    @Field(2)
    private final String id;

    public IdToken(String accountType, String id) {
        this.accountType = accountType;
        this.id = id;
    }

    /**
     * Returns {@code AccountManager} account type for the token.
     */
    public String getAccountType() {
        return accountType;
    }

    public static final Creator<IdToken> CREATOR = new AutoCreator<>(IdToken.class);
}

/*
 * SPDX-FileCopyrightText: 2016, microG Project Team
 * SPDX-License-Identifier: Apache-2.0
 * Notice: Portions of this file are reproduced from work created and shared by Google and used
 *         according to terms described in the Creative Commons 4.0 Attribution License.
 *         See https://developers.google.com/readme/policies for details.
 */

package com.google.android.gms.auth.api.credentials;

import org.microg.safeparcel.AutoSafeParcelable;

public class CredentialRequest extends AutoSafeParcelable {

    @Field(1)
    private final boolean passwordLoginSupported;

    public CredentialRequest(boolean passwordLoginSupported) {
        this.passwordLoginSupported = passwordLoginSupported;
    }

    /**
     * @deprecated Use {@link #isPasswordLoginSupported()}
     */
    @Deprecated
    public boolean getSupportsPasswordLogin() {
        return isPasswordLoginSupported();
    }

    public boolean isPasswordLoginSupported() {
        return passwordLoginSupported;
    }

    public static final Creator<CredentialRequest> CREATOR = new AutoCreator<CredentialRequest>(CredentialRequest.class);

    public static class Builder {

        public Builder() {
        }

        public void setAccountTypes(String... accountTypes) {
            accountTypes.clone();
        }
    }
}

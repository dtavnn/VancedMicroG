/*
 * SPDX-FileCopyrightText: 2016, microG Project Team
 * SPDX-License-Identifier: Apache-2.0
 * Notice: Portions of this file are reproduced from work created and shared by Google and used
 *         according to terms described in the Creative Commons 4.0 Attribution License.
 *         See https://developers.google.com/readme/policies for details.
 */

package com.google.android.gms.auth.api.credentials;

import androidx.annotation.NonNull;

import org.microg.gms.common.PublicApi;
import org.microg.safeparcel.AutoSafeParcelable;

@PublicApi
public class CredentialPickerConfig extends AutoSafeParcelable {

    @Field(1)
    private final boolean showAddAccountButton;
    @Field(2)
    private final boolean showCancelButton;
    @Field(3)
    private final boolean forNewAccount;

    public CredentialPickerConfig(boolean showAddAccountButton, boolean showCancelButton, boolean forNewAccount) {
        this.showAddAccountButton = showAddAccountButton;
        this.showCancelButton = showCancelButton;
        this.forNewAccount = forNewAccount;
    }

    /**
     * @deprecated It was determined that this method was not useful for developers.
     */
    @Deprecated
    public boolean isForNewAccount() {
        return forNewAccount;
    }

    @NonNull
    @Override
    public String toString() {
        return "CredentialPickerConfig{" +
                "showAddAccountButton=" + showAddAccountButton +
                ", showCancelButton=" + showCancelButton +
                '}';
    }

    public static class Builder {
        private final boolean showAddAccountButton;
        private final boolean showCancelButton;
        private final boolean forNewAccount;

        public Builder(boolean showAddAccountButton, boolean showCancelButton, boolean forNewAccount) {
            this.showAddAccountButton = showAddAccountButton;
            this.showCancelButton = showCancelButton;
            this.forNewAccount = forNewAccount;
        }

        public CredentialPickerConfig build() {
            return new CredentialPickerConfig(showAddAccountButton, showCancelButton, forNewAccount);
        }

    }

    public static final Creator<CredentialPickerConfig> CREATOR = new AutoCreator<>(CredentialPickerConfig.class);
}

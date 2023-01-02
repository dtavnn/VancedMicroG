/*
 * SPDX-FileCopyrightText: 2021, microG Project Team
 * SPDX-License-Identifier: Apache-2.0
 * Notice: Portions of this file are reproduced from work created and shared by Google and used
 *         according to terms described in the Creative Commons 4.0 Attribution License.
 *         See https://developers.google.com/readme/policies for details.
 */

package com.google.android.gms.auth.api.credentials;

import androidx.annotation.NonNull;

import org.microg.gms.common.PublicApi;
import org.microg.safeparcel.AutoSafeParcelable;

import java.util.Arrays;

/**
 * Parameters for requesting the display of the hint picker, via {@link CredentalsApi#getHintPickerIntent()}.
 * Instances can be created using {@link HintRequest.Builder}.
 */
@PublicApi
public class HintRequest extends AutoSafeParcelable {

    @Field(1)
    private final CredentialPickerConfig hintPickerConfig;
    @Field(2)
    private final boolean emailAddressIdentifierSupported;
    @Field(3)
    private final boolean phoneNumberIdentifierSupported;
    @Field(4)
    private final String[] accountTypes;
    @Field(5)
    private final boolean idTokenRequested;
    @Field(6)
    private final String serverClientId;
    @Field(7)
    private final String idTokenNonce;

    public HintRequest(CredentialPickerConfig hintPickerConfig, boolean emailAddressIdentifierSupported, boolean phoneNumberIdentifierSupported, String[] accountTypes, boolean idTokenRequested, String serverClientId, String idTokenNonce) {
        this.hintPickerConfig = hintPickerConfig;
        this.emailAddressIdentifierSupported = emailAddressIdentifierSupported;
        this.phoneNumberIdentifierSupported = phoneNumberIdentifierSupported;
        this.accountTypes = accountTypes;
        this.idTokenRequested = idTokenRequested;
        this.serverClientId = serverClientId;
        this.idTokenNonce = idTokenNonce;
    }

    public static final Creator<HintRequest> CREATOR = new AutoCreator<>(HintRequest.class);

    @NonNull
    @Override
    public String toString() {
        return "HintRequest{" +
                "hintPickerConfig=" + hintPickerConfig +
                ", emailAddressIdentifierSupported=" + emailAddressIdentifierSupported +
                ", phoneNumberIdentifierSupported=" + phoneNumberIdentifierSupported +
                ", accountTypes=" + Arrays.toString(accountTypes) +
                ", idTokenRequested=" + idTokenRequested +
                ", serverClientId='" + serverClientId + '\'' +
                ", idTokenNonce='" + idTokenNonce + '\'' +
                '}';
    }

    public static class Builder {
        private final CredentialPickerConfig hintPickerConfig;
        private final boolean emailAddressIdentifierSupported;
        private final boolean phoneNumberIdentifierSupported;
        private final String[] accountTypes;
        private final String serverClientId;
        private final String idTokenNonce;

        public Builder(CredentialPickerConfig hintPickerConfig, boolean emailAddressIdentifierSupported, boolean phoneNumberIdentifierSupported, String[] accountTypes, String serverClientId, String idTokenNonce) {
            this.hintPickerConfig = hintPickerConfig;
            this.emailAddressIdentifierSupported = emailAddressIdentifierSupported;
            this.phoneNumberIdentifierSupported = phoneNumberIdentifierSupported;
            this.accountTypes = accountTypes;
            this.serverClientId = serverClientId;
            this.idTokenNonce = idTokenNonce;
        }

        /**
         * Builds a {@link HintRequest}.
         */
        public HintRequest build() {
            boolean idTokenRequested = true;
            return new HintRequest(hintPickerConfig, emailAddressIdentifierSupported, phoneNumberIdentifierSupported, accountTypes, idTokenRequested, serverClientId, idTokenNonce);
        }

    }
}

/*
 * SPDX-FileCopyrightText: 2016, microG Project Team
 * SPDX-License-Identifier: Apache-2.0
 * Notice: Portions of this file are reproduced from work created and shared by Google and used
 *         according to terms described in the Creative Commons 4.0 Attribution License.
 *         See https://developers.google.com/readme/policies for details.
 */

package com.google.android.gms.auth.api.credentials;

import android.net.Uri;

import org.microg.gms.common.PublicApi;
import org.microg.safeparcel.AutoSafeParcelable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@PublicApi
public class Credential extends AutoSafeParcelable {

    @Field(1)
    private String id;
    @Field(2)
    private String name;
    @Field(3)
    private Uri profilePictureUri;
    @Field(value = 4, subClass = IdToken.class)
    private List<IdToken> tokens;
    @Field(5)
    private String password;
    @Field(6)
    private String accountType;
    @Field(7)
    private String generatedPassword;

    private Credential() {
    }

    /**
     * Returns the type of federated identity account used to sign in the user. While this may be
     * any string, it is strongly recommended that values from {@link com.google.android.gms.auth.api.credentials.IdentityProviders}
     * are used, which are the login domains for common identity providers.
     *
     * @return A string identifying the federated identity provider associated with this account,
     * typically in the form of the identity provider's login domain. null will be returned if the
     * credential is a password credential.
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Returns the credential identifier, typically an email address or user name, though it may
     * also be some encoded unique identifier for a federated identity account.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the display name of the credential, if available. Typically, the display name will
     * be the name of the user, or some other string which the user can easily recognize and
     * distinguish from other accounts they may have.
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credential)) return false;

        Credential that = (Credential) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(profilePictureUri, that.profilePictureUri))
            return false;
        if (!Objects.equals(password, that.password))
            return false;
        if (!Objects.equals(accountType, that.accountType))
            return false;
        return Objects.equals(generatedPassword, that.generatedPassword);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{id, name, profilePictureUri, password, accountType, generatedPassword});
    }

    public static class Builder {
        private final String id;
        private String name;
        private final Uri profilePictureUri;
        private final String password;
        private String accountType;

        @PublicApi(exclude = true)
        public List<IdToken> tokens;
        @PublicApi(exclude = true)
        private final String generatedPassword;

        /**
         * Copies the information stored in an existing credential, in order to allow that information to be modified.
         *
         * @param credential the existing credential
         */
        public Builder(Credential credential) {
            this.id = credential.id;
            this.name = credential.name;
            this.profilePictureUri = credential.profilePictureUri;
            this.password = credential.password;
            this.accountType = credential.accountType;
            this.tokens = credential.tokens;
            this.generatedPassword = credential.generatedPassword;
        }

        public Credential build() {
            Credential credential = new Credential();
            credential.id = id;
            credential.name = name;
            credential.profilePictureUri = profilePictureUri;
            credential.password = password;
            credential.accountType = accountType;
            credential.tokens = tokens;
            credential.generatedPassword = generatedPassword;
            return credential;
        }

        /**
         * Specifies the account type for a federated credential. The value should be set to
         * identity provider's login domain, such as "https://accounts.google.com" for Google
         * accounts. The login domains for common identity providers are listed in {@link IdentityProviders}.
         *
         * @param accountType The type of the account. Typically, one of the values in {@link IdentityProviders}.
         */
        public Builder setAccountType(String accountType) {
            this.accountType = accountType;
            return this;
        }

        /**
         * Sets the display name for the credential, which should be easy for the user to recognize
         * as associated to the credential, and distinguishable from other credentials they may
         * have. This string will be displayed more prominently than, or instead of, the account ID
         * whenever available. In most cases, the name of the user is sufficient.
         */
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

    }

    public static final Creator<Credential> CREATOR = new AutoCreator<>(Credential.class);
}

package org.microg.gms.auth

import android.content.Context
import org.microg.mgms.settings.SettingsContract
import org.microg.mgms.settings.SettingsContract.Auth

object AuthPrefs {

    @JvmStatic
    fun isTrustGooglePermitted(context: Context): Boolean? {
        return Auth.getContentUri(context)?.let {
            SettingsContract.getSettings(context, it, arrayOf(Auth.TRUST_GOOGLE)) { c ->
                c.getInt(0) != 0
            }
        }
    }

    @JvmStatic
    fun isAuthVisible(context: Context): Boolean? {
        return Auth.getContentUri(context)?.let {
            SettingsContract.getSettings(context, it, arrayOf(Auth.VISIBLE)) { c ->
                c.getInt(0) != 0
            }
        }
    }

}

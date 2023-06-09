@file:Suppress("DEPRECATION")

package org.microg.gms.gcm

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import org.microg.gms.gcm.TriggerReceiver.FORCE_TRY_RECONNECT
import org.microg.mgms.settings.SettingsContract
import org.microg.mgms.settings.SettingsContract.Gcm
import org.microg.mgms.settings.SettingsContract.setSettings
import org.microg.mgms.settings.SettingsProvider

data class GcmPrefs(
    val isGcmLogEnabled: Boolean,
    val lastPersistedId: String?,
    val gcmEnabled: Boolean,
    val networkMobile: Int,
    val networkWifi: Int,
    val networkRoaming: Int,
    val networkOther: Int,
    val learntMobileInterval: Int,
    val learntWifiInterval: Int,
    val learntOtherInterval: Int,
) {

    val isEnabled: Boolean get() = gcmEnabled

    val lastPersistedIds: List<String>
        get() = if (lastPersistedId.isNullOrEmpty()) emptyList() else lastPersistedId.split("\\|")

    companion object {
        const val PREF_NETWORK_MOBILE = Gcm.NETWORK_MOBILE
        const val PREF_NETWORK_WIFI = Gcm.NETWORK_WIFI
        const val PREF_NETWORK_ROAMING = Gcm.NETWORK_ROAMING
        const val PREF_NETWORK_OTHER = Gcm.NETWORK_OTHER

        @JvmStatic
        fun get(context: Context): GcmPrefs? {
            return Gcm.getContentUri(context)?.let {
                SettingsContract.getSettings(context, it, Gcm.PROJECTION) { c ->
                    GcmPrefs(
                        isGcmLogEnabled = c.getInt(0) != 0,
                        lastPersistedId = c.getString(1),
                        gcmEnabled = c.getInt(2) != 0,
                        networkMobile = c.getInt(3),
                        networkWifi = c.getInt(4),
                        networkRoaming = c.getInt(5),
                        networkOther = c.getInt(6),
                        learntMobileInterval = c.getInt(7),
                        learntWifiInterval = c.getInt(8),
                        learntOtherInterval = c.getInt(9),
                    )
                }
            }
        }

        fun write(context: Context, config: ServiceConfiguration) {
            Gcm.getContentUri(context)?.let {
                setSettings(context, it) {
                    put(Gcm.ENABLE_GCM, config.enabled)
                    put(Gcm.NETWORK_MOBILE, config.mobile)
                    put(Gcm.NETWORK_WIFI, config.wifi)
                    put(Gcm.NETWORK_ROAMING, config.roaming)
                    put(Gcm.NETWORK_OTHER, config.other)
                }
            }
            get(context)?.setEnabled(context, config.enabled)
        }

        @JvmStatic
        fun clearLastPersistedId(context: Context) {
            Gcm.getContentUri(context)?.let {
                setSettings(context, it) {
                    put(Gcm.LAST_PERSISTENT_ID, "")
                }
            }
        }
    }

    /**
     * Call this whenever the enabled state of GCM has changed.
     */
    private fun setEnabled(context: Context, enabled: Boolean) {
        if (gcmEnabled == enabled) return
        if (enabled) {
            val i = Intent(FORCE_TRY_RECONNECT, null, context, TriggerReceiver::class.java)
            context.sendBroadcast(i)
        } else {
            McsService.stop(context)
        }
    }

    fun getNetworkPrefForInfo(info: NetworkInfo?): String {
        if (info == null) return PREF_NETWORK_OTHER
        return if (info.isRoaming) PREF_NETWORK_ROAMING else when (info.type) {
            ConnectivityManager.TYPE_MOBILE -> PREF_NETWORK_MOBILE
            ConnectivityManager.TYPE_WIFI -> PREF_NETWORK_WIFI
            else -> PREF_NETWORK_OTHER
        }
    }

    fun getHeartbeatMsFor(info: NetworkInfo?): Int {
        return getHeartbeatMsFor(getNetworkPrefForInfo(info))
    }

    fun getHeartbeatMsFor(pref: String): Int {
        return if (PREF_NETWORK_ROAMING == pref) {
            if (networkRoaming != 0) networkRoaming * SettingsProvider.INTERVAL else learntMobileInterval
        } else if (PREF_NETWORK_MOBILE == pref) {
            if (networkMobile != 0) networkMobile * SettingsProvider.INTERVAL else learntMobileInterval
        } else if (PREF_NETWORK_WIFI == pref) {
            if (networkWifi != 0) networkWifi * SettingsProvider.INTERVAL else learntWifiInterval
        } else {
            if (networkOther != 0) networkOther * SettingsProvider.INTERVAL else learntOtherInterval
        }
    }

    fun learnTimeout(context: Context, pref: String) {
        Log.d("GmsGcmPrefs", "learnTimeout: $pref")
        when (pref) {
            PREF_NETWORK_MOBILE, PREF_NETWORK_ROAMING -> Gcm.getContentUri(context)?.let {
                setSettings(context, it) {
                    val newInterval = (learntMobileInterval * 0.95).toInt()
                    put(Gcm.LEARNT_MOBILE, newInterval)
                }
            }
            PREF_NETWORK_WIFI -> Gcm.getContentUri(context)?.let {
                setSettings(context, it) {
                    val newInterval = (learntMobileInterval * 0.95).toInt()
                    put(Gcm.LEARNT_WIFI, newInterval)
                }
            }
            else -> Gcm.getContentUri(context)?.let {
                setSettings(context, it) {
                    val newInterval = (learntMobileInterval * 0.95).toInt()
                    put(Gcm.LEARNT_OTHER, newInterval)
                }
            }
        }
    }

    fun learnReached(context: Context, pref: String, time: Long) {
        Log.d("GmsGcmPrefs", "learnReached: $pref / $time")
        when (pref) {
            PREF_NETWORK_MOBILE, PREF_NETWORK_ROAMING -> {
                if (time > learntMobileInterval / 4 * 3) {
                    Gcm.getContentUri(context)?.let {
                        setSettings(context, it) {
                            put(Gcm.LEARNT_MOBILE, SettingsProvider.INTERVAL)
                        }
                    }
                }
            }
            PREF_NETWORK_WIFI -> {
                if (time > learntWifiInterval / 4 * 3) {
                    Gcm.getContentUri(context)?.let {
                        setSettings(context, it) {
                            put(Gcm.LEARNT_WIFI, SettingsProvider.INTERVAL)
                        }
                    }
                }
            }
            else -> {
                if (time > learntOtherInterval / 4 * 3) {
                    Gcm.getContentUri(context)?.let {
                        setSettings(context, it) {
                            put(Gcm.LEARNT_OTHER, SettingsProvider.INTERVAL)
                        }
                    }
                }
            }
        }
    }

    fun isEnabledFor(info: NetworkInfo?): Boolean {
        return isEnabled && info != null && getHeartbeatMsFor(info) >= 0
    }

    fun extendLastPersistedId(context: Context, id: String) {
        val newId = if (lastPersistedId.isNullOrEmpty()) id else "$lastPersistedId|$id"
        Gcm.getContentUri(context)?.let {
            setSettings(context, it) {
                put(Gcm.LAST_PERSISTENT_ID, newId)
            }
        }
    }

}

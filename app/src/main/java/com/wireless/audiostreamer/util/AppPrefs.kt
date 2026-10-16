package com.wireless.audiostreamer.util

import android.content.Context
import android.content.SharedPreferences

object AppPrefs {
    const val ROLE_SENDER = "SENDER"
    const val ROLE_RECEIVER = "RECEIVER"

    private fun prefs(ctx: Context): SharedPreferences =
        ctx.getSharedPreferences("was_prefs", Context.MODE_PRIVATE)

    fun role(ctx: Context): String =
        prefs(ctx).getString("role", ROLE_RECEIVER) ?: ROLE_RECEIVER

    fun setRole(ctx: Context, role: String) =
        prefs(ctx).edit().putString("role", role).apply()

    fun pin(ctx: Context): String =
        prefs(ctx).getString("pin", "1234") ?: "1234"

    fun setPin(ctx: Context, pin: String) =
        prefs(ctx).edit().putString("pin", pin).apply()

    fun deviceName(ctx: Context? = null): String {
        if (ctx == null) return android.os.Build.MODEL
        return prefs(ctx).getString("device_name", android.os.Build.MODEL) ?: android.os.Build.MODEL
    }

    fun setDeviceName(ctx: Context, name: String) =
        prefs(ctx).edit().putString("device_name", name).apply()

    fun channels(ctx: Context): Int =
        if (prefs(ctx).getBoolean("stereo", false)) 2 else 1

    fun setStereo(ctx: Context, stereo: Boolean) =
        prefs(ctx).edit().putBoolean("stereo", stereo).apply()

    fun targetFrames(ctx: Context): Int =
        when (prefs(ctx).getInt("delay", 1)) {
            0 -> 2   // low latency ~40ms
            2 -> 8   // stable ~160ms
            else -> 4 // balanced ~80ms
        }

    fun setDelay(ctx: Context, level: Int) =
        prefs(ctx).edit().putInt("delay", level.coerceIn(0, 2)).apply()

    fun delayLevel(ctx: Context): Int = prefs(ctx).getInt("delay", 1)

    fun manualIp(ctx: Context): String =
        prefs(ctx).getString("manual_ip", "") ?: ""

    fun setManualIp(ctx: Context, ip: String) =
        prefs(ctx).edit().putString("manual_ip", ip).apply()

    fun language(ctx: Context): String =
        prefs(ctx).getString("lang", "") ?: ""

    fun setLanguage(ctx: Context, lang: String) =
        prefs(ctx).edit().putString("lang", lang).apply()

    fun isSetupDone(ctx: Context): Boolean =
        prefs(ctx).getBoolean("setup_done", false)

    fun setSetupDone(ctx: Context, done: Boolean = true) =
        prefs(ctx).edit().putBoolean("setup_done", done).apply()

    fun remoteAccessEnabled(ctx: Context): Boolean =
        prefs(ctx).getBoolean("ra_enabled", false)

    fun setRemoteAccessEnabled(ctx: Context, enabled: Boolean) =
        prefs(ctx).edit().putBoolean("ra_enabled", enabled).apply()
}

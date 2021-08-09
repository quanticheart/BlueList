/*
 *
 *  *                                     /@
 *  *                      __        __   /\/
 *  *                     /==\      /  \_/\/
 *  *                   /======\    \/\__ \__
 *  *                 /==/\  /\==\    /\_|__ \
 *  *              /==/    ||    \=\ / / / /_/
 *  *            /=/    /\ || /\   \=\/ /
 *  *         /===/   /   \||/   \   \===\
 *  *       /===/   /_________________ \===\
 *  *    /====/   / |                /  \====\
 *  *  /====/   /   |  _________    /      \===\
 *  *  /==/   /     | /   /  \ / / /         /===/
 *  * |===| /       |/   /____/ / /         /===/
 *  *  \==\             /\   / / /          /===/
 *  *  \===\__    \    /  \ / / /   /      /===/   ____                    __  _         __  __                __
 *  *    \==\ \    \\ /____/   /_\ //     /===/   / __ \__  ______  ____ _/ /_(_)____   / / / /__  ____ ______/ /_
 *  *    \===\ \   \\\\\\\/   ///////     /===/  / / / / / / / __ \/ __ `/ __/ / ___/  / /_/ / _ \/ __ `/ ___/ __/
 *  *      \==\/     \\\\/ / //////       /==/  / /_/ / /_/ / / / / /_/ / /_/ / /__   / __  /  __/ /_/ / /  / /_
 *  *      \==\     _ \\/ / /////        |==/   \___\_\__,_/_/ /_/\__,_/\__/_/\___/  /_/ /_/\___/\__,_/_/   \__/
 *  *        \==\  / \ / / ///          /===/
 *  *        \==\ /   / / /________/    /==/
 *  *          \==\  /               | /==/
 *  *          \=\  /________________|/=/
 *  *            \==\     _____     /==/
 *  *           / \===\   \   /   /===/
 *  *          / / /\===\  \_/  /===/
 *  *         / / /   \====\ /====/
 *  *        / / /      \===|===/
 *  *        |/_/         \===/
 *  *                       =
 *  *
 *  * Copyright(c) Developed by John Alves at 2019/6/20 at 9:52:56 for quantic heart studios
 *
 */

package com.quanticheart.core.system.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.quanticheart.core.system.services.AlarmAction
import com.quanticheart.core.system.services.startService

@Suppress("PrivatePropertyName", "unused", "SpellCheckingInspection")
internal class BootReceiver : BroadcastReceiver() {
    private val BOOT = Intent.ACTION_BOOT_COMPLETED
    private val BOOT_COMPLETED = "android.intent.action.ACTION_BOOT_COMPLETED"
    private val SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN"
    private val POWERON = "android.intent.action.QUICKBOOT_POWERON"
    private val POWERON_HTC = "com.htc.intent.action.QUICKBOOT_POWERON"
    private val POWERON_MIUI = "android.intent.action.REBOOT"

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            BOOT, BOOT_COMPLETED, SHUTDOWN, POWERON, POWERON_HTC, POWERON_MIUI -> {
                context.startMinuteService()
            }
        }
    }
}

fun Context.startMinuteService() =
    startService(AlarmAction::class.java)
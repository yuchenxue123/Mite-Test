package cute.neko.client

import cute.neko.client.module.ModuleManager
import cute.neko.client.ui.ClickScreen

/**
 * @author yuchenxue
 * @date 2025/07/14
 */

object Client {

    var loaded = false

    fun initialize() {
        ModuleManager

        ClickScreen

        loaded = true
    }

    fun shutdown() {

    }
}
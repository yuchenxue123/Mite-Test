package cute.neko.client

import cute.neko.client.module.ModuleManager
import cute.neko.client.ui.ClickScreen
import cute.neko.client.ui.font.Fonts

object Client {

    var loaded = false

    fun initialize() {
        ModuleManager

        ClickScreen

        Fonts

        loaded = true
    }

    fun shutdown() {

    }
}
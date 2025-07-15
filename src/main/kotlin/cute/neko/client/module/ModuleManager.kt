package cute.neko.client.module

import cute.neko.client.event.EventListener
import cute.neko.client.event.events.KeyPressEvent
import cute.neko.client.event.handle
import cute.neko.client.module.combat.ModuleAntiVelocity
import cute.neko.client.module.combat.ModuleKillAura
import cute.neko.client.module.player.ModuleFastBreak
import cute.neko.client.module.player.ModuleFastPlace
import cute.neko.client.module.player.ModuleFlight
import cute.neko.client.module.player.ModuleNoFall
import cute.neko.client.module.player.ModuleSprint
import cute.neko.client.module.render.ModuleBrightness
import cute.neko.client.module.render.ModuleClickScreen

/**
 * @author yuchenxue
 * @date 2025/07/14
 */

object ModuleManager : EventListener {

    val modules = mutableListOf<Module>()

    init {
        register(
            ModuleAntiVelocity,
            ModuleKillAura,

            ModuleFlight,
            ModuleNoFall,
            ModuleAntiVelocity,
            ModuleSprint,
            ModuleFastBreak,
            ModuleFastPlace,

            ModuleBrightness,
            ModuleClickScreen,
        )
    }

    fun register(vararg module: Module) {
        modules += module
    }

    private val onKeyPress = handle<KeyPressEvent> { event ->
        modules.filter { it.key == event.key }
            .forEach { it.toggle() }
    }
}
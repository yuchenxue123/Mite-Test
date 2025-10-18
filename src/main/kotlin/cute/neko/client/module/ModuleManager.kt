package cute.neko.client.module

import cute.neko.client.event.EventListener
import cute.neko.client.event.events.KeyPressEvent
import cute.neko.client.event.handle
import cute.neko.client.module.combat.ModuleAntiVelocity
import cute.neko.client.module.combat.ModuleKillAura
import cute.neko.client.module.movement.ModuleNoSlow
import cute.neko.client.module.player.ModuleFastBreak
import cute.neko.client.module.player.ModuleFastPlace
import cute.neko.client.module.player.ModuleFlight
import cute.neko.client.module.player.ModuleNoFall
import cute.neko.client.module.player.ModuleSprint
import cute.neko.client.module.render.ModuleArraylist
import cute.neko.client.module.render.ModuleBrightness
import cute.neko.client.module.render.ModuleClickScreen
import cute.neko.client.module.render.ModuleNotification

object ModuleManager : EventListener {

    val modules = mutableListOf<Module>()

    init {
        register(
            ModuleAntiVelocity,
            ModuleKillAura,

            ModuleFlight,
            ModuleNoFall,
            ModuleSprint,
            ModuleFastBreak,
            ModuleFastPlace,

            ModuleNoSlow,

            ModuleBrightness,
            ModuleClickScreen,
            ModuleNotification,
            ModuleArraylist,
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
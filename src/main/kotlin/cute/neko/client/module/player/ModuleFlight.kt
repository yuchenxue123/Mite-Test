package cute.neko.client.module.player

import cute.neko.client.event.events.PlayerMotionEvent
import cute.neko.client.event.events.PlayerTickEvent
import cute.neko.client.event.handle
import cute.neko.client.module.Category
import cute.neko.client.module.Module
import cute.neko.client.utils.player.Movement
import org.lwjgl.input.Keyboard

/**
 * @author yuchenxue
 * @date 2025/07/15
 */

object ModuleFlight : Module(
    name = "Flight",
    category = Category.PLAYER,
    key = Keyboard.KEY_F
) {

    private val onPlayerTick = handle<PlayerTickEvent> {
        Movement.strafe(1f)

        player.motionY = when {
            mc.gameSettings.keyBindJump.pressed -> 1.0
            mc.gameSettings.keyBindSneak.pressed -> -1.0
            else -> .0
        }
    }

    private val onPlayerMotionPre = handle<PlayerMotionEvent.Pre> { event ->
        event.ground = true
    }
}
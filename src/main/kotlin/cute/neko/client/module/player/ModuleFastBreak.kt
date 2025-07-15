package cute.neko.client.module.player

import cute.neko.client.event.events.PlayerTickEvent
import cute.neko.client.event.handle
import cute.neko.client.module.Category
import cute.neko.client.module.Module

/**
 * @author yuchenxue
 * @date 2025/07/15
 */

object ModuleFastBreak : Module(
    name = "FastBreak",
    category = Category.PLAYER,
    state = true
) {

    private val onPlayerTick = handle<PlayerTickEvent> {
        if (mc.gameSettings.keyBindAttack.pressed) {
            player.playerController.blockHitDelay = 0
            player.playerController.curBlockDamageMP = 1f
        }
    }
}
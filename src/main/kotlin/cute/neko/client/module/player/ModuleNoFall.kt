package cute.neko.client.module.player

import cute.neko.client.event.events.PlayerTickEvent
import cute.neko.client.event.handle
import cute.neko.client.module.Category
import cute.neko.client.module.Module
import cute.neko.client.utils.client.PacketUtils
import net.minecraft.Packet10Flying

object ModuleNoFall : Module(
    name = "NoFall",
    category = Category.PLAYER,
    state = true
) {
    private val onPlayerTick = handle<PlayerTickEvent> {
        if (player.fallDistance > 2f) {
            PacketUtils.sendPacket(Packet10Flying(true))
        }
    }
}
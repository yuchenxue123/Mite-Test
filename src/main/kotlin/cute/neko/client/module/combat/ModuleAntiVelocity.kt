package cute.neko.client.module.combat

import cute.neko.client.event.events.PacketEvent
import cute.neko.client.event.handle
import cute.neko.client.module.Category
import cute.neko.client.module.Module
import net.minecraft.Packet28EntityVelocity

/**
 * @author yuchenxue
 * @date 2025/07/15
 */

object ModuleAntiVelocity : Module(
    name = "AntiVelocity",
    category = Category.COMBAT,
    state = true
) {
    private val onPacketReceive = handle<PacketEvent.Receive> { event ->
        val packet = event.packet

        if (packet is Packet28EntityVelocity && packet.entityId == player.entityId) {
            event.cancel()
        }
    }
}
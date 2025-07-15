package cute.neko.client.utils.client

import net.minecraft.Packet


/**
 * @author yuchenxue
 * @date 2025/07/14
 */

object PacketUtils {
    fun sendPacket(vararg packets: Packet) {
        packets.forEach {
            mc.netHandler.addToSendQueue(it)
        }
    }
}
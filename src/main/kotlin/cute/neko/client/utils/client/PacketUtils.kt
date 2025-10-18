package cute.neko.client.utils.client

import net.minecraft.Packet

object PacketUtils {
    fun sendPacket(vararg packets: Packet) {
        packets.forEach {
            mc.netHandler.addToSendQueue(it)
        }
    }
}
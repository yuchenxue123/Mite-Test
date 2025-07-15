package cute.neko.client.event.events

import cute.neko.client.event.CancellableEvent
import cute.neko.client.event.Event
import net.minecraft.Packet

/**
 * @author yuchenxue
 * @date 2025/07/14
 */

object GameLoopEvent : Event

object GameTickEvent : Event

class KeyPressEvent(val key: Int) : Event

sealed class PacketEvent(val packet: Packet) : CancellableEvent() {
    class Send(packet: Packet) : PacketEvent(packet)

    class Receive(packet: Packet) : PacketEvent(packet)
}
package cute.neko.client.utils.player

import cute.neko.client.event.EventListener
import cute.neko.client.event.events.PacketEvent
import cute.neko.client.event.handle
import cute.neko.client.utils.client.player
import cute.neko.client.utils.extension.toRadians
import net.minecraft.Packet10Flying
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * @author yuchenxue
 * @date 2025/07/15
 */

object Movement : EventListener {

    val speed: Float
        get() = sqrt(player.motionX * player.motionX + player.motionZ * player.motionZ).toFloat()

    val moving: Boolean
        get() = player.movementInput.moveForward != 0.0f || player.movementInput.moveStrafe != 0.0f

    fun strafe(speed: Float = this.speed, strength: Float = 1f, angle: Double = direction) {
        if (!moving) {
            return
        }

        val prevX = player.motionX * (1.0 - strength)
        val prevZ = player.motionZ * (1.0 - strength)
        val useSpeed = speed * strength

        val x = (-sin(angle) * useSpeed) + prevX
        val z = (cos(angle) * useSpeed) + prevZ

        player.motionX = x
        player.motionZ = z
    }

    val direction: Double
        get() = direction(player.rotationYaw)

    fun direction(
        rotationYaw: Float
    ): Double {
        return direction(rotationYaw, player.movementInput.moveForward, player.movementInput.moveStrafe)
    }

    fun direction(
        rotationYaw: Float,
        moveForward: Float,
        moveStrafe: Float
    ): Double {
        var yaw = rotationYaw
        var forward = 1f

        when {
            moveForward < 0f -> {
                yaw += 180f
                forward = -0.5f
            }

            moveForward > 0f -> forward = 0.5f
        }

        when {
            moveStrafe < 0f -> yaw += 90f * forward
            moveStrafe > 0f -> yaw -= 90f * forward
        }


        return yaw.toRadians().toDouble()
    }

    var serverOnGround = false

    private val onPacketSend = handle<PacketEvent.Send> { event ->
        val packet = event.packet

        if (packet is Packet10Flying) {
            serverOnGround = packet.onGround
        }
    }
}
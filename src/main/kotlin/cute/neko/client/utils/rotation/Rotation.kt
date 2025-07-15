package cute.neko.client.utils.rotation

import cute.neko.client.utils.client.player
import cute.neko.client.utils.extension.rotation
import net.minecraft.EntityPlayer
import kotlin.math.roundToInt

/**
 * @author yuchenxue
 * @date 2025/07/14
 */

data class Rotation(
    var yaw: Float,
    var pitch: Float,
    var normalized: Boolean = false
) {

    fun normalize(): Rotation {
        if (normalized) return this

        var yaw = this.yaw % 360f

        if (yaw < -180f) yaw += 360f
        if (yaw > 180f) yaw -= 360f

        val pitch = this.pitch.coerceIn(-90f, 90f)

        return Rotation(yaw, pitch, true)
    }

    fun applyTo(player: EntityPlayer) {
        player.rotationYaw = yaw
        player.rotationPitch = pitch
    }
}
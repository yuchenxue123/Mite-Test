package cute.neko.client.event.events

import cute.neko.client.event.Event
import cute.neko.client.utils.rotation.Rotation
import org.joml.Vector3d

/**
 * @author yuchenxue
 * @date 2025/07/14
 */

object PlayerTickEvent : Event

object PlayerJumpEvent : Event

sealed class PlayerMotionEvent(
    var x: Double,
    var y: Double,
    var z: Double,
    var yaw: Float,
    var pitch: Float,
    var ground: Boolean
) : Event {
    class Pre(
        x: Double,
        y: Double,
        z: Double,
        yaw: Float,
        pitch: Float,
        ground: Boolean
    ) : PlayerMotionEvent(x, y, z, yaw, pitch, ground)

    class Post(
        x: Double,
        y: Double,
        z: Double,
        yaw: Float,
        pitch: Float,
        ground: Boolean
    ) : PlayerMotionEvent(x, y, z, yaw, pitch, ground)
}
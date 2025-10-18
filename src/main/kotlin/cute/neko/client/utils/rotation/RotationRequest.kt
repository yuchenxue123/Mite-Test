package cute.neko.client.utils.rotation

import cute.neko.client.event.EventListener
import cute.neko.client.utils.client.Priority

data class RotationRequest(
    val listener: EventListener,
    val rotation: Rotation,
    val priority: Int = Priority.ROTATION_NORMAL
)
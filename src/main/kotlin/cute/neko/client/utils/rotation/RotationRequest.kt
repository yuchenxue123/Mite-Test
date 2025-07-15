package cute.neko.client.utils.rotation

import cute.neko.client.event.EventListener
import cute.neko.client.utils.client.Priority


/**
 * @author yuchenxue
 * @date 2025/07/14
 */

data class RotationRequest(
    val listener: EventListener,
    val rotation: Rotation,
    val priority: Int = Priority.ROTATION_NORMAL
)
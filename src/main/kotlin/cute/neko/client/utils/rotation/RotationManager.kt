package cute.neko.client.utils.rotation

import cute.neko.client.event.EventListener
import cute.neko.client.event.events.PlayerMotionEvent
import cute.neko.client.event.handle
import cute.neko.client.utils.client.Priority
import cute.neko.client.utils.client.chat
import cute.neko.client.utils.client.mc
import cute.neko.client.utils.extension.rotation
import java.util.PriorityQueue

/**
 * @author yuchenxue
 * @date 2025/07/14
 */

object RotationManager : EventListener {

    private val requests = PriorityQueue<RotationRequest>(
        compareByDescending { it.priority }
    )

    val activeRequest: RotationRequest?
        get() {
            if (requests.isEmpty()) return null
            return requests.peek()
        }

    var currentRotation: Rotation? = null
        set(value) {
            previousRotation = if (value == null) {
                null
            } else {
                field ?: mc.thePlayer?.rotation
            }

            field = value
        }

    var previousRotation: Rotation? = null

    fun request(request: RotationRequest) {
        requests.removeAll { it.listener == request.listener }
        requests.add(request)

        update()
    }

    fun request(
        listener: EventListener,
        rotation: Rotation,
        priority: Int = Priority.ROTATION_NORMAL
    ) {
        request(RotationRequest(listener, rotation, priority))
    }

    fun remove(listener: EventListener) {
        requests.removeAll { it.listener == listener }

        update()
    }

    fun reset() {
        requests.clear()

        update()
    }

    fun update() {
        if (requests.isEmpty() || activeRequest == null) {
            currentRotation = null
            return
        }

        currentRotation = activeRequest?.rotation
    }

    private val onMotionPre = handle<PlayerMotionEvent.Pre> { event ->
        activeRequest?.let { request ->
            event.yaw = request.rotation.yaw
            event.pitch = request.rotation.pitch
        }
    }
}
package cute.neko.client.event.events

import cute.neko.client.event.Event

/**
 * @author yuchenxue
 * @date 2025/07/14
 */

class ScreenRenderEvent(val deltaTime: Float) : Event

class WorldRenderEvent(val deltaTime: Float) : Event
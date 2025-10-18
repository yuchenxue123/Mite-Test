package cute.neko.client.event.events

import cute.neko.client.event.Event

class ScreenRenderEvent(val deltaTime: Float) : Event

class WorldRenderEvent(val deltaTime: Float) : Event
package cute.neko.client.module

import cute.neko.client.Client
import cute.neko.client.event.EventListener
import cute.neko.client.module.render.ModuleNotification
import cute.neko.client.utils.client.Accessor
import cute.neko.client.utils.client.chat
import org.lwjgl.input.Keyboard

open class Module(
    val name: String,
    val category: Category,
    var key: Int = Keyboard.KEY_NONE,
    val lock: Boolean = false,
    state: Boolean = false
) : EventListener, Accessor {

    var state = state
        set(value) {
            field = value

            if (Client.loaded && ModuleNotification.running) {
                chat("$name is ${if (value) "enabled" else "disabled"} !")
            }

            if (value) {
                enable()
            } else {
                disable()
            }
        }

    open fun enable() {}
    open fun disable() {}

    fun toggle() {
        state = !state
    }

    override val running: Boolean
        get() = state
}
package cute.neko.client.event

interface Event

open class CancellableEvent : Event {

    var cancelled: Boolean = false
        private set

    fun cancel() {
        cancelled = true
    }
}
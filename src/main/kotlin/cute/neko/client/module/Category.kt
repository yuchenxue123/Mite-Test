package cute.neko.client.module

enum class Category {
    COMBAT,
    PLAYER,
    MOVEMENT,
    RENDER

    ;

    val modules
        get() = ModuleManager.modules.filter { it.category == this }.sortedBy { it.name }
}
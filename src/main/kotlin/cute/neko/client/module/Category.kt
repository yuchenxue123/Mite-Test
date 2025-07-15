package cute.neko.client.module

/**
 * @author yuchenxue
 * @date 2025/07/14
 */

enum class Category {
    COMBAT,
    PLAYER,
    MOVEMENT,
    RENDER

    ;

    val modules
        get() = ModuleManager.modules.filter { it.category == this }.sortedBy { it.name }
}
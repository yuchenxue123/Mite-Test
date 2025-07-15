package cute.neko.client.mixins.entity;

import net.minecraft.AbstractClientPlayer;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author yuchenxue
 * @date 2025/07/14
 */

@Mixin(AbstractClientPlayer.class)
public abstract class MixinAbstractClientPlayer extends MixinEntityPlayer {
}

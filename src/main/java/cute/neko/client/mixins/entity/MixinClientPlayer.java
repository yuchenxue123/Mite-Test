package cute.neko.client.mixins.entity;

import cute.neko.client.module.movement.ModuleNoSlow;
import net.minecraft.ClientPlayer;
import net.minecraft.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayer.class)
public abstract class MixinClientPlayer extends MixinAbstractClientPlayer {


    @Redirect(method = "onLivingUpdate", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/ClientPlayer;isUsingItem()Z",
            ordinal = 0))
    private boolean onLivingUpdate(ClientPlayer instance) {
        if (!((ClientPlayer) (Object) this == Minecraft.getMinecraft().thePlayer)) {
            return instance.isUsingItem();
        }

        if (ModuleNoSlow.INSTANCE.getRunning()) {
            return false;
        }

        return instance.isUsingItem();

    }
}

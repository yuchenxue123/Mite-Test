package cute.neko.client.mixins.entity;

import cute.neko.client.module.player.ModuleAntiHunger;
import net.minecraft.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends MixinEntityLivingBase {

    @Inject(method = "addHungerClientSide", at = @At(value = "HEAD"), cancellable = true)
    private void hookAntiHungerClient(float hunger, CallbackInfo ci) {
        if (ModuleAntiHunger.INSTANCE.getRunning()) {
            ci.cancel();
        }
    }

    @Inject(method = "addHungerServerSide", at = @At(value = "HEAD"), cancellable = true)
    private void hookAntiHungerServer(float hunger, CallbackInfo ci) {
        if (ModuleAntiHunger.INSTANCE.getRunning()) {
            ci.cancel();
        }
    }
}

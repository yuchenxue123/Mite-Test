package cute.neko.client.mixins.client;

import cute.neko.client.module.player.ModuleFastPlace;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerController {

    @Inject(method = "useButtonEnabled", at = @At(value = "RETURN"), cancellable = true)
    private void hookNoRightButtonDelay(CallbackInfoReturnable<Boolean> cir) {
        if (ModuleFastPlace.INSTANCE.getRunning()) {
           cir.setReturnValue(true);
        }
    }
}

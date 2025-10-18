package cute.neko.client.mixins.render;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import cute.neko.client.event.EventManager;
import cute.neko.client.event.events.WorldRenderEvent;
import cute.neko.client.module.render.ModuleBrightness;
import net.minecraft.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {

    @Inject(method = "renderWorld", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/Profiler;endSection()V",
            shift = At.Shift.AFTER)
    )
    private void hookWorldRenderEvent(float deltaTime, long finishNanoTime, CallbackInfo ci) {
        EventManager.INSTANCE.callEvent(new WorldRenderEvent(deltaTime));
    }

    @ModifyExpressionValue(method = "updateLightmap", at = @At(value = "FIELD", target = "Lnet/minecraft/GameSettings;gammaSetting:F"))
    private float hookBrightness(float gammaSetting) {
        if (ModuleBrightness.INSTANCE.getRunning()) {
            return 13f;
        }

        return gammaSetting;
    }
}

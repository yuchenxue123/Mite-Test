package cute.neko.client.mixins.gui;

import cute.neko.client.event.EventManager;
import cute.neko.client.event.events.ScreenRenderEvent;
import net.minecraft.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public class MixinGuiInGame {

    @Inject(method = "renderGameOverlay", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/EntityClientPlayerMP;isRidingHorse()Z",
            shift = At.Shift.BEFORE)
    )
    public void renderGameOverlay(float deltaTime, boolean par2, int par3, int par4, CallbackInfo ci) {
        EventManager.INSTANCE.callEvent(new ScreenRenderEvent(deltaTime));
    }
}

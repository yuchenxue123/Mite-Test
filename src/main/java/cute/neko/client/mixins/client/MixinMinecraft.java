package cute.neko.client.mixins.client;

import cute.neko.client.Client;
import cute.neko.client.event.EventManager;
import cute.neko.client.event.events.GameLoopEvent;
import cute.neko.client.event.events.GameTickEvent;
import cute.neko.client.event.events.KeyPressEvent;
import net.minecraft.Minecraft;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {

    @Shadow public abstract boolean isChatImposed();

    @Inject(method = "startGame",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/Minecraft;checkGLError(Ljava/lang/String;)V",
                    ordinal = 2,
                    shift = At.Shift.AFTER
            )
    )
    private void startGame(CallbackInfo ci) {
        Client.INSTANCE.initialize();
    }

    @Inject(method = "shutdown", at = @At(value = "HEAD"))
    private void shutdown(CallbackInfo ci) {
        Client.INSTANCE.shutdown();
    }

    @Inject(method = "runGameLoop",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/Profiler;startSection(Ljava/lang/String;)V",
                    ordinal = 1
            )
    )
    private void hookGameLoopEvent(CallbackInfo ci) {
        EventManager.INSTANCE.callEvent(GameLoopEvent.INSTANCE);
    }

    @Inject(method = "runTick", at = @At(value = "FIELD", target = "Lnet/minecraft/Minecraft;joinPlayerCounter:I"))
    private void hookGameTickEvent(CallbackInfo ci) {
        EventManager.INSTANCE.callEvent(GameTickEvent.INSTANCE);
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;isChatImposed()Z", ordinal = 3, shift = At.Shift.BEFORE))
    private void hookKeyEvent(CallbackInfo ci) {
        if (!this.isChatImposed()) {
            int k = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();

            EventManager.INSTANCE.callEvent(new KeyPressEvent(k));
        }
    }
}

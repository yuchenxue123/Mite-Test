package cute.neko.client.mixins.network;

import cute.neko.client.event.EventManager;
import cute.neko.client.event.events.PacketEvent;
import net.minecraft.NetClientHandler;
import net.minecraft.NetHandler;
import net.minecraft.Packet;
import net.minecraft.TcpConnection;
import net.minecraft.client.main.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Queue;

/**
 * @author yuchenxue
 * @date 2025/07/15
 */

@Mixin(TcpConnection.class)
public abstract class MixinTcpConnection {

    @Shadow private int sendQueueByteLength;

    @Shadow public abstract void networkShutdown(String par1Str, Object... par2ArrayOfObj);

    @Shadow private Queue readPackets;

    @Shadow private int field_74490_x;

    @Shadow private NetHandler theNetHandler;

    @Shadow public abstract void wakeThreads();

    @Shadow private volatile boolean isTerminating;

    @Shadow private String terminationReason;

    @Shadow private Object[] shutdownDescription;

    @Inject(method = "addToSendQueue", at = @At(value = "HEAD"), cancellable = true)
    private void addToSendQueue(Packet packet, CallbackInfo ci) {
        final PacketEvent.Send event = new PacketEvent.Send(packet);
        EventManager.INSTANCE.callEvent(event);

        if (event.getCancelled()) {
            ci.cancel();
        }
    }

    /**
     * @author yuchenxue
     * @reason shit
     */
    @Overwrite
    public void processReadPackets() {
        if (this.sendQueueByteLength > 0x200000) {
            this.networkShutdown("disconnect.overflow", new Object[0]);
        }
        if (this.readPackets.isEmpty()) {
            if (this.field_74490_x++ == 1200) {
                this.networkShutdown("disconnect.timeout", new Object[0]);
            }
        } else {
            this.field_74490_x = 0;
        }
        boolean is_MITE_DS_client_player = Main.is_MITE_DS && this.theNetHandler instanceof NetClientHandler;
        int var1 = 1000;
        while (var1-- >= 0) {
            Packet var2 = (Packet)this.readPackets.poll();
            if (is_MITE_DS_client_player && !Main.isPacketThatMITEDSClientPlayerCanSendOrReceive(var2) || var2 == null || this.theNetHandler.isConnectionClosed()) continue;

            final PacketEvent.Receive event = new PacketEvent.Receive(var2);
            EventManager.INSTANCE.callEvent(event);
            if (event.getCancelled()) {
                continue;
            }

            var2.processPacket(this.theNetHandler);
        }
        this.wakeThreads();
        if (this.isTerminating && this.readPackets.isEmpty()) {
            this.theNetHandler.handleErrorMessage(this.terminationReason, this.shutdownDescription);
        }
    }
}

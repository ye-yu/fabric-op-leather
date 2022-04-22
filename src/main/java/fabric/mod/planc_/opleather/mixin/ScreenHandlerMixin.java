package fabric.mod.planc_.opleather.mixin;

import fabric.mod.planc_.opleather.interfaces.CurseEnchantmentHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenHandler.class)
public class ScreenHandlerMixin {

    @Inject(method = "onSlotClick", at = @At("RETURN"))
    private void onSetStackEnd(int slot, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
        if (player instanceof CurseEnchantmentHolder) {
            System.out.printf("onSlotClick, slot %d%n", slot);
        }
        if (slot < 4 || slot >= 8 || !(player instanceof CurseEnchantmentHolder holder)) return;
        holder.rebuildEnchantmentList();
    }
}

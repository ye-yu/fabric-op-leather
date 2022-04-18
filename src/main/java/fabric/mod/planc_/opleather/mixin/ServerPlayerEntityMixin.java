package fabric.mod.planc_.opleather.mixin;

import com.mojang.authlib.GameProfile;
import fabric.mod.planc_.opleather.enchantments.ModEnchantments;
import fabric.mod.planc_.opleather.interfaces.CurseOfWebbingMessenger;
import fabric.mod.planc_.opleather.utils.Utils;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements CurseOfWebbingMessenger {

    private final HashMap<ModEnchantments, Integer> enchantmentMap = new HashMap<>();

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    private void rebuildEnchantmentList() {
        enchantmentMap.clear();
        for (ItemStack stack : getInventory().armor) {
            final var nbtList = stack.getEnchantments();
            for (int i = 0; i < nbtList.size(); i++) {
                final NbtCompound nbtCompound = nbtList.getCompound(i);
                final Identifier identifier2 = EnchantmentHelper.getIdFromNbt(nbtCompound);
                if (identifier2 == null) continue;
                final ModEnchantments matchedEnchantment = Utils.getId2EnchantmentMap().get(identifier2);
                if (matchedEnchantment == null) continue;

                final int levelFromNbt = EnchantmentHelper.getLevelFromNbt(nbtCompound);
                enchantmentMap.merge(matchedEnchantment, levelFromNbt, Math::max);
            }
        }
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
        super.equipStack(slot, stack);
        if (slot.getType() != EquipmentSlot.Type.ARMOR) return;
        rebuildEnchantmentList();
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTickStart(CallbackInfo ci) {
        enchantmentMap.forEach((key, value) -> key.enchantment.tick((ServerPlayerEntity)(Object)this, value));
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    public void onReadCustomDataToNbtEnd(NbtCompound nbt, CallbackInfo ci) {
        rebuildEnchantmentList();
    }

    private int speedLevel = 0;

    @Override
    public void setSpeedLevel(int speedLevel) {
        this.speedLevel = speedLevel;
    }

    @Override
    public int getSpeedLevel() {
        return this.speedLevel;
    }
}

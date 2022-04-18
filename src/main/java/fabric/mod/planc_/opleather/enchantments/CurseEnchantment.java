package fabric.mod.planc_.opleather.enchantments;

import fabric.mod.planc_.opleather.OpLeather;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.network.ServerPlayerEntity;

public abstract class CurseEnchantment extends Enchantment {
    public static final ItemPredicate LEATHER_PREDICATE = ItemPredicate.Builder.create().tag(OpLeather.LEATHER_ARMORS).build();

    protected CurseEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return super.isAcceptableItem(stack) && LEATHER_PREDICATE.test(stack);
    }
    public abstract void tick(ServerPlayerEntity player, int strength);

    public abstract void onInitialize();
}

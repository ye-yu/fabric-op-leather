package fabric.mod.planc_.opleather.enchantments;

import fabric.mod.planc_.opleather.OpLeather;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.item.ItemPredicate;

public class CurseOfWebbing extends Enchantment {
    public static final ItemPredicate LEATHER_PREDICATE = ItemPredicate.Builder.create().tag(OpLeather.LEATHER_ARMORS).build();

    public CurseOfWebbing() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
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
}

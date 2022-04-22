package fabric.mod.planc_.opleather.interfaces;

import fabric.mod.planc_.opleather.enchantments.ModEnchantments;

import java.util.Map;

public interface CurseEnchantmentHolder {
    void rebuildEnchantmentList();

    Map<ModEnchantments, Integer> getEnchantmentList();
}

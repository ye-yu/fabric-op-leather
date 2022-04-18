package fabric.mod.planc_.opleather.utils;

import fabric.mod.planc_.opleather.OpLeather;
import fabric.mod.planc_.opleather.enchantments.ModEnchantments;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class Utils {
    public static Identifier identifier(String name) {
        return new Identifier(OpLeather.NAMESPACE, name);
    }

    public static HashMap<Identifier, ModEnchantments> id2EnchantmentMap = new HashMap<>();

    public static HashMap<Identifier, ModEnchantments> getId2EnchantmentMap() {
        if (id2EnchantmentMap.size() == 0) {
            for (ModEnchantments value : ModEnchantments.values()) {
                id2EnchantmentMap.put(value.id, value);
            }
        }
        return id2EnchantmentMap;
    }

}

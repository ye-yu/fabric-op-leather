package fabric.mod.planc_.opleather.enchantments;

import fabric.mod.planc_.opleather.utils.Utils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.function.Supplier;

public enum ModEnchantments {
    CURSE_OF_WEBBING(CurseOfWebbing::new);

    public final Enchantment enchantment;
    public final Identifier id = Utils.identifier(this.name().toLowerCase(Locale.ENGLISH));


    ModEnchantments(Supplier<? extends Enchantment> supplier) {
        this.enchantment = supplier.get();
    }
}

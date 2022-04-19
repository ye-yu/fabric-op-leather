package fabric.mod.planc_.opleather.items;

import com.google.common.base.Suppliers;
import fabric.mod.planc_.opleather.utils.Utils;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.function.Supplier;

public enum ModItems {
    MYSTERIOUS_POTION(Suppliers.memoize(MysteriousPotion::new));

    public final Supplier<Item> item;
    public final Identifier id = Utils.identifier(this.name().toLowerCase(Locale.ENGLISH));

    ModItems(Supplier<Item> itemGenerator) {
        this.item = itemGenerator;
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
}

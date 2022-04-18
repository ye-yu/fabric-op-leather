package fabric.mod.planc_.opleather.items;

import fabric.mod.planc_.opleather.utils.Utils;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.function.Supplier;

public enum ModItems {
    MYSTERIOUS_POTION(MysteriousPotion::new);

    public final Item item;
    public final Identifier id = Utils.identifier(this.name().toLowerCase(Locale.ENGLISH));

    ModItems(Supplier<Item> itemGenerator) {
        this.item = itemGenerator.get();
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
}

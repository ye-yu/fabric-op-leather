package fabric.mod.planc_.opleather.items;

import fabric.mod.planc_.opleather.utils.Utils;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

import java.util.Locale;

public enum ModItems {
    MYSTERIOUS_POTION;

    public final Item item = new Item(new FabricItemSettings().group(ItemGroup.BREWING).maxCount(1));
    public final Identifier id = Utils.identifier(this.name().toLowerCase(Locale.ENGLISH));

    @Override
    public String toString() {
        return this.id.toString();
    }
}

package fabric.mod.planc_.opleather.utils;

import fabric.mod.planc_.opleather.OpLeather;
import net.minecraft.util.Identifier;

public class Utils {
    public static Identifier identifier(String name) {
        return new Identifier(OpLeather.NAMESPACE, name);
    }
}

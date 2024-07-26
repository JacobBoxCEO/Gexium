package net.jacobBoxCeo.gexium.util;

import net.jacobBoxCeo.gexium.Gexium;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class Util {
    public static String makeDescriptionId(String pType, @Nullable ResourceLocation pId) {
        return pId == null ? pType + ".unregistered_sadface" : pType + "." + Gexium.MODID + "." + pId.getPath().replace('/', '.');
    }
}

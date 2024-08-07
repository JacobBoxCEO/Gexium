package net.jacobBoxCeo.gexium.util;

import net.jacobBoxCeo.gexium.Gexium;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class Util {
    public static String makeDescriptionId(String pType, @Nullable ResourceLocation pId) {
        return pId == null ? pType + ".unregistered_sadface" : pType + "." + Gexium.MOD_ID + "." + pId.getPath().replace('/', '.');
    }
    public static void literalComponent(@NotNull List<Component> pTooltipComponents, String inputString, ChatFormatting... formatting) {
        pTooltipComponents.add(Component.literal((inputString))
                .withStyle(formatting));
    }
    public static void translatableComponent(@NotNull List<Component> pTooltipComponents, String type, String location, ChatFormatting... formatting) {
        pTooltipComponents.add(Component.translatable(makeDescriptionId(type,
                        new ResourceLocation(location)))
                .withStyle(formatting));
    }
}

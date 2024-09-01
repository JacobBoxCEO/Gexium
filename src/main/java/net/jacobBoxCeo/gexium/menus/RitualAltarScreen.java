package net.jacobBoxCeo.gexium.menus;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jacobBoxCeo.gexium.Gexium;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class RitualAltarScreen extends AbstractContainerScreen<RitualAltarMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Gexium.MOD_ID, "textures/gui/ritual_altar_gui.png");

    public RitualAltarScreen(RitualAltarMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
    }
}

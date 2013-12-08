package rrutil.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import rrutil.container.ContainerInventoryLookup;

public class GuiInventoryLookup extends GuiContainer
{
	public static final int xSize = 204;
	public static final int ySize = 191;

	public GuiInventoryLookup(IInventory player, IInventory admin)
	{
		super(new ContainerInventoryLookup(player, admin));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("rrutil", "textures/gui/inventorylookup.png"));
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

}

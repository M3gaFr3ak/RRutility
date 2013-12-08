package rrutil.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import rrutil.container.ContainerInventoryLookup;
import rrutil.gui.GuiInventoryLookup;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.server.FMLServerHandler;

public class CommonProxy implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
		case 0:
			ItemStack stack = player.inventory.getCurrentItem();
			if (!stack.hasTagCompound())
				stack.setTagCompound(new NBTTagCompound());
			String playername = stack.getTagCompound().getString("player");
			EntityPlayer victim = world.getPlayerEntityByName(playername);
			if (victim == null)
				return null;
			return new ContainerInventoryLookup(victim.inventory, player.inventory);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
		case 0:
			ItemStack stack = player.inventory.getCurrentItem();
			if (!stack.hasTagCompound())
				stack.setTagCompound(new NBTTagCompound());
			String playername = stack.getTagCompound().getString("player");
			EntityPlayer victim = world.getPlayerEntityByName(playername);
			if (victim == null)
				return null;
			return new GuiInventoryLookup(victim.inventory, player.inventory);
		default:
			return null;
		}
	}

}

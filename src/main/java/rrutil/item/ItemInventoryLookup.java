package rrutil.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import rrutil.RRUtility;
import cpw.mods.fml.common.FMLCommonHandler;

public class ItemInventoryLookup extends Item
{

	public ItemInventoryLookup(int id)
	{
		super(id);
		setCreativeTab(RRUtility.ModTab);
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		ItemStack stack = player.inventory.getCurrentItem();
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		String playername = stack.getTagCompound().getString("player");
		EntityPlayer victim = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().getPlayerForUsername(playername);
		if (victim == null)
			return itemstack;
		player.openGui(RRUtility.instance, 0, victim.getEntityWorld(), player.getPlayerCoordinates().posX, player.getPlayerCoordinates().posY, player.getPlayerCoordinates().posZ);
		return itemstack;
	}

	public String getStatName()
	{
		return "Inventory Lookup";
	}
}

package rrutil.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import rrutil.item.ItemInventoryLookup;

public class CommandSetIventoryLookup extends CommandBase
{

	@Override
	public String getCommandName()
	{
		return "setInventory";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "";
	}

	public List getCommandAliases()
	{
		return new ArrayList();
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
		if (icommandsender instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) icommandsender;
			ItemStack inHand = player.inventory.getCurrentItem();
			String output = "Your hands are empty!";
			if (inHand != null)
			{
				if (inHand.getItem() instanceof ItemInventoryLookup)
				{
					if (!inHand.hasTagCompound())
						inHand.setTagCompound(new NBTTagCompound());
					inHand.getTagCompound().setString("player", astring[0]);
				}
			}
		}
	}

	public int getRequiredPermissionLevel()
	{
		return 0;
	}
}

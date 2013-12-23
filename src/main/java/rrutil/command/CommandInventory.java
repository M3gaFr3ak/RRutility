package rrutil.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatMessageComponent;
import rrutil.RRUtility;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommandInventory extends CommandBase
{

	@Override
	public String getCommandName()
	{
		return RRUtility.instance.invCommandName;
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "Prints out the inventory of the victim. Usage: Commandname <username>";
	}

	@SuppressWarnings("rawtypes")
	public List getCommandAliases()
	{
		return RRUtility.instance.invCommandAliases;
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
		if (astring.length > 0 && icommandsender instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) icommandsender;
			String playername = astring[0];
			EntityPlayer victim = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().getPlayerForUsername(playername);
			if (victim instanceof EntityPlayer)
			{
				player.sendChatToPlayer(new ChatMessageComponent().addText("--------------------------------------"));
				for (Object stack : victim.inventoryContainer.getInventory())
				{
					if (stack instanceof ItemStack)
					{
						player.sendChatToPlayer(new ChatMessageComponent().addText(((ItemStack) stack).getDisplayName() + ": " + ((ItemStack) stack).stackSize));
					}
				}
				player.sendChatToPlayer(new ChatMessageComponent().addText("--------------------------------------"));
			}
		}
	}

	public int getRequiredPermissionLevel()
	{
		return 0;
	}

	public int compareTo(ICommand par1ICommand)
	{
		return this.getCommandName().compareTo(par1ICommand.getCommandName());
	}

	public int compareTo(Object par1Obj)
	{
		return this.compareTo((ICommand) par1Obj);
	}
}

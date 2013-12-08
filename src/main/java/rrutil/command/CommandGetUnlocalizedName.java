package rrutil.command;

import java.util.List;

import rrutil.RRUtility;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;

public class CommandGetUnlocalizedName extends CommandBase
{

	@Override
	public String getCommandName()
	{
		return RRUtility.instance.unlCommandName;
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "This command will tell you the unlocalized name of the item you are holding in your hand!";
	}

	public List getCommandAliases()
	{
		return RRUtility.instance.unlCommandAliases;
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
				output = "The unlocalized name of the item \"" + EnumChatFormatting.BOLD + inHand.getItem().getItemStackDisplayName(inHand) + EnumChatFormatting.RESET + "\" is: \"" + EnumChatFormatting.BOLD + inHand.getItem().getUnlocalizedName(inHand) + EnumChatFormatting.RESET + "\"";
			}
			icommandsender.sendChatToPlayer(new ChatMessageComponent().addText(output));
		}
	}

	public int getRequiredPermissionLevel()
	{
		return 0;
	}
}

package rrutil;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatMessageComponent;

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

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
		if (icommandsender instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) icommandsender;
			ItemStack inHand = player.inventory.getCurrentItem();
			if (inHand != null)
			{
				icommandsender.sendChatToPlayer(new ChatMessageComponent().addText("The unlocalized name of the item \"" + inHand.getItem().getItemStackDisplayName(inHand) + "\" is: " + inHand.getItem().getUnlocalizedName(inHand)));
			} else
			{
				icommandsender.sendChatToPlayer(new ChatMessageComponent().addText("Your hands are empty!"));
			}
		}
	}

	public int getRequiredPermissionLevel()
	{
		return 0;
	}
}

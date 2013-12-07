package rrutil;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = "rrutil", name = "Resonant Rise Utility")
public class RRUtility
{
	@Instance("rrutil")
	public static RRUtility instance;

	public String unlCommandName = "ULN";
	private Boolean enableIsUnlocalized = true;
	private Boolean enableContainsUnlocalized = true;
	private String[] removeIsUnlocalizedName = new String[0];
	private String[] removeContainsUnlocalizedName = new String[0];

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		unlCommandName = config.get("Commands", "getUnlocalizedNameCommand", unlCommandName, "commandString to call the getUnlocalizedName command").getString();
		enableIsUnlocalized = config.get("Recipe Removal Toggles", "enable_is_unlocalized", enableIsUnlocalized, "Enable the option to remove the Items from the \"is_unlocalized\" list").getBoolean(enableIsUnlocalized);
		enableContainsUnlocalized = config.get("Recipe Removal Toggles", "enable_contains_unlocalized", enableContainsUnlocalized, "Enable the option to remove the Items from the \"contains_unlocalized\" list").getBoolean(enableContainsUnlocalized);
		removeIsUnlocalizedName = config.get("Recipe Removal", "is_unlocalized", removeIsUnlocalizedName, "Recipes to be removed, if the unlocalized name is the entry").getStringList();
		removeContainsUnlocalizedName = config.get("Recipe Removal", "contains_unlocalized", removeContainsUnlocalizedName, "Recipes to be removed, if the unlocalized name contains the entry").getStringList();
		config.save();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();
		List<IRecipe> toRemove = new ArrayList<IRecipe>();
		for (IRecipe recipe : recipeList)
		{
			if (recipe != null && recipe.getRecipeOutput() != null)
			{
				for (String name : removeIsUnlocalizedName)
				{
					if (recipe.getRecipeOutput().getItem().getUnlocalizedName(recipe.getRecipeOutput()).toLowerCase().equalsIgnoreCase(name.toLowerCase()))
					{
						toRemove.add(recipe);
						break;
					}
				}
				for (String name : removeContainsUnlocalizedName)
				{
					if (recipe.getRecipeOutput().getItem().getUnlocalizedName(recipe.getRecipeOutput()).toLowerCase().contains(name.toLowerCase()))
					{
						toRemove.add(recipe);
						break;
					}
				}
			}
		}
		for (IRecipe recipe : toRemove)
		{
			recipeList.remove(recipe);
		}
	}

	@EventHandler
	public void serverStart(FMLServerStartingEvent event)
	{
		MinecraftServer server = MinecraftServer.getServer();
		ServerCommandManager manager = (ServerCommandManager) server.getCommandManager();
		manager.registerCommand(new CommandGetUnlocalizedName());
	}
}

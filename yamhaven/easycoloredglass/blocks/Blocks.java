package yamhaven.easycoloredglass.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import yamhaven.easycoloredglass.lib.BlockIds;
import yamhaven.easycoloredglass.lib.BlockInfo;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {
	public static Block coloredSandBlock;
	public static Block coloredGlassBlock;
	public static Block coloredGlassPaneBlock;
	public static Block blueLightBlock;
	
	public static void init() {
		coloredSandBlock = new ColoredSandBlock(BlockIds.coloredSandBlockID_actual);
		coloredGlassBlock = new ColoredGlassBlock(BlockIds.coloredGlassBlockID_actual);
		coloredGlassPaneBlock = new ColoredGlassPaneBlock(BlockIds.coloredGlassPaneBlockID_actual);
		
		GameRegistry.registerBlock(coloredSandBlock, ItemColoredGlassBlock.class, BlockInfo.coloredSandBlock_name);
		GameRegistry.registerBlock(coloredGlassBlock, ItemColoredGlassBlock.class, BlockInfo.coloredGlassBlock_name);
		GameRegistry.registerBlock(coloredGlassPaneBlock, ItemColoredGlassPaneBlock.class, BlockInfo.coloredGlassPaneBlock_name);
	}
	
	public static void addNames() {
		for(int i = 0; i<16; i++) {
			LanguageRegistry.addName(new ItemStack(coloredSandBlock, 1, i), BlockInfo.colors[i] + " " + BlockInfo.coloredSandBlock_name);
			LanguageRegistry.addName(new ItemStack(coloredGlassBlock, 1, i), BlockInfo.colors[i] + " " + BlockInfo.coloredGlassBlock_name);
			LanguageRegistry.addName(new ItemStack(coloredGlassPaneBlock, 1, i), BlockInfo.colors[i] + " " + BlockInfo.coloredGlassPaneBlock_name);
		}
	}
	
	public static void addBlockRecipes() {
		for(int i = 0; i<16; i++) {
			ItemStack sand = new ItemStack(Block.sand, 1);
            ItemStack glass = new ItemStack(Block.glass, 1);
            ItemStack dye = new ItemStack(Item.dyePowder, 1, i);
            
            ItemStack coloredSandStack = new ItemStack(coloredSandBlock, 1, i);
            ItemStack coloredGlassStack = new ItemStack(coloredGlassBlock, 1, i);
            ItemStack coloredGlassPaneStack = new ItemStack(coloredGlassPaneBlock, 1, i);

            //GameRegistry.addRecipe(coloredGlassStack, "xxx", "xyx", "xxx", 'x', glass, 'y', dye);
            GameRegistry.addShapelessRecipe(coloredSandStack, sand, dye);
            FurnaceRecipes.smelting().addSmelting(BlockIds.coloredSandBlockID_actual, i, coloredGlassStack, 0.1f);
            GameRegistry.addRecipe(new ItemStack(coloredGlassPaneBlock, 16, i), "xxx", "xxx", 'x', coloredGlassStack);
		}
	}
}
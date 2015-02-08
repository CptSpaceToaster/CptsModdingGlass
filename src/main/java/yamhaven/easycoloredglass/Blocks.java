package yamhaven.easycoloredglass;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import static net.minecraft.init.Blocks.sand;
import static net.minecraft.init.Blocks.sandstone;
import static net.minecraft.init.Blocks.stained_glass;

public class Blocks {
    public static Block coloredSand;
    public static Block coloredSandstone_normal;
    public static Block coloredSandstone_smooth;
    public static Block coloredSandstone_carved;
    public static Block coloredSandstone_stairs[] = new Block[16];

    public static String coloredSandName = "coloredSandBlock";
    public static String coloredSandstone = "coloredSandstone";
    public static String coloredSandstoneName_normal = "coloredSandstone_normal";
    public static String coloredSandstoneName_smooth = "coloredSandstone_smooth";
    public static String coloredSandstoneName_carved = "coloredSandstone_carved";
    public static String coloredSandstoneName_stairs = "coloredSandstone_stairs";


    protected static void addBlocks() {
        coloredSand = new BlockColoredSand().setHardness(0.5F).setStepSound(Block.soundTypeSand).setBlockName(coloredSandName);
        coloredSandstone_normal = new BlockColoredSandstone_normal().setHardness(0.8F).setStepSound(Block.soundTypePiston).setBlockName(coloredSandstoneName_normal);
        coloredSandstone_smooth = new BlockColoredSandstone_smooth().setHardness(0.8F).setStepSound(Block.soundTypePiston).setBlockName(coloredSandstoneName_smooth);
        coloredSandstone_carved = new BlockColoredSandstone_carved().setHardness(0.8F).setStepSound(Block.soundTypePiston).setBlockName(coloredSandstoneName_carved);
        for (int i = 0; i < 16; i++) {
            coloredSandstone_stairs[i] = new BlockColoredStairs(coloredSandstone_normal, i).setBlockName(coloredSandstoneName_stairs + i);
        }

        GameRegistry.registerBlock(coloredSand, ItemCGBlock.class, EasyColoredGlass.MOD_ID + coloredSandName);
        GameRegistry.registerBlock(coloredSandstone_normal, ItemCGBlock.class, EasyColoredGlass.MOD_ID + coloredSandstoneName_normal);
        GameRegistry.registerBlock(coloredSandstone_smooth, ItemCGBlock.class, EasyColoredGlass.MOD_ID + coloredSandstoneName_smooth);
        GameRegistry.registerBlock(coloredSandstone_carved, ItemCGBlock.class, EasyColoredGlass.MOD_ID + coloredSandstoneName_carved);
        for (int i = 0; i < 16; i++) {
            GameRegistry.registerBlock(coloredSandstone_stairs[i], ItemCGBlock.class, EasyColoredGlass.MOD_ID + coloredSandstoneName_stairs + i);
        }
    }

    protected static void addRecipes() {
        for (int i = 0; i < 16; i++)
        {
            ItemStack cDye = new ItemStack(Items.dye, 1, i);
            ItemStack cSand = new ItemStack(coloredSand, 1, i);
            ItemStack cSandstone_normal = new ItemStack(coloredSandstone_normal, 1, i);

            // Dyeing Recipes
            GameRegistry.addRecipe(new ItemStack(coloredSand, 8, i), "sss", "sds", "sss", 's', new ItemStack(sand, 1, 0), 'd', cDye);
            GameRegistry.addRecipe(new ItemStack(coloredSandstone_normal, 8, i), "sss", "sds", "sss", 's', new ItemStack(sandstone, 1, 0), 'd', cDye);
            GameRegistry.addRecipe(cSandstone_normal, "ss", "ss", 's', cSand);
            // TODO: Ordict the colored Sandstone entries together
            GameRegistry.addRecipe(new ItemStack(coloredSandstone_smooth, 4, i), "ss", "ss", 's', cSandstone_normal);
            GameRegistry.addRecipe(new ItemStack(coloredSandstone_stairs[i], 4, 0), "s  ", "ss ", "sss", 's', cSandstone_normal);
            GameRegistry.addRecipe(new ItemStack(coloredSandstone_stairs[i], 4, 0), "  s", " ss", "sss", 's', cSandstone_normal);
            // Smelting Recipes
            FurnaceRecipes.smelting().func_151394_a(new ItemStack(coloredSand, 1, i), new ItemStack(stained_glass, 1, 15 - i), 0.1f);
        }
    }
}

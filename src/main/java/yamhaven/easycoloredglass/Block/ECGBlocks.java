package yamhaven.easycoloredglass.Block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import yamhaven.easycoloredglass.EasyColoredGlass;

import static net.minecraft.init.Blocks.*;

public class ECGBlocks {
    public static String[] colors =
    {
        "Black",
        "Red",
        "Green",
        "Brown",
        "Blue",
        "Purple",
        "Cyan",
        "LightGray",
        "Gray",
        "Pink",
        "Lime",
        "Yellow",
        "LightBlue",
        "Magenta",
        "Orange",
        "White"
    };

    public static Block coloredSand;
    public static Block coloredSandstone_normal;
    public static Block coloredSandstone_carved;
    public static Block coloredSandstone_smooth;
    public static Block coloredSandstone_stairs[] = new Block[16];

    public static String coloredSandName = "coloredSand";
    public static String coloredSandstoneName = "coloredSandstone";
    public static String coloredSandstoneName_normal = "coloredSandstone_normal";
    public static String coloredSandstoneName_carved = "coloredSandstone_carved";
    public static String coloredSandstoneName_smooth = "coloredSandstone_smooth";
    public static String coloredSandstoneName_stairs = "coloredSandstone_stairs";


    protected static void addBlocks() {
        coloredSand = new BlockColoredSand().setHardness(0.5F).setStepSound(Block.soundTypeSand).setBlockName(coloredSandName);
        coloredSandstone_normal = new BlockColoredSandstone_normal().setHardness(0.8F).setStepSound(Block.soundTypePiston).setBlockName(coloredSandstoneName_normal);
        coloredSandstone_carved = new BlockColoredSandstone_carved().setHardness(0.8F).setStepSound(Block.soundTypePiston).setBlockName(coloredSandstoneName_carved);
        coloredSandstone_smooth = new BlockColoredSandstone_smooth().setHardness(0.8F).setStepSound(Block.soundTypePiston).setBlockName(coloredSandstoneName_smooth);
        for (int i = 0; i < 16; i++) {
            coloredSandstone_stairs[i] = new BlockColoredStairs(coloredSandstone_normal, i).setBlockName(coloredSandstoneName_stairs + i);
        }

        GameRegistry.registerBlock(coloredSand, ItemCGBlock.class, EasyColoredGlass.MOD_ID + coloredSandName);
        GameRegistry.registerBlock(coloredSandstone_normal, ItemCGBlock.class, EasyColoredGlass.MOD_ID + coloredSandstoneName_normal);
        GameRegistry.registerBlock(coloredSandstone_carved, ItemCGBlock.class, EasyColoredGlass.MOD_ID + coloredSandstoneName_carved);
        GameRegistry.registerBlock(coloredSandstone_smooth, ItemCGBlock.class, EasyColoredGlass.MOD_ID + coloredSandstoneName_smooth);

        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre(coloredSandstoneName + colors[i], new ItemStack(coloredSandstone_normal, 1, i));
            OreDictionary.registerOre(coloredSandstoneName + colors[i], new ItemStack(coloredSandstone_carved, 1, i));
            OreDictionary.registerOre(coloredSandstoneName + colors[i], new ItemStack(coloredSandstone_smooth, 1, i));

            GameRegistry.registerBlock(coloredSandstone_stairs[i], ItemCGBlock.class, EasyColoredGlass.MOD_ID + coloredSandstoneName_stairs + i);
        }
    }

    protected static void addRecipes() {
        for (int i = 0; i < 16; i++) {
            // Dyeing Recipes
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(coloredSand, 8, i), "sss", "sds", "sss", 's', new ItemStack(sand, 1, 0), 'd', "dye" + colors[i] ));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(coloredSandstone_normal, 8, i), "sss", "sds", "sss", 's', new ItemStack(sandstone, 1, 0), 'd', "dye" + colors[i]));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(coloredSandstone_carved, 8, i), "sss", "sds", "sss", 's', new ItemStack(sandstone, 1, 1), 'd', "dye" + colors[i]));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(coloredSandstone_smooth, 8, i), "sss", "sds", "sss", 's', new ItemStack(sandstone, 1, 2), 'd', "dye" + colors[i]));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(coloredSandstone_stairs[i], 8, 0), "sss", "sds", "sss", 's', new ItemStack(sandstone_stairs, 1, 0), 'd', "dye" + colors[i]));

            // Block recipes
            GameRegistry.addRecipe(new ItemStack(coloredSandstone_normal, 1, i), "ss", "ss", 's', new ItemStack(coloredSand, 1, i));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(coloredSandstone_smooth, 4, i), "ss", "ss", 's', coloredSandstoneName + colors[i]));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(coloredSandstone_stairs[i], 4, 0), "s  ", "ss ", "sss", 's', coloredSandstoneName + colors[i]));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(coloredSandstone_stairs[i], 4, 0), "  s", " ss", "sss", 's', coloredSandstoneName + colors[i]));

            // Smelting Recipes
            FurnaceRecipes.smelting().func_151394_a(new ItemStack(coloredSand, 1, i), new ItemStack(stained_glass, 1, 15 - i), 0.1f);
        }
    }
}

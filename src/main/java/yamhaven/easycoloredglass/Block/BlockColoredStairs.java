package yamhaven.easycoloredglass.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;

public class BlockColoredStairs extends BlockStairs {
    protected BlockColoredStairs(Block block, int color) {
        super(block, color);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.lightOpacity = 0; // Dirty fix to stop a weird shadow bug when rendering an inside corner...
    }
}

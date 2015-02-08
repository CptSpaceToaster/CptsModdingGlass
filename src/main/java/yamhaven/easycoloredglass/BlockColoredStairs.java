package yamhaven.easycoloredglass;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by toaster on 2/8/15.
 */
public class BlockColoredStairs extends BlockStairs {
    protected BlockColoredStairs(Block block, int color)
    {
        super(block, color);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.lightOpacity = 0; // Dirty fix to stop a weird shadow bug when rendering an inside corner...
    }
}

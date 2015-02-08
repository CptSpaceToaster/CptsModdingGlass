package yamhaven.easycoloredglass;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class BlockColoredSand extends BlockFalling {
    private IIcon[] icons = new IIcon[16];

    public BlockColoredSand() {
        super(Material.sand);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    public IIcon getIcon(int p_149691_1_, int meta)
    {
        return icons[meta];
    }

    public void registerBlockIcons(IIconRegister p_149651_1_) {
        for(int i=0; i<16; i++) {
            icons[i] = p_149651_1_.registerIcon(EasyColoredGlass.MOD_ID + ":" + ECGBlocks.coloredSandName + "/" + i);
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int p_149692_1_)
    {
        return p_149692_1_;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
        for(int i=0; i<16; i++) {
            p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
        }
    }
}

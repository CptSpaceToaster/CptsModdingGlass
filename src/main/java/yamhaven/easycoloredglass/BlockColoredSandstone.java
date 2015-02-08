package yamhaven.easycoloredglass;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public abstract class BlockColoredSandstone extends Block {
    protected IIcon[] side_textures;
    protected IIcon[] top_textures;
    protected IIcon[] bot_textures;

    public BlockColoredSandstone()
    {
        super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    /**
     * Gets the block's texture. Args: side_texture, meta
     */
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        if (p_149691_1_ == 0) {
            return this.bot_textures[p_149691_2_];
        } else if (p_149691_1_ == 1) {
            return this.top_textures[p_149691_2_];
        } else {
            return this.side_textures[p_149691_2_];
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
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        for(int i=0; i<16; i++) {
            p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
        }
    }

    public abstract void registerBlockIcons(IIconRegister p_149651_1_);
}

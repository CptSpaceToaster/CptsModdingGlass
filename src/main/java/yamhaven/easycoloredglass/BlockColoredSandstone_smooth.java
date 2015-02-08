package yamhaven.easycoloredglass;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * Created by toaster on 2/7/15.
 */
public class BlockColoredSandstone_smooth extends BlockColoredSandstone {
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        if (p_149691_1_ == 0 || p_149691_1_ == 1) {
            return this.top_textures[p_149691_2_];
        } else {
            return this.side_textures[p_149691_2_];
        }
    }

    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.side_textures = new IIcon[16];
        this.top_textures = new IIcon[16];
        this.bot_textures = new IIcon[16];

        for (int i = 0; i < 16; ++i)
        {
            this.side_textures[i] = p_149651_1_.registerIcon(EasyColoredGlass.MOD_ID + ":" + Blocks.coloredSandstoneName + "/smooth" + i);
            this.top_textures[i] = p_149651_1_.registerIcon(EasyColoredGlass.MOD_ID + ":" + Blocks.coloredSandstoneName + "/top" + i);
            this.bot_textures[i] = p_149651_1_.registerIcon(EasyColoredGlass.MOD_ID + ":" + Blocks.coloredSandstoneName + "/bottom" + i);
        }
    }
}

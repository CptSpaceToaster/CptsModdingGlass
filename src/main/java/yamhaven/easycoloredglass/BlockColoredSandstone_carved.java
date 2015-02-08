package yamhaven.easycoloredglass;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * Created by toaster on 2/7/15.
 */
public class BlockColoredSandstone_carved extends BlockColoredSandstone {
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.side_textures = new IIcon[16];
        this.top_textures = new IIcon[16];
        this.bot_textures = new IIcon[16];

        for (int i = 0; i < 16; ++i)
        {
            this.side_textures[i] = p_149651_1_.registerIcon(EasyColoredGlass.MOD_ID + ":" + Blocks.coloredSandstone + "/carved" + i);
            this.top_textures[i] = p_149651_1_.registerIcon(EasyColoredGlass.MOD_ID + ":" + Blocks.coloredSandstone + "/top" + i);
            this.bot_textures[i] = p_149651_1_.registerIcon(EasyColoredGlass.MOD_ID + ":" + Blocks.coloredSandstone + "/bottom" + i);
        }
    }
}

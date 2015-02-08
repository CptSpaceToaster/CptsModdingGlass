package yamhaven.easycoloredglass;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockColoredSandstone_normal extends BlockColoredSandstone {
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.side_textures = new IIcon[16];
        this.top_textures = new IIcon[16];
        this.bot_textures = new IIcon[16];

        for (int i = 0; i < 16; ++i)
        {
            this.side_textures[i] = p_149651_1_.registerIcon(EasyColoredGlass.MOD_ID + ":" + Blocks.coloredSandstoneName + "/normal" + i);
            this.top_textures[i] = p_149651_1_.registerIcon(EasyColoredGlass.MOD_ID + ":" + Blocks.coloredSandstoneName + "/top" + i);
            this.bot_textures[i] = p_149651_1_.registerIcon(EasyColoredGlass.MOD_ID + ":" + Blocks.coloredSandstoneName + "/bottom" + i);
        }
    }
}

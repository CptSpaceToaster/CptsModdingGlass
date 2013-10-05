package yamhaven.easycoloredglass.render;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;
import yamhaven.easycoloredglass.blocks.ColoredGlassPaneBlock;
import yamhaven.easycoloredglass.lib.ModInfo;
import yamhaven.easycoloredglass.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.prupe.mcpatcher.ctm.ColoredGlassPaneRenderer;

import java.util.Arrays;

public class RenderColoredGlassPaneBlock implements ISimpleBlockRenderingHandler {
	Tessellator tessellator;
	
	//private Object blockAccess;
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if (modelId == ClientProxy.RenderColoredGlassPaneBlockID) 
		{
			if (!ModInfo.connectedTextures_actual) {
				return renderColoredGlassPane(world, x, y, z, (ColoredGlassPaneBlock) block, renderer);
			} else {	
				try {
					return ColoredGlassPaneRenderer.render(renderer, (ColoredGlassPaneBlock) block, x, y, z);
				}
				catch(Throwable e) {
					ModInfo.connectedTextures_actual = false;
					return renderColoredGlassPane(world, x, y, z, (ColoredGlassPaneBlock) block, renderer);
				}
			}
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return ClientProxy.RenderColoredGlassPaneBlockID;
	}
	
	@SideOnly(Side.CLIENT)
	private boolean renderColoredGlassPane(IBlockAccess world, int par2, int par3, int par4, ColoredGlassPaneBlock par1BlockPane, RenderBlocks renderer)
	{
		int l = renderer.blockAccess.getHeight();
        tessellator = Tessellator.instance;
        tessellator.setBrightness(par1BlockPane.getMixedBrightnessForBlock(renderer.blockAccess, par2, par3, par4));
        float f = 1.0F;
        int i1 = par1BlockPane.colorMultiplier(renderer.blockAccess, par2, par3, par4);
        float f1 = (float)(i1 >> 16 & 255) / 255.0F;
        float f2 = (float)(i1 >> 8 & 255) / 255.0F;
        float f3 = (float)(i1 & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
            float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
            float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }

        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
        Icon icon;
        Icon icon1;

        int metadata = renderer.blockAccess.getBlockMetadata(par2, par3, par4);
        icon = par1BlockPane.getIcon(0, metadata);
        icon1 = par1BlockPane.getSideTextureFromMetadata(metadata);

        double d0 = (double)icon.getMinU();
        double d1 = (double)icon.getInterpolatedU(8.0D);
        double d2 = (double)icon.getMaxU();
        double d3 = (double)icon.getMinV();
        double d4 = (double)icon.getMaxV();
        double d5 = (double)icon1.getInterpolatedU(7.0D);
        double d6 = (double)icon1.getInterpolatedU(9.0D);
        double d7 = (double)icon1.getMinV();
        double d8 = (double)icon1.getInterpolatedV(8.0D);
        double d9 = (double)icon1.getMaxV();
        double d10 = (double)par2;
        double d11 = (double)par2 + 0.5D;
        double d12 = (double)(par2 + 1);
        double d13 = (double)par4;
        double d14 = (double)par4 + 0.5D;
        double d15 = (double)(par4 + 1);
        double d16 = (double)par2 + 0.5D - 0.0625D;
        double d17 = (double)par2 + 0.5D + 0.0625D;
        double d18 = (double)par4 + 0.5D - 0.0625D;
        double d19 = (double)par4 + 0.5D + 0.0625D;
        boolean flag = par1BlockPane.canPaneConnectTo(renderer.blockAccess,par2, par3, par4, NORTH);
        boolean flag1 = par1BlockPane.canPaneConnectTo(renderer.blockAccess,par2, par3, par4, SOUTH);
        boolean flag2 = par1BlockPane.canPaneConnectTo(renderer.blockAccess,par2, par3, par4, WEST);
        boolean flag3 = par1BlockPane.canPaneConnectTo(renderer.blockAccess,par2, par3, par4, EAST);
        boolean flag4 = par1BlockPane.shouldSideBeRendered(renderer.blockAccess, par2, par3 + 1, par4, 1);
        boolean flag5 = par1BlockPane.shouldSideBeRendered(renderer.blockAccess, par2, par3 - 1, par4, 0);
        double d20 = 0.01D;
        double d21 = 0.005D;

        if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1))
        {
            if (flag2 && !flag3)
            {
                tessellator.addVertexWithUV(d10, (double)(par3 + 1), d14, d0, d3);
                tessellator.addVertexWithUV(d10, (double)(par3 + 0), d14, d0, d4);
                tessellator.addVertexWithUV(d11, (double)(par3 + 0), d14, d1, d4);
                tessellator.addVertexWithUV(d11, (double)(par3 + 1), d14, d1, d3);
//                tessellator.addVertexWithUV(d11, (double)(par3 + 1), d14, d0, d3);
//                tessellator.addVertexWithUV(d11, (double)(par3 + 0), d14, d0, d4);
//                tessellator.addVertexWithUV(d10, (double)(par3 + 0), d14, d1, d4);
//                tessellator.addVertexWithUV(d10, (double)(par3 + 1), d14, d1, d3);

                if (!flag1 && !flag)
                {
                    tessellator.addVertexWithUV(d11, (double)(par3 + 1), d19, d5, d7);
                    tessellator.addVertexWithUV(d11, (double)(par3 + 0), d19, d5, d9);
                    tessellator.addVertexWithUV(d11, (double)(par3 + 0), d18, d6, d9);
                    tessellator.addVertexWithUV(d11, (double)(par3 + 1), d18, d6, d7);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1), d18, d5, d7);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 0), d18, d5, d9);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 0), d19, d6, d9);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1), d19, d6, d7);
                }

                if (flag4 || par3 < l - 1 && renderer.blockAccess.isAirBlock(par2 - 1, par3 + 1, par4))
                {
                    tessellator.addVertexWithUV(d10, (double)(par3 + 1) + 0.01D, d19, d6, d8);
                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d19, d6, d9);
                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d18, d5, d9);
                    tessellator.addVertexWithUV(d10, (double)(par3 + 1) + 0.01D, d18, d5, d8);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d19, d6, d8);
//                    tessellator.addVertexWithUV(d10, (double)(par3 + 1) + 0.01D, d19, d6, d9);
//                    tessellator.addVertexWithUV(d10, (double)(par3 + 1) + 0.01D, d18, d5, d9);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d18, d5, d8);
                }

                if (flag5 || par3 > 1 && renderer.blockAccess.isAirBlock(par2 - 1, par3 - 1, par4))
                {
                    tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d19, d6, d8);
                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d19, d6, d9);
                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d18, d5, d9);
                    tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d18, d5, d8);
//                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d19, d6, d8);
//                    tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d19, d6, d9);
//                    tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d18, d5, d9);
//                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d18, d5, d8);
                }
            }
            else if (!flag2 && flag3)
            {
                tessellator.addVertexWithUV(d11, (double)(par3 + 1), d14, d1, d3);
                tessellator.addVertexWithUV(d11, (double)(par3 + 0), d14, d1, d4);
                tessellator.addVertexWithUV(d12, (double)(par3 + 0), d14, d2, d4);
                tessellator.addVertexWithUV(d12, (double)(par3 + 1), d14, d2, d3);
//                tessellator.addVertexWithUV(d12, (double)(par3 + 1), d14, d1, d3);
//                tessellator.addVertexWithUV(d12, (double)(par3 + 0), d14, d1, d4);
//                tessellator.addVertexWithUV(d11, (double)(par3 + 0), d14, d2, d4);
//                tessellator.addVertexWithUV(d11, (double)(par3 + 1), d14, d2, d3);

                if (!flag1 && !flag)
                {
                    tessellator.addVertexWithUV(d11, (double)(par3 + 1), d18, d5, d7);
                    tessellator.addVertexWithUV(d11, (double)(par3 + 0), d18, d5, d9);
                    tessellator.addVertexWithUV(d11, (double)(par3 + 0), d19, d6, d9);
                    tessellator.addVertexWithUV(d11, (double)(par3 + 1), d19, d6, d7);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1), d19, d5, d7);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 0), d19, d5, d9);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 0), d18, d6, d9);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1), d18, d6, d7);
                }

                if (flag4 || par3 < l - 1 && renderer.blockAccess.isAirBlock(par2 + 1, par3 + 1, par4))
                {
                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d19, d6, d7);
                    tessellator.addVertexWithUV(d12, (double)(par3 + 1) + 0.01D, d19, d6, d8);
                    tessellator.addVertexWithUV(d12, (double)(par3 + 1) + 0.01D, d18, d5, d8);
                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d18, d5, d7);
//                    tessellator.addVertexWithUV(d12, (double)(par3 + 1) + 0.01D, d19, d6, d7);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d19, d6, d8);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d18, d5, d8);
//                    tessellator.addVertexWithUV(d12, (double)(par3 + 1) + 0.01D, d18, d5, d7);
                }

                if (flag5 || par3 > 1 && renderer.blockAccess.isAirBlock(par2 + 1, par3 - 1, par4))
                {
                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d19, d6, d7);
                    tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d19, d6, d8);
                    tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d18, d5, d8);
                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d18, d5, d7);
//                    tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d19, d6, d7);
//                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d19, d6, d8);
//                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d18, d5, d8);
//                    tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d18, d5, d7);
                }
            }
        }
        else
        {
            tessellator.addVertexWithUV(d10, (double)(par3 + 1), d14, d0, d3);
            tessellator.addVertexWithUV(d10, (double)(par3 + 0), d14, d0, d4);
            tessellator.addVertexWithUV(d12, (double)(par3 + 0), d14, d2, d4);
            tessellator.addVertexWithUV(d12, (double)(par3 + 1), d14, d2, d3);
//            tessellator.addVertexWithUV(d12, (double)(par3 + 1), d14, d0, d3);
//            tessellator.addVertexWithUV(d12, (double)(par3 + 0), d14, d0, d4);
//            tessellator.addVertexWithUV(d10, (double)(par3 + 0), d14, d2, d4);
//            tessellator.addVertexWithUV(d10, (double)(par3 + 1), d14, d2, d3);

            if (flag4)
            {
                tessellator.addVertexWithUV(d10, (double)(par3 + 1) + 0.01D, d19, d6, d9);
                tessellator.addVertexWithUV(d12, (double)(par3 + 1) + 0.01D, d19, d6, d7);
                tessellator.addVertexWithUV(d12, (double)(par3 + 1) + 0.01D, d18, d5, d7);
                tessellator.addVertexWithUV(d10, (double)(par3 + 1) + 0.01D, d18, d5, d9);
//                tessellator.addVertexWithUV(d12, (double)(par3 + 1) + 0.01D, d19, d6, d9);
//                tessellator.addVertexWithUV(d10, (double)(par3 + 1) + 0.01D, d19, d6, d7);
//                tessellator.addVertexWithUV(d10, (double)(par3 + 1) + 0.01D, d18, d5, d7);
//                tessellator.addVertexWithUV(d12, (double)(par3 + 1) + 0.01D, d18, d5, d9);
            }
            else
            {
                if (par3 < l - 1 && renderer.blockAccess.isAirBlock(par2 - 1, par3 + 1, par4))
                {
                    tessellator.addVertexWithUV(d10, (double)(par3 + 1) + 0.01D, d19, d6, d8);
                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d19, d6, d9);
                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d18, d5, d9);
                    tessellator.addVertexWithUV(d10, (double)(par3 + 1) + 0.01D, d18, d5, d8);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d19, d6, d8);
//                    tessellator.addVertexWithUV(d10, (double)(par3 + 1) + 0.01D, d19, d6, d9);
//                    tessellator.addVertexWithUV(d10, (double)(par3 + 1) + 0.01D, d18, d5, d9);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d18, d5, d8);
                }

                if (par3 < l - 1 && renderer.blockAccess.isAirBlock(par2 + 1, par3 + 1, par4))
                {
                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d19, d6, d7);
                    tessellator.addVertexWithUV(d12, (double)(par3 + 1) + 0.01D, d19, d6, d8);
                    tessellator.addVertexWithUV(d12, (double)(par3 + 1) + 0.01D, d18, d5, d8);
                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d18, d5, d7);
//                    tessellator.addVertexWithUV(d12, (double)(par3 + 1) + 0.01D, d19, d6, d7);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d19, d6, d8);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d18, d5, d8);
//                    tessellator.addVertexWithUV(d12, (double)(par3 + 1) + 0.01D, d18, d5, d7);
                }
            }

            if (flag5)
            {
                tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d19, d6, d9);
                tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d19, d6, d7);
                tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d18, d5, d7);
                tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d18, d5, d9);
//                tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d19, d6, d9);
//                tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d19, d6, d7);
//                tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d18, d5, d7);
//                tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d18, d5, d9);
            }
            else
            {
                if (par3 > 1 && renderer.blockAccess.isAirBlock(par2 - 1, par3 - 1, par4))
                {
                    tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d19, d6, d8);
                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d19, d6, d9);
                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d18, d5, d9);
                    tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d18, d5, d8);
//                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d19, d6, d8);
//                    tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d19, d6, d9);
//                    tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d18, d5, d9);
//                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d18, d5, d8);
                }

                if (par3 > 1 && renderer.blockAccess.isAirBlock(par2 + 1, par3 - 1, par4))
                {
                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d19, d6, d7);
                    tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d19, d6, d8);
                    tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d18, d5, d8);
                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d18, d5, d7);
//                    tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d19, d6, d7);
//                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d19, d6, d8);
//                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d18, d5, d8);
//                    tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d18, d5, d7);
                }
            }
        }

        if ((!flag || !flag1) && (flag2 || flag3 || flag || flag1))
        {
            if (flag && !flag1)
            {
//                tessellator.addVertexWithUV(d11, (double)(par3 + 1), d13, d0, d3);
//                tessellator.addVertexWithUV(d11, (double)(par3 + 0), d13, d0, d4);
//                tessellator.addVertexWithUV(d11, (double)(par3 + 0), d14, d1, d4);
//                tessellator.addVertexWithUV(d11, (double)(par3 + 1), d14, d1, d3);
                tessellator.addVertexWithUV(d11, (double)(par3 + 1), d14, d0, d3);
                tessellator.addVertexWithUV(d11, (double)(par3 + 0), d14, d0, d4);
                tessellator.addVertexWithUV(d11, (double)(par3 + 0), d13, d1, d4);
                tessellator.addVertexWithUV(d11, (double)(par3 + 1), d13, d1, d3);

                if (!flag3 && !flag2)
                {
                    tessellator.addVertexWithUV(d16, (double)(par3 + 1), d14, d5, d7);
                    tessellator.addVertexWithUV(d16, (double)(par3 + 0), d14, d5, d9);
                    tessellator.addVertexWithUV(d17, (double)(par3 + 0), d14, d6, d9);
                    tessellator.addVertexWithUV(d17, (double)(par3 + 1), d14, d6, d7);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1), d14, d5, d7);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 0), d14, d5, d9);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 0), d14, d6, d9);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1), d14, d6, d7);
                }

                if (flag4 || par3 < l - 1 && renderer.blockAccess.isAirBlock(par2, par3 + 1, par4 - 1))
                {
                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d13, d6, d7);
                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d14, d6, d8);
                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d14, d5, d8);
                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d13, d5, d7);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d14, d6, d7);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d13, d6, d8);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d13, d5, d8);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d14, d5, d7);
                }

                if (flag5 || par3 > 1 && renderer.blockAccess.isAirBlock(par2, par3 - 1, par4 - 1))
                {
                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d13, d6, d7);
                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d14, d6, d8);
                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d14, d5, d8);
                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d13, d5, d7);
//                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d14, d6, d7);
//                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d13, d6, d8);
//                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d13, d5, d8);
//                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d14, d5, d7);
                }
            }
            else if (!flag && flag1)
            {
//                tessellator.addVertexWithUV(d11, (double)(par3 + 1), d14, d1, d3);
//                tessellator.addVertexWithUV(d11, (double)(par3 + 0), d14, d1, d4);
//                tessellator.addVertexWithUV(d11, (double)(par3 + 0), d15, d2, d4);
//                tessellator.addVertexWithUV(d11, (double)(par3 + 1), d15, d2, d3);
                tessellator.addVertexWithUV(d11, (double)(par3 + 1), d15, d1, d3);
                tessellator.addVertexWithUV(d11, (double)(par3 + 0), d15, d1, d4);
                tessellator.addVertexWithUV(d11, (double)(par3 + 0), d14, d2, d4);
                tessellator.addVertexWithUV(d11, (double)(par3 + 1), d14, d2, d3);

                if (!flag3 && !flag2)
                {
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1), d14, d5, d7);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 0), d14, d5, d9);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 0), d14, d6, d9);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1), d14, d6, d7);
                    tessellator.addVertexWithUV(d16, (double)(par3 + 1), d14, d5, d7);
                    tessellator.addVertexWithUV(d16, (double)(par3 + 0), d14, d5, d9);
                    tessellator.addVertexWithUV(d17, (double)(par3 + 0), d14, d6, d9);
                    tessellator.addVertexWithUV(d17, (double)(par3 + 1), d14, d6, d7);
                }

                if (flag4 || par3 < l - 1 && renderer.blockAccess.isAirBlock(par2, par3 + 1, par4 + 1))
                {
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d14, d5, d8);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d15, d5, d9);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d15, d6, d9);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d14, d6, d8);
                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d15, d5, d8);
                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d14, d5, d9);
                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d14, d6, d9);
                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d15, d6, d8);
                }

                if (flag5 || par3 > 1 && renderer.blockAccess.isAirBlock(par2, par3 - 1, par4 + 1))
                {
//                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d14, d5, d8);
//                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d15, d5, d9);
//                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d15, d6, d9);
//                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d14, d6, d8);
                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d15, d5, d8);
                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d14, d5, d9);
                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d14, d6, d9);
                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d15, d6, d8);
                }
            }
        }
        else
        {
            tessellator.addVertexWithUV(d11, (double)(par3 + 1), d15, d0, d3);
            tessellator.addVertexWithUV(d11, (double)(par3 + 0), d15, d0, d4);
            tessellator.addVertexWithUV(d11, (double)(par3 + 0), d13, d2, d4);
            tessellator.addVertexWithUV(d11, (double)(par3 + 1), d13, d2, d3);
//            tessellator.addVertexWithUV(d11, (double)(par3 + 1), d13, d0, d3);
//            tessellator.addVertexWithUV(d11, (double)(par3 + 0), d13, d0, d4);
//            tessellator.addVertexWithUV(d11, (double)(par3 + 0), d15, d2, d4);
//            tessellator.addVertexWithUV(d11, (double)(par3 + 1), d15, d2, d3);

            if (flag4)
            {
                tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d15, d6, d9);
                tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d13, d6, d7);
                tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d13, d5, d7);
                tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d15, d5, d9);
//                tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d13, d6, d9);
//                tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d15, d6, d7);
//                tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d15, d5, d7);
//                tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d13, d5, d9);
            }
            else
            {
                if (par3 < l - 1 && renderer.blockAccess.isAirBlock(par2, par3 + 1, par4 - 1))
                {
                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d13, d6, d7);
                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d14, d6, d8);
                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d14, d5, d8);
                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d13, d5, d7);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d14, d6, d7);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d13, d6, d8);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d13, d5, d8);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d14, d5, d7);
                }

                if (par3 < l - 1 && renderer.blockAccess.isAirBlock(par2, par3 + 1, par4 + 1))
                {
                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d14, d5, d8);
                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d15, d5, d9);
                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d15, d6, d9);
                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d14, d6, d8);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d15, d5, d8);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d14, d5, d9);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d14, d6, d9);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d15, d6, d8);
                }
            }

            if (flag5)
            {
                tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d15, d6, d9);
                tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d13, d6, d7);
                tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d13, d5, d7);
                tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d15, d5, d9);
//                tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d13, d6, d9);
//                tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d15, d6, d7);
//                tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d15, d5, d7);
//                tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d13, d5, d9);
            }
            else
            {
                if (par3 > 1 && renderer.blockAccess.isAirBlock(par2, par3 - 1, par4 - 1))
                {
                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d13, d6, d7);
                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d14, d6, d8);
                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d14, d5, d8);
                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d13, d5, d7);
//                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d14, d6, d7);
//                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d13, d6, d8);
//                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d13, d5, d8);
//                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d14, d5, d7);
                }

                if (par3 > 1 && renderer.blockAccess.isAirBlock(par2, par3 - 1, par4 + 1))
                {
                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d14, d5, d8);
                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d15, d5, d9);
                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d15, d6, d9);
                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d14, d6, d8);
//                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d15, d5, d8);
//                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d14, d5, d9);
//                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d14, d6, d9);
//                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d15, d6, d8);
                }
            }
        }
        return true;
	}
}


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
			if (ModInfo.connectedTextures_actual) {	
				try {
					return ColoredGlassPaneRenderer.render(renderer, (ColoredGlassPaneBlock) block, x, y, z);
				}
				catch(Throwable e) {
					System.out.println("Oops, Colored Glass Panes Can't use CTM... Defaulting");
					ModInfo.connectedTextures_actual = false;
					return renderColoredGlassPane(world, x, y, z, (ColoredGlassPaneBlock) block, renderer);
				}
			} else {
				return renderColoredGlassPane(world, x, y, z, (ColoredGlassPaneBlock) block, renderer);
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
	private boolean renderColoredGlassPane(IBlockAccess world, int i, int j, int k, ColoredGlassPaneBlock blockPane, RenderBlocks renderer)
	{
		int l = renderer.blockAccess.getHeight();
        tessellator = Tessellator.instance;
        tessellator.setBrightness(blockPane.getMixedBrightnessForBlock(renderer.blockAccess, i, j, k));
        float f = 0.8F;
        int n = blockPane.colorMultiplier(renderer.blockAccess, i, j, k);
        float f1 = (float)(n >> 16 & 255) / 255.0F;
        float f2 = (float)(n >> 8 & 255) / 255.0F;
        float f3 = (float)(n & 255) / 255.0F;

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

        int metadata = renderer.blockAccess.getBlockMetadata(i, j, k);
        icon = blockPane.getIcon(0, metadata);
        icon1 = blockPane.getSideTextureFromMetadata(metadata);

        double u0 = (double)icon.getMinU();
        double uM = (double)icon.getInterpolatedU(8.0D);
        double u1 = (double)icon.getMaxU();
        double v0 = (double)icon.getMinV();
        double v1 = (double)icon.getMaxV();
        double d5 = (double)icon1.getInterpolatedU(7.0D);
        double d6 = (double)icon1.getInterpolatedU(9.0D);
        double d7 = (double)icon1.getMinV();
        double d8 = (double)icon1.getInterpolatedV(8.0D);
        double d9 = (double)icon1.getMaxV();
        double i0 = (double)i;
        double iM = (double)i + 0.5D;
        double i1 = (double)(i + 1);
        double j0 = (double)j;
        double j1 = (double)j + 1.0D;
        double k0= (double)k;
        double kM = (double)k + 0.5D;
        double k1 = (double)(k + 1);
        double d16 = (double)i + 0.5D - 0.0625D;
        double d17 = (double)i + 0.5D + 0.0625D;
        double d18 = (double)k + 0.5D - 0.0625D;
        double d19 = (double)k + 0.5D + 0.0625D;
        boolean connectNorth = blockPane.canPaneConnectTo(renderer.blockAccess,i, j, k, NORTH);
        boolean connectSouth = blockPane.canPaneConnectTo(renderer.blockAccess,i, j, k, SOUTH);
        boolean connectWest = blockPane.canPaneConnectTo(renderer.blockAccess,i, j, k, WEST);
        boolean connectEast = blockPane.canPaneConnectTo(renderer.blockAccess,i, j, k, EAST);
        boolean connectAny = connectWest || connectEast || connectNorth || connectSouth;
        boolean connectUp = blockPane.shouldSideBeRendered(renderer.blockAccess, i, j + 1, k, 1);
        boolean connectDown = blockPane.shouldSideBeRendered(renderer.blockAccess, i, j - 1, k, 0);
        double d20 = 0.01D;
        double d21 = 0.005D;

        if ((!connectWest || !connectEast) && connectAny) //Check for a Half West Pane OR a Half East Pane, AND that there is a connection
        {
            if (connectWest && !connectEast) //Half West Pane
            {
            	tessellator.addVertexWithUV(i0, j0, kM, uM, v1);
            	tessellator.addVertexWithUV(i0, j1, kM, uM, v0);
            	tessellator.addVertexWithUV(iM, j1, kM, u0, v0);
            	tessellator.addVertexWithUV(iM, j0, kM, u0, v1);
                
//                tessellator.addVertexWithUV(iM, j1, kM, u0, v0);
//                tessellator.addVertexWithUV(iM, j0, kM, u0, v1);
//                tessellator.addVertexWithUV(i0, j0, kM, uM, v1);
//                tessellator.addVertexWithUV(i0, j1, kM, uM, v0);

                if (!connectSouth && !connectNorth) //Check for ONLY a Half West Pane and render the edge in the middle
                {
                    tessellator.addVertexWithUV(iM, j1, d19, d5, d7);
                    tessellator.addVertexWithUV(iM, j0, d19, d5, d9);
                    tessellator.addVertexWithUV(iM, j0, d18, d6, d9);
                    tessellator.addVertexWithUV(iM, j1, d18, d6, d7);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1), d18, d5, d7);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 0), d18, d5, d9);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 0), d19, d6, d9);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1), d19, d6, d7);
                }

                if (connectUp || j < l - 1 && renderer.blockAccess.isAirBlock(i - 1, j + 1, k))	//Top Glass edge for Half West Pane
                {
                    tessellator.addVertexWithUV(i0, j1 + 0.01D, d19, d6, d8);
                    tessellator.addVertexWithUV(iM, j1 + 0.01D, d19, d6, d9);
                    tessellator.addVertexWithUV(iM, j1 + 0.01D, d18, d5, d9);
                    tessellator.addVertexWithUV(i0, j1 + 0.01D, d18, d5, d8);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d19, d6, d8);
//                    tessellator.addVertexWithUV(d10, (double)(par3 + 1) + 0.01D, d19, d6, d9);
//                    tessellator.addVertexWithUV(d10, (double)(par3 + 1) + 0.01D, d18, d5, d9);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d18, d5, d8);
                }

                if (connectDown || j > 1 && renderer.blockAccess.isAirBlock(i - 1, j - 1, k)) //Low Glass Edge for Half West Pane
                {
                    tessellator.addVertexWithUV(i0, (double)j - 0.01D, d19, d6, d8);
                    tessellator.addVertexWithUV(iM, (double)j - 0.01D, d19, d6, d9);
                    tessellator.addVertexWithUV(iM, (double)j - 0.01D, d18, d5, d9);
                    tessellator.addVertexWithUV(i0, (double)j - 0.01D, d18, d5, d8);
//                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d19, d6, d8);
//                    tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d19, d6, d9);
//                    tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d18, d5, d9);
//                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d18, d5, d8);
                }
            }
            else if (!connectWest && connectEast) //Half East Pane
            {
//                tessellator.addVertexWithUV(iM, j1, kM, uM, v0);
//                tessellator.addVertexWithUV(iM, j0, kM, uM, v1);
//                tessellator.addVertexWithUV(i1, j0, kM, u1, v1);
//                tessellator.addVertexWithUV(i1, j1, kM, u1, v0);
                tessellator.addVertexWithUV(i1, j0, kM, uM, v1);
                tessellator.addVertexWithUV(i1, j1, kM, uM, v0);
                tessellator.addVertexWithUV(iM, j1, kM, u1, v0);
                tessellator.addVertexWithUV(iM, j0, kM, u1, v1);

                if (!connectSouth && !connectNorth) //Check for ONLY a Half East Pane and render the edge in the middle
                {
                    tessellator.addVertexWithUV(iM, j1, d18, d5, d7);
                    tessellator.addVertexWithUV(iM, j0, d18, d5, d9);
                    tessellator.addVertexWithUV(iM, j0, d19, d6, d9);
                    tessellator.addVertexWithUV(iM, j1, d19, d6, d7);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1), d19, d5, d7);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 0), d19, d5, d9);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 0), d18, d6, d9);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1), d18, d6, d7);
                }

                if (connectUp || j < l - 1 && renderer.blockAccess.isAirBlock(i + 1, j + 1, k)) //Top East Edge for Half East Pane
                {
                    tessellator.addVertexWithUV(iM, j1 + 0.01D, d19, d6, d7);
                    tessellator.addVertexWithUV(i1, j1 + 0.01D, d19, d6, d8);
                    tessellator.addVertexWithUV(i1, j1 + 0.01D, d18, d5, d8);
                    tessellator.addVertexWithUV(iM, j1 + 0.01D, d18, d5, d7);
//                    tessellator.addVertexWithUV(d12, (double)(par3 + 1) + 0.01D, d19, d6, d7);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d19, d6, d8);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d18, d5, d8);
//                    tessellator.addVertexWithUV(d12, (double)(par3 + 1) + 0.01D, d18, d5, d7);
                }

                if (connectDown || j > 1 && renderer.blockAccess.isAirBlock(i + 1, j - 1, k)) //Low East Edge for Half East Pane
                {
                    tessellator.addVertexWithUV(iM, (double)j - 0.01D, d19, d6, d7);
                    tessellator.addVertexWithUV(i1, (double)j - 0.01D, d19, d6, d8);
                    tessellator.addVertexWithUV(i1, (double)j - 0.01D, d18, d5, d8);
                    tessellator.addVertexWithUV(iM, (double)j - 0.01D, d18, d5, d7);
//                    tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d19, d6, d7);
//                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d19, d6, d8);
//                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d18, d5, d8);
//                    tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d18, d5, d7);
                }
            }
        }
        else //Full East West Pane	
        {
            tessellator.addVertexWithUV(i0, j1, kM, u1, v0);
            tessellator.addVertexWithUV(i0, j0, kM, u1, v1);
            tessellator.addVertexWithUV(i1, j0, kM, u0, v1);
            tessellator.addVertexWithUV(i1, j1, kM, u0, v0);
//            tessellator.addVertexWithUV(i1, j1, kM, u0, v0);
//            tessellator.addVertexWithUV(i1, j0, kM, u0, v1);
//            tessellator.addVertexWithUV(i0, j0, kM, u1, v1);
//            tessellator.addVertexWithUV(i0, j1, kM, u1, v0);

            if (connectUp) //Full Top Edge
            {
                tessellator.addVertexWithUV(i0, j1 + 0.01D, d19, d6, d9);
                tessellator.addVertexWithUV(i1, j1 + 0.01D, d19, d6, d7);
                tessellator.addVertexWithUV(i1, j1 + 0.01D, d18, d5, d7);
                tessellator.addVertexWithUV(i0, j1 + 0.01D, d18, d5, d9);
                
//                tessellator.addVertexWithUV(d12, j1 + 0.01D, d19, d6, d9);
//                tessellator.addVertexWithUV(d10, j1 + 0.01D, d19, d6, d7);
//                tessellator.addVertexWithUV(d10, j1 + 0.01D, d18, d5, d7);
//                tessellator.addVertexWithUV(d12, j1 + 0.01D, d18, d5, d9);
            }
            else //Not a Full Top Edge
            {
                if (j < l - 1 && renderer.blockAccess.isAirBlock(i - 1, j + 1, k)) //West or East Half Top Edge
                {
                    tessellator.addVertexWithUV(i0, j1 + 0.01D, d19, d6, d8);
                    tessellator.addVertexWithUV(iM, j1 + 0.01D, d19, d6, d9);
                    tessellator.addVertexWithUV(iM, j1 + 0.01D, d18, d5, d9);
                    tessellator.addVertexWithUV(i0, j1 + 0.01D, d18, d5, d8);
                    
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d19, d6, d8);
//                    tessellator.addVertexWithUV(d10, (double)(par3 + 1) + 0.01D, d19, d6, d9);
//                    tessellator.addVertexWithUV(d10, (double)(par3 + 1) + 0.01D, d18, d5, d9);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d18, d5, d8);
                }

                if (j < l - 1 && renderer.blockAccess.isAirBlock(i + 1, j + 1, k)) //The other Half Top Edge for West/East
                {
                    tessellator.addVertexWithUV(iM, j1 + 0.01D, d19, d6, d7);
                    tessellator.addVertexWithUV(i1, j1 + 0.01D, d19, d6, d8);
                    tessellator.addVertexWithUV(i1, j1 + 0.01D, d18, d5, d8);
                    tessellator.addVertexWithUV(iM, j1 + 0.01D, d18, d5, d7);
//                    tessellator.addVertexWithUV(d12, (double)(par3 + 1) + 0.01D, d19, d6, d7);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d19, d6, d8);
//                    tessellator.addVertexWithUV(d11, (double)(par3 + 1) + 0.01D, d18, d5, d8);
//                    tessellator.addVertexWithUV(d12, (double)(par3 + 1) + 0.01D, d18, d5, d7);
                }
            }

            if (connectDown) //Full Low Edge
            {
                tessellator.addVertexWithUV(i0, (double)j - 0.01D, d19, d6, d9);
                tessellator.addVertexWithUV(i1, (double)j - 0.01D, d19, d6, d7);
                tessellator.addVertexWithUV(i1, (double)j - 0.01D, d18, d5, d7);
                tessellator.addVertexWithUV(i0, (double)j - 0.01D, d18, d5, d9);
//                tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d19, d6, d9);
//                tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d19, d6, d7);
//                tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d18, d5, d7);
//                tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d18, d5, d9);
            }
            else
            {
                if (j > 1 && renderer.blockAccess.isAirBlock(i - 1, j - 1, k)) //West or East Half Low Edge
                {
                    tessellator.addVertexWithUV(i0, (double)j - 0.01D, d19, d6, d8);
                    tessellator.addVertexWithUV(iM, (double)j - 0.01D, d19, d6, d9);
                    tessellator.addVertexWithUV(iM, (double)j - 0.01D, d18, d5, d9);
                    tessellator.addVertexWithUV(i0, (double)j - 0.01D, d18, d5, d8);
//                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d19, d6, d8);
//                    tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d19, d6, d9);
//                    tessellator.addVertexWithUV(d10, (double)par3 - 0.01D, d18, d5, d9);
//                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d18, d5, d8);
                }

                if (j > 1 && renderer.blockAccess.isAirBlock(i + 1, j - 1, k)) //The other Half Low Edge for West/East
                {
                    tessellator.addVertexWithUV(iM, (double)j - 0.01D, d19, d6, d7);
                    tessellator.addVertexWithUV(i1, (double)j - 0.01D, d19, d6, d8);
                    tessellator.addVertexWithUV(i1, (double)j - 0.01D, d18, d5, d8);
                    tessellator.addVertexWithUV(iM, (double)j - 0.01D, d18, d5, d7);
//                    tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d19, d6, d7);
//                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d19, d6, d8);
//                    tessellator.addVertexWithUV(d11, (double)par3 - 0.01D, d18, d5, d8);
//                    tessellator.addVertexWithUV(d12, (double)par3 - 0.01D, d18, d5, d7);
                }
            }
        }

        if ((!connectNorth || !connectSouth) && connectAny) //Check for a Half West Pane OR a Half East Pane, AND that there is a connection
        {
            if (connectNorth && !connectSouth) //Half North Pane
            {
                tessellator.addVertexWithUV(iM, j1, kM, u0, v0);
                tessellator.addVertexWithUV(iM, j0, kM, u0, v1);
                tessellator.addVertexWithUV(iM, j0, k0, uM, v1);
                tessellator.addVertexWithUV(iM, j1, k0, uM, v0);
//                tessellator.addVertexWithUV(iM, j1, kM, u0, v0);
//                tessellator.addVertexWithUV(iM, j0, kM, u0, v1);
//                tessellator.addVertexWithUV(iM, j0, k0, uM, v1);
//                tessellator.addVertexWithUV(iM, j1, k0, uM, v0);

                if (!connectEast && !connectWest) //Check for ONLY a Half North Pane and render the edge in the middle
                {
                    tessellator.addVertexWithUV(d16, j1, kM, d5, d7);
                    tessellator.addVertexWithUV(d16, j0, kM, d5, d9);
                    tessellator.addVertexWithUV(d17, j0, kM, d6, d9);
                    tessellator.addVertexWithUV(d17, j1, kM, d6, d7);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1), d14, d5, d7);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 0), d14, d5, d9);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 0), d14, d6, d9);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1), d14, d6, d7);
                }

                if (connectUp || j < l - 1 && renderer.blockAccess.isAirBlock(i, j + 1, k - 1)) //Top North Edge for Half East Pane
                {
                    tessellator.addVertexWithUV(d16, j1 + 0.005D, k0, d6, d7);
                    tessellator.addVertexWithUV(d16, j1 + 0.005D, kM, d6, d8);
                    tessellator.addVertexWithUV(d17, j1 + 0.005D, kM, d5, d8);
                    tessellator.addVertexWithUV(d17, j1 + 0.005D, k0, d5, d7);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d14, d6, d7);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d13, d6, d8);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d13, d5, d8);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d14, d5, d7);
                }

                if (connectDown || j > 1 && renderer.blockAccess.isAirBlock(i, j - 1, k - 1)) //Low North Edge for Half East Pane
                {
                    tessellator.addVertexWithUV(d16, (double)j - 0.005D, k0, d6, d7);
                    tessellator.addVertexWithUV(d16, (double)j - 0.005D, kM, d6, d8);
                    tessellator.addVertexWithUV(d17, (double)j - 0.005D, kM, d5, d8);
                    tessellator.addVertexWithUV(d17, (double)j - 0.005D, k0, d5, d7);
//                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d14, d6, d7);
//                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d13, d6, d8);
//                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d13, d5, d8);
//                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d14, d5, d7);
                }
            }
            else if (!connectNorth && connectSouth) //Half South Pane
            {
//                tessellator.addVertexWithUV(d11, (double)(par3 + 1), d14, d1, d3);
//                tessellator.addVertexWithUV(d11, (double)(par3 + 0), d14, d1, d4);
//                tessellator.addVertexWithUV(d11, (double)(par3 + 0), d15, d2, d4);
//                tessellator.addVertexWithUV(d11, (double)(par3 + 1), d15, d2, d3);
                tessellator.addVertexWithUV(iM, j1, k1, uM, v0);
                tessellator.addVertexWithUV(iM, j0, k1, uM, v1);
                tessellator.addVertexWithUV(iM, j0, kM, u1, v1);
                tessellator.addVertexWithUV(iM, j1, kM, u1, v0);

                if (!connectEast && !connectWest) //Check for ONLY a Half South Pane and render the edge in the middle
                {
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1), d14, d5, d7);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 0), d14, d5, d9);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 0), d14, d6, d9);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1), d14, d6, d7);
                    tessellator.addVertexWithUV(d16, j1, kM, d5, d7);
                    tessellator.addVertexWithUV(d16, j0, kM, d5, d9);
                    tessellator.addVertexWithUV(d17, j0, kM, d6, d9);
                    tessellator.addVertexWithUV(d17, j1, kM, d6, d7);
                }

                if (connectUp || j < l - 1 && renderer.blockAccess.isAirBlock(i, j + 1, k + 1)) //Top South Edge for Half East Pane
                {
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d14, d5, d8);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d15, d5, d9);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d15, d6, d9);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d14, d6, d8);
                    tessellator.addVertexWithUV(d16, j1 + 0.005D, k1, d5, d8);
                    tessellator.addVertexWithUV(d16, j1 + 0.005D, kM, d5, d9);
                    tessellator.addVertexWithUV(d17, j1 + 0.005D, kM, d6, d9);
                    tessellator.addVertexWithUV(d17, j1 + 0.005D, k1, d6, d8);
                }

                if (connectDown || j > 1 && renderer.blockAccess.isAirBlock(i, j - 1, k + 1)) //Low South Edge for Half East Pane
                {
//                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d14, d5, d8);
//                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d15, d5, d9);
//                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d15, d6, d9);
//                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d14, d6, d8);
                    tessellator.addVertexWithUV(d16, (double)j - 0.005D, k1, d5, d8);
                    tessellator.addVertexWithUV(d16, (double)j - 0.005D, kM, d5, d9);
                    tessellator.addVertexWithUV(d17, (double)j - 0.005D, kM, d6, d9);
                    tessellator.addVertexWithUV(d17, (double)j - 0.005D, k1, d6, d8);
                }
            }
        }
        else //Full North South Pane
        {
            tessellator.addVertexWithUV(iM, j1, k1, u0, v0);
            tessellator.addVertexWithUV(iM, j0, k1, u0, v1);
            tessellator.addVertexWithUV(iM, j0, k0, u1, v1);
            tessellator.addVertexWithUV(iM, j1, k0, u1, v0);
//            tessellator.addVertexWithUV(d11, (double)(par3 + 1), d13, d0, d3);
//            tessellator.addVertexWithUV(d11, (double)(par3 + 0), d13, d0, d4);
//            tessellator.addVertexWithUV(d11, (double)(par3 + 0), d15, d2, d4);
//            tessellator.addVertexWithUV(d11, (double)(par3 + 1), d15, d2, d3);

            if (connectUp) //Full Top Edge
            {
                tessellator.addVertexWithUV(d17, j1 + 0.005D, k1, d6, d9);
                tessellator.addVertexWithUV(d17, j1 + 0.005D, k0, d6, d7);
                tessellator.addVertexWithUV(d16, j1 + 0.005D, k0, d5, d7);
                tessellator.addVertexWithUV(d16, j1 + 0.005D, k1, d5, d9);
//                tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d13, d6, d9);
//                tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d15, d6, d7);
//                tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d15, d5, d7);
//                tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d13, d5, d9);
            }
            else //Not a Full Top Edge
            {
                if (j < l - 1 && renderer.blockAccess.isAirBlock(i, j + 1, k - 1)) //North or South Half Top Edge
                {
                    tessellator.addVertexWithUV(d16, j1 + 0.005D, k0, d6, d7);
                    tessellator.addVertexWithUV(d16, j1 + 0.005D, kM, d6, d8);
                    tessellator.addVertexWithUV(d17, j1 + 0.005D, kM, d5, d8);
                    tessellator.addVertexWithUV(d17, j1 + 0.005D, k0, d5, d7);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d14, d6, d7);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d13, d6, d8);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d13, d5, d8);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d14, d5, d7);
                }

                if (j < l - 1 && renderer.blockAccess.isAirBlock(i, j + 1, k + 1)) //The other Half Top Edge for North/South
                {
                    tessellator.addVertexWithUV(d16, j1 + 0.005D, kM, d5, d8);
                    tessellator.addVertexWithUV(d16, j1 + 0.005D, k1, d5, d9);
                    tessellator.addVertexWithUV(d17, j1 + 0.005D, k1, d6, d9);
                    tessellator.addVertexWithUV(d17, j1 + 0.005D, kM, d6, d8);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d15, d5, d8);
//                    tessellator.addVertexWithUV(d16, (double)(par3 + 1) + 0.005D, d14, d5, d9);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d14, d6, d9);
//                    tessellator.addVertexWithUV(d17, (double)(par3 + 1) + 0.005D, d15, d6, d8);
                }
            }

            if (connectDown) //Full Low Edge
            {
                tessellator.addVertexWithUV(d17, (double)j - 0.005D, k1, d6, d9);
                tessellator.addVertexWithUV(d17, (double)j - 0.005D, k0, d6, d7);
                tessellator.addVertexWithUV(d16, (double)j - 0.005D, k0, d5, d7);
                tessellator.addVertexWithUV(d16, (double)j - 0.005D, k1, d5, d9);
//                tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d13, d6, d9);
//                tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d15, d6, d7);
//                tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d15, d5, d7);
//                tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d13, d5, d9);
            }
            else //Not a Full Low Edge
            {
                if (j > 1 && renderer.blockAccess.isAirBlock(i, j - 1, k - 1)) //North or South Half Low Edge
                {
                    tessellator.addVertexWithUV(d16, (double)j - 0.005D, k0, d6, d7);
                    tessellator.addVertexWithUV(d16, (double)j - 0.005D, kM, d6, d8);
                    tessellator.addVertexWithUV(d17, (double)j - 0.005D, kM, d5, d8);
                    tessellator.addVertexWithUV(d17, (double)j - 0.005D, k0, d5, d7);
//                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d14, d6, d7);
//                    tessellator.addVertexWithUV(d16, (double)par3 - 0.005D, d13, d6, d8);
//                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d13, d5, d8);
//                    tessellator.addVertexWithUV(d17, (double)par3 - 0.005D, d14, d5, d7);
                }

                if (j > 1 && renderer.blockAccess.isAirBlock(i, j - 1, k + 1)) //The other Half Low Edge for North/South
                {
                    tessellator.addVertexWithUV(d16, (double)j - 0.005D, kM, d5, d8);
                    tessellator.addVertexWithUV(d16, (double)j - 0.005D, k1, d5, d9);
                    tessellator.addVertexWithUV(d17, (double)j - 0.005D, k1, d6, d9);
                    tessellator.addVertexWithUV(d17, (double)j - 0.005D, kM, d6, d8);
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


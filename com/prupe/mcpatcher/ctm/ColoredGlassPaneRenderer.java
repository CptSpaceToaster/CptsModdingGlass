package com.prupe.mcpatcher.ctm;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;

import com.prupe.mcpatcher.Config;
import com.prupe.mcpatcher.MCPatcherUtils;
import com.prupe.mcpatcher.TessellatorUtils;
import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;

import java.util.Arrays;

import yamhaven.easycoloredglass.blocks.ColoredGlassPaneBlock;

public class ColoredGlassPaneRenderer {
    public static boolean active;

    private static final Icon[] icons = new Icon[6];
    private static Tessellator tessellator;

    private static double u0; // left edge
    private static double uM; // left-right midpoint 
    private static double u1; // right edge
    private static double v0; // top edge
    private static double v1; // bottom edge

    public static boolean render(RenderBlocks renderer, ColoredGlassPaneBlock blockPane, int i, int j, int k) {
    	tessellator = Tessellator.instance;
    	tessellator.setBrightness(blockPane.getMixedBrightnessForBlock(renderer.blockAccess, i, j, k));
        float f = 1.0F;
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
    	
    	
    	
    	boolean connectNorth = blockPane.canPaneConnectTo(renderer.blockAccess,i, j, k, NORTH);
        boolean connectSouth = blockPane.canPaneConnectTo(renderer.blockAccess,i, j, k, SOUTH);
        boolean connectWest = blockPane.canPaneConnectTo(renderer.blockAccess,i, j, k, WEST);
        boolean connectEast = blockPane.canPaneConnectTo(renderer.blockAccess,i, j, k, EAST);
    	
        active = true;
        for (int face = TileOverride.NORTH_FACE; face <= TileOverride.EAST_FACE; face++) {
        	
        	int metadata = renderer.blockAccess.getBlockMetadata(i, j, k);
            Icon icon = blockPane.getIcon(0, metadata);
            Icon icon1 = blockPane.getSideTextureFromMetadata(metadata);
            
            
            icons[face] = CTMUtils.getTile(renderer, blockPane, i, j, k, face, icon, Tessellator.instance);
            if (icons[face] == null) {
                active = RenderPassAPI.instance.skipDefaultRendering(blockPane);
                return false;
            }
        }

        final double i0 = i;
        final double iM = i0 + 0.5;
        final double i1 = i0 + 1.0;
        final double j0 = j;
        final double j1 = j0 + 1.0;
        final double k0 = k;
        final double kM = k0 + 0.5;
        final double k1 = k0 + 1.0;

        final boolean connectAny = connectWest || connectEast || connectNorth || connectSouth;

        if ((connectEast && connectWest) || !connectAny) {
            // full west-east pane
            setupTileCoords(TileOverride.SOUTH_FACE);
            tessellator.addVertexWithUV(i0, j1, kM, u0, v0);
            tessellator.addVertexWithUV(i0, j0, kM, u0, v1);
            tessellator.addVertexWithUV(i1, j0, kM, u1, v1);
            tessellator.addVertexWithUV(i1, j1, kM, u1, v0);

//            setupTileCoords(TileOverride.NORTH_FACE);
//            tessellator.addVertexWithUV(i1, j1, kM, u0, v0);
//            tessellator.addVertexWithUV(i1, j0, kM, u0, v1);
//            tessellator.addVertexWithUV(i0, j0, kM, u1, v1);
//            tessellator.addVertexWithUV(i0, j1, kM, u1, v0);
        } else if (connectWest && !connectEast) {
            // west half-pane
            setupTileCoords(TileOverride.SOUTH_FACE);
            tessellator.addVertexWithUV(i0, j1, kM, uM, v0);
            tessellator.addVertexWithUV(i0, j0, kM, uM, v1);
            tessellator.addVertexWithUV(iM, j0, kM, u1, v1);
            tessellator.addVertexWithUV(iM, j1, kM, u1, v0);

//            setupTileCoords(TileOverride.NORTH_FACE);
//            tessellator.addVertexWithUV(iM, j1, kM, u0, v0);
//            tessellator.addVertexWithUV(iM, j0, kM, u0, v1);
//            tessellator.addVertexWithUV(i0, j0, kM, uM, v1);
//            tessellator.addVertexWithUV(i0, j1, kM, uM, v0);
        } else if (!connectWest && connectEast) {
            // east half-pane
            setupTileCoords(TileOverride.SOUTH_FACE);
            tessellator.addVertexWithUV(iM, j1, kM, u0, v0);
            tessellator.addVertexWithUV(iM, j0, kM, u0, v1);
            tessellator.addVertexWithUV(i1, j0, kM, uM, v1);
            tessellator.addVertexWithUV(i1, j1, kM, uM, v0);

//            setupTileCoords(TileOverride.NORTH_FACE);
//            tessellator.addVertexWithUV(i1, j1, kM, uM, v0);
//            tessellator.addVertexWithUV(i1, j0, kM, uM, v1);
//            tessellator.addVertexWithUV(iM, j0, kM, u1, v1);
//            tessellator.addVertexWithUV(iM, j1, kM, u1, v0);
        }

        if ((connectNorth && connectSouth) || !connectAny) {
            // full north-south pane
            setupTileCoords(TileOverride.WEST_FACE);
            tessellator.addVertexWithUV(iM, j1, k0, u0, v0);
            tessellator.addVertexWithUV(iM, j0, k0, u0, v1);
            tessellator.addVertexWithUV(iM, j0, k1, u1, v1);
            tessellator.addVertexWithUV(iM, j1, k1, u1, v0);

//            setupTileCoords(TileOverride.EAST_FACE);
//            tessellator.addVertexWithUV(iM, j1, k1, u0, v0);
//            tessellator.addVertexWithUV(iM, j0, k1, u0, v1);
//            tessellator.addVertexWithUV(iM, j0, k0, u1, v1);
//            tessellator.addVertexWithUV(iM, j1, k0, u1, v0);
        } else if (connectNorth && !connectSouth) {
            // north half-pane
            setupTileCoords(TileOverride.WEST_FACE);
            tessellator.addVertexWithUV(iM, j1, k0, uM, v0);
            tessellator.addVertexWithUV(iM, j0, k0, uM, v1);
            tessellator.addVertexWithUV(iM, j0, kM, u1, v1);
            tessellator.addVertexWithUV(iM, j1, kM, u1, v0);

//            setupTileCoords(TileOverride.EAST_FACE);
//            tessellator.addVertexWithUV(iM, j1, kM, u0, v0);
//            tessellator.addVertexWithUV(iM, j0, kM, u0, v1);
//            tessellator.addVertexWithUV(iM, j0, k0, uM, v1);
//            tessellator.addVertexWithUV(iM, j1, k0, uM, v0);
        } else if (!connectNorth && connectSouth) {
            // south half-pane
            setupTileCoords(TileOverride.WEST_FACE);
            tessellator.addVertexWithUV(iM, j1, kM, u0, v0);
            tessellator.addVertexWithUV(iM, j0, kM, u0, v1);
            tessellator.addVertexWithUV(iM, j0, k1, uM, v1);
            tessellator.addVertexWithUV(iM, j1, k1, uM, v0);

//            setupTileCoords(TileOverride.EAST_FACE);
//            tessellator.addVertexWithUV(iM, j1, k1, uM, v0);
//            tessellator.addVertexWithUV(iM, j0, k1, uM, v1);
//            tessellator.addVertexWithUV(iM, j0, kM, u1, v1);
//            tessellator.addVertexWithUV(iM, j1, kM, u1, v0);
        }

        Arrays.fill(icons, null);
        tessellator = null;
        
        return true;
    }

    private static void setupTileCoords(int face) {
        Icon icon = icons[face];
        tessellator = TessellatorUtils.getTessellator(Tessellator.instance, icons[face]);
        u0 = icon.getMinU();
        uM = icon.getInterpolatedU(8.0);
        u1 = icon.getMaxU();
        v0 = icon.getMinV();
        v1 = icon.getMaxV();
    }
}
package yamhaven.easycoloredglass.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import yamhaven.easycoloredglass.lib.BlockIds;
import yamhaven.easycoloredglass.lib.BlockInfo;
import yamhaven.easycoloredglass.lib.ModInfo;
import yamhaven.easycoloredglass.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ColoredGlassPaneBlock extends BlockPane {
    /**
     * Holds the texture index of the side of the pane (the thin lateral side)
     */
    //private final String faceTextureIndex;

    /**
     * If this field is true, the pane block drops itself when destroyed (like the iron fences), otherwise, it's just
     * destroyed (like glass panes)
     */
    private final boolean canDropItself;
    //private final String edgeTextureIndex;
    
    @SideOnly(Side.CLIENT)
    private Icon[] faceIcons;
    @SideOnly(Side.CLIENT)
	private Icon[] edgeIcons;
    
    protected ColoredGlassPaneBlock(int par1) {
        super(par1, "coloredGlass", "coloredGlassPane", Material.glass, false);
        canDropItself = false;
        
        setHardness(0.3F);
        setStepSound(Block.soundGlassFootstep);
        setCreativeTab(CreativeTabs.tabDecorations);
        
        Block.opaqueCubeLookup[par1] = true;
        Block.opaqueCubeLookup[Block.thinGlass.blockID] = true;
        Block.opaqueCubeLookup[BlockIds.coloredGlassBlockID_actual] = true;
	}
    
    @SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		//blockIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + BlockInfo.coloredGlassBlock_unlocalizedName);
		
		faceIcons = new Icon[16];
		edgeIcons = new Icon[16];
		for(int i = 0; i<faceIcons.length; i++) {
			faceIcons[i] =  icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + BlockInfo.coloredGlassBlock_unlocalizedName + i);
			edgeIcons[i] =  icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + BlockInfo.coloredGlassPaneBlock_unlocalizedName + i);
		}
	}
    
    @SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return faceIcons[par2];
	}
    
    @SideOnly(Side.CLIENT)
    public Icon getSideTextureFromMetadata(int metadata)
    {
        return edgeIcons[metadata];
    }
    
    @SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i<16; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}
	
	public int quantityDropped(Random par1Random) {
        return 0;
    }
	
	public int damageDropped(int par1) {
		return par1;
	}
	
	public int getRenderBlockPass() {
        return 1;
    }
	
	public boolean isOpaqueCube() {
        return false;
    }
	
	public boolean renderAsNormalBlock() {
        return false;
    }
	
	public int getRenderType()
    {
        return ClientProxy.RenderColoredGlassPaneBlockID;
    }
	
    
}

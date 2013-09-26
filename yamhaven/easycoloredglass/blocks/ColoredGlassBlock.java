package yamhaven.easycoloredglass.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import yamhaven.easycoloredglass.lib.BlockInfo;
import yamhaven.easycoloredglass.lib.ModInfo;
import yamhaven.easycoloredglass.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ColoredGlassBlock extends Block {

	public ColoredGlassBlock(int id) {
		super(id, Material.glass);
		setUnlocalizedName(BlockInfo.coloredGlassBlock_unlocalizedName);
		setHardness(0.3F);
		setStepSound(Block.soundGlassFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		icons = new Icon[16];
		for(int i = 0; i<icons.length; i++) {
			icons[i] =  icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + BlockInfo.coloredGlassBlock_unlocalizedName + i);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return icons[par2];
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i<icons.length; i++) {
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
	
	protected boolean canSilkHarvest() {
        return true;
    }
}

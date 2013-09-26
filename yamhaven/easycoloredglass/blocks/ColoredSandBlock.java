package yamhaven.easycoloredglass.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import yamhaven.easycoloredglass.lib.BlockInfo;
import yamhaven.easycoloredglass.lib.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ColoredSandBlock extends BlockSand {

	public static boolean fallInstantly = false;
	
	public ColoredSandBlock(int id) {
		super(id, Material.sand);
		setUnlocalizedName(BlockInfo.coloredSandBlock_unlocalizedName);
		setHardness(0.5F);
		setStepSound(Block.soundSandFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		//blockIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + BlockInfo.coloredGlassBlock_unlocalizedName);
		
		icons = new Icon[16];
		for(int i = 0; i<icons.length; i++) {
			icons[i] =  icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + BlockInfo.coloredSandBlock_unlocalizedName + i);
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
	
	public int damageDropped(int par1) {
		return par1;
	}
}

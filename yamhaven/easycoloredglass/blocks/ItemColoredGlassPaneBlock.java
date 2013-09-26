package yamhaven.easycoloredglass.blocks;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemColoredGlassPaneBlock extends ItemBlock {

	public ItemColoredGlassPaneBlock(int par1) {
		super(par1);
		setHasSubtypes(true);
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + itemstack.getItemDamage();
	}
	
	public int getMetadata(int par1) {
		return par1;
	}
	
	public Icon getIconFromDamage(int metadata) {
		//Hooray, I don't have to make my own item textures as well!
		return Blocks.coloredGlassPaneBlock.getIcon(0, metadata);
	}
}

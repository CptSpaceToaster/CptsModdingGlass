package yamhaven.easycoloredglass.blocks;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemColoredSandBlock extends ItemBlock {

	public ItemColoredSandBlock(int par1) {
		super(par1);
		setHasSubtypes(true);
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + itemstack.getItemDamage();
	}
	
	public int getMetadata(int par1) {
		return par1;
	}
}
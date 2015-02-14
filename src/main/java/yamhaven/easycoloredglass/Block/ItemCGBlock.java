package yamhaven.easycoloredglass.Block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemCGBlock extends ItemBlock {

    public ItemCGBlock(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return getUnlocalizedName() + itemstack.getItemDamage();
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
    }
}
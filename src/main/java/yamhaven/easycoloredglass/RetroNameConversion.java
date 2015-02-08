package yamhaven.easycoloredglass;

import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class RetroNameConversion {
    // List of names that should be converted
    static final private HashMap<String, String> namingConversion = new HashMap<String, String>();

    // List of modID's that the will be considered by this class.
    static final private HashSet<String> modIDs = new HashSet<String>();

    public static class NameConversions {
        public final static int MAX = 1;

        protected static String conversion(String oldname, int step) {
            switch (step) {
                //TODO:
                /* Conversion strategies.  Currently, this is simple enough, that we only need to lookup an oldname->newname using the map */
                case 0:
                    return namingConversion.get(oldname);
                default:
                    throw new IndexOutOfBoundsException("conversion got invalid parameter step=" + step);
            }
        }
    }

    static public void init() {
        modIDs.add("easycoloredglass");
        namingConversion.put("easycoloredglasscoloredSandBlock", "easycoloredglasscoloredSand");
    }

    public static Item findItem(String oldname) {
        oldname = cleanModTag(oldname);
        EasyColoredGlass.CGLog.trace("findItem() START " + oldname);
        Item item = null;
        for (int i = 0; i < NameConversions.MAX && item == null; i++) {
            EasyColoredGlass.CGLog.trace("findItem() Checking for " + NameConversions.conversion(oldname, i));
            item = GameRegistry.findItem(EasyColoredGlass.MOD_ID, NameConversions.conversion(oldname, i));
        }

        return item;
    }

    public static Block findBlock(String oldname) {
        oldname = cleanModTag(oldname);
        EasyColoredGlass.CGLog.trace("findBlock() START: " + oldname);
        Block block = null;
        for (int i = 0; i < NameConversions.MAX && block == null; i++) {
            EasyColoredGlass.CGLog.trace("findBlock() Checking for " + NameConversions.conversion(oldname, i));
            block = GameRegistry.findBlock(EasyColoredGlass.MOD_ID, NameConversions.conversion(oldname, i));
        }

        return block;
    }

    /* Cleans a block ID - modID:blockname - to read as - blockname */
    private static String cleanModTag(String in) {
        int col = in.indexOf(':');

        // We found the colon character!
        if (col >= 0) {
            // Check to see if we should pay attention to this modID
            if (modIDs.contains(in.substring(0, col))) {
                // Return a substring with the tag removed
                return in.substring(col + 1);
            }
        }

        // mod tag doesn't exist
        return in;
    }
}
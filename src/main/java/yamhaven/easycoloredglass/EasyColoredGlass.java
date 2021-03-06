package yamhaven.easycoloredglass;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import yamhaven.easycoloredglass.Block.ECGBlocks;

@Mod(modid = EasyColoredGlass.MOD_ID, version = EasyColoredGlass.VERSION)
public class EasyColoredGlass
{
    public static final String MOD_ID = "easycoloredglass";
    public static final String MOD_NAME = "Easy Colored Glass";
    public static final String VERSION = "1.0.1";
    public static final Logger CGLog = LogManager.getLogger(MOD_NAME);

    @Mod.Instance(MOD_ID)
    public static EasyColoredGlass instance;

    @SidedProxy(clientSide = "yamhaven.easycoloredglass.ClientProxy", serverSide = "yamhaven.easycoloredglass.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void missingMapping(FMLMissingMappingsEvent event) {
        RetroNameConversion.init();

        for (FMLMissingMappingsEvent.MissingMapping m : event.get()) {
            if (m.type == GameRegistry.Type.BLOCK) {
                final Block block = RetroNameConversion.findBlock(m.name);

                if (block != null) {
                    m.remap(block);
                    CGLog.info("Remapping block " + m.name + " to " + block.getUnlocalizedName());
                } else {
                    CGLog.warn("Block " + m.name + " could not get remapped.");
                }
            }
            if (m.type == GameRegistry.Type.ITEM) {
                final Item item = RetroNameConversion.findItem(m.name);

                if (item != null) {
                    m.remap(item);
                    CGLog.info("Remapping item " + m.name + " to " + item.getUnlocalizedName());
                } else {
                    CGLog.warn("Item " + m.name + " could not get remapped.");
                }
            }
        }
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ECGBlocks.addBlocks();
        //TODO:
        /*
        File configFile = event.getSuggestedConfigurationFile();
        Configurations.configExists = configFile.exists();
        Configurations.config = new Configuration(configFile);
        Configurations.config.load();
        Configurations.refreshConfig();
        */
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // Add Recipes
        ECGBlocks.addRecipes();
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        //NOP
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        //TODO:
        /*if (event.modID.equals("mod")) {
            Configurations.refreshConfig();
        }*/
    }
}

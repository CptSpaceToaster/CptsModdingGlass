package yamhaven.easycoloredglass;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;

@Mod(modid = EasyColoredGlass.MOD_ID, version = EasyColoredGlass.VERSION)
public class EasyColoredGlass
{
    public static Block coloredSand;
    public static String coloredSandName = "coloredSand";

    public static final String MOD_ID = "easycoloredglass";
    public static final String MOD_NAME = "Easy Colored Glass";
    public static final String VERSION = "1.0.0";
    public static final Logger CGLog = LogManager.getLogger(MOD_NAME);

    @Mod.Instance(MOD_ID)
    public static EasyColoredGlass instance;

    @SidedProxy(clientSide = "yamhaven.easycoloredglass.ClientProxy", serverSide = "yamhaven.easycoloredglass.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        EasyColoredGlass.coloredSand = new BlockColoredSand().setHardness(0.5F).setStepSound(Block.soundTypeSand).setBlockName(EasyColoredGlass.coloredSandName);
        GameRegistry.registerBlock(EasyColoredGlass.coloredSand, ItemCGBlock.class, EasyColoredGlass.MOD_ID + EasyColoredGlass.coloredSandName);
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
    public void init(FMLInitializationEvent event)
    {
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

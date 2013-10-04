package yamhaven.easycoloredglass;

import yamhaven.easycoloredglass.blocks.Blocks;
import yamhaven.easycoloredglass.lib.ConfigHandler;
import yamhaven.easycoloredglass.lib.ModInfo;
import yamhaven.easycoloredglass.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
@NetworkMod (channels = {ModInfo.CHANNEL}, clientSideRequired = true, serverSideRequired = true)

public class EasyColoredGlass {
	@SidedProxy( clientSide = ModInfo.PROXY_LOCATION + ".ClientProxy", serverSide = ModInfo.PROXY_LOCATION + ".CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		//proxy.initRenderers();
		Blocks.init();
	}
	
	//Hi, this is auto! :D
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		Blocks.addNames();
		Blocks.addBlockRecipes();
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		
	}
}

package yamhaven.easycoloredglass.proxy;

import yamhaven.easycoloredglass.render.RenderColoredGlassPaneBlock;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	public static int RenderColoredGlassPaneBlockID;
	
	@Override
	public void initRenderers() {
		//RenderColoredGlassPaneBlockID = RenderingRegistry.getNextAvailableRenderId();
		//RenderingRegistry.instance().registerBlockHandler(new RenderColoredGlassPaneBlock());
	}
	
	@Override
	public void initSounds() {
	
	}
}
package yamhaven.easycoloredglass.lib;

import java.io.File;
import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	public static void init(File configFile) {
		Configuration config = new Configuration(configFile);
		
		final String BOOLEANS = "booleans";
		
		config.load();
		ModInfo.coloredSandBlockID_actual = config.getBlock(BlockInfo.coloredSandBlock_name, ModInfo.coloredSandBlockID_default).getInt();
		ModInfo.coloredGlassBlockID_actual = config.getBlock(BlockInfo.coloredGlassBlock_name, ModInfo.coloredGlassBlockID_default).getInt();
		ModInfo.coloredGlassPaneBlockID_actual = config.getBlock(BlockInfo.coloredGlassPaneBlock_name, ModInfo.coloredGlassPaneBlockID_default).getInt();
		
		ModInfo.easyRecipes_actual = config.get(BOOLEANS, "Easy Recipes", ModInfo.easyRecipes_default).getBoolean(ModInfo.easyRecipes_default);
		ModInfo.connectedTextures_actual = ModInfo.connectedTextures_default;
		config.save();

	}
}
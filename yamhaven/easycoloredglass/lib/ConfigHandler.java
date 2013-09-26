package yamhaven.easycoloredglass.lib;

import java.io.File;
import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	public static void init(File configFile) {
		Configuration config = new Configuration(configFile);
		config.load();
		BlockIds.coloredSandBlockID_actual = config.getBlock(BlockInfo.coloredSandBlock_name, BlockIds.coloredSandBlockID_default).getInt();
		BlockIds.coloredGlassBlockID_actual = config.getBlock(BlockInfo.coloredGlassBlock_name, BlockIds.coloredGlassBlockID_default).getInt();
		BlockIds.coloredGlassPaneBlockID_actual = config.getBlock(BlockInfo.coloredGlassPaneBlock_name, BlockIds.coloredGlassPaneBlockID_default).getInt();
		config.save();

	}
}
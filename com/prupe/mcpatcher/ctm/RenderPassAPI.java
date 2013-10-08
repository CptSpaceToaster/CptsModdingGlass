package com.prupe.mcpatcher.ctm;

import net.minecraft.block.Block;

public interface RenderPassAPI {
	static RenderPassAPI instance = null;
    boolean skipDefaultRendering(Block block);
    boolean skipThisRenderPass(Block block, int pass);
    void clear();
    void setRenderPassForBlock(Block block, int pass);
    void finish();
}
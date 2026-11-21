package com.zxl.apple164.client;

import com.zxl.apple164.CommonProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.MinecraftForge;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void init() {
        MinecraftForge.EVENT_BUS.register(new FoodPreviewOverlay());
    }
}

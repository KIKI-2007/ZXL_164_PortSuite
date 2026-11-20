package com.zxl.apple164.core;
import org.junit.Test; import static org.junit.Assert.*;
public class FoodPreviewTest { @Test public void clampsHungerAndSaturation() { FoodPreview p=new FoodPreview(19,19,6,0.8F); assertEquals(20,p.hungerAfter); assertTrue(p.saturationAfter<=20.0F); } }

package com.zxl.apple164.core;
/** Pure calculation model; values are supplied by the 1.6.4 client/server adapter. */
public final class FoodPreview {
    public final int hungerAfter;
    public final float saturationAfter;
    public FoodPreview(int currentHunger, float currentSaturation, int foodHeal, float saturationModifier) {
        this.hungerAfter = Math.min(20, Math.max(0, currentHunger) + Math.max(0, foodHeal));
        float gain = Math.max(0.0F, foodHeal) * Math.max(0.0F, saturationModifier) * 2.0F;
        this.saturationAfter = Math.min((float)hungerAfter, Math.max(0.0F, currentSaturation) + gain);
    }
}

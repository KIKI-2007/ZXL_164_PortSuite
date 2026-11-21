package com.zxl.tooltip164.core;
import org.junit.Test; import static org.junit.Assert.*;
public class TooltipLayoutTest { @Test public void wrapsAtLimit() { assertEquals(2,TooltipLayout.wrap("one two three",7).size()); } }

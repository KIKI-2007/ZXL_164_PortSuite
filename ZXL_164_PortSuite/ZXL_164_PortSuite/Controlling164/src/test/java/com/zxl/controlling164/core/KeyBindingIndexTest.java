package com.zxl.controlling164.core;
import org.junit.Test; import static org.junit.Assert.*;
public class KeyBindingIndexTest {
 @Test public void searchesAndFindsConflicts() { KeyBindingIndex i=new KeyBindingIndex(); i.add(new KeyBindingIndex.Entry("Open Map",42)); i.add(new KeyBindingIndex.Entry("Open Menu",42)); assertEquals(1,i.search("map").size()); assertEquals(2,i.conflicts().size()); }
}

package com.zxl.tooltip164.core;
import java.util.ArrayList;
import java.util.List;
/** Text wrapping helper; renderer supplies actual font metrics in the Forge adapter. */
public final class TooltipLayout {
 public static List<String> wrap(String text, int maxChars) {
  List<String> lines=new ArrayList<String>(); if(text==null) return lines; if(maxChars<1) throw new IllegalArgumentException("maxChars must be positive");
  String[] words=text.split("\\s+"); StringBuilder line=new StringBuilder();
  for(String word:words) { if(line.length()>0 && line.length()+1+word.length()>maxChars) { lines.add(line.toString()); line.setLength(0); }
   if(line.length()>0) line.append(' '); if(word.length()<=maxChars) line.append(word); else { int p=0; while(p<word.length()) { int end=Math.min(word.length(),p+maxChars); if(line.length()>0) { lines.add(line.toString()); line.setLength(0); } lines.add(word.substring(p,end)); p=end; } }
  }
  if(line.length()>0) lines.add(line.toString()); return lines;
 }
}

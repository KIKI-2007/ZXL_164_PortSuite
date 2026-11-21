package com.zxl.controlling164.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Version-independent model for searching and detecting key conflicts. */
public final class KeyBindingIndex {
    public static final class Entry {
        public final String description;
        public final int keyCode;
        public Entry(String description, int keyCode) { this.description = description == null ? "" : description; this.keyCode = keyCode; }
    }
    private final List<Entry> entries = new ArrayList<Entry>();
    public void add(Entry entry) { if (entry != null) entries.add(entry); }
    public List<Entry> search(String query) {
        String q = query == null ? "" : query.toLowerCase();
        List<Entry> out = new ArrayList<Entry>();
        for (Entry e : entries) if (e.description.toLowerCase().contains(q)) out.add(e);
        return Collections.unmodifiableList(out);
    }
    public List<Entry> conflicts() {
        List<Entry> out = new ArrayList<Entry>();
        for (int i=0;i<entries.size();i++) for (int j=i+1;j<entries.size();j++)
            if (entries.get(i).keyCode != 0 && entries.get(i).keyCode == entries.get(j).keyCode) { out.add(entries.get(i)); out.add(entries.get(j)); }
        return Collections.unmodifiableList(out);
    }
}

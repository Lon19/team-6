package org.ytt.code4good;

import android.graphics.Color;
import android.graphics.Path;

class FingerPath {
    public final int color;
//    public boolean emboss;
//    public boolean blur;
    public final int strokeWidth;
    public final Path path;

    public FingerPath(Path path) {
        this.color = Color.BLACK;
        this.strokeWidth = 20;
        this.path = path;
    }
}

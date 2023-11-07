package com.zedo;

import java.util.HashMap;
import java.util.Map;

import com.google.appinventor.components.runtime.AndroidViewComponent;

public class BorderInfo {
    private int color;
    private int width;

    private static Map<AndroidViewComponent, BorderInfo> borderInfoMap = new HashMap<>();

    public BorderInfo(int color, int width) {
        this.color = color;
        this.width = width;
    }

    public static Map<AndroidViewComponent,BorderInfo> getBorderInfoMap() {
        return borderInfoMap;
    }

    public void setBorderInfoMap(Map<AndroidViewComponent,BorderInfo> borderInfoMap) {
        BorderInfo.borderInfoMap = borderInfoMap;
    }

    public int getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public void setColor(int color) {
        this.color = color;
    }
    public void setWidth(int width) {
        this.width = width;
    }
}
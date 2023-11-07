package com.zedo;

import java.util.HashMap;

import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.HVArrangement;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.HorizontalScrollArrangement;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;
import com.google.appinventor.components.runtime.util.YailList;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import gnu.math.IntNum;

public class BackGroundColorClass {

    // Color dictionary
    private HashMap<AndroidViewComponent, Integer> originalColors = new HashMap<>();

    public BackGroundColorClass() {
    }

    public HashMap<AndroidViewComponent,Integer> getOriginalColors() {
        return this.originalColors;
    }

    public void setOriginalColors(HashMap<AndroidViewComponent,Integer> originalColors) {
        this.originalColors = originalColors;
    }

    /**
     * Change the background color of a component.
     *
     * @param component The component to change the background color of.
     * @param colorValue The color value, which can be an integer, HEX string, RGB, or ARGB.
     * @throws IllegalArgumentException If the component is null, the color value is invalid, or the type is not supported.
     */
    public void ChangeBackgroundColor(Object components, Object colorValue) {
        if (components == null) {
            throw new IllegalArgumentException("The component cannot be null.");
        }
        int backgroundColor;
        try {
            if (colorValue instanceof Integer) {
                // Convert an integer value to a color
                backgroundColor = (int) colorValue;
            } 
            else if (colorValue instanceof IntNum) {
                // Explicit casting to gnu.math.IntNum
                IntNum colorIntNum = (IntNum) colorValue;
                backgroundColor = colorIntNum.intValue();
            }
            else if (colorValue instanceof String) {
                String colorString = (String) colorValue;
                if (colorString.matches("^(?:#?[0-9a-fA-F]{6})$")) {
                    // Value in hexadecimal format (RRGGBB)
                    if (!colorString.startsWith("#")){
                        colorString = "#" + colorString;
                    }
                    backgroundColor = Color.parseColor(colorString);
                }
                else if (colorString.matches("^(#?[0-9a-fA-F]{8})$")) {
                    // Value in ARGB format (AARRGGBB)
                    backgroundColor = Color.parseColor(colorString); 
                }
                else if (colorString.matches("^\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*$")) {
                    // Value in RGB format ("255, 0, 0")
                    String[] rgb = colorString.split(",");
                    if (rgb.length == 3) {
                        int r = Integer.parseInt(rgb[0].trim());
                        int g = Integer.parseInt(rgb[1].trim());
                        int b = Integer.parseInt(rgb[2].trim());
                        backgroundColor = Color.rgb(r, g, b);
                    }
                    else {
                        throw new IllegalArgumentException("Invalid RGB value. Use the format R, G, B (e.g., 255, 0, 0 for red).");
                    }
                } 
                else {
                    throw new IllegalArgumentException("Invalid color value. Use HEX format (#RRGGBB or RRGGBB), RGB (R, G, B), or ARGB (#AARRGGBB).");
                }
            } 
            else {
                throw new IllegalArgumentException("Unsupported value type. Use an integer value or a string.");
            }

            if (components instanceof AndroidViewComponent) {
                // The argument is an individual component
                AndroidViewComponent component = (AndroidViewComponent) components;
                ApplyBackgroundColor(component, backgroundColor);
            } else if (components instanceof YailList) {
                // The argument is a list of components
                YailList componentList = (YailList) components;
                for (Object componentObj : componentList.toArray()) {
                    if (componentObj instanceof AndroidViewComponent) {
                        AndroidViewComponent component = (AndroidViewComponent) componentObj;
                        ApplyBackgroundColor(component, backgroundColor);
                    }
                }
            } else {
                throw new IllegalArgumentException("The components argument is not valid. It should be a component or a list of components.");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void ApplyBackgroundColor(AndroidViewComponent component, int backgroundColor) {
        // Save the original color in the dictionary
        originalColors.put(component, backgroundColor);
        // Apply the color to the component
        View view = component.getView();
        // Update the background color of the Drawable
        if (view.getBackground() instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground();
            gradientDrawable.setColor(backgroundColor);
        }
        else {
            // If the background is not a GradientDrawable, set the background color directly
            view.setBackgroundColor(backgroundColor);
        }
    }

    /**
     * Returns the original background color of a component, if available.
     *
     * @param component The component from which you want to get the original background color.
     * @return The original background color value, or null if not found in the dictionary.
     * @throws IllegalArgumentException If the component is null.
     */
    public int ReturnBackgroundColor(AndroidViewComponent component){
        if (component == null) {
            throw new IllegalArgumentException("The component cannot be null.");
        }
        View view = component.getView();
        if (originalColors.containsKey(component)) {
            // If the component is in the dictionary, set the original color as the background
            return originalColors.get(component);
        }
        else if (view.getBackground() instanceof ColorDrawable) {
            if (component instanceof HorizontalArrangement){
                return ((HorizontalArrangement) component).BackgroundColor();
            }
            else if (component instanceof VerticalArrangement){
                return ((VerticalArrangement) component).BackgroundColor();
            }
            else if (component instanceof HorizontalScrollArrangement){
                return ((VerticalArrangement) component).BackgroundColor();
            }
            else if (component instanceof VerticalScrollArrangement){
                return ((VerticalArrangement) component).BackgroundColor();
            }
            else{
                return (Integer)(((ColorDrawable)view.getBackground()).getColor());
            }
        }
        else{
            return Color.LTGRAY;
        }
    }
}

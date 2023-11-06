package com.zedo;

import com.google.appinventor.components.runtime.AndroidViewComponent;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

public class BorderClass {

    public BorderClass() {
    }

    /**
     * Apply a border to a given Android component.
     *
     * @param component   The AndroidViewComponent to which the border will be applied.
     * @param borderColor The color of the border.
     * @param borderWidth The width of the border.
     */
    public void ApplyBorderToView(AndroidViewComponent component, int borderColor, int borderWidth) {
        // Get the view associated with the AndroidViewComponent
        View view = component.getView();

        // Get the existing background drawable, or create a new GradientDrawable if it doesn't exist
        GradientDrawable roundedDrawable;
        Drawable currentBackground = view.getBackground();
        if (currentBackground instanceof GradientDrawable) {
            roundedDrawable = (GradientDrawable) currentBackground;
        } else {
            roundedDrawable = new GradientDrawable();
        }

        Object originalColorValue;
        // Check if the component is in the dictionary
        if (!BackGroundColorClass.getOriginalColors().containsKey(component)) {
            // If the component is not in the dictionary, set the original color to the background
            BackGroundColorClass.getOriginalColors().put(component, BackGroundColorClass.ReturnBackgroundColor(component));
        }
        originalColorValue = BackGroundColorClass.getOriginalColors().get(component);

        // Set the border color and width
        roundedDrawable.setStroke(borderWidth, borderColor);

        BorderInfo.getBorderInfoMap().put(component, new BorderInfo(borderColor, borderWidth));

        // Apply the updated Drawable to the view
        view.setBackground(roundedDrawable);
        BackGroundColorClass.ChangeBackgroundColor(component, originalColorValue);
    }
}
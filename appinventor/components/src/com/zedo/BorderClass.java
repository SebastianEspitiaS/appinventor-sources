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
    public void ApplyBorderToView(AndroidViewComponent component, int borderColor, int borderWidth, BackGroundColorClass backGroundColorClass) {
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

        // Set the border color and width
        roundedDrawable.setStroke(borderWidth, borderColor);

        // Apply the updated Drawable to the view
        view.setBackground(roundedDrawable);
    }

}

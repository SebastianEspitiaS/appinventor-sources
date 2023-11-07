package com.zedo;

import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.util.YailList;

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
    public void BorderToView(Object components, int borderColor, int borderWidth, BackGroundColorClass backGroundColorClass) {
        if (components == null) {
            throw new IllegalArgumentException("The component cannot be null.");
        }
        if (components instanceof AndroidViewComponent) {
            AndroidViewComponent component = (AndroidViewComponent) components;
            ApplyBorderToView(component, borderColor, borderWidth, backGroundColorClass);
        } else if (components instanceof YailList) {
            YailList componentList = (YailList) components;
            for (Object componentObj : componentList.toArray()) {
                if (componentObj instanceof AndroidViewComponent) {
                    AndroidViewComponent component = (AndroidViewComponent) componentObj;
                    ApplyBorderToView(component, borderColor, borderWidth, backGroundColorClass);
                }
            }
        } else {
            throw new IllegalArgumentException("The components argument is not valid. It should be a component or a list of components.");
        }
    }

    public void ApplyBorderToView (AndroidViewComponent component, int borderColor, int borderWidth, BackGroundColorClass backGroundColorClass){
        
        View view = component.getView();

        // Get the existing background drawable, or create a new GradientDrawable if it doesn't exist
        GradientDrawable roundedDrawable;
        Drawable currentBackground = view.getBackground();
        if (currentBackground instanceof GradientDrawable) {
            roundedDrawable = (GradientDrawable) currentBackground;
        } else {
            roundedDrawable = new GradientDrawable();
        }

        int originalColorValue;
        // Check if the component is in the dictionary
        if (!backGroundColorClass.getOriginalColors().containsKey(component)) {
            // If the component is not in the dictionary, set the original color to the background
            backGroundColorClass.getOriginalColors().put(component, backGroundColorClass.ReturnBackgroundColor(component));
        }
        originalColorValue = backGroundColorClass.getOriginalColors().get(component);

        // Set the border color and width
        roundedDrawable.setStroke(borderWidth, borderColor);

        BorderInfo.getBorderInfoMap().put(component, new BorderInfo(borderColor, borderWidth));

        // Apply the updated Drawable to the view
        view.setBackground(roundedDrawable);
        backGroundColorClass.ChangeBackgroundColor(component, originalColorValue);

    }

}

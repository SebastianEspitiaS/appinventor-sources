package com.zedo;

import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.util.YailList;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

public class RoundCornerClass {

    public RoundCornerClass() {
    }

     /**
     * Apply equal corner radii to all edges of a component to round them.
     *
     * @param component The component to which corner radii will be applied.
     * @param round The corner radius value to apply to all sides.
     * @throws IllegalArgumentException If the component is null.
     */
    public void RoundAllCorners(Object components, float round, BackGroundColorClass backGroundColorClass) {
        if (components == null) {
            throw new IllegalArgumentException("The component cannot be null.");
        }
        if (components instanceof AndroidViewComponent) {
            AndroidViewComponent component = (AndroidViewComponent) components;
            ApplyCornerRadiiToView(component, round, round, round, round, backGroundColorClass);
        } else if (components instanceof YailList) {
            YailList componentList = (YailList) components;
            for (Object componentObj : componentList.toArray()) {
                if (componentObj instanceof AndroidViewComponent) {
                    AndroidViewComponent component = (AndroidViewComponent) componentObj;
                    ApplyCornerRadiiToView(component, round, round, round, round, backGroundColorClass);
                }
            }
        } else {
            throw new IllegalArgumentException("The components argument is not valid. It should be a component or a list of components.");
        }
    }

    /**
     * Apply custom corner radii to each edge of a component.
     *
     * @param component The component to which corner radii will be applied.
     * @param topLeft Radius for the top-left corner.
     * @param topRight Radius for the top-right corner.
     * @param bottomLeft Radius for the bottom-left corner.
     * @param bottomRight Radius for the bottom-right corner.
     * @throws IllegalArgumentException If the component is null.
     */
    public void RoundCorners(Object components, float topLeft, float topRight, float bottomLeft, float bottomRight, BackGroundColorClass backGroundColorClass) {
        if (components == null) {
            throw new IllegalArgumentException("The component cannot be null.");
        }
        if (components instanceof AndroidViewComponent) {
            AndroidViewComponent component = (AndroidViewComponent) components;
            ApplyCornerRadiiToView(component, topLeft, topRight, bottomLeft, bottomRight, backGroundColorClass);
        } else if (components instanceof YailList) {
            YailList componentList = (YailList) components;
            for (Object componentObj : componentList.toArray()) {
                if (componentObj instanceof AndroidViewComponent) {
                    AndroidViewComponent component = (AndroidViewComponent) componentObj;
                    ApplyCornerRadiiToView(component, topLeft, topRight, bottomLeft, bottomRight, backGroundColorClass);
                }
            }
        } else {
            throw new IllegalArgumentException("The components argument is not valid. It should be a component or a list of components.");
        }
    }

    /**
     * Apply the configured corner radii to the component and restore the original background color.
     *
     * @param component The component to which corner radii will be applied.
     * @param radiusTopLeft Radius for the top-left corner.
     * @param radiusTopRight Radius for the top-right corner.
     * @param radiusBottomLeft Radius for the bottom-left corner.
     * @param radiusBottomRight Radius for the bottom-right corner.
     * @throws IllegalArgumentException If the component is null.
     */
    public void ApplyCornerRadiiToView(AndroidViewComponent component, float radiusTopLeft, float radiusTopRight, float radiusBottomLeft, float radiusBottomRight, BackGroundColorClass backGroundColorClass) {
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
    
        // Set the corner radii
        roundedDrawable.setShape(GradientDrawable.RECTANGLE);
        roundedDrawable.setCornerRadii(new float[]{
            radiusTopLeft, radiusTopLeft,
            radiusTopRight, radiusTopRight,
            radiusBottomRight, radiusBottomRight,
            radiusBottomLeft, radiusBottomLeft
        });

        Object originalColorValue;
        // Check if the component is in the dictionary
        if (backGroundColorClass.getOriginalColors().containsKey(component)) {
            // If the component is in the dictionary, set the original color to the background
            originalColorValue = backGroundColorClass.getOriginalColors().get(component);
        } else if (view.getBackground() instanceof ColorDrawable) {
            // Get the component's background color
            originalColorValue = (Integer)(((ColorDrawable)view.getBackground()).getColor());
        } else {
            // Set a default color
            originalColorValue = Color.LTGRAY; // Default color (you can change it if desired)
        }
        // Apply the rounded Drawable to the view
        view.setBackground(roundedDrawable);
        // Ensure that the content does not overflow
        view.setClipToOutline(true);
        // Restore the original background
        backGroundColorClass.ChangeBackgroundColor(component, originalColorValue);
    }
}

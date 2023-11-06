package com.zedo;

import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.util.YailList;

import android.view.View;
import android.view.ViewGroup;

public class MarginClass {

    public MarginClass() {
    }
    
    /**
     * Apply equal margins to all sides of a component.
     *
     * @param component The component to which margins will be applied.
     * @param margin The margin value to apply to all sides.
     * @throws IllegalArgumentException If the component is null.
     */
    public void ApplyMarginAll(Object components, int margin) {
        if (components == null) {
            throw new IllegalArgumentException("The component cannot be null.");
        }
        if (components instanceof AndroidViewComponent) {
            AndroidViewComponent component = (AndroidViewComponent) components;
            ApplyMargins(component, margin, margin, margin, margin);
        } else if (components instanceof YailList) {
            YailList componentList = (YailList) components;
            for (Object componentObj : componentList.toArray()) {
                if (componentObj instanceof AndroidViewComponent) {
                    AndroidViewComponent component = (AndroidViewComponent) componentObj;
                    ApplyMargins(component, margin, margin, margin, margin);
                }
            }
        } else {
            throw new IllegalArgumentException("The components argument is not valid. It should be a component or a list of components.");
        }
    }

    /**
     * Apply custom margins to a component.
     *
     * @param component The component to which margins will be applied.
     * @param left The left margin.
     * @param top The top margin.
     * @param right The right margin.
     * @param bottom The bottom margin.
     * @throws IllegalArgumentException If the component is null.
     */
    public void ApplyCustomMargins(Object components, int left, int top, int right, int bottom) {
        if (components == null) {
            throw new IllegalArgumentException("The component cannot be null.");
        }
        if (components instanceof AndroidViewComponent) {
            AndroidViewComponent component = (AndroidViewComponent) components;
            ApplyMargins(component, left, top, right, bottom);
        } else if (components instanceof YailList) {
            YailList componentList = (YailList) components;
            for (Object componentObj : componentList.toArray()) {
                if (componentObj instanceof AndroidViewComponent) {
                    AndroidViewComponent component = (AndroidViewComponent) componentObj;
                    ApplyMargins(component, left, top, right, bottom);
                }
            }
        } else {
            throw new IllegalArgumentException("The components argument is not valid. It should be a component or a list of components.");
        }
    }

    /**
     * Apply margins to the component.
     *
     * @param component The component to which margins will be applied.
     * @param left The left margin.
     * @param top The top margin.
     * @param right The right margin.
     * @param bottom The bottom margin.
     * @throws IllegalArgumentException If the component is null.
     */
    public void ApplyMargins(AndroidViewComponent component, int left, int top, int right, int bottom) {
        // Get the view associated with the AndroidViewComponent
        View view = component.getView();
        // Apply the margins
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        params.setMargins(left, top, right, bottom);

        // Call requestLayout to update the layout of the view
        view.requestLayout();
    }
}

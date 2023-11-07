package com.zedo;

import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.util.YailList;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;

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
    public void ApplyMarginAll(Object components, int margin, boolean isPercentage) {
        if (components == null) {
            throw new IllegalArgumentException("The component cannot be null.");
        }
        if (components instanceof AndroidViewComponent) {
            AndroidViewComponent component = (AndroidViewComponent) components;
            if (isPercentage) {
                ApplyMarginsInPercentage(component, margin, margin, margin, margin);
            } else {
                ApplyMargins(component, margin, margin, margin, margin);
            }
        } else if (components instanceof YailList) {
            YailList componentList = (YailList) components;
            for (Object componentObj : componentList.toArray()) {
                if (componentObj instanceof AndroidViewComponent) {
                    AndroidViewComponent component = (AndroidViewComponent) componentObj;
                    if (isPercentage) {
                        ApplyMarginsInPercentage(component, margin, margin, margin, margin);
                    } else {
                        ApplyMargins(component, margin, margin, margin, margin);
                    }
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
    public void ApplyCustomMargins(Object components, int left, int top, int right, int bottom, boolean isPercentage) {
        if (components == null) {
            throw new IllegalArgumentException("The component cannot be null.");
        }
        if (components instanceof AndroidViewComponent) {
            AndroidViewComponent component = (AndroidViewComponent) components;
            if (isPercentage) {
                ApplyMarginsInPercentage(component, left, top, right, bottom);
            } else {
                ApplyMargins(component, left, top, right, bottom);
            }
        } else if (components instanceof YailList) {
            YailList componentList = (YailList) components;
            for (Object componentObj : componentList.toArray()) {
                if (componentObj instanceof AndroidViewComponent) {
                    AndroidViewComponent component = (AndroidViewComponent) componentObj;
                    if (isPercentage) {
                        ApplyMarginsInPercentage(component, left, top, right, bottom);
                    } else {
                        ApplyMargins(component, left, top, right, bottom);
                    }
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
        MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
        params.setMargins(left, top, right, bottom);
    
        // Call requestLayout to update the layout of the view
        view.requestLayout();
    }
    
    public void ApplyMarginsInPercentage(AndroidViewComponent component, int left, int top, int right, int bottom) {
        // Get the view associated with the AndroidViewComponent
        View view = component.getView();
        
        // Get the parent view's width and height
        ViewParent parent = view.getParent();
        if (parent instanceof View) {
            int parentWidth = ((View) parent).getWidth();
            int parentHeight = ((View) parent).getHeight();
        
            // Calculate margins in pixels from percentages
            int leftMargin = (int) (parentWidth * (left / 100f));
            int topMargin = (int) (parentHeight * (top / 100f));
            int rightMargin = (int) (parentWidth * (right / 100f));
            int bottomMargin = (int) (parentHeight * (bottom / 100f));
        
            // Apply the margins
            MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
            params.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        
            // Call requestLayout to update the layout of the view
            view.requestLayout();
        }
    }

}

package com.zedo;

import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.util.YailList;

import android.view.View;

public class PaddingClass {

    public PaddingClass() {
    }

    /**
     * Applies equal paddings to all sides of a component.
     *
     * @param components The component or list of components to apply paddings to.
     * @param paddings   The amount of padding to apply to all sides.
     * @throws IllegalArgumentException If the components argument is not valid.
     */
    public void AddPaddingAll(Object components, int paddings, BackGroundColorClass backGroundColorClass){
        if (components == null) {
            throw new IllegalArgumentException("The component cannot be null.");
        }
        if (components instanceof AndroidViewComponent) {
            AndroidViewComponent component = (AndroidViewComponent) components;
            ApplyPaddings(component, paddings, paddings, paddings, paddings, backGroundColorClass);
        } else if (components instanceof YailList) {
            YailList componentList = (YailList) components;
            for (Object componentObj : componentList.toArray()) {
                if (componentObj instanceof AndroidViewComponent) {
                    AndroidViewComponent component = (AndroidViewComponent) componentObj;
                    ApplyPaddings(component, paddings, paddings, paddings, paddings, backGroundColorClass);
                }
            }
        } else {
            throw new IllegalArgumentException("The components argument is not valid. It should be a component or a list of components.");
        }
    }

    /**
     * Applies custom paddings to a component.
     *
     * @param components     The component or list of components to apply paddings to.
     * @param leftPadding    The padding value for the left side.
     * @param rightPadding   The padding value for the right side.
     * @param topPadding     The padding value for the top side.
     * @param bottomPadding  The padding value for the bottom side.
     * @throws IllegalArgumentException If the components argument is not valid.
     */
    public void AddCustomPadding(Object components, int leftPadding, int rightPadding, int topPadding, int bottomPadding, BackGroundColorClass backGroundColorClass) {
        if (components == null) {
            throw new IllegalArgumentException("The component cannot be null.");
        }
        if (components instanceof AndroidViewComponent) {
            AndroidViewComponent component = (AndroidViewComponent) components;
            ApplyPaddings(component, leftPadding, rightPadding, topPadding, bottomPadding, backGroundColorClass);
        } else if (components instanceof YailList) {
            YailList componentList = (YailList) components;
            for (Object componentObj : componentList.toArray()) {
                if (componentObj instanceof AndroidViewComponent) {
                    AndroidViewComponent component = (AndroidViewComponent) componentObj;
                    ApplyPaddings(component, leftPadding, rightPadding, topPadding, bottomPadding, backGroundColorClass);
                }
            }
        } else {
            throw new IllegalArgumentException("The components argument is not valid. It should be a component or a list of components.");
        }
    }

    /**
     * Applies custom paddings to a component and changes the background color.
     *
     * @param component      The component to apply paddings to.
     * @param leftPadding    The padding value for the left side.
     * @param rightPadding   The padding value for the right side.
     * @param topPadding     The padding value for the top side.
     * @param bottomPadding  The padding value for the bottom side.
     */
    public void ApplyPaddings(AndroidViewComponent component, int leftPadding, int rightPadding, int topPadding, int bottomPadding, BackGroundColorClass backGroundColorClass){
        View view = component.getView();
        view.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
        BackGroundColorClass.ChangeBackgroundColor(component, BackGroundColorClass.ReturnBackgroundColor(component));
        view.requestLayout();
    }
}

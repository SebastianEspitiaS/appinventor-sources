package com.zedo;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.AndroidViewComponent;

@DesignerComponent(
    version = 1,
    description = "This is an extension that allows you to customize the corner radii of an element. You can specify the radii for the top, bottom, left, and right corners.",
    category = ComponentCategory.EXTENSION,
    nonVisible = true,
    iconName = "https://i.imgur.com/Q1gD75M.png")
@SimpleObject(external = true)
public class ExtensionGUI extends AndroidNonvisibleComponent {
    
    BackGroundColorClass backGroundColorClass = new BackGroundColorClass();
    MarginClass marginClass = new MarginClass();
    PaddingClass paddingClass = new PaddingClass();
    RoundCornerClass cornerClass = new RoundCornerClass();
    BorderClass borderClass = new BorderClass();

    public ExtensionGUI(ComponentContainer container) {
        super(container.$form());
    }


    //////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////Section: Background//////////////////////////////////////


    @SimpleFunction(description = "Change background color (HEX, RGB, ARGB, Integer, or color blocks)")
    public void ChangeBackgroundColor(Object components, Object colorValue) {
        backGroundColorClass.ChangeBackgroundColor(components, colorValue);
    }

    @SimpleFunction(description = "Return the background color.")
    public Object ReturnBackgroundColor(AndroidViewComponent component){
        return backGroundColorClass.ReturnBackgroundColor(component);
    }


    //////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////Section: Margins////////////////////////////////////////


    @SimpleFunction(description = "Apply equal margins to all sides of a component.")
    public void MarginAll(Object components, int margin, boolean isPercentage) {
        marginClass.ApplyMarginAll(components, margin, isPercentage);
    }

    @SimpleFunction(description = "Apply custom margins to a component.")
    public void CustomMargins(Object components, int left, int top, int right, int bottom, boolean isPercentage) {
        marginClass.ApplyCustomMargins(components, left, top, right, bottom, isPercentage);
    }


    //////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////Section: Paddings///////////////////////////////////////


    @SimpleFunction(description = "Apply equal paddings to all sides of a component.")
    public void PaddingAll(Object components, int paddings){
       paddingClass.AddPaddingAll(components, paddings, backGroundColorClass);
    }

    @SimpleFunction(description = "Apply custom paddings to a component")
    public void CustomPadding(Object components, int leftPadding, int rightPadding, int topPadding, int bottomPadding) {
        paddingClass.AddCustomPadding(components, leftPadding, rightPadding, topPadding, bottomPadding, backGroundColorClass);
    }
       

    //////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////Section: Round Corners////////////////////////////////////


    @SimpleFunction(description = "Apply equal corner radii to all edges of a component.")
    public void RoundAllCorners(Object components, float round) {
       cornerClass.RoundAllCorners(components, round, backGroundColorClass);
    }
    
    @SimpleFunction(description = "Apply custom corner radii to a component.")
    public void RoundCorners(Object components, float topLeft, float topRight, float bottomLeft, float bottomRight) {
        cornerClass.RoundCorners(components, topLeft, topRight, bottomLeft, bottomRight, backGroundColorClass);
    }

    public void CornerRadiiToView(AndroidViewComponent component, float radiusTopLeft, float radiusTopRight, float radiusBottomLeft, float radiusBottomRight) {
        cornerClass.ApplyCornerRadiiToView(component, radiusTopLeft, radiusTopRight, radiusBottomLeft, radiusBottomRight, backGroundColorClass);
    }


    //////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////Section: Borders//////////////////////////////////////////


    @SimpleFunction(description = "Apply border to a component.")
    public void ApplyBorderToView(AndroidViewComponent component, int borderColor, int borderWidth) {
        borderClass.BorderToView(component, borderColor, borderWidth, backGroundColorClass);
    }

}

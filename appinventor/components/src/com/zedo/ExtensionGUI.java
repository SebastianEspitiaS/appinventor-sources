package com.zedo;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.Label;

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

    @SimpleFunction(description = "Ver el tipo de dato del componente.")
    public void TipoDato(AndroidViewComponent component, Object value){
        if (component instanceof Label) {
            Label label = (Label) component;
            String texto = value.getClass().getName();
            label.Text(texto);
        } 
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
    /////////////////////////////////Seccion: Margenes////////////////////////////////////////


    @SimpleFunction(description = "Apply equal margins to all sides of a component.")
    public void ApplyMarginAll(Object components, int margin) {
        marginClass.ApplyMarginAll(components, margin);
    }

    @SimpleFunction(description = "Apply custom margins to a component.")
    public void ApplyCustomMargins(Object components, int left, int top, int right, int bottom) {
        marginClass.ApplyCustomMargins(components, left, top, right, bottom);
    }

    public void ApplyMargins(AndroidViewComponent component, int left, int top, int right, int bottom) {
        marginClass.ApplyMargins(component, left, top, right, bottom);
    }


    //////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////Seccion: Padding///////////////////////////////////////


    @SimpleFunction(description = "Apply equal paddings to all sides of a component.")
    public void AddPaddingAll(Object components, int paddings){
       paddingClass.AddPaddingAll(components, paddings, backGroundColorClass);
    }

    @SimpleFunction(description = "Apply custom paddings to a component")
    public void AddCustomPadding(Object components, int leftPadding, int rightPadding, int topPadding, int bottomPadding) {
        paddingClass.AddCustomPadding(components, leftPadding, rightPadding, topPadding, bottomPadding, backGroundColorClass);
    }

    public void ApplyPaddings(AndroidViewComponent component, int leftPadding, int rightPadding, int topPadding, int bottomPadding){
        paddingClass.ApplyPaddings(component, leftPadding, rightPadding, topPadding, bottomPadding, backGroundColorClass);
    }
       

    //////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////Seccion: Redondear Bordes////////////////////////////////////


    @SimpleFunction(description = "Apply equal corner radii to all edges of a component.")
    public void RoundAllCorners(Object components, float round) {
       cornerClass.RoundAllCorners(components, round, backGroundColorClass);
    }
    
    @SimpleFunction(description = "Apply custom corner radii to a component.")
    public void RoundCorners(Object components, float topLeft, float topRight, float bottomLeft, float bottomRight) {
        cornerClass.RoundCorners(components, topLeft, topRight, bottomLeft, bottomRight, backGroundColorClass);
    }

    public void ApplyCornerRadiiToView(AndroidViewComponent component, float radiusTopLeft, float radiusTopRight, float radiusBottomLeft, float radiusBottomRight) {
        cornerClass.ApplyCornerRadiiToView(component, radiusTopLeft, radiusTopRight, radiusBottomLeft, radiusBottomRight, backGroundColorClass);
    }


    //////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////Seccion: Bordes//////////////////////////////////////////


    @SimpleFunction(description = "Apply border to a component.")
    public void ApplyBorderToView(AndroidViewComponent component, int borderColor, int borderWidth) {
        borderClass.ApplyBorderToView(component, borderColor, borderWidth);
    }

}

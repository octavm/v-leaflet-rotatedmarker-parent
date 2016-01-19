package org.vaadin.gleaflet.rotatedmarker.client;

import org.peimari.gleaflet.client.MarkerOptions;

public class RotatedMarkerOptions extends MarkerOptions {

    protected RotatedMarkerOptions() {
    }

    public static native RotatedMarkerOptions create()
    /*-{
        return {};
    }-*/;

    public native final void setRotationAngle(double newAngle)
    /*-{
        this.rotationAngle = newAngle;
    }-*/;

    public native final void setRotationOrigin(String newOrigin)
    /*-{
        this.rotationOrigin = newOrigin;
    }-*/;

}

package org.vaadin.gleaflet.rotatedmarker.client;

import org.peimari.gleaflet.client.*;
import org.vaadin.gleaflet.rotatedmarker.client.resources.LeafletRotatedMarkerResourceInjector;

/**
 * 
 * @author octavm
 *
 */
public class RotatedMarker extends Marker {
	
	static {
		LeafletRotatedMarkerResourceInjector.ensureInjected();
	}
	
	protected RotatedMarker() {}

	public static native RotatedMarker create(LatLng latlng, RotatedMarkerOptions options)
	/*-{
		return new $wnd.L.Marker(latlng, options);
	}-*/;
	
	public native final void setRotationAngle(double newAngle)
	/*-{
		this.setRotationAngle(newAngle);
	}-*/;

    public native final void setRotationOrigin(String newOrigin)
    /*-{
        this.setRotationOrigin(newOrigin);
    }-*/;

}

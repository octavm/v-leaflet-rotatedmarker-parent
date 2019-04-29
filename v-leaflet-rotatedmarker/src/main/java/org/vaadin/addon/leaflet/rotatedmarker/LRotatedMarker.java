package org.vaadin.addon.leaflet.rotatedmarker;

import org.vaadin.addon.leaflet.LMarker;
import org.vaadin.addon.leaflet.rotatedmarker.client.LeafletRotatedMarkerState;
import org.locationtech.jts.geom.Point;

/**
 * <code>LRotatedMarker</code> is a normal <code>LMarker</code> with added possibility to rotate the icon around an origin
 */
public class LRotatedMarker extends LMarker {

    /**
     * Marker with rotated icon
     * @param jtsPoint geographical position of the marker
     */
    public LRotatedMarker(Point jtsPoint) {
        super(jtsPoint);
    }

    /**
     *  Set the rotation angle value
     * @param rotationAngle rotation angle, in degrees, clockwise. Default is 0
     */
    public void setRotationAngle(Double rotationAngle) {
        getState().rotationAngle = rotationAngle;
    }

    /**
     * Sets the rotation origin value
     * @param rotationOrigin The rotation center, as a transform-origin CSS rule. Default is "bottom center"
     */
    public void setRotationOrigin(String rotationOrigin) {
        getState().rotationOrigin = rotationOrigin;
    }

    protected LeafletRotatedMarkerState getState() {
        return (LeafletRotatedMarkerState) super.getState();
    }

}

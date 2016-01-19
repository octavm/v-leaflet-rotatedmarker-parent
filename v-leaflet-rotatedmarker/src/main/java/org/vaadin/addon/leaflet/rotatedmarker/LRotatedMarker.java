package org.vaadin.addon.leaflet.rotatedmarker;

import org.vaadin.addon.leaflet.LMarker;
import org.vaadin.addon.leaflet.rotatedmarker.client.LeafletRotatedMarkerState;

/**
 * author marcuo
 */
public class LRotatedMarker extends LMarker {

    public LRotatedMarker(com.vividsolutions.jts.geom.Point jtsPoint) {
        super(jtsPoint);
    }

    public void setRotationAngle(Double rotationAngle) {
        getState().rotationAngle = rotationAngle;
    }

    public void setRotationOrigin(String rotationOrigin) {
        getState().rotationOrigin = rotationOrigin;
    }

    protected LeafletRotatedMarkerState getState() {
        return (LeafletRotatedMarkerState) super.getState();
    }

}

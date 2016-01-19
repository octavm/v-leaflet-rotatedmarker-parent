package org.vaadin.addon.leaflet.rotatedmarker.client;

import com.vaadin.shared.ui.Connect;
import org.peimari.gleaflet.client.LatLng;
import org.peimari.gleaflet.client.MarkerOptions;
import org.vaadin.addon.leaflet.client.LeafletMarkerConnector;
import org.vaadin.gleaflet.rotatedmarker.client.RotatedMarker;
import org.vaadin.gleaflet.rotatedmarker.client.RotatedMarkerOptions;

@Connect(org.vaadin.addon.leaflet.rotatedmarker.LRotatedMarker.class)
public class LeafletRotatedMarkerConnector extends LeafletMarkerConnector {

    public LeafletRotatedMarkerState getState() {
        return (LeafletRotatedMarkerState) super.getState();
    }

    protected RotatedMarkerOptions createOptions() {
        RotatedMarkerOptions o = RotatedMarkerOptions.create();
        LeafletRotatedMarkerState s = getState();

        if (s.rotationAngle != null) {
            o.setRotationAngle(s.rotationAngle);
        }

        if (s.rotationOrigin != null) {
            o.setRotationOrigin(s.rotationOrigin);
        }

        return o;
    }

    protected RotatedMarker create(LatLng latlng, MarkerOptions options) {
        return RotatedMarker.create(latlng, options);
    }

}

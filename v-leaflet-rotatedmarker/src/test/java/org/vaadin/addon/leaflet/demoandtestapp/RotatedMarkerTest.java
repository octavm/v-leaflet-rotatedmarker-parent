package org.vaadin.addon.leaflet.demoandtestapp;

import com.vaadin.server.ClassResource;
import com.vaadin.ui.Component;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.vaadin.addon.leaflet.LLayerGroup;
import org.vaadin.addon.leaflet.LMap;
import org.vaadin.addon.leaflet.LTileLayer;
import org.vaadin.addon.leaflet.rotatedmarker.LRotatedMarker;
import org.vaadin.addonhelpers.AbstractTest;

public class RotatedMarkerTest extends AbstractTest {

    @Override
    public Component getTestComponent() {

        LMap leafletMap = new LMap();

        Point p = new GeometryFactory().createPoint(new Coordinate(8.622, 45.819));

        leafletMap.setCenter(p);
        leafletMap.setZoomLevel(11);
        leafletMap.setMaxZoom(19);

        LTileLayer osm = new LTileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png");
        osm.setAttributionString("&copy; <a href='http://osm.org/copyright'>OpenStreetMap</a> contributors");

        osm.setMaxZoom(18);
        osm.setDetectRetina(true);
        leafletMap.addBaseLayer(osm, "Open Street Map");


        leafletMap.addComponent(getRotatedMarkers());

        return leafletMap;
    }

    private LLayerGroup getRotatedMarkers() {
        LLayerGroup layerGroup = new LLayerGroup();

        Point p1 = new GeometryFactory().createPoint(new Coordinate(8.622, 45.819));
        Point p2 = new GeometryFactory().createPoint(new Coordinate(8.54724, 45.73686));
        Point p3 = new GeometryFactory().createPoint(new Coordinate(8.49243, 45.74453));

        LRotatedMarker rotatedMarker1 = new LRotatedMarker(p1);
        rotatedMarker1.setRotationAngle(35.0);
        rotatedMarker1.setPopup("35° rotated marker");

        LRotatedMarker rotatedMarker2 = new LRotatedMarker(p2);
        rotatedMarker2.setIcon(new ClassResource("testicon.png"));
        rotatedMarker2.setIconSize(new org.vaadin.addon.leaflet.shared.Point(24,24));
        rotatedMarker2.setRotationAngle(75.5);
        rotatedMarker2.setRotationOrigin("bottom right");
        rotatedMarker2.setPopup("75.5° rotated marker");

        LRotatedMarker rotatedMarker3 = new LRotatedMarker(p3);
        rotatedMarker3.setRotationAngle(225.2);
        rotatedMarker3.setPopup("225.2° rotated marker");

        layerGroup.addComponent(rotatedMarker1);
        layerGroup.addComponent(rotatedMarker2);
        layerGroup.addComponent(rotatedMarker3);

        return layerGroup;
    }
}

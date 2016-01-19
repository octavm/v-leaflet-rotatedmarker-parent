package org.vaadin.gleaflet.rotatedmarker.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.DataResource.DoNotEmbed;
import com.google.gwt.resources.client.TextResource;

/**
 * @author octavm
 */
public interface LeafletRotatedMarkerClientBundle extends ClientBundle {
 
    @Source("leaflet.rotatedMarker.js")
    TextResource rotatedMarkerScript();

}

package org.vaadin.gleaflet.rotatedmarker.client.resources;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.ScriptInjector;
import org.peimari.gleaflet.client.resources.LeafletResourceInjector;

import com.google.gwt.core.client.GWT;

public class LeafletRotatedMarkerResourceInjector {

	protected static LeafletRotatedMarkerClientBundle bundle;

	public static void ensureInjected() {
		if (bundle == null) {
			LeafletResourceInjector.ensureInjected();
			bundle = GWT.create(LeafletRotatedMarkerClientBundle.class);
			LeafletRotatedMarkerResourceInjector injector = GWT
					.create(LeafletRotatedMarkerResourceInjector.class);
			injector.injectResources();
		}
	}
	
	/**
	 * Override this with deferred binding to customize injected stuff
	 */
	protected void injectResources() {
		final String rotatedMarkerTxt = bundle.rotatedMarkerScript().getText();
        ScriptInjector.fromString(rotatedMarkerTxt).setWindow(nativeTopWindow()).inject();
	}

    private static native JavaScriptObject nativeTopWindow()
    /*-{
        return $wnd;
    }-*/;

}

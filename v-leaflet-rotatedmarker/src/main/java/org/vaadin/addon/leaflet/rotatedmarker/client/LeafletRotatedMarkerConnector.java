package org.vaadin.addon.leaflet.rotatedmarker.client;

import com.google.gwt.core.client.Scheduler;
import com.vaadin.client.MouseEventDetailsBuilder;
import com.vaadin.shared.communication.URLReference;
import com.vaadin.shared.ui.ComponentStateUtil;
import com.vaadin.shared.ui.Connect;
import org.peimari.gleaflet.client.*;
import org.vaadin.addon.leaflet.client.LeafletMarkerConnector;
import org.vaadin.addon.leaflet.client.LeafletPopupConnector;
import org.vaadin.addon.leaflet.client.U;
import org.vaadin.addon.leaflet.shared.DragEndServerRpc;
import org.vaadin.addon.leaflet.shared.EventId;
import org.vaadin.gleaflet.rotatedmarker.client.RotatedMarker;
import org.vaadin.gleaflet.rotatedmarker.client.RotatedMarkerOptions;

@Connect(org.vaadin.addon.leaflet.rotatedmarker.LRotatedMarker.class)
public class LeafletRotatedMarkerConnector extends LeafletMarkerConnector {

    private RotatedMarker rotatedMarker;

    DragEndServerRpc dragServerRcp = getRpcProxy(DragEndServerRpc.class);

    public LeafletRotatedMarkerConnector() {
        super();
    }

    public LeafletRotatedMarkerState getState() {
        return (LeafletRotatedMarkerState) super.getState();
    }

    ClickListener handler = new ClickListener() {
        @Override
        public void onClick(MouseEvent event) {
            rpc.onClick(U.toPoint(event.getLatLng()),
                    MouseEventDetailsBuilder.buildMouseEventDetails(event.getNativeEvent(), getLeafletMapConnector().getWidget().getElement()));
        }
    };

    @Override
    protected void update() {
        if (rotatedMarker != null) {
            removeLayerFromParent();
            rotatedMarker.removeClickListener();
            rotatedMarker.removeMouseOverListener();
            rotatedMarker.removeMouseOutListener();
            rotatedMarker.removeContextMenuListener();
        }
        LatLng latlng = LatLng.create(getState().point.getLat(),
                getState().point.getLon());
        RotatedMarkerOptions options = createOptions();

        URLReference urlReference = getState().resources.get("icon");
        String divIcon = getState().divIcon;
        if (urlReference != null && urlReference.getURL().startsWith("fonticon://")) {
            // fonticons have special handling
            com.vaadin.client.ui.Icon vIcon = getIcon();
            String fontAwesomeChar = vIcon.getElement().getInnerText();
            StringBuilder svgSb = new StringBuilder();
            // TODO make this configurable, consider making also possible to
            // use configurable SVG rotatedMarker without fontawesome icon in rotatedMarker
            svgSb.append("<svg width=\"25px\" height=\"40px\"><path fill=\"#44AEEA\" stroke=\"#005FA8\" d=\"M12.544,0.5C5.971,0.5,0.5,6.24,0.5,12.416c0,2.777,1.564,6.308,2.694,8.745\n"
                    + "L12.5,38.922l9.262-17.761c1.13-2.438,2.738-5.791,2.738-8.745C24.5,6.24,19.117,0.5,12.544,0.5L12.544,0.5z\"/><text fill=\"#fff\" x=\"12.5\" y=\"20\" text-anchor=\"middle\" font-size=\"16\" class=\"");
            svgSb.append(vIcon.getStyleName());
            svgSb.append("\">");
            svgSb.append(fontAwesomeChar);
            svgSb.append("</text></svg>");

            DivIconOptions divIconOptions = DivIconOptions.create();
            divIconOptions.setClassName("v-leaflet-custom-svg");
            divIconOptions.setHtml(svgSb.toString());
            divIconOptions.setIconSize(Point.create(25, 40));
            divIconOptions.setIconAnchor(Point.create(12.5, 40));
            configureIconSize(divIconOptions);

            DivIcon icon = DivIcon.create(divIconOptions);
            options.setIcon(icon);

        } else if (divIcon != null) {
            DivIconOptions divIconOptions = DivIconOptions.create();
            configureIconSize(divIconOptions);
            if (ComponentStateUtil.hasStyles(getState())) {
                StringBuilder builder = new StringBuilder();
                for (String style : getState().styles) {
                    builder.append(style).append(" ");
                }
                divIconOptions.setClassName(builder.toString());
            }
            divIconOptions.setHtml(divIcon);
            DivIcon icon = DivIcon.create(divIconOptions);
            options.setIcon(icon);
        } else if (urlReference != null) {
            IconOptions iconOptions = IconOptions.create();
            iconOptions.setIconUrl(urlReference.getURL());
            if (getState().iconAnchor != null) {
                iconOptions.setIconAnchor(Point.create(
                        getState().iconAnchor.getLat(),
                        getState().iconAnchor.getLon()));
            }
            if (getState().iconSize != null) {
                iconOptions.setIconSize(Point.create(
                        getState().iconSize.getLat(),
                        getState().iconSize.getLon()));
            }
            Icon icon = Icon.create(iconOptions);
            options.setIcon(icon);
        }

        String title = getState().title;
        if (title != null) {
            options.setTitle(title);
        }

        if (hasEventListener("dragend")) {
            options.setDraggable(true);
        }

        rotatedMarker = RotatedMarker.create(latlng, options);
        if (hasEventListener("dragend")) {
            rotatedMarker.addDragEndListener(new ClickListener() {
                @Override
                public void onClick(MouseEvent event) {
                    dragServerRcp.dragEnd(U.toPoint(rotatedMarker.getLatLng()));
                }
            });
        }
        if (hasEventListener(EventId.MOUSEOVER)) {
            /*
             * Add listener lazily to avoid extra event if layer is modified in
             * server side listener. This can be removed if "clear and rebuild"
             * style component updates are changed into something more
             * intelligent at some point.
             */
            Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                @Override
                public void execute() {
                    rotatedMarker.addMouseOverListener(new MouseOverListener() {
                        @Override
                        public void onMouseOver(MouseEvent event) {
                            mouseOverRpc.onMouseOver(U.toPoint(event.getLatLng()));
                        }
                    });
                }
            });
        }
        if (hasEventListener(EventId.MOUSEOUT)) {
            rotatedMarker.addMouseOutListener(new MouseOutListener() {
                @Override
                public void onMouseOut(MouseEvent event) {
                    mouseOutRpc.onMouseOut(U.toPoint(event.getLatLng()));
                }
            });
        }
        if (hasEventListener(EventId.CONTEXTMENU)) {
            rotatedMarker.addContextMenuListener(new ContextMenuListener() {
                @Override
                public void onContextMenu(MouseEvent event) {
                    contextMenuRpc.onContextMenu(U.toPoint(event.getLatLng()),
                            MouseEventDetailsBuilder.buildMouseEventDetails(event.getNativeEvent(), getLeafletMapConnector().getWidget().getElement()));
                }
            });
        }
        String popup = getState().popup;
        if (popup != null) {
            PopupOptions popupOptions = LeafletPopupConnector.popupOptionsFor(getState().popupState, this);
            rotatedMarker.bindPopup(popup, popupOptions);
        }

        Double rotationAngle = getState().rotationAngle;
        if (rotationAngle != null) {
            rotatedMarker.setRotationAngle(rotationAngle);
        }

        String rotationOrigin = getState().rotationOrigin;
        if (rotationOrigin != null) {
            rotatedMarker.setRotationOrigin(rotationOrigin);
        }

        addToParent(rotatedMarker);

        rotatedMarker.addClickListener(handler);
    }

    private void configureIconSize(DivIconOptions divIconOptions) {
        if (getState().iconAnchor != null) {
            divIconOptions.setIconAnchor(Point.create(
                    getState().iconAnchor.getLat(),
                    getState().iconAnchor.getLon()));
        }
        if (getState().iconSize != null) {
            divIconOptions.setIconSize(Point.create(
                    getState().iconSize.getLat(),
                    getState().iconSize.getLon()));
        }
    }

    @Override
    protected RotatedMarkerOptions createOptions() {
        RotatedMarkerOptions rotatedMarkerOptions = RotatedMarkerOptions.create();
        return rotatedMarkerOptions;
    }

    @Override
    public Layer getLayer() {
        return rotatedMarker;
    }

}

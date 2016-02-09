# Leaflet.RotatedMarker wrappers for Vaadin & GWT

This project provides Java API for both client (GWT, with "g-" prefix) and server (Vaadin) side for the popular Leaflet.RotatedMarker add-on made by Benjamin Becquet.


Usage
---

```js
LMap map = new LMap();
LRotatedMarker rotatedMarker = new LRotatedMarker(....);
roatatedMarker.setIcon(...);
rotatedMarker.setRotationAngle(45.0);
rotatedMarker.setRotationOrigin("center center");
map.addLayer(rotatedMarker);
```


API
---

It simply extends the `LMarker` class adding two new options:

Option | Type | Default | Description  
-------|------|---------|------------
**`rotationAngle`** | `Double` | 0 | Rotation angle, in degrees, clockwise.
**`rotationOrigin`** | `String` | `'bottom center'` | The rotation center, as a [`transform-origin`](https://developer.mozilla.org/en-US/docs/Web/CSS/transform-origin) CSS rule.

and two new methods:

Method |Default value|Parameter Type| Returns | Description
-------|-------------|--------------|---------|------------
**`setRotationAngle(newAngle)`** |0.0| `Double`| `void` | Sets the rotation angle value in degrees, clockwise
**`setRotationOrigin(newOrigin)`** | `"bottom center"` | `String`| `void` | Sets the rotation origin value, as a [`transform-origin`](https://developer.mozilla.org/en-US/docs/Web/CSS/transform-origin) CSS rule.


The default `rotationOrigin` value will rotate around the bottom center point, corresponding to the "tip" of the marker for most commonly used icons. If your marker icon has no tip, or you want to rotate around its center, use `center center`.


Realeases can be downloaded form [https://vaadin.com/directory](https://vaadin.com/directory)

### Vaadin API for Leaflet: [V-Leaflet](https://github.com/mstahv/v-leaflet)

During development you might need to have development version of v-leaflet, g-leaflet and possibly g-leaflet-draw as well.

### The original Leaflet add-on library: [ Leaflet.rotatedmarker](https://github.com/bbecquet/Leaflet.RotatedMarker)

### The core slippy map library: [LeafletJS](http://leafletjs.com)



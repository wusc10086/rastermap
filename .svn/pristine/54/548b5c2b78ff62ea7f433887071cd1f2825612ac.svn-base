var testData = {
  max: 13,
  data: [{lat: 39.5, lng:116.2, count: 10},{lat:36.5, lng:117, count: 5},{lat:28, lng:112.1, count: 4},{lat:32, lng:112.1, count: 3},{lat:28, lng:115, count: 5},{lat:28.05, lng:112, count: 4}]
};

var cfg = {
  // radius should be small ONLY if scaleRadius is true (or small radius is intended)
  // if scaleRadius is false it will be the constant radius used in pixels
  "radius": 2,
  "maxOpacity": .8, 
  // scales the radius based on map zoom
  "scaleRadius": true, 
  // if set to false the heatmap uses the global maximum for colorization
  // if activated: uses the data maximum within the current map boundaries 
  //   (there will always be a red spot with useLocalExtremas true)
  "useLocalExtrema": true,
  // which field name in your data represents the latitude - default "lat"
  latField: 'lat',
  // which field name in your data represents the longitude - default "lng"
  lngField: 'lng',
  // which field name in your data represents the data value - default "value"
  valueField: 'count'
};

var heatmapLayer = new HeatmapOverlay(cfg);
heatmapLayer.setData(testData);
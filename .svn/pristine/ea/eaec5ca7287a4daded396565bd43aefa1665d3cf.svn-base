package org.rasterdb.utils;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Geometry;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.MultiGeometry;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Polygon;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * KML 文件解析
 * @author Administrator
 */

@IocBean(name = "kmlFileUtil")
public class KMLFileUtil {

    public KMLFileUtil() {
    }
    

    /**
     * 解析KML文件，将空间数据转成wkt格式列表
     *
     * @param kmlfile KML文件
     * @return wkt格式空间数据列表
     * @throws IOException
     */
    public List<String> parserKMLFile(File kmlfile) throws IOException {
        List<String> polygons = new ArrayList<String>();

        if (kmlfile.exists()) {
            final Kml kml = Kml.unmarshal(kmlfile);
            final Document document = (Document) kml.getFeature();
            List<Feature> t = document.getFeature();
            System.out.println(t.size());

            //for each loop for iterating through the folders
            for (Object o : t) {
                Folder f = (Folder) o;

//                System.out.println(f.getName());

                List<Feature> tg = f.getFeature();

                String folderName = f.getName();
//                System.out.println(folderName);

                //Iterating through placemarks inside all folders
                for (Object ftg : tg) {
                    Placemark g = (Placemark) ftg;

                    //check if the node under placemark is MultiGeometry
                    if ((g.getGeometry() instanceof MultiGeometry)) {

                        MultiGeometry mpg = (MultiGeometry) g.getGeometry();
                        List<Geometry> gmList = mpg.getGeometry();
                       
                        //Get all the geometries and traverse them one by one
                        for (Geometry geoItr : gmList) {
                            if (geoItr instanceof Polygon) {
                                Polygon multiGeoPoly = (Polygon) geoItr;
                                StringBuilder polyBuilder = new StringBuilder("polygon((");
                                List<Coordinate> coordList = multiGeoPoly.getOuterBoundaryIs().getLinearRing().getCoordinates();

                                for (int i = 0; i < coordList.size(); i++) {
                                    Coordinate point = coordList.get(i);
                                    polyBuilder.append(point.getLongitude()).append(" ").append(point.getLatitude());
                                    if((i+1)<coordList.size()){
                                        polyBuilder.append(",");
                                    }else{
                                        polyBuilder.append("))");
                                    }
                                }
//                                System.out.println(polyBuilder.toString());
                                polygons.add(polyBuilder.toString());
                            }

                        }

                    }

                }
            }
        }
        return polygons;

    }

}

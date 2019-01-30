package org.rasterdb.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.nutz.ioc.loader.annotation.IocBean;
import org.opengis.feature.simple.SimpleFeature;

/**
 * 读取shp文件，解析空间数据
 * @author Administrator
 */

@IocBean(name = "shpFileUtil")
public class ShpFileUtil {

    private  ShapefileDataStoreFactory dataStoreFactory=null;

    public ShpFileUtil() {
        dataStoreFactory = new ShapefileDataStoreFactory();
    }
    
    /**
     * 解析shp文件，将空间数据转成wkt格式列表
     * @param shpFile  shp文件
     * @return  wkt格式空间数据列表
     * @throws IOException 
     */
    public List<String> parserShpFile(File shpFile) throws IOException {
        List<String> polygons = new ArrayList<String>();

        if (shpFile.exists()) {
            
            ShapefileDataStore sds = (ShapefileDataStore) dataStoreFactory.createDataStore(shpFile.toURI().toURL());
            sds.setCharset(Charset.forName("GBK"));
            SimpleFeatureSource featureSource = sds.getFeatureSource();
            SimpleFeatureIterator itertor = featureSource.getFeatures().features();

            while (itertor.hasNext()) {
                SimpleFeature feature = itertor.next();
                String wkt =  feature.getDefaultGeometryProperty().getValue().toString();
                polygons.add(wkt);
                System.out.println(wkt);
            }
        }
        return polygons;
    }
    
//    public static void main(String[] args) {
//        ShpFileUtil u = new ShpFileUtil();
//        
//        File f = new File("/Users/Administrator/Documents/三明/尤溪/cswl.shp");
//        try {
//            u.parserShpFile(f);
//        } catch (IOException ex) {
//            Logger.getLogger(ShpFileUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//                
//    }

}

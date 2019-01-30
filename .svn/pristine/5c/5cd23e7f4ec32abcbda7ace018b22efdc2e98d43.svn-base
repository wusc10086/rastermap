package org.rasterdb.utils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.rasterdb.domain.RProductinfo;

/**
 * 影像数据边界生成空间坐标
 *
 * @author Administrator
 */
@IocBean(fields = {"dao"}, name = "geoutilService")
public final class RasterGeoUtil extends IdEntityService {

    Logger  logger = Logger.getLogger(RasterGeoUtil.class);
    
    @Inject("dao2")
    protected Dao dao2;
    
    private Map<Integer, Integer> map = null;
    private final GeometryFactory gf = new GeometryFactory();
    private final WKTReader wktreader = new WKTReader();
    private final WKTWriter wktwriter = new WKTWriter();

    public RasterGeoUtil() {
        init();
    }

    public void initGeoTable() {
        List<RProductinfo> list = this.dao().query(RProductinfo.class, null);

        for (int i = 0; i < list.size(); i++) {
            RProductinfo rp = list.get(i);
            int center = rp.getCentralmederian();
            String sno = rp.getSn();
            double x1 =rp.getSouthwestabs();
            double y1 = rp.getSouthwestord();
            double x2 = rp.getSoutheastabs();
            double y2 = rp.getSoutheastord();

            String p1 = "point(" + x1 + " " + y1 + ")";

            try {
                p1 = transformSystem2Point(x1, y1, center);
                if(p1==null){
                    System.out.println("出错SN = "+rp.getSn());
                    continue;
                }
                Point point = (Point) wktreader.read(p1);
                //判断是否坐标颠倒了
                if(point.getY()<10){
                    p1 = transformSystem2Point(y1, x1, center);
                    point = (Point) wktreader.read(p1);
                }
                x1 = point.getX();
                y1 = point.getY();
            } catch (ParseException ex) {
                logger.error("解析出错", ex);
            }
            if (x2 <= 180) {

            } else {
                try {
                    String p2 = "point(" + x2 + " " + y2 + ")";
                    p2 = transformSystem2Point(x2, y2, center);
                    Point point = (Point) wktreader.read(p2);
                    
                    //判断是否坐标颠倒了
                if(point.getY()<10){
                    
                    p2 = transformSystem2Point(y2, x2, center);
                    point = (Point) wktreader.read(p2);
                }
                    
                    x2 = point.getX();
                    y2 = point.getY();
                } catch (ParseException ex) {
                     logger.error("解析出错", ex);
                }
            }

            double x3 = rp.getNortheastabs();
            double y3 = rp.getNortheastord();
            double x4 = rp.getNorthwestabs(); 
            double y4 = rp.getNorthwestord();

            try {
                String p3 = "point(" + x3 + " " + y3 + ")";
                p3 = transformSystem2Point(x3, y3, center);
                Point point = (Point) wktreader.read(p3);
                
                //判断是否坐标颠倒了
                if(point.getY()<10){
                    
                    p3 = transformSystem2Point(y3, x3, center);
                    point = (Point) wktreader.read(p3);
                }
                
                x3 = point.getX();
                y3 = point.getY();
            } catch (ParseException ex) {
                 logger.error("解析出错", ex);
            }
            if (x4 <= 180) {

            } else {
                try {
                    String p4 = "point(" + x4 + " " + y4 + ")";
                    p4 = transformSystem2Point(x4, y4, center);
                    Point point = (Point) wktreader.read(p4);
                    
                    //判断是否坐标颠倒了
                if(point.getY()<10){
                    
                    p4 = transformSystem2Point(y4, x4, center);
                    point = (Point) wktreader.read(p4);
                }
                    
                    x4 = point.getX();
                    y4 = point.getY();
                } catch (ParseException ex) {
                    logger.error("解析出错", ex);
                }
            }

            String wkt = "polygon((" + x1 + " " + y1 + "," + x2 + " " + y2 + "," + x3 + " " + y3 + "," + x4 + " " + y4 + "," + x1 + " " + y1 + "))";
//            String wkt = "polygon((" +110 + " " + 30 + "," + 111 + " " + 30 + "," + 111 + " " + 32 + "," + 110 + " " + 32 + "," + 110 + " " + 30 + "))";
            // String wgs84wkt = transformPolygon(wkt, center);

            //构建插入语句
//            String insert = "insert into rasterextent (id,sno,extent) values(" + i + ",'" + sno + "'," + "sdo_geometry('" + wkt + "',4326))";
            String insert = "insert into rasterextent (id,sno,extent) values(" + i + ",'" + sno + "'," + "st_geomfromtext('" + wkt + "',4326))";
            
            if(logger.isDebugEnabled()){
                 logger.debug("insert sql = " + insert);
            }
            
           
            //执行插入操作
//            Sql sqls = this.dao().execute(Sqls.create(insert));
            dao2.execute(Sqls.create(insert));
        }
    }

    /**
     * 坐标转换，将WGS84的坐标转换成 CGCS2000 的坐标
     *
     * @param x
     * @param y
     * @param center
     * @return
     */
    public String transformSystem2Point(double x, double y, int center) {
        String pos = null;
        Integer srid = getSrid(center);
        if (srid != null) {
            try {
                CoordinateReferenceSystem sourceCRS = null;
                CoordinateReferenceSystem targetCRS = null;

                CRSAuthorityFactory factory = CRS.getAuthorityFactory(true);
                try {
                    sourceCRS = factory.createCoordinateReferenceSystem("EPSG:" + srid);
                    targetCRS = factory.createCoordinateReferenceSystem("EPSG:4326");
                } catch (FactoryException ex) {
                     logger.error( ex);
                }

                Point sp = gf.createPoint(new Coordinate(x, y));
                Point p = (Point) transform(sp, sourceCRS, targetCRS);
                pos = wktwriter.write(p);
            } catch (MismatchedDimensionException ex) {
                 logger.error( ex);
            }
        }
        return pos;
    }

    public String transformPoint2System(double x, double y, int center) {
        String pos = null;
        Integer srid = getSrid(center);
        if (srid != null) {
            try {
                CoordinateReferenceSystem targetCRS = null;
                CoordinateReferenceSystem sourceCRS = null;

                CRSAuthorityFactory factory = CRS.getAuthorityFactory(true);
                try {
                    targetCRS = factory.createCoordinateReferenceSystem("EPSG:" + srid);
                    sourceCRS = factory.createCoordinateReferenceSystem("EPSG:4326");
                } catch (FactoryException ex) {
                     logger.error( ex);
                }
                Point sp = gf.createPoint(new Coordinate(x, y));
                Point p = (Point) transform(sp, sourceCRS, targetCRS);
                pos = wktwriter.write(p);
            } catch (MismatchedDimensionException ex) {
                 logger.error( ex);
            }
        }
        return pos;
    }

    /**
     * 坐标转换，将CGCS2000 的坐标转换成WGS84 的坐标
     *
     * @param wktpolygon
     * @param center
     * @return
     */
    public String transformPolygon(String wktpolygon, int center) {
        String polygon = null;
        Integer srid = getSrid(center);
        if (srid != null) {
            try {
                CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:" + srid);
                CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:4326");
                Geometry poly = wktreader.read(wktpolygon);
                Geometry respoly = transform(poly, sourceCRS, targetCRS);
                polygon = wktwriter.write(respoly);
            } catch (FactoryException ex) {
                logger.error( ex);
            } catch (ParseException ex) {
                logger.error( ex);
            }
        } else {
            System.out.println("获取 srid 出错，center=" + center);
        }
        return polygon;
    }

    public Geometry transform(Geometry geom, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS) {
        Geometry g = null;
        try {
            MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS, true);
            g = JTS.transform(geom, transform);
        } catch (FactoryException ex) {
            logger.error( ex);
        } catch (MismatchedDimensionException ex) {
            logger.error( ex);
        } catch (TransformException ex) {
            logger.error( ex);
        }
        return g;
    }

    /**
     * 清除所有记录
     */
    public void clearData() {
        dao2.clear("rasterextent");
    }

    public Integer getSrid(int center) {
        return map.get(center);
    }

    public void init() {
        map = new HashMap<Integer, Integer>();
        map.put(135, 4512);
        map.put(129, 4511);
        map.put(123, 4510);
        map.put(117, 4509);
        map.put(111, 4508);
        map.put(105, 4507);
        map.put(99, 4506);
        map.put(93, 4505);
        map.put(108, 4545);
        map.put(21, 4499);
        map.put(81, 4503);
        map.put(87, 4504);
//        map.put(243, 4505);
        map.put(114, 4547);
//        map.put(195, 4505);
        map.put(105, 4507);
        map.put(0, 4490);
    }

}

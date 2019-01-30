package org.rasterdb.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;
import org.rasterdb.domain.RDataproductioninfo;
import org.rasterdb.domain.RDatasourceinfo;
import org.rasterdb.domain.RProductinfo;
import org.rasterdb.service.parma.DataSourceParm;
import org.rasterdb.service.parma.ProductParma;
import org.rasterdb.service.parma.RasterParm;
import org.rasterdb.domain.JsDataProductinfo;
import org.rasterdb.service.parma.JSproductionParm;
import java.util.Date;
import org.nutz.dao.Chain;
import org.rasterdb.domain.Order;

/**
 * 影像数据查询服务，提供影像的各种信息的查询
 *
 * @author Administrator
 */
@IocBean(fields = {"dao"}, name = "queryService")
public class QueryService extends IdEntityService {

    public void intersectQuery() {

    }

    /**
     * 获取所有Product信息。
     *
     * @return
     */
    public List<RProductinfo> getProductInfos() {
        return this.dao().query(RProductinfo.class, Cnd.where("1", "=", 1));
    }

    /**
     * 江苏 查询所有数据yzz
     */
    public List<JsDataProductinfo> getJsProduction() {
        return this.dao().query(JsDataProductinfo.class, Cnd.where("1", "=", 1));
    }

    /**
     * JS 条件查询 yzz 可用
     * @param parm 接受前端传来的参数
     * @return
     * @throws ParseException 
     */
    public List<JsDataProductinfo> getJsProduction(JSproductionParm parm) throws ParseException {
        Cnd cnd = Cnd.where("1", "=", 1);
        if (parm.getSatallite() != null && !parm.getSatallite().isEmpty()) {
            cnd.and("satelitetype", "=", parm.getSatallite());
        }
        if (parm.getDemsource() != null && !parm.getDemsource().isEmpty()) {
            cnd.and("demsource", "=", parm.getDemsource());
        }
        if (parm.getColormode() != null && !parm.getColormode().isEmpty()) {
            cnd.and("imagecolor", "=", parm.getColormode());
        }
        if (parm.getStarttime() != null && !parm.getStarttime().isEmpty()) {
            
            String s = parm.getStarttime();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date d=sdf.parse(s);
            cnd.and("ProductDate", ">=", d);
        }
        if (parm.getEndtime() != null && !parm.getEndtime().isEmpty()) {
            String s = parm.getEndtime();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date d=sdf.parse(s);
            cnd.and("ProductDate", "<=", d);
        }
        return this.dao().query(JsDataProductinfo.class, cnd);
    }

    /**
     * 根据产品参数来查询
     *
     * @param pro
     * @return
     */
    public List<RProductinfo> getProductInfos(ProductParma pro) {
        Cnd cnd = Cnd.where("1", "=", 1);

        if (pro.getProducttype() != null && !pro.getProducttype().isEmpty()) {
            cnd.and("productname", "=", pro.getProducttype());
        }
        if (pro.getProducer() != null && !pro.getProducer().isEmpty()) {
            cnd.and("producer", "=", pro.getProducer());
        }

        if (pro.getColormode() != null && !pro.getColormode().isEmpty()) {
            cnd.and("imgcolormodel", "=", pro.getColormode());
        }
        if (pro.getResolution() != null) {
            double res = pro.getResolution() / (double) 10;
            cnd.and("groundresolution", "=", res);
        }

        if (pro.getStarttime() != null && pro.getEndtime() != null) {
            cnd.and("producedate", ">=", pro.getStarttime());
            cnd.and("producedate", "<=", pro.getEndtime());
        }

        return this.dao().query(RProductinfo.class, cnd);
    }

    /**
     * 根据数据源参数查询数据
     *
     * @param dp
     * @return
     */
    public List<Map> getDataSourceList(DataSourceParm dp) {
        Cnd cnd = Cnd.where("1", "=", 1);
        //检测卫星名称
        if (dp.getSatallite() != null) {
            cnd.and("a.satename", "=", dp.getSatallite());
        }

        //检测色彩模式
        if (dp.getColormode() != null && !dp.getColormode().isEmpty()) {
            if (dp.getColormode().equals("多光谱")) {
                cnd.and("a.multibandsensortype", "=", dp.getCgq());
                if (dp.getStarttime() != null && dp.getEndtime() != null) {
                    cnd.and("a.multibanddate", ">=", dp.getStarttime());
                    cnd.and("a.multibanddate", "<=", dp.getEndtime());
                }
            }

            if (dp.getColormode().equals("全色")) {
                cnd.and("a.pbandsensortype", "=", dp.getCgq());
                if (dp.getStarttime() != null && dp.getEndtime() != null) {
                    cnd.and("a.pbanddate", ">=", dp.getStarttime());
                    cnd.and("a.pbanddate", "<=", dp.getEndtime());
                }
            }
            //设置色彩模式
            cnd.and("b.imgcolormodel", "=", dp.getColormode());

        } else {
            cnd.and("a.multibandsensortype", "=", dp.getCgq());
            if (dp.getStarttime() != null && dp.getEndtime() != null) {
                cnd.and("a.multibanddate", ">=", dp.getStarttime());
                cnd.and("a.multibanddate", "<=", dp.getEndtime());
            }
            cnd.and("a.pbandsensortype", "=", dp.getCgq());
            if (dp.getStarttime() != null && dp.getEndtime() != null) {
                cnd.and("a.pbanddate", ">=", dp.getStarttime());
                cnd.and("a.pbanddate", "<=", dp.getEndtime());
            }
        }

        String wheresql = cnd.toString() + " AND a.sn=b.sn";

        String q = "select a.sn,a.satename,a.pbanddate,a.pbandsensortype,a.multibandsensortype,a.multibanddate,b.productname,b.imgcolormodel from r_datasourceinfo a,r_productinfo b " + wheresql;

        Sql sqls = Sqls.create(q);
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Map> listmap = new ArrayList<Map>();

                while (rs.next()) {
                    Map map = new HashMap();
                    map.put("sn", rs.getString("sn"));
                    map.put("satename", rs.getString("satename"));
                    map.put("multibanddate", rs.getString("multibanddate"));
                    map.put("pbanddate", rs.getString("pbanddate"));
                    map.put("multibandsensortype", rs.getString("multibandsensortype"));
                    map.put("pbandsensortype", rs.getString("pbandsensortype"));
                    listmap.add(map);
                }
                return listmap;
            }
        });

        List<Map> listmap = this.dao().execute(sqls).getObject(List.class);
        return listmap;
    }

    /**
     * 获取多光谱数据最新时间
     *
     * @return 多光谱数据最新时间
     */
    public String getLastRGBDataTime() {
        String date = "";
        RDatasourceinfo source = this.dao().fetch(RDatasourceinfo.class, Cnd.wrap("multibanddate is not NULL and multibandnum > 0 order by multibanddate desc"));
        if (source != null) {
            date = source.getMultibanddate();
        }
        return date;
    }

    /**
     * 获取全色地图数据最新时间
     *
     * @return 全色地图数据最新时间
     */
    public String getLastPbandDataTime() {
        String date = "";
        RDatasourceinfo source = this.dao().fetch(RDatasourceinfo.class, Cnd.wrap(" where pbanddate  is not  NULL  order by pbanddate desc"));
        if (source != null) {
            date = source.getPbanddate();
        }
        return date;
    }

    /**
     * 根据复杂条件查询数据
     *
     * @deprecated
     * @param rp
     * @return
     */
    public Object getListRasterFiles(RasterParm rp) {
        String whereSql = "";
        Cnd cnd = Cnd.where("1", "=", 1);
        if (rp.getAreaid() != null) {
            cnd.and("areaid", "=", rp.getAreaid());
        }
        if (rp.getCityid() != null) {
            cnd.and("cityid", "=", rp.getCityid());
        }
        if (rp.getProvinceid() != null) {
            cnd.and("provinceid", "=", rp.getProvinceid());
        }

        if (rp.getColorMode() != null) {
            cnd.and("imgcolormodel", "=", rp.getColorMode());
            if (rp.getColorMode().equals("多光谱")) {
                cnd.and("multibandsensortype", "=", rp.getCgq());
                if (rp.getStarttime() != null && rp.getEndtime() != null) {
                    cnd.and("multibanddate", ">=", rp.getStarttime());
                    cnd.and("multibanddate", "<=", rp.getEndtime());
                }
            } else {
                cnd.and("pbandsensortype", "=", rp.getCgq());
                if (rp.getStarttime() != null && rp.getEndtime() != null) {
                    cnd.and("pbanddate", ">=", rp.getStarttime());
                    cnd.and("pbanddate", "<=", rp.getEndtime());
                }
            }
        }

        whereSql = cnd.toString();
        String q = "select * from r_datasourceinfo，r_productinfo";
        Sql sql = Sqls.create(q + whereSql);

        this.dao().execute(sql);

        return sql.getList(Object.class);
    }

    /**
     * 获取一幅影像的详细信息
     *
     * @param sno 影像元数据唯一值ID
     * @return 影像详细信息
     */
    public Map getRasterImageDetail(String sno) {

        RDatasourceinfo dsinfo = this.dao().fetch(RDatasourceinfo.class, Cnd.where("sno", "=", sno));
        RDataproductioninfo dpinfo = this.dao().fetch(RDataproductioninfo.class, Cnd.where("sno", "=", sno));
        RProductinfo pinfo = this.dao().fetch(RProductinfo.class, Cnd.where("sno", "=", sno));

        Map map = new HashMap();

        map.put("dsinfo", dsinfo);
        map.put("dpinfo", dpinfo);
        map.put("pinfo", pinfo);

        return map;

    }

    /**
     * TODO 实现获取拇指图
     *
     * @param sno
     * @return
     */
    public byte[] getThumbImage(String sno) {

        byte[] img = null;
        return img;

    }

    /**
     * TODO 获取影像缩略图
     *
     * @param sno
     * @return
     */
    public byte[] getMidleRasterImage(String sno) {
        byte[] img = null;
        return img;
    }
    
    /**
     * 下单服务
     * @param fid
     * @return 
     */
    public String saveInColletion(String userandimage) {
       int index= userandimage.indexOf("--");
       String usernameString=userandimage.substring(0,index);
       String imageString=userandimage.substring(index+2);
       
        Order order=new Order();
        order.setImage(imageString);
        order.setUsernameString(usernameString);
        order.setStatus(0);
        this.dao().insert(order);
        return "已提交申请";
    }
    /**
     * 初始化用户订单状态
     * @param username
     * @return 
     */
    public List<Order> queryOrderservice(String username) {
       return this.dao().query(Order.class, Cnd.where("username","=", username));
    }
    /**
     * 删除订单服务
     * @param userandimage
     * @return 
     */
    public String removeFromColletion(String userandimage) {
       int index= userandimage.indexOf("--");
       String usernameString=userandimage.substring(0,index);
       String imageString=userandimage.substring(index+2);
       String conditon="delete from js_order where username =" + "\'"+usernameString+"\'" + " and image = " +"\'"+imageString+"\'";
       Sql sql=Sqls.create(conditon);
       this.dao().execute(sql);
       return "已删除申请";
    }
   /**
    * 管理员审核数据初始化服务
    * @return 
    */
    public List<Order> adminQueryService() {
        return this.dao().query(Order.class,Cnd.where("status","!=",1));
    }
    /**
     * 
     * @param parameters
     * @return 
     */
    public int adUpdateOrderStatusService(String parameters) {
        int speratePara1=parameters.indexOf("--");
        int speratePara2=parameters.lastIndexOf("--");
        String uaString=parameters.substring(0,speratePara1);
        String imageString=parameters.substring(speratePara1+2,speratePara2);
        String statuscode=parameters.substring(speratePara2+2);
        int sc=Integer.parseInt(statuscode);
        Cnd conditionCnd=Cnd.where("username","=", uaString);
        conditionCnd.and("image","=", imageString);
        return this.dao().update(Order.class,Chain.make("status",sc ) ,conditionCnd);
        
    }

    public int userUpdateOrderStatusService(String parameters) {
        int speratePara1=parameters.indexOf("--");
        int speratePara2=parameters.lastIndexOf("--");
        String uaString=parameters.substring(0,speratePara1);
        String imageString=parameters.substring(speratePara1+2,speratePara2);
        String statuscode=parameters.substring(speratePara2+2);
        int sc=Integer.parseInt(statuscode);
        Cnd conditionCnd=Cnd.where("username","=", uaString);
        conditionCnd.and("image","=", imageString);
        return this.dao().update(Order.class,Chain.make("status",sc ) ,conditionCnd);
    }
}

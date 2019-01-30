package org.rasterdb.modules;

import com.beyondb.bigmap.services.TileService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;

/**
 * 瓦片地图服务模块
 * @author 
 */
@IocBean
@At("/tile")
public class TileModule {

    @Inject
    private TileService tileService;

    @POST
    @GET
    @Ok("raw")
    @At("/maptile/?/?/?/?")
    public Object getMapTile(String type, String zoom, String x, String y, HttpServletResponse response) {
        try {
            response.setContentType("image");
           // PrintWriter out = response.getWriter();
           
            byte[] allBytesInBlob = tileService.getTile(x, y, zoom, type);

            if (allBytesInBlob != null) {
               
             return allBytesInBlob;
            } 

        } catch (SQLException ex) {
            Logger.getLogger(TileModule.class.getName()).log(Level.SEVERE, null, ex);
              return  "no image";
        }  
        return  "no image";
    }
}

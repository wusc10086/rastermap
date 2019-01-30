package com.beyondb.bigmap.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdNameEntityService;

/**
 *  瓦片服务
 * @author Administrator
 */
@IocBean(name = "tileService", fields = {"dao"})
public class TileService extends IdNameEntityService {
    
    private byte[] img =null;
    private boolean online =false;

    public TileService() {
        super();
    }
    public TileService(boolean online) {
        super();
        this.online = online;
    }
    
    public TileService(Dao dao) {
        super(dao);
    }
  

    public byte[] getTile(String x, String y, String zoom, String type) throws SQLException {
        byte[] allBytesInBlob = null;

        String query = String.format("select Tile from gmapnetcache   where X = %s and Y = %s and Zoom = %s and Type = %s", x, y, zoom, type);

        Sql sql = Sqls.create(query);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                byte[] allBytesInBlob = null;
                if (rs != null) {
                    try {
                        if (rs.next()) {
                            allBytesInBlob = rs.getBytes(1);
                        }
                    } finally {
                        try {
                            rs.close();
                        } catch (Exception ignore) {
                        }
                    }
                }
                return allBytesInBlob;
            }
        });
        this.dao().execute(sql);
        allBytesInBlob = sql.getObject(byte[].class);
        if(allBytesInBlob == null){
            allBytesInBlob = getNoTileImage();
        }
        return allBytesInBlob;
    }

    public byte[] getNoTileImage() {
        
        if(online){
            System.out.println("online service!=====================================");
        } 
        if(img != null){
            return img;
        }
        try {
            URL imgurl = this.getClass().getResource("/com/beyondb/bigmap/images/notile.png");
            BufferedImage bufimg = ImageIO.read(imgurl);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufimg, "png", baos);
            img = baos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(TileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
}

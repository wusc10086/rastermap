package org.rasterdb.domain;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.View;

/**
 *
 * @author Administrator
 */
@View("satellite_view")
public class SatelliteView {
    
    @Column("satelitetype")
    private String satename;
    
    @Column("imagecolor")
    private String imagecolor;
    
    @Column("demsource")
    private String demsource;

    public String getImagecolor() {
        return imagecolor;
    }

    public void setImagecolor(String imagecolor) {
        this.imagecolor = imagecolor;
    }

    public String getDemsource() {
        return demsource;
    }

    public void setDemsource(String demsource) {
        this.demsource = demsource;
    }
    
    public String getSatename() {
        return satename;
    }

    public void setSatename(String satename) {
        this.satename = satename;
    }
}

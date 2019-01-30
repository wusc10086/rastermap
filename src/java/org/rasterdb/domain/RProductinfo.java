package org.rasterdb.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;

import org.nutz.dao.entity.annotation.Table;

/**
 * 产品信息
 * @author Administrator
 */

@Table("R_PRODUCTINFO")

public class RProductinfo implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//
//    @Column("ID")
    private Integer id;
    
    @Column("SN")
    private String sn;
    
    @Column("METADATAFILENAME")
    private String metadatafilename;
    
    @Column("PRODUCTNAME")
    private String productname;
    
    @Column("OWNER")
    private String owner;
    
    @Column("PRODUCER")
    private String producer;
    
    @Column("PUBLISHER")
    private String publisher;
    
    @Column("PRODUCTDATE")
    private String producedate;
 
    @Column("CONFIDENTIALLEVEL")
    private String confidentiallevel;
    @Column("GROUNDRESOLUTION")
    private Integer groundresolution;
    
    @Column("IMGCOLORMODEL")
    private String imgcolormodel;
    @Column("PIXELBITS")
    private Integer pixelbits;
    @Column("IMGSIZE")
    private Integer imgsize;
    
    @Column("DataFormat")
    private String DataFormat;
    @Column("SOUTHWESTABS")
    private Integer southwestabs;
    @Column("SOUTHWESTORD")
    private Integer southwestord;
    @Column("NORTHWESTABS")
    private Integer northwestabs;
    @Column("NORTHWESTORD")
    private Integer northwestord;
    @Column("NORTHEASTABS")
    private Integer northeastabs;
    @Column("NORTHEASTORD")
    private Integer northeastord;
    @Column("SOUTHEASTABS")
    private Integer southeastabs;
    @Column("SOUTHEASTORD")
    private Integer southeastord;
    @Column("LONGERRADIUS")
    private Integer longerradius;
    
    @Column("OBLATUSRATIO")
    private String oblatusratio;
    
    @Column("GEODETICDATUM")
    private String geodeticdatum;
    
    @Column("MAPPROJECTION")
    private String mapprojection;
    @Column("CENTRALMEDERIAN")
    private Integer centralmederian;
    
    @Column("ZONEDIVISIONMODE")
    private String zonedivisionmode;
    @Column("GAUSSKRUGERZONENO")
    private Integer gausskrugerzoneno;
    
    @Column("imagecolor")
    private String imagecolor;
    
    @Column("COORDINATIONUNIT")
    private String coordinationunit;
    
    @Column("HEIGHTSYSTEM")
    private String heightsystem;
    
    @Column("HEIGHTDATUM")
    private String heightdatum;
    
    private String extent;

    public RProductinfo() {
    }

    public String getImagecolor() {
        return imagecolor;
    }

    public void setImagecolor(String imagecolor) {
        this.imagecolor = imagecolor;
    }

    public RProductinfo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMetadatafilename() {
        return metadatafilename;
    }

    public void setMetadatafilename(String metadatafilename) {
        this.metadatafilename = metadatafilename;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getProducedate() {
        return producedate;
    }

    public void setProducedate(String producedate) {
        this.producedate = producedate;
    }

    public String getConfidentiallevel() {
        return confidentiallevel;
    }

    public void setConfidentiallevel(String confidentiallevel) {
        this.confidentiallevel = confidentiallevel;
    }

    public Integer getGroundresolution() {
        return groundresolution;
    }

    public void setGroundresolution(Integer groundresolution) {
        this.groundresolution = groundresolution;
    }

    public String getImgcolormodel() {
        return imgcolormodel;
    }

    public void setImgcolormodel(String imgcolormodel) {
        this.imgcolormodel = imgcolormodel;
    }

    public Integer getPixelbits() {
        return pixelbits;
    }

    public void setPixelbits(Integer pixelbits) {
        this.pixelbits = pixelbits;
    }

    public Integer getImgsize() {
        return imgsize;
    }

    public void setImgsize(Integer imgsize) {
        this.imgsize = imgsize;
    }

    public String getDataFormat() {
        return DataFormat;
    }

    public void setDataFormat(String DataFormat) {
        this.DataFormat = DataFormat;
    }

   

    public Integer getSouthwestabs() {
        return southwestabs;
    }

    public void setSouthwestabs(Integer southwestabs) {
        this.southwestabs = southwestabs;
    }

    public Integer getSouthwestord() {
        return southwestord;
    }

    public void setSouthwestord(Integer southwestord) {
        this.southwestord = southwestord;
    }

    public Integer getNorthwestabs() {
        return northwestabs;
    }

    public void setNorthwestabs(Integer northwestabs) {
        this.northwestabs = northwestabs;
    }

    public Integer getNorthwestord() {
        return northwestord;
    }

    public void setNorthwestord(Integer northwestord) {
        this.northwestord = northwestord;
    }

    public Integer getNortheastabs() {
        return northeastabs;
    }

    public void setNortheastabs(Integer northeastabs) {
        this.northeastabs = northeastabs;
    }

    public Integer getNortheastord() {
        return northeastord;
    }

    public void setNortheastord(Integer northeastord) {
        this.northeastord = northeastord;
    }

    public Integer getSoutheastabs() {
        return southeastabs;
    }

    public void setSoutheastabs(Integer southeastabs) {
        this.southeastabs = southeastabs;
    }

    public Integer getSoutheastord() {
        return southeastord;
    }

    public void setSoutheastord(Integer southeastord) {
        this.southeastord = southeastord;
    }

    public Integer getLongerradius() {
        return longerradius;
    }

    public void setLongerradius(Integer longerradius) {
        this.longerradius = longerradius;
    }

    public String getOblatusratio() {
        return oblatusratio;
    }

    public void setOblatusratio(String oblatusratio) {
        this.oblatusratio = oblatusratio;
    }

    public String getGeodeticdatum() {
        return geodeticdatum;
    }

    public void setGeodeticdatum(String geodeticdatum) {
        this.geodeticdatum = geodeticdatum;
    }

    public String getMapprojection() {
        return mapprojection;
    }

    public void setMapprojection(String mapprojection) {
        this.mapprojection = mapprojection;
    }

    public Integer getCentralmederian() {
        return centralmederian;
    }

    public void setCentralmederian(Integer centralmederian) {
        this.centralmederian = centralmederian;
    }

    public String getZonedivisionmode() {
        return zonedivisionmode;
    }

    public void setZonedivisionmode(String zonedivisionmode) {
        this.zonedivisionmode = zonedivisionmode;
    }

    public Integer getGausskrugerzoneno() {
        return gausskrugerzoneno;
    }

    public void setGausskrugerzoneno(Integer gausskrugerzoneno) {
        this.gausskrugerzoneno = gausskrugerzoneno;
    }

    public String getCoordinationunit() {
        return coordinationunit;
    }

    public void setCoordinationunit(String coordinationunit) {
        this.coordinationunit = coordinationunit;
    }

    public String getHeightsystem() {
        return heightsystem;
    }

    public void setHeightsystem(String heightsystem) {
        this.heightsystem = heightsystem;
    }

    public String getHeightdatum() {
        return heightdatum;
    }

    public void setHeightdatum(String heightdatum) {
        this.heightdatum = heightdatum;
    }

    public String getExtent() {
        return extent;
    }

    public void setExtent(String extent) {
        this.extent = extent;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RProductinfo)) {
            return false;
        }
        RProductinfo other = (RProductinfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.rasterdb.domain.RProductinfo[ id=" + id + " ]";
    }
    
}

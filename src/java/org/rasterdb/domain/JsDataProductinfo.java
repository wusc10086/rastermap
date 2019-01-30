/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rasterdb.domain;

import java.io.Serializable;
import java.util.Date;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

/**
 *
 * @author yangzz
 */
@Table("js_image")
public class JsDataProductinfo implements Serializable {

    private static final long servicalVersionID = 1;
    @Column("dataName")
    private String dataName;

    @Column("SateliteType")
    private String SataliteType;

    @Column("NumImage")
    private String NumImage;

    @Column("ProductDate")
    private Date ProductDate;

    @Column("CorrectionMethods")
    private String CorrectionMethods;

    @Column("SensorType")
    private String SensorType;

    @Column("ImageDate")
    private String ImageDate;

    @Column("BandSelection")
    private String BandSelection;

    @Column("DEMSource")
    private String DEMSource;

    @Column("BandSelection")
    private String CoordinateSystem;

    @Column("CoordinateSystem")
    private String Ellipsoid;

    @Column("CoordinateUnit")
    private String CoordinateUnit;

    @Column("Resolution")
    private String Resolution;

    @Column("BandSelection")
    private String DataFormat;

    @Column("DataAmount")
    private Double DataAmount;

    @Column("DataIntegrity")
    private String DataIntegrity;

    @Column("DatelongitudeRange")
    private String DatelongitudeRange;

    @Column("DataLatitudeRang")
    private String DataLatitudeRang;

    @Column("LongRadius")
    private Double LongRadius;

    @Column("ShortRadius")
    private Double ShortRadius;

    @Column("Flattening")
    private String Flattening;

    @Column("EdgeProblem")
    private String EdgeProblem;

    @Column("EdgeQuality")
    private String EdgeQuality;

    @Column("DataOverallQuality")
    private String DataOverallQuality;

    @Column("SecurityClassification")
    private String SecurityClassification;

    @Column("Deep")
    private String Deep;

    @Column("productor")
    private String productor;

    @Column("management")
    private String management;

    @Column("DataOwnership")
    private String DataOwnership;

    @Column("description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String extent;

    public String getExtent() {
        return extent;
    }

    public void setExtent(String extent) {
        this.extent = extent;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getSataliteType() {
        return SataliteType;
    }

    public void setSataliteType(String SataliteType) {
        this.SataliteType = SataliteType;
    }

    public String getNumImage() {
        return NumImage;
    }

    public void setNumImage(String NumImage) {
        this.NumImage = NumImage;
    }

    public String getImageDate() {
        return ImageDate;
    }

    public void setImageDate(String ImageDate) {
        this.ImageDate = ImageDate;
    }

    public Date getProductDate() {
        return ProductDate;
    }

    public void setProductDate(Date ProductDate) {
        this.ProductDate = ProductDate;
    }

    public String getCorrectionMethods() {
        return CorrectionMethods;
    }

    public void setCorrectionMethods(String CorrectionMethods) {
        this.CorrectionMethods = CorrectionMethods;
    }

    public String getSensorType() {
        return SensorType;
    }

    public void setSensorType(String SensorType) {
        this.SensorType = SensorType;
    }

    public String getBandSelection() {
        return BandSelection;
    }

    public void setBandSelection(String BandSelection) {
        this.BandSelection = BandSelection;
    }

    public String getDEMSource() {
        return DEMSource;
    }

    public void setDEMSource(String DEMSource) {
        this.DEMSource = DEMSource;
    }

    public String getCoordinateSystem() {
        return CoordinateSystem;
    }

    public void setCoordinateSystem(String CoordinateSystem) {
        this.CoordinateSystem = CoordinateSystem;
    }

    public String getEllipsoid() {
        return Ellipsoid;
    }

    public void setEllipsoid(String Ellipsoid) {
        this.Ellipsoid = Ellipsoid;
    }

    public String getCoordinateUnit() {
        return CoordinateUnit;
    }

    public void setCoordinateUnit(String CoordinateUnit) {
        this.CoordinateUnit = CoordinateUnit;
    }

    public String getResolution() {
        return Resolution;
    }

    public void setResolution(String Resolution) {
        this.Resolution = Resolution;
    }

    public String getDataFormat() {
        return DataFormat;
    }

    public void setDataFormat(String DataFormat) {
        this.DataFormat = DataFormat;
    }

    public Double getDataAmount() {
        return DataAmount;
    }

    public void setDataAmount(Double DataAmount) {
        this.DataAmount = DataAmount;
    }

    public String getDataIntegrity() {
        return DataIntegrity;
    }

    public void setDataIntegrity(String DataIntegrity) {
        this.DataIntegrity = DataIntegrity;
    }

    public String getDatelongitudeRange() {
        return DatelongitudeRange;
    }

    public void setDatelongitudeRange(String DatelongitudeRange) {
        this.DatelongitudeRange = DatelongitudeRange;
    }

    public String getDataLatitudeRang() {
        return DataLatitudeRang;
    }

    public void setDataLatitudeRang(String DataLatitudeRang) {
        this.DataLatitudeRang = DataLatitudeRang;
    }

    public Double getLongRadius() {
        return LongRadius;
    }

    public void setLongRadius(Double LongRadius) {
        this.LongRadius = LongRadius;
    }

    public Double getShortRadius() {
        return ShortRadius;
    }

    public void setShortRadius(Double ShortRadius) {
        this.ShortRadius = ShortRadius;
    }

    public String getFlattening() {
        return Flattening;
    }

    public void setFlattening(String Flattening) {
        this.Flattening = Flattening;
    }

    public String getEdgeProblem() {
        return EdgeProblem;
    }

    public void setEdgeProblem(String EdgeProblem) {
        this.EdgeProblem = EdgeProblem;
    }

    public String getEdgeQuality() {
        return EdgeQuality;
    }

    public void setEdgeQuality(String EdgeQuality) {
        this.EdgeQuality = EdgeQuality;
    }

    public String getDataOverallQuality() {
        return DataOverallQuality;
    }

    public void setDataOverallQuality(String DataOverallQuality) {
        this.DataOverallQuality = DataOverallQuality;
    }

    public String getSecurityClassification() {
        return SecurityClassification;
    }

    public void setSecurityClassification(String SecurityClassification) {
        this.SecurityClassification = SecurityClassification;
    }

    public String getDeep() {
        return Deep;
    }

    public void setDeep(String Deep) {
        this.Deep = Deep;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public String getDataOwnership() {
        return DataOwnership;
    }

    public void setDataOwnership(String DataOwnership) {
        this.DataOwnership = DataOwnership;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }
}

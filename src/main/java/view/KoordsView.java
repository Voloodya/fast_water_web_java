package view;

import java.io.Serializable;

public class KoordsView implements Serializable {
    private Integer idGeographKoords;
    private String koordinateTxt;
    private String typeKoordinate;
    private Integer srid;
    private Double radiusAction;
    private Double heighSeaLevel;
    private String description;

    public KoordsView(String typeKoordinate, Integer srid, Double radiusAction, Double heighSeaLevel, String koordinateTxt, String description) {
        this.typeKoordinate = typeKoordinate;
        this.srid = srid;
        this.radiusAction = radiusAction;
        this.heighSeaLevel = heighSeaLevel;
        this.koordinateTxt = koordinateTxt;
        this.description = description;
    }

    public KoordsView() {
    }

    public KoordsView(Integer idGeographKoords, String typeKoordinate, Integer srid, Double radiusAction, Double heighSeaLevel, String koordinateTxt, String description) {
        this.idGeographKoords = idGeographKoords;
        this.typeKoordinate = typeKoordinate;
        this.srid = srid;
        this.radiusAction = radiusAction;
        this.heighSeaLevel = heighSeaLevel;
        this.koordinateTxt = koordinateTxt;
        this.description = description;
    }

    public Integer getIdGeographKoords() {
        return idGeographKoords;
    }

    public String getTypeKoordinate() {
        return typeKoordinate;
    }

    public Integer getSrid() {
        return srid;
    }

    public Double getRadiusAction() {
        return radiusAction;
    }

    public Double getHeighSeaLevel() {
        return heighSeaLevel;
    }

    public String getKoordinateTxt() {
        return koordinateTxt;
    }

    public String getDescription() {
        return description;
    }

    public void setIdGeographKoords(Integer idGeographKoords) {
        this.idGeographKoords = idGeographKoords;
    }

    public void setTypeKoordinate(String typeKoordinate) {
        this.typeKoordinate = typeKoordinate;
    }

    public void setSrid(Integer srid) {
        this.srid = srid;
    }

    public void setRadiusAction(Double radiusAction) {
        this.radiusAction = radiusAction;
    }

    public void setHeighSeaLevel(Double heighSeaLevel) {
        this.heighSeaLevel = heighSeaLevel;
    }

    public void setKoordinateTxt(String koordinateTxt) {
        this.koordinateTxt = koordinateTxt;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

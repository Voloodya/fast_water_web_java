package view;

import entity.*;

import java.io.Serializable;
import java.util.Collection;

public class PostView implements Serializable {
    private int idPost;
    private String namePost;
    private Double distanceBeetwenSensors;
    private String geographkoords;
    private double hightSeaLevel;
    private String basinLocality;
    private String groundByGroundId;
    private String localityByLocalityId;
    private String description;

    public PostView(int idPost, String namePost, Double distanceBeetwenSensors, String geographkoords,double hightSeaLevel, String basinLocality, String groundByGroundId, String localityByLocalityId, String description) {
        this.idPost = idPost;
        this.namePost = namePost;
        this.distanceBeetwenSensors = distanceBeetwenSensors;
        this.geographkoords = geographkoords;
        this.hightSeaLevel=hightSeaLevel;
        this.basinLocality = basinLocality;
        this.groundByGroundId = groundByGroundId;
        this.localityByLocalityId = localityByLocalityId;
        this.description = description;
    }

    public PostView() {
    }

    public PostView(int idPost) {
        this.idPost = idPost;
    }

    public int getIdPost() {
        return idPost;
    }

    public String getNamePost() {
        return namePost;
    }

    public Double getDistanceBeetwenSensors() {
        return distanceBeetwenSensors;
    }

    public String getGeographkoords() {
        return geographkoords;
    }

    public String getBasinLocality() {
        return basinLocality;
    }

    public String getGroundByGroundId() {
        return groundByGroundId;
    }

    public String getLocalityByLocalityId() {
        return localityByLocalityId;
    }

    public String getDescription() {
        return description;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public void setNamePost(String namePost) {
        this.namePost = namePost;
    }

    public void setDistanceBeetwenSensors(Double distanceBeetwenSensors) {
        this.distanceBeetwenSensors = distanceBeetwenSensors;
    }

    public void setGeographkoords(String geographkoords) {
        this.geographkoords = geographkoords;
    }

    public void setBasinLocality(String basinLocality) {
        this.basinLocality = basinLocality;
    }

    public void setGroundByGroundId(String groundByGroundId) {
        this.groundByGroundId = groundByGroundId;
    }

    public void setLocalityByLocalityId(String localityByLocalityId) {
        this.localityByLocalityId = localityByLocalityId;
    }

    public double getHightSeaLevel() {
        return hightSeaLevel;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

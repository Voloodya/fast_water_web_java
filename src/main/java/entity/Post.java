package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "Post", schema = "db_fastwater", catalog = "")
public class Post implements Serializable {

    private Integer idPost;
    private String namePost;
    // private Integer geographKoordsId;
    // private Integer basinLocalityId;
    // private Integer groundId;
    // private Integer localityId;
    private Double distanceBeetwenSensors;
    private Geographkoords geographkoordsId;
    private Basinlocality basinlocalityId;
    private Ground groundId;
    private Locality localityId;
    private String description;
    private Collection<Flood> floodsId;
    private Collection<Predictionlevelsnow> predictionlevelsnowsId;
    private Collection<Predictionlevelwater> predictionlevelwatersId;

    public Post(String namePost, Double distanceBeetwenSensors, Geographkoords geographkoordsId, Basinlocality basinlocalityId, Ground groundId, Locality localityId, String description) {
        this.namePost = namePost;
        this.distanceBeetwenSensors = distanceBeetwenSensors;
        this.geographkoordsId = geographkoordsId;
        this.basinlocalityId = basinlocalityId;
        this.groundId = groundId;
        this.localityId = localityId;
        this.description = description;
    }

    public Post() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Post")
    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    @Basic
    @NotNull
    @Column(name = "NamePost")
    public String getNamePost() {
        return namePost;
    }

    public void setNamePost(String namePost) {
        this.namePost = namePost;
    }

//    @Basic
//    @Column(name = "GeographKoordsID")
//    public Integer getGeographKoordsId() {
//        return geographKoordsId;
//    }
//
//    public void setGeographKoordsId(Integer geographKoordsId) {
//        this.geographKoordsId = geographKoordsId;
//    }

//    @Basic
//    @Column(name = "BasinLocalityID")
//    public Integer getBasinLocalityId() {
//        return basinLocalityId;
//    }
//
//    public void setBasinLocalityId(Integer basinLocalityId) {
//        this.basinLocalityId = basinLocalityId;
//    }

//    @Basic
//    @Column(name = "GroundID")
//    public Integer getGroundId() {
//        return groundId;
//    }
//
//    public void setGroundId(Integer groundId) {
//        this.groundId = groundId;
//    }

//    @Basic
//    @Column(name = "LocalityID")
//    public Integer getLocalityId() {
//        return localityId;
//    }
//
//    public void setLocalityId(Integer localityId) {
//        this.localityId = localityId;
//    }

    @Basic
    @Column(name = "DistanceBeetwenSensors")
    public Double getDistanceBeetwenSensors() {
        return distanceBeetwenSensors;
    }

    public void setDistanceBeetwenSensors(Double distanceBeetwenSensors) {
        this.distanceBeetwenSensors = distanceBeetwenSensors;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "postId",cascade =CascadeType.ALL,orphanRemoval = true)
    public Collection<Flood> getFloodsId() {
        return floodsId;
    }

    public void setFloodsId(Collection<Flood> floodsId) {
        this.floodsId = floodsId;
    }

    @ManyToOne
    @JoinColumn(name = "GeographKoordsID", referencedColumnName = "Id_GeographKoords")
    public Geographkoords getGeographkoordsId() {
        return geographkoordsId;
    }

    public void setGeographkoordsId(Geographkoords geographkoordsId) {
        this.geographkoordsId = geographkoordsId;
    }

    @ManyToOne
    @JoinColumn(name = "BasinLocalityID", referencedColumnName = "Id_BasinLocality")
    public Basinlocality getBasinlocalityId() {
        return basinlocalityId;
    }

    public void setBasinlocalityId(Basinlocality basinlocalityId) {
        this.basinlocalityId = basinlocalityId;
    }

    @ManyToOne
    @JoinColumn(name = "GroundID", referencedColumnName = "Id_Ground")
    public Ground getGroundId() {
        return groundId;
    }

    public void setGroundId(Ground groundId) {
        this.groundId = groundId;
    }

    @ManyToOne
    @JoinColumn(name = "LocalityID", referencedColumnName = "Id_Locality")
    public Locality getLocalityId() {
        return localityId;
    }

    public void setLocalityId(Locality localityId) {
        this.localityId = localityId;
    }


    @OneToMany(mappedBy = "postId",cascade =CascadeType.ALL,orphanRemoval = true)
    public Collection<Predictionlevelsnow> getPredictionlevelsnowsId() {
        return predictionlevelsnowsId;
    }

    public void setPredictionlevelsnowsId(Collection<Predictionlevelsnow> predictionlevelsnowsId) {
        this.predictionlevelsnowsId = predictionlevelsnowsId;
    }

    @OneToMany(mappedBy = "postId",cascade =CascadeType.ALL,orphanRemoval = true)
    public Collection<Predictionlevelwater> getPredictionlevelwatersId() {
        return predictionlevelwatersId;
    }

    public void setPredictionlevelwatersId(Collection<Predictionlevelwater> predictionlevelwatersId) {
        this.predictionlevelwatersId = predictionlevelwatersId;
    }
}

package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "basinlocality", schema = "db_fastwater", catalog = "")
public class Basinlocality implements Serializable {

    private Integer idBasinLocality;
    // private Integer basinId;
    //  private Integer geographKoordsId;
    private Double limitLevel;
    private Double deep;
    //  private Integer typeGroundBottomId;
    private String description;
    private Basin basinId;
    private Geographkoords geographkoordsId;
    private Ground groundId;
    private Collection<Post> postsId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_BasinLocality")
    public Integer getIdBasinLocality() {
        return idBasinLocality;
    }

    public void setIdBasinLocality(Integer idBasinLocality) {
        this.idBasinLocality = idBasinLocality;
    }

//    @Basic
//    @Column(name = "BasinID")
//    public int getBasinId() {
//        return basinId;
//    }
//
//    public void setBasinId(int basinId) {
//        this.basinId = basinId;
//    }

//    @Basic
//    @Column(name = "GeographKoordsID")
//    public Integer getGeographKoordsId() {
//        return geographKoordsId;
//    }
//
//    public void setGeographKoordsId(Integer geographKoordsId) {
//        this.geographKoordsId = geographKoordsId;
//    }

    @Basic
    @Column(name = "LimitLevel")
    public Double getLimitLevel() {
        return limitLevel;
    }

    public void setLimitLevel(Double limitLevel) {
        this.limitLevel = limitLevel;
    }

    @Basic
    @Column(name = "Deep")
    public Double getDeep() {
        return deep;
    }

    public void setDeep(Double deep) {
        this.deep = deep;
    }

//    @Basic
//    @Column(name = "TypeGroundBottomID")
//    public Integer getTypeGroundBottomId() {
//        return typeGroundBottomId;
//    }
//
//    public void setTypeGroundBottomId(Integer typeGroundBottomId) {
//        this.typeGroundBottomId = typeGroundBottomId;
//    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @ManyToOne
    @JoinColumn(name = "BasinID", referencedColumnName = "Id_Basin", nullable = false)
    public Basin getBasinId() {
        return basinId;
    }

        public void setBasinId(Basin basinId) {
        this.basinId = basinId;
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
    @JoinColumn(name = "TypeGroundBottomID", referencedColumnName = "Id_Ground")
    public Ground getGroundId() {
        return groundId;
    }

    public void setGroundId(Ground groundId) {
        this.groundId = groundId;
    }

    @OneToMany(mappedBy = "basinlocalityId",cascade =CascadeType.ALL,orphanRemoval = true)
    public Collection<Post> getPostsId() {
        return postsId;
    }

    public void setPostsId(Collection<Post> postsId) {
        this.postsId = postsId;
    }
}

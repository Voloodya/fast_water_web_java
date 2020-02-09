package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "basin", schema = "db_fastwater", catalog = "")
public class Basin implements Serializable {
    private Integer idBasin;
    private String nameBasin;
    //private Integer geographKoordsId;
    private Double normalSpeedBasin;
    private String description;
    private Geographkoords geographkoordsId;
    private Collection<Basinlocality> basinlocalitiesId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Basin")
    public Integer getIdBasin() {
        return idBasin;
    }

    public void setIdBasin(Integer idBasin) {
        this.idBasin = idBasin;
    }

    @Basic
    @NotNull
    @Column(name = "NameBasin")
    public String getNameBasin() {
        return nameBasin;
    }

    public void setNameBasin(String nameBasin) {
        this.nameBasin = nameBasin;
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

    @Basic
    @Column(name = "NormalSpeedBasin")
    public Double getNormalSpeedBasin() {
        return normalSpeedBasin;
    }

    public void setNormalSpeedBasin(Double normalSpeedBasin) {
        this.normalSpeedBasin = normalSpeedBasin;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "GeographKoordsID", referencedColumnName = "Id_GeographKoords")
    public Geographkoords getGeographkoordsId() {
        return geographkoordsId;
    }

    public void setGeographkoordsId(Geographkoords geographkoordsId) {
        this.geographkoordsId = geographkoordsId;
    }

    @OneToMany(mappedBy = "basinId",cascade =CascadeType.ALL,orphanRemoval = true)
    public Collection<Basinlocality> getBasinlocalitiesId() {
        return basinlocalitiesId;
    }

    public void setBasinlocalitiesId(Collection<Basinlocality> basinlocalitiesId) {
        this.basinlocalitiesId = basinlocalitiesId;
    }
}

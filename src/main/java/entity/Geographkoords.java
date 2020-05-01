package entity;

import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "GeographKoords", schema = "db_fastwater", catalog = "")
public class Geographkoords implements  Serializable{
    private Integer idGeographKoords;
    private String koordinate;
    private String typeKoordinates;
    private Integer srid;
    private Double radiusAction;
    private Double heighSeaLevel;
    private String description;
    private Collection<Basin> basinsByIdGeographKoords;
    private Collection<Basinlocality> basinlocalitiesByIdGeographKoords;
    private Collection<Locality> localitiesByIdGeographKoords;
    private Collection<Post> postsByIdGeographKoords;


    public Geographkoords() {
    }

    public Geographkoords(String koordinate, String typeKoordinates, Integer srid, Double radiusAction, Double heighSeaLevel, String description) {
        this.koordinate = koordinate;
        this.typeKoordinates = typeKoordinates;
        this.srid = srid;
        this.radiusAction = radiusAction;
        this.heighSeaLevel = heighSeaLevel;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_GeographKoords")
    public Integer getIdGeographKoords() {
        return idGeographKoords;
    }

    public void setIdGeographKoords(Integer idGeographKoords) {
        this.idGeographKoords = idGeographKoords;
    }

    @Basic
    @NotNull
    @Column(name = "Koordinate")
    public String getKoordinate() {
        return koordinate;
    }

    public void setKoordinate(String koordinate) {
        this.koordinate = koordinate;
    }

    @Basic
    @Column(name = "TypeKoordinates")
    public String getTypeKoordinate() {
        return typeKoordinates;
    }

    public void setTypeKoordinate(String typeKoordinates) {
        this.typeKoordinates = typeKoordinates;
    }

    @Basic
    @Column(name = "SRID_")
    public Integer getSrid() {
        return srid;
    }

    public void setSrid(Integer srid) {
        this.srid = srid;
    }

    @Basic
    @Column(name = "RadiusAction")
    public Double getRadiusAction() {
        return radiusAction;
    }

    public void setRadiusAction(Double radiusAction) {
        this.radiusAction = radiusAction;
    }

    @Basic
    @Column(name = "HeighSeaLevel")
    public Double getHeighSeaLevel() {
        return heighSeaLevel;
    }

    public void setHeighSeaLevel(Double heighSeaLevel) {
        this.heighSeaLevel = heighSeaLevel;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "geographkoordsId",cascade =CascadeType.ALL,orphanRemoval = true)
    public Collection<Basin> getBasinsByIdGeographKoords() {
        return basinsByIdGeographKoords;
    }

    public void setBasinsByIdGeographKoords(Collection<Basin> basinsByIdGeographKoords) {
        this.basinsByIdGeographKoords = basinsByIdGeographKoords;
    }

    @OneToMany(mappedBy = "geographkoordsId",cascade =CascadeType.ALL,orphanRemoval = true)
    public Collection<Basinlocality> getBasinlocalitiesByIdGeographKoords() {
        return basinlocalitiesByIdGeographKoords;
    }

    public void setBasinlocalitiesByIdGeographKoords(Collection<Basinlocality> basinlocalitiesByIdGeographKoords) {
        this.basinlocalitiesByIdGeographKoords = basinlocalitiesByIdGeographKoords;
    }

    @OneToMany(mappedBy = "geographkoordsId",cascade =CascadeType.ALL,orphanRemoval = true)
    public Collection<Locality> getLocalitiesByIdGeographKoords() {
        return localitiesByIdGeographKoords;
    }

    public void setLocalitiesByIdGeographKoords(Collection<Locality> localitiesByIdGeographKoords) {
        this.localitiesByIdGeographKoords = localitiesByIdGeographKoords;
    }

    @OneToMany(mappedBy = "geographkoordsId",cascade =CascadeType.ALL,orphanRemoval = true)
    public Collection<Post> getPostsByIdGeographKoords() {
        return postsByIdGeographKoords;
    }

    public void setPostsByIdGeographKoords(Collection<Post> postsByIdGeographKoords) {
        this.postsByIdGeographKoords = postsByIdGeographKoords;
    }
}

package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "locality", schema = "db_fastwater", catalog = "")
public class Locality implements Serializable {

    private Integer idLocality;
    private String nameLocality;
    //  private Integer geographKoordsId;
    private String description;
    private Geographkoords geographkoordsId;
    private Collection<Post> postsId;
    private Collection<Postandlocality> postandlocalitiesId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Locality")
    public Integer getIdLocality() {
        return idLocality;
    }

    public void setIdLocality(Integer idLocality) {
        this.idLocality = idLocality;
    }

    @Basic
    @NotNull
    @Column(name = "NameLocality")
    public String getNameLocality() {
        return nameLocality;
    }

    public void setNameLocality(String nameLocality) {
        this.nameLocality = nameLocality;
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

    @OneToMany(mappedBy = "localityId",cascade =CascadeType.ALL,orphanRemoval = true)
    public Collection<Post> getPostsId() {
        return postsId;
    }

    public void setPostsId(Collection<Post> postsId) {
        this.postsId = postsId;
    }

    @OneToMany(mappedBy = "localityId",cascade=CascadeType.ALL,orphanRemoval = true)
    public Collection<Postandlocality> getPostandlocalitiesId() {
        return postandlocalitiesId;
    }

    public void setPostandlocalitiesId(Collection<Postandlocality> postandlocalitiesId) {
        this.postandlocalitiesId = postandlocalitiesId;
    }
}

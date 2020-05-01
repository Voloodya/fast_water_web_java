package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "Ground", schema = "db_fastwater", catalog = "")
public class Ground implements Serializable {

    private Integer idGround;
    private String typeGround;
    private Double levelAbsorbency;
    private String description;
    private Collection<Basinlocality> basinlocalitiesId;
    private Collection<Post> postsId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Ground")
    public Integer getIdGround() {
        return idGround;
    }

    public void setIdGround(Integer idGround) {
        this.idGround = idGround;
    }

    @Basic
    @Column(name = "TypeGround")
    public String getTypeGround() {
        return typeGround;
    }

    public void setTypeGround(String typeGround) {
        this.typeGround = typeGround;
    }

    @Basic
    @Column(name = "LevelAbsorbency")
    public Double getLevelAbsorbency() {
        return levelAbsorbency;
    }

    public void setLevelAbsorbency(Double levelAbsorbency) {
        this.levelAbsorbency = levelAbsorbency;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "groundId",cascade =CascadeType.ALL,orphanRemoval = true)
    public Collection<Basinlocality> getBasinlocalitiesId() {
        return basinlocalitiesId;
    }

    public void setBasinlocalitiesId(Collection<Basinlocality> basinlocalitiesId) {
        this.basinlocalitiesId = basinlocalitiesId;
    }

    @OneToMany(mappedBy = "groundId",cascade =CascadeType.ALL,orphanRemoval = true)
    public Collection<Post> getPostsId() {
        return postsId;
    }

    public void setPostsId(Collection<Post> postsId) {
        this.postsId = postsId;
    }
}

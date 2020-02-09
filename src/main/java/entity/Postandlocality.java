package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "postandlocality", schema = "db_fastwater", catalog = "")
public class Postandlocality implements Serializable {
    private Integer idPostAndLocality;
    private Post postId;
    private Locality localityId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_PostAndLocality")
    public Integer getIdPostAndLocality() {
        return idPostAndLocality;
    }

    public void setIdPostAndLocality(Integer idPostAndLocality) {
        this.idPostAndLocality = idPostAndLocality;
    }

    @ManyToOne
    @JoinColumn(name = "PostID", referencedColumnName = "Id_Post", nullable = false)
    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
    }

    @ManyToOne
    @JoinColumn(name = "LocalityID", referencedColumnName = "Id_Locality", nullable = false)
    public Locality getLocalityId() {
        return localityId;
    }

    public void setLocalityId(Locality localityId) {
        this.localityId = localityId;
    }
}

package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.sql.Time;

@Entity
@Table(name = "PredictionLevelSnow", schema = "db_fastwater", catalog = "")
public class Predictionlevelsnow implements Serializable {

    private Integer idPredictionLevelSnow;
    // private int postId;
    private Date date;
    private Time time;
    private Double changeLevelSnow;
    private Double levelSnow;
    private Post postId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_PredictionLevelSnow")
    public Integer getIdPredictionLevelSnow() {
        return idPredictionLevelSnow;
    }

    public void setIdPredictionLevelSnow(Integer idPredictionLevelSnow) {
        this.idPredictionLevelSnow = idPredictionLevelSnow;
    }

//    @Basic
//    @Column(name = "PostID")
//    public int getPostId() {
//        return postId;
//    }
//
//    public void setPostId(int postId) {
//        this.postId = postId;
//    }

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "Date_")
    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "Time_")
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Basic
    @Column(name = "ChangeLevelSnow")
    public Double getChangeLevelSnow() {
        return changeLevelSnow;
    }

    public void setChangeLevelSnow(Double changeLevelSnow) {
        this.changeLevelSnow = changeLevelSnow;
    }

    @Basic
    @Column(name = "LevelSnow")
    public Double getLevelSnow() {
        return levelSnow;
    }

    public void setLevelSnow(Double levelSnow) {
        this.levelSnow = levelSnow;
    }

    @ManyToOne
    @JoinColumn(name = "PostID", referencedColumnName = "Id_Post", nullable = false)
    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
    }
}

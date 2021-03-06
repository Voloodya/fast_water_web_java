package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.sql.Time;

@Entity
@Table(name = "PredictionLevelWater", schema = "db_fastwater", catalog = "")
public class Predictionlevelwater implements Serializable {
    private Integer idPredictionLevelWater;
    //  private int postId;
    private Date date;
    private Time time;
    private Double changeLevelWater;
    private double levelWater;
    private int warningFlood;
    private Post postId;

    public Predictionlevelwater() {
    }

    public Predictionlevelwater(Date date, Time time, Double changeLevelWater, double levelWater, Post postId) {
        this.date = date;
        this.time = time;
        this.changeLevelWater = (double)Math.round(changeLevelWater * 10000d)/10000d;
        this.levelWater = (double)Math.round(levelWater * 10000d)/10000d;
        this.postId = postId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_PredictionLevelWater")
    public Integer getIdPredictionLevelWater() {
        return idPredictionLevelWater;
    }

    public void setIdPredictionLevelWater(Integer idPredictionLevelWater) {
        this.idPredictionLevelWater = idPredictionLevelWater;
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
    @Column(name = "ChangeLevelWater")
    public Double getChangeLevelWater() {
        return changeLevelWater;
    }

    public void setChangeLevelWater(Double changeLevelWater) {
        this.changeLevelWater = changeLevelWater;
    }

    @Basic
    @NotNull
    @Column(name = "LevelWater")
    public double getLevelWater() {
        return levelWater;
    }

    public void setLevelWater(double levelWater) {
        this.levelWater = levelWater;
    }

    @Basic
    @NotNull
    @Column(name = "WarningFlood")
    public int getWarningFlood() {
        return warningFlood;
    }

    public void setWarningFlood(int warningFlood) {
        this.warningFlood = warningFlood;
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

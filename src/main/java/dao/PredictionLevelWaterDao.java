package dao;

import entity.Post;
import entity.Predictionlevelwater;
import java.util.List;


public interface PredictionLevelWaterDao {

    List<Predictionlevelwater> getAll();
    void add (Predictionlevelwater predictLevelWater);
    void dell (int idPredictionWater);
    void dell (String namePost);
    List<Predictionlevelwater> search (int idPost);
    void dell (Predictionlevelwater predictionlevelwater);
    void dellAll ();
}

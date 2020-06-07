package dao;

import entity.Predictionlevelwater;
import java.util.List;


public interface PredictionLevelWaterDao {

    List<Predictionlevelwater> getAll();

    void dell (int idPredictionWater);
}

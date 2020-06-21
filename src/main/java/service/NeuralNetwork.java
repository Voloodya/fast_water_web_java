package service;

//import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
//import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
//import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
//import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
//import org.nd4j.common.io.ClassPathResource;
//import org.nd4j.linalg.api.ndarray.INDArray;
//import org.nd4j.linalg.factory.Nd4j;

import org.springframework.stereotype.Service;
import view.FloodView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NeuralNetwork {

//    private MultiLayerNetwork modelMultiLayer;
    private String simpleMlp="";

    public NeuralNetwork() {
//        try {
//            this.simpleMlp =new ClassPathResource("trained _neural_network.H5").getFile().getPath();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        KerasModelImport kerasModelImport=new KerasModelImport();
//        try {
//            this.modelMultiLayer=kerasModelImport.importKerasSequentialModelAndWeights(simpleMlp);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvalidKerasConfigurationException e) {
//            e.printStackTrace();
//        } catch (UnsupportedKerasConfigurationException e) {
//            e.printStackTrace();
//        }finally {
//
//        }
    }

    public Double getForecast (FloodView floodView)
    {
        Double forecast=floodView.getChangeLevelWater()!=null?floodView.getChangeLevelWater():0.00;
        double [] parametrs={floodView.getNumberPostForNrlNtwrk(),floodView.getNumberDaysYear(),floodView.getSnowRain(),
        floodView.getLevelSnow(),floodView.getWaterReserveInSnow(),floodView.getLevelFreezingGround(),floodView.getHeightIceOnWater(),
        floodView.getTemperatureMax(),floodView.getTemperatureMin(),floodView.getHumidityDeficit(),floodView.getSolaractivity(),
        floodView.getLevelWater(),floodView.getChangeLevelSnow(),floodView.getChangeSnowBefore10days(),floodView.getChangeWaterBefore3dayMiddle(),
        floodView.getDownfallBefore3days(),floodView.getTemperatureMiddleBefore3days()};

        int inputs = 17;
//        INDArray features2 = Nd4j.zeros(inputs);
//        for (int i=0; i<inputs; i++){
//            features2.putScalar(new int [] {i},parametrs[i]);}
//
//        INDArray features = Nd4j.create(parametrs, 1, 17);
//        if(modelMultiLayer!=null){
//                forecast=modelMultiLayer.output(features).getDouble(0);
//        }

        return forecast;
    }

}

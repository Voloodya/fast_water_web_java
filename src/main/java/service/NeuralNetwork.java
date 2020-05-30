package service;

//import org.deeplearning4j.nn.graph.ComputationGraph;
//import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
//import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
//import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
//import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
//import org.nd4j.common.io.ClassPathResource;
//import org.tensorflow.SavedModelBundle;

import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class NeuralNetwork {

//    private SavedModelBundle model;
//    private MultiLayerNetwork modelMultiLayer;
    String simpleMlp="";

    public NeuralNetwork() {
        String simpleMlp="";
     //   this.model = SavedModelBundle.load("E:\\Fast_Water_project\\DataMiningHydrology");
//        try {
//            String simpleMlp =new ClassPathResource("E:\\Fast_Water_project\\DataMiningHydrology\\trained _neural_network.H5").getFile().getPath();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            this.modelMultiLayer= KerasModelImport.importKerasSequentialModelAndWeights(simpleMlp);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvalidKerasConfigurationException e) {
//            e.printStackTrace();
//        } catch (UnsupportedKerasConfigurationException e) {
//            e.printStackTrace();
//        }
//        try {
//            simpleMlp =new ClassPathResource("trained _neural_network.H5").getFile().getPath();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            this.modelMultiLayer=KerasModelImport.importKerasSequentialModelAndWeights(simpleMlp);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (UnsupportedKerasConfigurationException e) {
//            e.printStackTrace();
//        } catch (InvalidKerasConfigurationException e) {
//            e.printStackTrace();
//        }
    }


}

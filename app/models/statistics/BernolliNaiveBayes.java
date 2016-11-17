package models.statistics;

import com.google.inject.Inject;
import models.entities.WekaSource;

import play.Logger;
import play.libs.Json;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.Instances;


public class BernolliNaiveBayes {
    private static final Logger.ALogger logger = Logger.of(BernolliNaiveBayes.class);
    @Inject
    WekaSource source;
    public NaiveBayes run() throws Exception {
        Instances trainingSet = this.source.retrieveDataSet();

        trainingSet.setClass(new Attribute("position"));
        trainingSet.setClassIndex(trainingSet.numAttributes() - 1);
        NaiveBayes nb = new NaiveBayes();
        nb.buildClassifier(trainingSet);
        return nb;
    }

    public void evaluate() throws Exception{
        Instances trainingSet = this.source.retrieveDataSet();
        trainingSet.setClass(new Attribute("position"));
        trainingSet.setClassIndex(trainingSet.numAttributes() - 1);
        Evaluation evalNewInstance = new Evaluation(trainingSet);
        evalNewInstance.evaluateModel(this.run(),trainingSet);
        logger.error(Json.toJson(evalNewInstance).toString());
    }
}

package controllers;

import com.google.inject.Inject;
import models.entities.WekaSource;
import models.parser.BuildTrainingSet;
import models.statistics.BernolliNaiveBayes;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

public class CreateTrainingSetController extends Controller {
    private static final Logger.ALogger logger = Logger.of(CreateTrainingSetController.class);
    @Inject
    WekaSource wekaSource;
    @Inject
    BernolliNaiveBayes bernolliNaiveBayes;

    public Result index() throws Exception {
        BuildTrainingSet.build();
        bernolliNaiveBayes.evaluate();
        return ok("Training-set created!!");
    }

}

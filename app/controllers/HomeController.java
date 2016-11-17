package controllers;

import java.io.File;
import java.util.List;

import com.google.inject.Inject;
import models.entities.WekaSource;
import models.parser.BuildDataSet;
import models.parser.BuildTrainingSet;
import models.parser.filters.implementation.Invoice;
import models.statistics.BernolliNaiveBayes;
import org.w3c.dom.Document;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import models.parser.Xml;
import models.parser.walker.XmlIterator;

public class HomeController extends Controller {
    private static final Logger.ALogger logger = Logger.of(HomeController.class);
    @Inject
    WekaSource wekaSource;
    @Inject
    BernolliNaiveBayes bernolliNaiveBayes;
    public Result index() throws Exception {
        BuildTrainingSet.build();
        //BuildDataSet.build();
        bernolliNaiveBayes.evaluate();
        return ok("Your new application is ready.");
    }

}

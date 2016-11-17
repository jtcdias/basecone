package models.entities;

import play.Configuration;
import weka.core.Instances;
import weka.experiment.InstanceQuery;
import javax.inject.Inject;

public class WekaSource {
    private final InstanceQuery query;
    @Inject
    WekaSource(Configuration configuration) throws Exception {
        this.query = new InstanceQuery();
        this.query.setUsername(configuration.getConfig("db").getString("default.weka_user"));
        this.query.setPassword(configuration.getConfig("db").getString("default.weka_password"));
    }

    public Instances retrieveDataSet() throws Exception {
        this.query.setQuery("Select * from invoice_training_set where tag = 1");
        return this.query.retrieveInstances();
    }
}

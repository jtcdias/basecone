package models.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.Model;

@Entity
@Table
public class InvoiceTrainingSet extends Model {
    public static Finder<Long, InvoiceTrainingSet> find = new Finder<>(InvoiceTrainingSet.class);

    @Id
    public Long id;
    public Long posLeft;
    public Long width;
    public Long height;
    public Long posTop;
    public String closestSynonymous;
    public Double distance;
    public String keyWord;
    public String value;
    public Boolean tag;
    public String fileName;

}

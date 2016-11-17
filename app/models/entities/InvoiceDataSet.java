package models.entities;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class InvoiceDataSet extends Model {
    public static Finder<Long, InvoiceDataSet> find = new Finder<>(InvoiceDataSet.class);

    @Id
    public Long id;
    public Double distance;
    public String value;
    public Boolean tag;
    public String fileName;
}

package models.parser;

import exceptions.NoPatternFound;
import models.entities.InvoiceDataSet;
import models.parser.filters.implementation.Invoice;
import models.parser.helpers.PositionHelper;
import models.parser.utils.InvoiceSynonymous;
import models.parser.walker.XmlIterator;
import org.w3c.dom.Document;
import play.Logger;
import play.libs.Json;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class BuildDataSet {
    private static final Logger.ALogger logger = Logger.of(BuildDataSet.class);


    private static final String filePath = "assets/training-set/";

    public static void build() throws Exception {
        File[] files = new File(filePath).listFiles();
        for (File file : files != null ? files : new File[0]) {
            Document xml = Xml.parse(file);
            XmlIterator<Invoice> it = new XmlIterator<>();
            it.setFilter(new Invoice());
            List<PositionHelper> matches = it.iterate(xml).stream().filter(h -> h.getValue().length() > 4).collect(Collectors.toList());
            PositionHelper synonymous = InvoiceSynonymous.findSynonymous(xml).get();
            try{
                saveDataSet(getOptimalInvoice(matches, synonymous), true, file);
            } catch(NoPatternFound  e){
                logger.error(Json.toJson(matches).toString());
            }

        }
    }

    protected static void saveDataSet(PositionHelper dataInvoice, Boolean tag, File file){
        InvoiceDataSet row = new InvoiceDataSet();
        row.value = dataInvoice.getValue();
        row.distance = dataInvoice.getDistance();
        row.tag = tag;
        row.fileName = file.getName();
        row.save();
    }
    protected static PositionHelper getOptimalInvoice(List<PositionHelper> matches, PositionHelper synonymous) throws NoPatternFound {
        if(synonymous.getLeft() == null){
            throw new NoPatternFound("Word matching invoice, not found.");
        }
        Double minimumDistance = InvoiceSynonymous. calculateDistance(
                synonymous.getLeft(),
                synonymous.getTop(),
                matches.get(0).getLeft(),
                matches.get(0).getTop()
        );
        PositionHelper minimumHelper = matches.get(0);
        for(PositionHelper match: matches){
            Double distance = InvoiceSynonymous.calculateDistance(
                    synonymous.getLeft(),
                    synonymous.getTop(),
                    match.getLeft(),
                    match.getTop()
            );
            if(distance < minimumDistance){
                minimumDistance = distance;
                minimumHelper = match;
            }
        }
        minimumHelper.setDistance(minimumDistance);
        return minimumHelper;
    }
}

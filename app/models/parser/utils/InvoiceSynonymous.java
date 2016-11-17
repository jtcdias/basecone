package models.parser.utils;

import models.parser.filters.implementation.Word;
import models.parser.helpers.PositionHelper;
import models.parser.walker.XmlIterator;
import org.w3c.dom.Document;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class InvoiceSynonymous {
    public static String[] synonymousList = {
            "factuur-id",
            "factuurnummer",
            "factuur",
            "factuurnr"

    };

    public static Optional<PositionHelper> findSynonymous(Document xml) {
        XmlIterator<Word> it = new XmlIterator<>();
        it.setFilter(new Word());
        List<PositionHelper> matches = it.iterate(xml);
        return matches.stream().filter(f ->
                Arrays.asList(InvoiceSynonymous.synonymousList).contains(f.getValue().toLowerCase()
                )).findFirst();
    }

    public static Double calculateDistance(Long x1,Long y1, Long x2, Long y2){
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }
}

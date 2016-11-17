package models.parser;

import models.entities.InvoiceTrainingSet;
import models.parser.filters.implementation.Invoice;
import models.parser.helpers.PositionHelper;
import models.parser.utils.AssetsHelper;
import models.parser.utils.InvoiceSynonymous;
import models.parser.walker.XmlIterator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.w3c.dom.Document;
import play.Logger;

import java.io.*;
import java.util.List;
import java.util.Optional;

public class BuildTrainingSet {

    public static void build() throws IOException, InvalidFormatException {
        File file = new File(AssetsHelper.basePath + "metadata.xlsx");
        Workbook excel = WorkbookFactory.create(file);
        int i = 0;
        while(i < excel.getNumberOfSheets()){
            Sheet sheet = excel.getSheetAt(i);
            InvoiceTrainingSet invoiceTrainingSet = new InvoiceTrainingSet();
            invoiceTrainingSet.value = getEntryValue(14,1,sheet);
            invoiceTrainingSet.fileName = sheet.getSheetName();
            invoiceTrainingSet.tag = getTag(sheet);
            invoiceTrainingSet.keyWord = "Invoice";
            invoiceTrainingSet.save();
            i = i +1;
        }
        findDistances();
    }

    private static void findDistances(){
        List<InvoiceTrainingSet> trainingSet = InvoiceTrainingSet.find.where().isNotNull("value").eq("tag", true).findList();
        trainingSet.forEach(t -> {
            Document xml = parseFile(t.fileName);
            XmlIterator<Invoice> it = findPattern();
            Optional<PositionHelper> matches = it.iterate(xml).stream()
                    .filter(i -> i.getValue().equals(t.value))
                    .findAny();
            Optional<PositionHelper> synonymous = InvoiceSynonymous.findSynonymous(xml);
            if(matches.isPresent()) {
                t.posLeft = matches.get().getLeft();
                t.posTop = matches.get().getTop();
                t.width = matches.get().getWidth();
                t.height = matches.get().getHeight();
                if(synonymous.isPresent()){
                    t.closestSynonymous = synonymous.get().getValue();
                    t.distance =InvoiceSynonymous.calculateDistance(
                            matches.get().getLeft(),
                            matches.get().getTop(),
                            synonymous.get().getLeft(),
                            synonymous.get().getTop()
                    );
                }

            }
            t.save();
        });
    }
    private static Document parseFile(String fileName){
        File file = new File(AssetsHelper.trainingSetPath + fileName.split("\\.")[0] +".xml");
        return Xml.parse(file);
    }

    private static XmlIterator<Invoice> findPattern(){
        XmlIterator<Invoice> it = new XmlIterator<>();
        it.setFilter(new Invoice());
        return it;
    }

    private static String getEntryValue(int rowIndex, int cellIndex, Sheet excel){
        Row row = excel.getRow(rowIndex);
        Cell cell = row.getCell(cellIndex);
        return cell.getStringCellValue();
    }

    private static Boolean getTag(Sheet excel){
        return "Yes".equals(getEntryValue(14,3, excel));
    }
}


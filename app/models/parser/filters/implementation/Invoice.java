package models.parser.filters.implementation;

import java.util.regex.Pattern;

import models.parser.filters.IFilter;
import org.w3c.dom.Node;

/**
 * @author jdias.
 */
public class Invoice implements IFilter {
    @Override
    public short getType() {
        return Node.ELEMENT_NODE;
    }

    @Override
    public String getTag() {
        return "charParams";
    }

    @Override
    public Pattern getPattern() {
        return Pattern.compile("\\d+");
    }
}

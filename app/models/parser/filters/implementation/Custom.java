package models.parser.filters.implementation;

import models.parser.filters.IFilter;
import org.w3c.dom.Node;

import java.util.regex.Pattern;

public class Custom  implements IFilter {
    private String pattern;

    public Custom(String pattern) {
        this.pattern = pattern;
    }

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
        return Pattern.compile(String.format("\\%s", this.pattern));
    }
}

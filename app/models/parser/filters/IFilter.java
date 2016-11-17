package models.parser.filters;

import java.util.regex.Pattern;

public interface IFilter {
    short getType();
    String getTag();
    Pattern getPattern();
}

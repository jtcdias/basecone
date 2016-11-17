package models.parser.filters;

import java.util.regex.Pattern;

/**
 * @author jdias.
 */
public interface IFilter {
    short getType();
    String getTag();
    Pattern getPattern();
}

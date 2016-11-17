package models.parser.walker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.parser.filters.IFilter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import models.parser.helpers.PositionHelper;
import play.Logger;

public class XmlIterator<T extends IFilter> {
    private static final Logger.ALogger logger = Logger.of(XmlIterator.class);
    private  T filter;

    public List<PositionHelper> iterate(Document xmlDocument) {
        NodeList nodeList = xmlDocument.getElementsByTagName("*");
        int i = 0;
        List<PositionHelper> matchList = new ArrayList<>();
        PositionHelper helper = new PositionHelper();
        while (i < nodeList.getLength()) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == this.getFilter().getType()) {
                if (isPatternValid(currentNode)){
                        helper = updateHelperAttributes(currentNode, helper);
                } else {
                    if(helper.getLeft() != null){
                        matchList.add(
                            new PositionHelper(
                                helper.getLeft(),
                                helper.getTop(),
                                helper.getWidth(),
                                helper.getHeight(),
                                helper.getValue()
                            )
                        );
                        helper = helper.reset();
                    }
                }
            }
            i = i + 1;
        }
        return matchList;
    }

    private T getFilter() {
        return filter;
    }

    public void setFilter(T filter) {
        this.filter = filter;
    }

    private Boolean isSequential(NodeList nodeList, int index){
        return isPatternValid(nodeList.item(index-1));
    }

    private Boolean isPatternValid(Node currentNode){
        Pattern p = filter.getPattern();
        Matcher m = p.matcher(currentNode.getTextContent());
        return Objects.equals(currentNode.getNodeName(), filter.getTag()) && m.find();
    }

    private PositionHelper updateHelperAttributes(Node currentNode, PositionHelper positionHelper){
        return positionHelper.updateValues(
                Long.parseLong(currentNode.getAttributes().getNamedItem("l").getNodeValue()),
                Long.parseLong(currentNode.getAttributes().getNamedItem("t").getNodeValue()),
                Long.parseLong(currentNode.getAttributes().getNamedItem("r").getNodeValue()),
                Long.parseLong(currentNode.getAttributes().getNamedItem("b").getNodeValue()),
                currentNode.getTextContent()
        );
    }
}

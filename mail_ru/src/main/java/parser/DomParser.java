package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.w3c.dom.*;

public class DomParser {
    public List<MessageData> parse(Document document) throws FileNotFoundException, XMLStreamException {
        NodeList nodeList = document.getElementsByTagName("Message");
        List<MessageData> messageData = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            messageData.add(getMessageData(nodeList.item(i)));
        }
        return messageData;
    }

    private static MessageData getMessageData(Node node) {
        MessageData messageData = new MessageData();
        Element element = (Element) node;
        messageData.setAddress(getTagValue("address", element));
        messageData.setMessageText(getTagValue("text", element));
         return messageData;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }


}
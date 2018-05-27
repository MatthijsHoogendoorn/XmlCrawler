package com.xmlcrawler.service.Crawler;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {

    private String url;

    public Crawler(String url){

        this.url = url;
    }

    public String findFirst(String target, boolean isRegex, boolean isDeepSearch) throws Exception {

        Node rootNode = getRootNode();

        if(target == null || target.isEmpty()){
            throw new Exception("Invalid target.");
        }

        return findFirstRec(target, isRegex, isDeepSearch, rootNode);
    }

    public String findLast(String target, boolean isRegex, boolean isDeepSearch) throws Exception {

        if(target == null || target.isEmpty()){
            throw new Exception("Invalid target.");
        }

        List<String > hits =  findAll(target, isRegex, isDeepSearch);

        return hits.get(hits.size() - 1);
    }

    public List<String> findAll(String target, boolean isRegex, boolean isDeepSearch) throws Exception{

        if(target == null || target.isEmpty()){
            throw new Exception("Invalid target.");
        }

        Node rootNode = getRootNode();

        List<String> hits = new ArrayList<>();

        return findAllRec(target, isRegex, isDeepSearch, rootNode, hits);
    }

    private String findFirstRec(String target, boolean isRegex, boolean isDeepSearch, Node node){

        String foundTarget = getTargetFromNode(target, isRegex, node);

        if(foundTarget != null && foundTarget.length() > 0){
            return foundTarget;
        }

        if(isDeepSearch){

            NamedNodeMap attributes = node.getAttributes();
            if(attributes != null){

                int attrLength = attributes.getLength();

                for(int i = 0; i < attrLength; i++){

                    Node attr = attributes.item(i);

                    String fnTarget = getTargetFromNode(target, isRegex, attr);

                    if(fnTarget != null && fnTarget.length() > 0){
                        return fnTarget;
                    }
                }
            }

        }

        NodeList children = node.getChildNodes();
        int length = children.getLength();

        for (int i = 0; i < length; i++) {
            Node item = children.item(i);

            String fnTarget = findFirstRec(target, isRegex, isDeepSearch, item);

            if(fnTarget != null && fnTarget.length() > 0){
                return fnTarget;
            }
        }

        return "";
    }

    private List<String> findAllRec(String target, boolean isRegex, boolean isDeepSearch, Node node, List<String> hits){

        String foundTarget = getTargetFromNode(target, isRegex, node);

        if(foundTarget != null && foundTarget.length() > 0){
            hits.add(foundTarget);
        }

        if(isDeepSearch){

            NamedNodeMap attributes = node.getAttributes();
            if(attributes != null){

                int attrLength = attributes.getLength();

                for(int i = 0; i < attrLength; i++){

                    Node attr = attributes.item(i);

                    String fnTarget = getTargetFromNode(target, isRegex, attr);

                    if(fnTarget != null && fnTarget.length() > 0){
                        hits.add(fnTarget);
                    }
                }
            }

        }

        NodeList children = node.getChildNodes();
        int length = children.getLength();

        for (int i = 0; i < length; i++) {
            Node item = children.item(i);

            String fnTarget = findFirstRec(target, isRegex, isDeepSearch, item);

            if(fnTarget != null && fnTarget.length() > 0){
                hits.add(fnTarget);
            }
        }

        return hits;
    }

    private String getTargetFromNode(String target, boolean isRegex, Node node){

        String nodeContent = node.getNodeValue();
        if(nodeContent != null && nodeContent.length() > 0){
            if(isRegex){

                Pattern pattern = Pattern.compile(target);
                Matcher matcher = pattern.matcher(nodeContent);
                boolean hasMatch = matcher.find();

                if(hasMatch){
                    return nodeContent;
                }
            }
            else if(nodeContent.length() >= target.length()){
                if(nodeContent.contains(target)){

                    return nodeContent;
                }
            }
        }

        return  "";
    }

    private Node getRootNode() throws Exception {

        if(this.url == null || this.url.isEmpty()){
            throw new Exception("Invalid url.");
        }

        HtmlRequestHandler htmlRequestHandler = new HtmlRequestHandler();
        String html = htmlRequestHandler.getHtml(this.url);

        if (html != null && html.isEmpty() == false){
            HtmlToDocumentConverter docConverter = new HtmlToDocumentConverter();
            Document doc = docConverter.convert(html);

            if(doc != null){
                return doc.getDocumentElement();
            }
        }

        return null;
    }
}

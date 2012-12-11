package hilbert.rapla;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import org.dom4j.dom.DOMDocument;
import org.w3c.dom.NodeList;

import org.dom4j.io.SAXReader;

/**
 *
 * @author Delon Newman
 */
public class DataExport {
    private final String      path;
    private final SAXReader   reader;
    
    private DataExport(String path) throws IOException {
        this.path   = path;
	this.reader = new SAXReader();
    }
    
    public static DataExport parse(String path) throws IOException {
        return new DataExport(path);
    }
    
    @Override
    public String toString() {
        return this.dom.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
	return this.dom.equals(obj);
    }
    
    public List<Resource> resources() {
	NodeList nodes = this.dom.getElementsByTagName("rapla:resource");
	ArrayList<Resource> resources = new ArrayList<Resource>();
	
	for (int i = 0; i < nodes.getLength(); i++) {
	    Resource r = Resource.parse(nodes.item(i));
	    resources.add(r);
	}
	
	return resources;
    }
    
    public List<String> categories() {
	NodeList          nodes = this.dom.getChildNodes(); //this.dom.getElementsByTagName("rapla:categories");
	ArrayList<String> cats  = new ArrayList<String>();
	
	System.out.println("" + nodes.getLength() + " Nodes");
	System.out.println(nodes);
	
	for (int i = 0; i < nodes.getLength(); i++) {
	    String c = nodes.item(i).getTextContent();
	    cats.add(c);
	}
	
	return cats;
    }
}
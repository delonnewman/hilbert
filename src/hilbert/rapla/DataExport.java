package hilbert.rapla;

import java.util.List;
import java.util.ArrayList;

import org.dom4j.Node;
import org.dom4j.Document;
import org.dom4j.Element;

import org.dom4j.io.SAXReader;

/**
 *
 * @author Delon Newman
 */
public class DataExport {
    private final String    path;
    private final SAXReader reader;
    private final Document  document;
    
    private DataExport(String path) throws Exception {
        this.path     = path;
		this.reader   = new SAXReader();
		this.document = reader.read(this.path);
		
		System.out.println(this.document);
		System.out.println(this.document.getRootElement().elements("rapla:resources"));
    }
    
    public static DataExport parse(String path) throws Exception {
        return new DataExport(path);
    }
    
    @Override
    public String toString() {
        return this.document.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
		return this.document.equals(obj);
    }
    
    public List<String> resources() {
		List<Element>     elements  = this.document.getRootElement().elements();
		ArrayList<String> resources = new ArrayList<String>();
	
		System.out.println("" + elements.size() + " Elements");
		System.out.println(elements);
	
		for ( Element e : elements ) {
			//Resource r = Resource.parse();
			List<Element> els = e.elements();
			for ( Element e2 : els ) {
				if ( e2.getName().equals("resource") ) {
					System.out.println(e2);
				}
			}
		}
	
		return resources;
    }
    
    public List<String> categories() {
		ArrayList<String> cats  = new ArrayList<String>();
		return cats;
    }
}
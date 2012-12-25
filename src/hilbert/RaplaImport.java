package hilbert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.dom4j.Document;
import org.dom4j.Element;

import org.dom4j.io.SAXReader;

/**
 *
 * @author Delon Newman
 */
public class RaplaImport {
    private final String    path;
    private final SAXReader reader;
    private final Document  document;
	private final Element   root;
	
	private final String[] courseTypes = {
			"Biology",
			"Math",
			"Comp_Sci",
			"Chemistry",
			"Biochem",
			"Forensics",
			"Physics",
			"Nursing"
	};
    
    private RaplaImport(String path) throws Exception {
        this.path     = path;
		this.reader   = new SAXReader();
		this.document = reader.read(this.path);
		this.root     = this.document.getRootElement();
		
		/*System.out.println(this.document);
		System.out.println(this.document.getRootElement().elements("rapla:resources"));*/
    }
    
    public static RaplaImport parse(String path) throws Exception {
        return new RaplaImport(path);
    }
    
    @Override
    public String toString() {
        return this.document.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
		return this.document.equals(obj);
    }
    
    public List<Element> resources() {
		ArrayList<Element> resources  = new ArrayList<Element>();
		Element resourcesElement = this.root.element("resources");
	
		for ( Iterator i = resourcesElement.elementIterator(); i.hasNext(); ) {
			Element e = (Element) i.next();
			if ( e.getName().equals("resource") ) {
				//System.out.println(e);
				resources.add(e);
			}
		}
	
		return resources;
    }
	
	public List<Course> courses() {
		ArrayList<Course> courses = new ArrayList<Course>();
			
		for ( Element r : this.resources() ) {
			for ( String type : courseTypes ) {
				Element course = r.element(type);
				if ( course != null ) {
					courses.add(parseCourse(course));
				}
			}
		}
		
		return courses;
	}
	
	private Course parseCourse(Element e) {
		String name = "";
		
		for ( Iterator i = e.elementIterator(); i.hasNext(); ) {
			Element elem = (Element) i.next();
			if ( elem.getName().matches("\\w+_Course_Name") ) {
				name = elem.getText();
				System.out.println("Course Name: " + name);
			}
		}
		
		return new Course(name, "", "", "", "", "", 0, "");
	}
	
	public List<Element> reservations() {
		ArrayList<Element> list     = new ArrayList<Element>();
		Element            elements = this.root.element("reservation");
	
		for ( Iterator i = elements.elementIterator(); i.hasNext(); ) {
			Element e = (Element) i.next();
			if ( e.getName().equals("reservation") ) {
				//System.out.println(e);
				list.add(e);
			}
		}
	
		return list;
	}
    
    public List<Element> categories() {
		ArrayList<Element> cats        = new ArrayList<Element>();
		Element            catsElement = this.root.element("categories");
	
		for ( Iterator i = catsElement.elementIterator(); i.hasNext(); ) {
			Element e = (Element) i.next();
			if ( e.getName().equals("category") && e.attributeValue("key").equals("c1") ) {
				for ( Iterator j = e.elementIterator(); j.hasNext(); ) {
					Element cat = (Element) j.next();
					if ( cat.getName().equals("category") ) {
						//System.out.println(cat);
						cats.add(cat);
					}
				}
			}
		}
	
		return cats;
    }
}
package hilbert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.dom4j.Document;
import org.dom4j.Element;

import org.dom4j.io.SAXReader;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
	
	public List<Course> courses() throws Exception {
		ArrayList<Course> list     = new ArrayList<Course>();
		Element           elements = this.root.element("reservations");
	
		if ( elements == null ) {
			throw new Exception("Couldn't retrieve reservations");
		}
		else {
			List appts;
			String name, start, end;
			DateTime startDate;
			
			DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
			
			for ( Iterator i = elements.elementIterator(); i.hasNext(); ) {
				Element e = (Element) i.next();
				if ( e.getName().equals("reservation") ) {
					name  = e.element("defaultReservation").element("name").getText();
					appts = e.elements("appointment");
					
					Element first = (Element) appts.get(0);
					start = first.attributeValue("start-time", "");
					end   = first.attributeValue("end-time", "");
					
					StringBuilder sb = new StringBuilder();
					for ( Iterator j = appts.iterator(); j.hasNext(); ) {
						Element el = (Element) j.next();
						sb.append(fmt.parseDateTime(el.attributeValue("start-date", "")).dayOfWeek().getAsShortText().substring(0, 1) + " ");
					}
					
					list.add(new Course(name, "", "", sb.toString(), start, end, 0, ""));
				}
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
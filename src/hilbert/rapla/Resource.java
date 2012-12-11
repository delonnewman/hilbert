package hilbert.rapla;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Delon Newman
 */
public class Resource {
	public static Resource parse(Node node) {
		NodeList nodes = node.getChildNodes();
		
		return new Resource();
	}
}

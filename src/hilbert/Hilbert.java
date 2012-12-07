/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hilbert;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.dom4j.dom.DOMDocument;

/**
 *
 * @author delon
 */
public class Hilbert {
    private static boolean DEBUG = true;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if ( args.length < 1 ) {
            System.out.println("Usage: hilbert RAPLA_EXPORT");
        }
        else {
            String path = args[0];

            try {
                String xml = FileUtils.readFileToString(new File(path));
                DOMDocument doc = new DOMDocument(xml);
                System.out.println(doc);
            }
            catch (IOException e) {
                System.out.println("Can't read from '" + path + "'");
                if ( DEBUG ) { e.printStackTrace(); }
            }
        }
    }
}
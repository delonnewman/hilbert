package hilbert;

import hilbert.rapla.DataExport;

import java.io.IOException;

/**
 *
 * @author Delon Newman
 */
public class Hilbert {
    /**
     * Flag for debug mode
     */
    public final static boolean DEBUG = true;

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
                DataExport data = DataExport.parse(path);
                //System.out.println(data);
				data.resources();
				/*for ( String res : data.resources() ) {
					System.out.println(res);
				}*/
            }
            catch (Exception e) {
                System.out.println("Can't read from '" + path + "'");
                if ( DEBUG ) { e.printStackTrace(); }
            }
        }
    }
}
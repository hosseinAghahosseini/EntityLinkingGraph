
package EntityLinkingGraph;

/**
 * @author hosseinAghahosseini
 */

import java.io.*;
import java.net.*;

public class EntityLinkigGraph {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        htmlPageGetter a = new htmlPageGetter();
        try 
        {
            //System.out.println(a.getHTML("https://www.google.com/"));
            
        }
        catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }
    
}


package EntityLinkingGraph;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author hosseinAghahosseini
 */

public class EntityLinkigGraph {

    public static void main(String[] args) {
        
        System.out.println(LocalDateTime.now());
        
        htmlPageGetter a = new htmlPageGetter();
        //System.out.println(a.getHTML("https://www.google.com/"));
        //String url = "https://en.wikipedia.org/wiki/Louis_V._Gerstner_Jr.";
        //String url = "https://en.wikipedia.org/wiki/Alessandro_Del_Piero";
        String url = "https://en.wikipedia.org/wiki/Graph_(discrete_mathematics)";
        
        AtagList lst = new AtagList();
        lst.initialize(url,false);
        
        lst.remove_Hashtag();
        
        for (int i = 0 ; i < lst.links.size() ; i++)
            System.out.println(lst.links.get(i).attr("href"));
        
        System.out.println(LocalDateTime.now());

    }
    
}

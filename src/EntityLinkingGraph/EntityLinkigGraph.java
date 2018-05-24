
package EntityLinkingGraph;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author hosseinAghahosseini
 */

public class EntityLinkigGraph {

    public static void main(String[] args) {
        
        System.out.println(LocalDateTime.now());
        
        /*
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

        wikiTitle w = new wikiTitle();
        //System.out.println(w.getWikiPageAddressFromTitle("delpiero"));
        w.initialize("delpiero");
        System.out.println(w.title);
        System.out.println(w.link);
        
        */
        
        ArrayList<String> visitedLinks = new ArrayList<>();
        wikiTitle wt = new wikiTitle();
        wt.initialize("AliDaei");
        wt.setChildren(false, 1, visitedLinks);
        
        System.out.println(LocalDateTime.now());
    }
    
}

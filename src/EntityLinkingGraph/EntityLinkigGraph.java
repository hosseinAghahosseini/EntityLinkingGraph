
package EntityLinkingGraph;

import java.time.LocalDateTime;

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

        //ArrayList<String> visitedLinks = new ArrayList<>();
        wikiTitle wt = new wikiTitle();
        wt.initialize("marcelloMastroianni");
//        wt.title = "انتگرال";
//        wt.link = "https://fa.wikipedia.org/wiki/%D8%A7%D9%86%D8%AA%DA%AF%D8%B1%D8%A7%D9%84";

        //wt.setChildren(false, 1, visitedLinks);
        wt.setChildren(false,1 , staticAddedLinks.visitedLinks);
        
        
        
//        for (int i = 0 ; i < staticAddedLinks.visitedLinks.size() ; i++)
//            System.out.println(staticAddedLinks.visitedLinks.get(i));

        graphVisualize gv = new graphVisualize();
        gv.visualizeGraph(wt);
        gv.colorizeGraph(wt);
        
        
        gv.displayGraph();
        
        gv.topologicalSort();
        
        //wt.postOrderVisit(wt,0);
        
//        MyGraph gs = new MyGraph();
//        gs.addVertex("A");
//    gs.addVertex("B");
//    gs.addVertex("C");
//    gs.addVertex("D");
//    gs.addVertex("E");
//    gs.addVertex("F");
//    gs.addVertex("G");
//    gs.addVertex("H");
//    gs.addVertex("K");
//
//    gs.addEdge("A", "B");
//    gs.addEdge("A", "D");
//    gs.addEdge("B", "D");
//    gs.addEdge("E", "D");
//    gs.addEdge("B", "C");
//    gs.addEdge("F", "C");
//    gs.addEdge("C", "H");
//    gs.addEdge("G", "H");
//    gs.addEdge("F", "G");
//    gs.topologicalSort();
        
        System.out.println("\n"+LocalDateTime.now());
        
    }
    
}

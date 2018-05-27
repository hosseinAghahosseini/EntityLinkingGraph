/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityLinkingGraph;

import java.util.Random;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author hosseinAghahosseini
 */
public class graphVisualize {
    
    final static String styleSheet="node {"+
   " fill-color: DarkGrey;"+
   " size: 8px;"+
   " stroke-mode: plain;"+
   " stroke-color: black;"+
   " stroke-width: 1px;"+
   "}"+
   "node.n1 {"+
   " fill-color: red;"+
   " size: 14px;"+
   "}"+
   "node.n2 {"+
   " fill-color: black;"+
   " size: 11px;"+
   "}";
    
    Graph g = new SingleGraph("Tutorial 1");
    //Graph g = new MultiGraph("Tutorial 1");
    MyGraph gm = new MyGraph();
    
    public void visualizeGraph(wikiTitle awt)
    {
        Node n;
        try {
            n = g.addNode(awt.title);
            n.addAttribute("ui.label", awt.title);
            gm.addVertex(awt.title);
        }
        catch (org.graphstream.graph.IdAlreadyInUseException er)
        {
//            Random rand = new Random();
//            int  x = rand.nextInt(255);
//            n = g.addNode(awt.title + Integer.toString(x));
        }
        
        
        for (int i = 0 ; i < awt.childNodes.size() ; i++)
        {
            visualizeGraph(awt.childNodes.get(i));
            try {
                g.addEdge(awt.title+awt.childNodes.get(i).title, awt.title, awt.childNodes.get(i).title);
                gm.addEdge(awt.childNodes.get(i).title,awt.title);
            }
            catch (org.graphstream.graph.IdAlreadyInUseException er)
            {
                Random rand = new Random();
                int  x = rand.nextInt(255);
                try {
                g.addEdge(awt.title+awt.childNodes.get(i).title+ Integer.toString(x), awt.title, awt.childNodes.get(i).title);
                gm.addEdge(awt.childNodes.get(i).title,awt.title);
                }
                catch (org.graphstream.graph.EdgeRejectedException ee)
                {
                    
                }
            }
        }
    }
    
    public void colorizeGraph(wikiTitle awt)
    {
        Node n = g.getNode(awt.title);

        g.addAttribute("ui.stylesheet", styleSheet);
        n.addAttribute("ui.class", "n1"); 
        
        for (int i = 0 ; i < awt.childNodes.size() ; i++)
        {
            n = g.getNode((awt.childNodes.get(i).title));
            n.addAttribute("ui.class", "n2");
        }
        
    }
    
    public void displayGraph()
    {
        g.spliterator();
        g.setStrict(false);
        g.setAutoCreate( true );
        g.display(); 
    }
    
    public void topologicalSort() 
    {
        System.out.println("topological Sort :");
        gm.topologicalSort();
    }
    
    
        

}

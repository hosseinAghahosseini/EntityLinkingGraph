/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityLinkingGraph;

import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author hosseinAghahosseini
 */
public class wikiTitle {
    
     public ArrayList<wikiTitle> childNodes = new ArrayList<>();
     String title = "";
     String link = "";
     
     public void initialize(String title_)
     {
         link = getWikiPageAddressFromTitle(title_)[0];
         title = getWikiPageAddressFromTitle(title_)[1];
     }
  
     public String[] getWikiPageAddressFromTitle (String title_)
     {
         htmlPageGetter a = new htmlPageGetter();
         String resolvedXML = a.getHTML("https://en.wikipedia.org/w/api.php?action=opensearch&format=xml&formatversion=2&search=" + title_ + "&namespace=0&limit=1&suggest=true");
         
         Document doc = Jsoup.parse(resolvedXML);
         Elements linku = doc.select("Url");
         Elements linkt = doc.select("Text");
         
         String [] s = new String[2];
         s[0] = linku.get(0).html();
         s[1] = linkt.get(0).html();
         
         return s;
     }
     
     public void setChildren(Boolean includeContent , int threshold , ArrayList<String> visitedLinks)
     {
         if (threshold >= 0)
         {
            System.out.println("thisLink:"+this.link);

            visitedLinks.add(this.link);
            
            AtagList al = new AtagList();
            if (includeContent)
               al.initialize(link, true);
            else
                al.initialize(link, false);
            
            //al.remove_Hashtag();
            
            try {
                for (int i = 0 ; i < al.links.size() ; i++)
                {
                    String childLink = "https://en.wikipedia.org"+al.links.get(i).attr("href");
                    System.out.println(threshold + ":parent:" + this.title + " - childLink:" +  childLink );

                    if (!isInVisitedLinks(childLink, visitedLinks))
                    {
                        wikiTitle wt = new wikiTitle();
                        wt.link = childLink;
                        wt.title = wt.link.substring(30);
                        childNodes.add(wt);
                    }
                }    
            }
            catch (NullPointerException ne) {}
            
            threshold--;
            
            for (int i = 0 ; i < childNodes.size() ; i++)
            {
                childNodes.get(i).setChildren(includeContent , threshold , visitedLinks);
            }
            
            System.out.println("--------");
         }
     }
     
     public Boolean isInVisitedLinks(String link , ArrayList<String> visitedLinks)
     {
         for (int i = 0 ; i < visitedLinks.size() ; i++)
         {
             if (link.equals(visitedLinks.get(i)))
                 return true;
         }
         return false;
     }
     
    
}


package EntityLinkingGraph;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author hosseinAghahosseini
 */

public class AtagList {
    
    public String linkURL = "";
    public Document doc;
    public Elements links;
    
    public void initialize (String url , Boolean includeContent)
    {
        linkURL = url;
        htmlPageGetter a = new htmlPageGetter();
        
        int referencesIndex = 0;
        String referencesRemoved = a.getHTML(url);
        
        //content removal  - <h2>Contents</h2>
        if(includeContent == false)
        {
            //referencesIndex = 4;
            referencesIndex = referencesRemoved.indexOf("<h2>Contents</h2>");
            //System.out.println("before:"+referencesRemoved);
            System.out.println(referencesIndex);
            referencesRemoved = referencesRemoved.substring(0, referencesIndex-1);
            //System.out.println("after:"+referencesRemoved);
        }
        //System.out.print(referencesRemoved);
        else {
        //references removal
            try 
            {
                referencesIndex = referencesRemoved.indexOf("<span class=\"mw-headline\" id=\"References\">References</span>");
                referencesRemoved = referencesRemoved.substring(0, referencesIndex-1);
            }
            catch (StringIndexOutOfBoundsException se) 
            {
                referencesRemoved = a.getHTML(url);
            }
        }
        
        //info table removal 
        String tempR = referencesRemoved;
        try {
            referencesIndex = referencesRemoved.indexOf("<table class=\"infobox");
            for(int i = referencesIndex ; i < referencesRemoved.length()-1 ; i++ )
            {
                if(referencesRemoved.substring(i, i+8).equals("</table>"))
                {
                    //System.out.println(referencesRemoved.substring(i, i+8));
                    String temp = "";
                    temp = referencesRemoved.substring(0, referencesIndex+1);
                    temp += referencesRemoved.substring(i+8, referencesRemoved.length()-1);
                    referencesRemoved = temp;
                    break;
                }
            }
        }
        catch (StringIndexOutOfBoundsException se) 
        {
            referencesRemoved = tempR;
        }
        
        //<div class="thumb removal
        tempR = referencesRemoved;
        try {
            referencesIndex = referencesRemoved.indexOf("<div class=\"thumb");
            for(int i = referencesIndex ; i < referencesRemoved.length()-1 ; i++ )
            {
                if(referencesRemoved.substring(i, i+6).equals("</div>"))
                {
                    //System.out.println(referencesRemoved.substring(i, i+8));
                    String temp = "";
                    temp = referencesRemoved.substring(0, referencesIndex+1);
                    temp += referencesRemoved.substring(i+6, referencesRemoved.length()-1);
                    referencesRemoved = temp;
                    break;
                }
            }
        }
        catch (StringIndexOutOfBoundsException se) 
        {
            referencesRemoved = tempR;
        }
        
        doc = Jsoup.parse(referencesRemoved);
        links = doc.select("a[href]");
    }
    
    public void remove_Hashtag()
    {
        //try 
        {
            for (int i = 0 ; i < links.size() ; i++)
            {   
                //hashtag
                if(links.get(i).attr("href").charAt(0) == '#')
                {
                    links.remove(i);
                    i--;
                }
                
                /*img
                if(i>=0 && links.get(i).toString().contains("<img"))
                {
                    links.remove(i);
                    i--;
                }
                */
            }
        }
        //catch (Exception er)
        {
            
        }
    }
}


package EntityLinkingGraph;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author hosseinAghahosseini
 */

public class AtagList {
    
    //public String linkURL = "";
    public Document doc;
    public Elements links;
    
    public void initialize (String url , Boolean includeContent)
    {
        //linkURL = url;
        htmlPageGetter a = new htmlPageGetter();
        
        int referencesIndex = 0;
        String referencesRemoved = a.getHTML(url);
        String tempR = referencesRemoved;
        
        //content removal  - <h2>Contents</h2>
        if(includeContent == false)
        {
            referencesIndex = referencesRemoved.indexOf("<h2>Contents</h2>");
            //referencesIndex = referencesRemoved.indexOf("<h2>محتویات</h2>");
            //System.out.println("before:"+referencesRemoved);
            //System.out.println(referencesIndex);
            try{
            referencesRemoved = referencesRemoved.substring(0, referencesIndex-1);
            }
            catch (StringIndexOutOfBoundsException si) 
            {
                return;
            }
            //System.out.println("after:"+referencesRemoved);
        }
        else {
        //references removal
            try 
            {
                referencesIndex = referencesRemoved.indexOf("<span class=\"mw-headline\" id=\"References\">References</span>");
                referencesRemoved = referencesRemoved.substring(0, referencesIndex-1);
            }
            catch (StringIndexOutOfBoundsException se) 
            {
                referencesRemoved = tempR;
            }
        }
        
        //info table removal 
        tempR = referencesRemoved;
        try {
            referencesIndex = referencesRemoved.indexOf("<table class=\"infobox");
            for(int i = referencesIndex ; i < referencesRemoved.length()-1 ; i++ )
            {
                //if (referencesRemoved.charAt(i) == '<')
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
        
        this.remove_Hashtag();
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
                
                //help
                try
                {
                    if(links.get(i).attr("href").charAt(6) == 'H' && links.get(i).attr("href").contains("Help:"))
                    {
                        links.remove(i);
                        i--;
                    }
                }
                catch (ArrayIndexOutOfBoundsException ae) {}
                
                //file
                try
                {
                    if(links.get(i).attr("href").charAt(6) == 'F' && links.get(i).attr("href").contains("File:"))
                    {
                        links.remove(i);
                        i--;
                    }
                }
                catch (ArrayIndexOutOfBoundsException ae) {}
                
                //Wikipedia
                try
                {
                    if(links.get(i).attr("href").charAt(6) == 'W' && links.get(i).attr("href").contains("Wikipedia:"))
                    {
                        links.remove(i);
                        i--;
                    }
                }
                catch (ArrayIndexOutOfBoundsException ae) {}
                
                //Only /wiki/
                try
                {
                    if(links.get(i).attr("href").charAt(1) != 'w' || links.get(i).attr("href").charAt(2) != 'i')
                    {
                        links.remove(i);
                        i--;
                    }
                }
                catch (ArrayIndexOutOfBoundsException ae) {}
                
            }
        }
        //catch (Exception er)
        {
            
        }
    }
}


package EntityLinkingGraph;

/**
 * @author hosseinAghahosseini
 */
import java.io.*;
import java.net.*;

public class htmlPageGetter {
    
    public String getHTML(String urlToRead){
      try
      {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
           result.append(line);
        }
        rd.close();
        return result.toString();
        }
      catch (Exception en)
      {
          System.out.println(en.getMessage());
          return "$";
      }
   }
    
}

/**
 * Created by Brandi on 4/14/2016.
 */
import java.net.URL;
import java.io.InputStream;
import java.lang.Byte;
import java.io.ByteArrayOutputStream;
import com.google.gson.Gson;
import com.example.guiteam.binge.MovieResults;
import com.example.guiteam.binge.MovieObject;

public class SearchHandler {
    //makes a call to the API and creates objects from the JSON return string
    public MovieObject[] search(String param) throws Exception{

        URL url = new URL("http://api-public.guidebox.com/v1.43/US/Fut6oDhZRLxplPnBK0YSi22RN40RQi"+param);
        Gson gson = new Gson();
        //borrowed from Adamski at http://stackoverflow.com/questions/1264709/convert-inputstream-to-byte-array-in-java
        InputStream input = url.openStream();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] bytes = new byte[(input.available())*3];
        int nextChar = input.read(bytes, 0, bytes.length);
        while(nextChar!=-1){
            output.write(bytes, 0, nextChar);
            nextChar = input.read(bytes, 0, bytes.length);
        }
        output.flush();
        byte[] data = new byte[input.available()*3];
        data = output.toByteArray();
        String result = new String(data);
        input.close();
        //end of borrowed code

        MovieResults results = gson.fromJson(result, MovieResults.class);
        results.addMovies();
        return results.objects;
    }

    /*
    *Searches the movie objects by title.
    * Accepts string movie title as parameter.
    * Returns the titles that matches the search criteria.
    */
    public MovieObject[] movieSearch(String title) throws Exception{
        return search("/search/movie/title/"+encodeTitle(title));
    }

    /*
    *Searches all of the objects by title.
    *Accepts string title as parameter.
    *Returns the titles that matches the search criteria.
    */
    public MovieObject[] tvSearch(String title) throws Exception{
        return search("/search/title/"+encodeTitle(title));
    }

    /*
    *Displays all possible genres
    */
    public MovieObject[] genreSearch() throws Exception{
        return search("/genres");
    }

    //removes non-alphabetic or non-numeric characters and encodes spaces as %252520
    public String encodeTitle(String title){
        String encodedTitle = "";
        for(int i=0; i<title.length(); i++){
            if(((int) title.charAt(i) >= 65 && (int) title.charAt(i) <= 90) || ((int) title.charAt(i) >=97 && (int) title.charAt(i) <= 122) || Character.isDigit(title.charAt(i))){
                encodedTitle = encodedTitle+title.charAt(i);
            }
            else if( (int) title.charAt(i)==32){
                encodedTitle = encodedTitle+"%252520";
            }
        }
        System.out.println(encodedTitle);
        return encodedTitle;
    }
}

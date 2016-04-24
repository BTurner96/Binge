package com.example.guiteam.binge; /**
 * Created by Brandi on 4/14/2016.
 */

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class SearchHandler {
    //makes a call to the API and creates objects from the JSON return string
    public MovieObject[] search(String param) throws Exception{

        URL url = new URL("http://api-public.guidebox.com/v1.43/US/Fut6oDhZRLxplPnBK0YSi22RN40RQi"+param);
        Gson gson = new Gson();
        //borrowed from Adamski at http://stackoverflow.com/questions/1264709/convert-inputstream-to-byte-array-in-java
        InputStream input = url.openConnection().getInputStream();
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
    //search movies
    public MovieObject[] movieSearch(String title) throws Exception{
        return search("/search/movie/title/"+encodeTitle(title));
    }
    //search tv shows
    public MovieObject[] tvSearch(String title) throws Exception{
        return search("/search/title/"+encodeTitle(title));
    }
    //search genre (only displays possible genres)
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

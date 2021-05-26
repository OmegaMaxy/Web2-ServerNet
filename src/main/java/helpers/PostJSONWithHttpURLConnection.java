package helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PostJSONWithHttpURLConnection {

    public static void main (String []args) throws IOException{
        //Change the URL with any other publicly accessible POST resource, which accepts JSON request body
        URL url = new URL ("https://reqres.in/api/users");


        //JSON String need to be constructed for the specific resource.
        //We may construct complex JSON using any third-party JSON libraries such as jackson or org.json



    }

}
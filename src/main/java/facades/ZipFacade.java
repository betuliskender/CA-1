package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ZipDto;
import entities.Hobby;
import org.eclipse.persistence.sessions.serializers.JSONSerializer;

import javax.json.JsonArray;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class ZipFacade {

    public static void main(String[] args) throws IOException, InterruptedException {
//        var client = HttpClient.newHttpClient();
//        var request = HttpRequest
//                .newBuilder( URI.create("https://api.dataforsyningen.dk/postnumre/2400"))
//                .header("accept", "application/json")
//                .build();
//        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

//        URL url = new URL("https://api.dataforsyningen.dk/postnumre");
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
//        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer content = new StringBuffer();
//        while ((inputLine = in.readLine()) != null) {
//            content.append(inputLine);
//        }in.close();
//        //System.out.println(content);

        Gson GSON = new GsonBuilder().create();
        String urlString = "https://api.dataforsyningen.dk/postnumre";
        URL yahoo = new URL(urlString);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader( new InputStreamReader( yc.getInputStream()));
        String inputLine;
        StringBuilder sb = new StringBuilder();

        while ((inputLine = in.readLine()) != null)
            sb.append(inputLine);
        in.close();
        System.out.println(sb);
        ZipDto[] myDtos = GSON.fromJson(sb.toString(), ZipDto[].class);
        System.out.println(myDtos.length);
    }
}

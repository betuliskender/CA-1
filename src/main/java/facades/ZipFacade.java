package facades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ZipFacade {

    public static void main(String[] args) throws IOException, InterruptedException {
//        var client = HttpClient.newHttpClient();
//        var request = HttpRequest
//                .newBuilder( URI.create("https://api.dataforsyningen.dk/postnumre/2400"))
//                .header("accept", "application/json")
//                .build();
//        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

        URL url = new URL("https://api.dataforsyningen.dk/postnumre");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }in.close();
        System.out.println(content);

    }
}

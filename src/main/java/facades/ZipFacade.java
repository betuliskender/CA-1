package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ZipDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;

public class ZipFacade {

    public static void main(String[] args) throws IOException, InterruptedException {
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
        ZipDto[] zipDtos = GSON.fromJson(sb.toString(), ZipDto[].class);
        for (ZipDto zipDto : zipDtos) {
            System.out.println(zipDto.getNr() + " " + zipDto.getNavn());
        }
    }
}

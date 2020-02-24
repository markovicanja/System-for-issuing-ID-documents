/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korisnik;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import entiteti.Documentrequest;
import java.io.DataOutputStream;

/**
 *
 * @author Anja
 */
public class HttpURL {
    private String serverConnectionString = "http://virtserver.swaggerhub.com/petar.noki0x60/ETFTask/1.0.0";
    
    public void dohvatiSlobodneTermine(String dan) throws Exception {
        String url = serverConnectionString + "/terminCentar/getAvailableTimeslots/?regionalniCentarId=17420&dan="+dan;

        HttpURLConnection httpClient = (HttpURLConnection)new URL(url).openConnection();
        httpClient.setRequestMethod("GET");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = httpClient.getResponseCode();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line+"\n");
            }
            System.out.println(response.toString());
        }
    }
    
     public boolean proveraDostupnostiTermina(String termin) throws Exception {
        String url = serverConnectionString + "/terminCentar/checkTimeslotAvailability/?regionalniCentarId=17420&termin="+termin;
        HttpURLConnection httpClient = (HttpURLConnection)new URL(url).openConnection();
        httpClient.setRequestMethod("GET");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = httpClient.getResponseCode();
        if (responseCode == 400) {
            return false; //nevalidan parametar
        }
        JSONParser parser=new JSONParser();
        JSONObject jo;
        try (BufferedReader in= new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {
            Object obj = parser.parse(in);
            jo = (JSONObject)obj;
        }
        String poruka = (String) jo.get("poruka");
        Boolean dostupnost = (Boolean) jo.get("dostupnost");        
        //System.out.println(poruka+"\n"+dostupnost);        
        return dostupnost;
    }
}

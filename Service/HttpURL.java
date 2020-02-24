/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servis;

import entiteti.Documentrequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Anja
 */
public class HttpURL {
    private String serverConnectionString = "http://virtserver.swaggerhub.com/petar.noki0x60/ETFTask/1.0.0";
    
    public int slanjeZahtevaZaKreiranje(Documentrequest d) throws Exception {
        String url = serverConnectionString + "/persoCentar/submit";
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        
        JSONObject jsonObj =new JSONObject();
        jsonObj.put("id", d.getBrojZahteva());
        jsonObj.put("JMBG", d.getJmbg());
        jsonObj.put("ime", d.getIme());
        jsonObj.put("prezime", d.getPrezime());
        jsonObj.put("imeMajke", d.getImeMajke());
        jsonObj.put("imeOca", d.getImeOca());
        jsonObj.put("prezimeMajke", d.getPrezimeMajke());
        jsonObj.put("prezimeOca", d.getPrezimeOca());
        jsonObj.put("pol", d.getPol());
        jsonObj.put("datumRodjenja", d.getDatumRodjenja());
        jsonObj.put("nacionalnost", d.getNacionalnost());
        jsonObj.put("prfesija", d.getProfesija());
        jsonObj.put("bracnoStanje", d.getBracnoStanje());
        jsonObj.put("opstinaPrebivalista", d.getOpstinaPrebivalista());
        jsonObj.put("ulicaPrebivalista", d.getUlicaPrebivalista());
        jsonObj.put("brojPrebivalista", d.getBrojPrebivalista());
        String jsonString = jsonObj.toJSONString();
        
        httpClient.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
            wr.writeBytes(jsonString);
            wr.flush();
        }
        return httpClient.getResponseCode();
    }
    
    
    public int dohvatanjeDokumenta(String dokumentId) throws Exception {
        String url = serverConnectionString + "/persoCentar/"+dokumentId;
        //"http://virtserver.swaggerhub.com/petar.noki0x60/ETFTask/1.0.0/persoCentar/{idDok}"
        HttpURLConnection httpClient = (HttpURLConnection)new URL(url).openConnection();
        httpClient.setRequestMethod("GET");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = httpClient.getResponseCode();
        if (responseCode != 200) { //400 - Nevalidan id dokumenta, 404 - Dokument nije nadjen, 200-uspesno izvrsen zahtev
            return responseCode;
        }
        JSONParser parser=new JSONParser();
        JSONObject jo;
        try (BufferedReader in= new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {
            Object obj = parser.parse(in);
            jo = (JSONObject)obj;
        }
        if (jo.get("status").equals("cekaNaIzvrsenje")) {
            return 200;
        }
        else return 201;
    }
}

package briqAssignment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class FetchDataCsv {

    public static void main(String[] args) {
    	 try {
           
             SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy-HH-mm-ss");
             String timestamp = dateFormat.format(new Date());
             System.out.println("Timestamp: " + timestamp);

       
             String urlStr = "https://data.sfgov.org/resource/p4e4-a5a7.json"; 
             String jsonData = fetchJsonData(urlStr);

     
             JSONArray jsonArray = new JSONArray(jsonData);

             if (jsonArray.length() > 0) {
                 JSONObject firstObject = jsonArray.getJSONObject(0);
                 Iterator<String> keys = firstObject.keys();

                
                 StringBuilder header = new StringBuilder();
                 while (keys.hasNext()) {
                     String key = keys.next();
                     header.append(String.format("%-20s", key)); // Adjust column width as needed
                 }
                 System.out.println(header);

                 for (int i = 0; i < jsonArray.length(); i++) {
                     JSONObject jsonObject = jsonArray.getJSONObject(i);
                     keys = jsonObject.keys();
                     StringBuilder row = new StringBuilder();

                     while (keys.hasNext()) {
                         String key = keys.next();
                         String value = jsonObject.optString(key, "N/A");
                         row.append(String.format("%-20s", value)); // Adjust column width as needed
                     }
                     System.out.println(row);
                 }
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
    
    private static String fetchJsonData(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            throw new IOException("Failed to fetch JSON data. HTTP Response Code: " + responseCode);
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CIQTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.json.*;
import org.json.CDL;
import org.apache.commons.io.FileUtils; 


/**
 *
 * @author nikhil
 */
public class RestClientAPI {
    
    public static void getLastYearCommitActivity()
    {
        try{

            URL url = new URL("https://api.github.com/repos/apache/airflow/stats/commit_activity");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200 && connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ connection.getResponseCode());
		}
            BufferedReader br = new BufferedReader(new InputStreamReader(
			(connection.getInputStream())));
                
                StringBuilder jsonString = new StringBuilder();
                String inLine = "";
                while ((inLine = br.readLine()) != null) {
			jsonString.append(inLine);
		}
                StringBuilder jsonString1 = new StringBuilder();
                jsonString1.append("{\"result\" :");
                jsonString1.append(jsonString.toString());
                jsonString1.append("}");
                JSONObject output;
                
                try{
                    output = new JSONObject(jsonString1.toString());
                    JSONArray docs = output.getJSONArray("result");
                    for(int index=0;index<docs.length();index++)
                    {
                            JSONObject objects = docs.getJSONObject(index);
                            long unix = (int)objects.get("week");
                            objects.put("week", convertUnixToDate(unix));
                    }
                    String file_path = System.getenv("Last_Year_Commit_Activity_Filename");
                    File file = new File(file_path);
                    String csv = CDL.toString(docs);
                    
                    FileUtils.writeStringToFile(file, csv);
                    System.out.println("Data has been Sucessfully Writeen to "+ file);
                }
                catch (IOException e) {
			
                    e.printStackTrace();
			
                } 
		connection.disconnect();
        }
        catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }
    }
    public static void getNumberOfAdditionsAndDeletions()
    {
        try{
          
            URL url = new URL("https://api.github.com/repos/apache/airflow/stats/contributors");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200 && connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ connection.getResponseCode());
		}
            BufferedReader br = new BufferedReader(new InputStreamReader(
			(connection.getInputStream())));
                
                StringBuilder jsonString = new StringBuilder();
                String inLine = "";
                while ((inLine = br.readLine()) != null) {
                        
			jsonString.append(inLine);
		}           
                StringBuilder jsonString1 = new StringBuilder();
                jsonString1.append("{\"result\" :");
                jsonString1.append(jsonString.toString());
                jsonString1.append("}");
                JSONObject output;
      
                try{
                    output = new JSONObject(jsonString1.toString());
                    
                    JSONArray getArray = output.getJSONArray("result");
                    String file_path = System.getenv("Addition_Deletion_Activity_Filename");
                    File file = new File(file_path);
                    for(int index=0;index<getArray.length();index++)
                    {
                        JSONObject objects = getArray.getJSONObject(index);
                        JSONArray docs = objects.getJSONArray("weeks");
                        for(int index1=0;index1<docs.length();index1++)
                        {
                            JSONObject objects1 = docs.getJSONObject(index1);
                            long unix = (int)objects1.get("w");
                            objects1.put("w", convertUnixToDate(unix));
                        }
                        String csv = CDL.toString(docs);
                        FileUtils.writeStringToFile(file, csv);
                        
                    }
                    
                    System.out.println("Data has been Sucessfully Writeen to "+ file);
                }
                catch (IOException e) {
			
                    e.printStackTrace();
			
                } 
		connection.disconnect();
        }
        catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }
    }
    public static String convertUnixToDate(long unix)
    {
        Date date = new Date(unix*1000L); 
        SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd");
        jdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        String java_date = jdf.format(date);
        return java_date;
        
    }
    public static void main(String[] args)
    {
        getLastYearCommitActivity();
        getNumberOfAdditionsAndDeletions();
	    
    }
}

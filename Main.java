import java.util.*;
import java.net.URL;
import java.net.URLConnection;
import java.io.*;

import com.google.gson.*;
import com.google.gson.reflect.*;

class Main {
  public static Map<String, Object> jsonToMap(String str) { 
      Map<String, Object> map = new Gson().fromJson(
          str, new TypeToken<HashMap<String,Object>>() {}.getType()
      );
      return map;
  }
  
  public static void main(String[] args) {
    
    Scanner ireader = new Scanner(System.in); //Integer Scanner
    Scanner sreader = new Scanner(System.in); //String Scanner
    //Free API key from OpenWeather
    String api_key = "b15b0b64af496e9e494d8b18bf7b78ec";

    //Introduces user to the program
    System.out.println("Welcome to the Weather app utilizing OpenWeather API");
    //Allows the user to use the app again
    while(true){
      System.out.println("How would you like to search by?\n1. By City\n2. By Zip Code\n3. Close");
      int choice = ireader.nextInt();

      if(choice==1){
        //Prompts user to search using a city
        System.out.println("Please enter a city.");
        String city = sreader.nextLine();
        //URL for the city API call
        String cityUrlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + api_key + "&units=imperial";
        
        try {
          //Establishes a connection to the OpenWeather API and obtains current weather data within a 3 hour forecast
          //Note that it only allows for 60 calls per minute on the free API key 
          //Once data is obtained, the JSON data is appended to a stringBuilder so that it can be converted to readable text
          StringBuilder result = new StringBuilder();
          URL url = new URL(cityUrlString);
          URLConnection conn = url.openConnection();
          BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
          String line;
          while ((line = rd.readLine()) != null) {
            result.append(line);
          }
          rd.close();
          System.out.println(result);

          //Hashmaps of weather information that is outputted by the API response and cut into specific maps for specific information
          //Main Map
          Map<String, Object > respMap = jsonToMap (result.toString());
          //Maps that pull info from the main map
          Map<String, Object > mainMap = jsonToMap (respMap.get("main").toString());
          Map<String, Object > windMap = jsonToMap (respMap.get("wind").toString());
          Map<String, Object > sysMap = jsonToMap (respMap.get("sys").toString());

          //Substring method to get the weather description from the response Hashmap toString because there isn't a direct way to get it
          String weather = ""+respMap.get("weather")+"";
          String[] cutText = weather.split("\\=");
          String weather2 = cutText[3];
          String[] cutText2 = weather2.split("\\,");

          //Clears the stream of past and unnecessary outputs
          System.out.print("\033[H\033[2J");  
          System.out.flush();

          //Output Log
          System.out.println("Location: " + respMap.get("name") + ", " +  sysMap.get("country")  );
          System.out.println("Weather: " + cutText2[0]  );
          System.out.println("Current Temperature: " + mainMap.get("temp")  );
          System.out.println("Max Temperature: " + mainMap.get("temp_max")  );
          System.out.println("Min Temperature: " + mainMap.get("temp_min")  );
          System.out.println("Current Humidity: " + mainMap.get("humidity")  );
          System.out.println("Wind Speed: " + windMap.get("speed")  );
          System.out.println("\n");
        } catch (IOException e) {
            //Clears the stream of past and unnecessary outputs
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            //Error message if city is not found
            System.out.println(e.getMessage());
            System.out.println("City not found");
        }
      }

      if(choice==2){
        //Prompts user to search using a zip code
        System.out.println("Please enter a zipcode.");
        String zip = sreader.nextLine();
        //URL for the zip code API call
        String zipUrlString = "http://api.openweathermap.org/data/2.5/weather?zip=" + zip + "&appid=" + api_key + "&units=imperial";
        
        try {
          //Establishes a connection to the OpenWeather API and obtains current weather data within a 3 hour forecast
          //Note that it only allows for 60 calls per minute on the free API key 
          //Once data is obtained, the JSON data is appended to a stringBuilder so that it can be converted to readable text
          StringBuilder result = new StringBuilder();
          URL url = new URL(zipUrlString);
          URLConnection conn = url.openConnection();
          BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
          String line;
          while ((line = rd.readLine()) != null) {
            result.append(line);
          }
          rd.close();
          System.out.println(result);

          //Hashmaps of weather information that is outputted by the API response and cut into specific maps for specific information
          //Main Map
          Map<String, Object > respMap = jsonToMap (result.toString());
          //Maps that pull info from the main map
          Map<String, Object > mainMap = jsonToMap (respMap.get("main").toString());
          Map<String, Object > windMap = jsonToMap (respMap.get("wind").toString());
          Map<String, Object > sysMap = jsonToMap (respMap.get("sys").toString());

          //Substring method to get the weather description from the response Hashmap toString because there isn't a direct way to get it
          String weather = ""+respMap.get("weather")+"";
          String[] cutText = weather.split("\\=");
          String weather2 = cutText[3];
          String[] cutText2 = weather2.split("\\,");

          //Clears the stream of past and unnecessary outputs
          System.out.print("\033[H\033[2J");  
          System.out.flush();

          //Output Log
          System.out.println("Location: " + respMap.get("name") + ", " +  sysMap.get("country")  );
          System.out.println("Weather: " + cutText2[0]  );
          System.out.println("Current Temperature: " + mainMap.get("temp")  );
          System.out.println("Max Temperature: " + mainMap.get("temp_max")  );
          System.out.println("Min Temperature: " + mainMap.get("temp_min")  );
          System.out.println("Current Humidity: " + mainMap.get("humidity")  );
          System.out.println("Wind Speed: " + windMap.get("speed")  );
          System.out.println("\n");
        } catch (IOException e) {
            //Clears the stream of past and unnecessary outputs
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            //Error message if city is not found
            System.out.println(e.getMessage());
            System.out.println("City not found");
        }
      }

      //Ends loop and program
      if(choice==3){
        break;
      }
      
      //Catch statement if the user doesn't enter any elligible choice
      else{

      }
    }
  }
}
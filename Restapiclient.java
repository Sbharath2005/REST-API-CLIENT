import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherAPI {
    public static void main(String[] args) {
        try {
            // Example: OpenWeatherMap API (replace YOUR_API_KEY with your key)
            String apiKey = "YOUR_API_KEY";
            String city = "London";
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" 
                                + city + "&appid=" + apiKey + "&units=metric";

            // Make HTTP request
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON response
            JSONObject json = new JSONObject(response.toString());
            String cityName = json.getString("name");
            JSONObject main = json.getJSONObject("main");
            double temp = main.getDouble("temp");
            int humidity = main.getInt("humidity");
            String weather = json.getJSONArray("weather").getJSONObject(0).getString("description");

            // Display structured output
            System.out.println("Weather Data for " + cityName + ":");
            System.out.println("Temperature: " + temp + " °C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Condition: " + weather);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

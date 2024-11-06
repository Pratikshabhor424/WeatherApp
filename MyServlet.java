package MyPackage;

import java.io.IOException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
//import java.net.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String inputData=request.getParameter("userInput");
		System.out.println(inputData);
		String apiKey="82e7b5f408159c2deff4f9149ca3b237";
		String city=request.getParameter("city");
		
		String apiUrl="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+apiKey;
	
		@SuppressWarnings("deprecation")
		URL url = new URL(apiUrl);
		HttpURLConnection connection=(HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		InputStream inputStream=connection.getInputStream();
		InputStreamReader reader = new InputStreamReader(inputStream);
		StringBuilder responseContent = new StringBuilder();
		Scanner Scanner =  new Scanner(reader);
		while(Scanner.hasNext()) {
			responseContent.append(Scanner.nextLine());
		}
		Scanner.close();
		Gson gson = new Gson();
		JsonObject jsonObject= gson.fromJson(responseContent.toString(), JsonObject.class);
		System.out.println(jsonObject);
		
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now=LocalDateTime.now();
		String datetime =(dtf.format(now));
		
		double tempertureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
		int tempertureCelsius =(int)(tempertureKelvin - 273.15);
		
		int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
		
		double windSpeed =jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
		
		String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
		
		request.setAttribute("datetime", datetime);
		request.setAttribute("city", city);
		request.setAttribute("tempertureCelsius",tempertureCelsius);
		request.setAttribute("weatherCondition", weatherCondition);
		request.setAttribute("humidity", humidity);
		request.setAttribute("windSpeed", windSpeed);
		request.setAttribute("weatherData", responseContent.toString());
		connection.disconnect();
	request.getRequestDispatcher("index1.jsp").forward(request,response);
	

		
	}

}
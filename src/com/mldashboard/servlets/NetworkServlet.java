package com.mldashboard.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mldashboard.model.Feature;
import com.mldashboard.network.RegressionNetwork;
import com.mldashboard.util.FilesPath;

/**
 * Servlet implementation class NetworkServlet
 */
@WebServlet("/NetworkServlet")
public class NetworkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RegressionNetwork network;
	FilesPath filesPath;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NetworkServlet() {
		super();
		// TODO Auto-generated constructor stub
		network = new RegressionNetwork();
		filesPath = new FilesPath();
	}

	public void shuffle(){
		network.shuffle(filesPath.getBaseFile());
	}

	public void segregate(){
		network.segregate(filesPath.getShuffledBaseFile());
	}

	public void setFeaturesType(HttpServletRequest request, HttpServletResponse response) throws IOException{
		System.out.println("Normalazing");
		Scanner scanner = new Scanner(new File(filesPath.getBaseFile()));
		String csvHeader = scanner.nextLine();
		String features[] = csvHeader.split("\\s*,\\s*");
		scanner.close();
		Gson gson = new Gson();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(features));
	}
	
	public void normalize(HttpServletRequest request, HttpServletResponse response){
		String types = request.getParameter("featureTypes");
		System.out.println(types);
	}
	
	public void trainNetwork(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		List<Double> errors = network.trainNetwork();
		String errorsJson = new Gson().toJson(errors);
		out.println(errorsJson);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String btnPressed = request.getParameter("btnPressed");
		System.out.println(btnPressed);
		if(btnPressed.equals("shuffle")){
			
		}else if(btnPressed.equals("segregate")){
			shuffle();
			segregate();
		}else if(btnPressed.equals("setFeaturesType")){
			setFeaturesType(request, response);
		}else if(btnPressed.equals("normalize")){
			String types = request.getParameter("featureTypes");
			Gson gson = new Gson();
			Type typeList = new TypeToken<List<Feature>>() {}.getType();
			List<Feature> features = gson.fromJson(types, typeList);
			network.normalize(features);
			network.createNetwork(filesPath.getTrainedNetworkFile());
		}else if(btnPressed.equals("train")){
			trainNetwork(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

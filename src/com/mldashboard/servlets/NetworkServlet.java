package com.mldashboard.servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
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

	public void normalize(HttpServletRequest request, HttpServletResponse response) throws IOException{
		System.out.println("Normalazing");
		Scanner scanner = new Scanner(new File(filesPath.getBaseFile()));
		String csvHeader = scanner.nextLine();
		String features[] = csvHeader.split("\\s*,\\s*");
		Gson gson = new Gson();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(features));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String btnPressed = request.getParameter("btnPressed");
		System.out.println(btnPressed);
		if(btnPressed.equals("shuffle")){
			shuffle();
		}else if(btnPressed.equals("segregate")){
			segregate();
		}else if(btnPressed.equals("normalize")){
			normalize(request, response);
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

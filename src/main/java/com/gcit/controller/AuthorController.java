package com.gcit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.dao.AuthorDao;
import com.gcit.dto.Author;
import com.gcit.service.AuthorService;
import com.google.gson.Gson;
import java.util.Random; 

/**
 * Servlet implementation class AuthorController
 */
@WebServlet({ "/author", "/author/id/*" , "/author/remove/id/*", "/author/retrieve/id/*"})


public class AuthorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AuthorService authorService=  new AuthorService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getRequestURI().substring(request.getContextPath().length());
		Gson gson = new Gson();
		if("/author".equals(path) || "/author/".equals(path)) {
			response.setContentType("application/json"); 
			PrintWriter out = response.getWriter();
			System.out.println(authorService.getAllAuthors());
			out.print(gson.toJson(authorService.getAllAuthors()));
			out.flush();
		}
		
		if(path.contains("/author/remove/id")) {
			String pathInfo = request.getPathInfo();

			if(pathInfo == null) {
				System.out.println("hi");
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
			else {
				Integer id = Integer.parseInt(pathInfo.replaceAll("/", ""));
				authorService.deleteAuthorByID(id);
				PrintWriter out = response.getWriter();
				out.print("Your author got deleted");
			}
		}
		if(path.contains("/author/retrieve/id")) {
			System.out.println("3");

			String pathInfo = request.getPathInfo();
			if(pathInfo == null) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
			else {
				try {
					Integer id = Integer.parseInt(pathInfo.replaceAll("/", ""));
					
					response.setContentType("application/json");
				     
					PrintWriter out = response.getWriter();
					  
					out.print(gson.toJson( authorService.getAllAuthors().stream()
												.filter( author -> author.getAuthorId() == id )
												.collect(Collectors.toList()) )
							);
					
					out.flush();
				}
				catch(Exception e) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//String name = request.getParameter(name);
		String uname = request.getParameter("uname");
		String strID = request.getParameter("id");
		Gson gson = new Gson();
		System.out.println(strID);
		if(strID.equals("")) {
	        authorService.createAuthor( uname);
			PrintWriter out = response.getWriter();
			out.print(gson.toJson( uname));
			out.flush();
		}
		else {
			int id= Integer.parseInt(strID);
			authorService.updateAuthorName(id,  uname);
			PrintWriter out = response.getWriter();
			out.print(gson.toJson( uname));
			out.flush();

		}
	}

}

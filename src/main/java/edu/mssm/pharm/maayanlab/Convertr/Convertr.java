package edu.mssm.pharm.maayanlab.Convertr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

@WebServlet(urlPatterns= {"/convert"})
public class Convertr extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8436947486144190717L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Content-Disposition", "attachment; filename=graph.svg");		
		
		response.getOutputStream().write(DatatypeConverter.parseBase64Binary(request.getParameter("data")));
	}
}

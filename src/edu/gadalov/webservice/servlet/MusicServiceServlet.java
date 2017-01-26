package edu.gadalov.webservice.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.gadalov.webservice.command.CommandManager;
import edu.gadalov.webservice.connection.ConnectionPool;


@WebServlet("/MusicServiceServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, 
maxFileSize=1024*1024*20,      
maxRequestSize=1024*1024*50) 
public class MusicServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MusicServiceServlet() {
        super();
    }
    public void init(ServletConfig config) throws ServletException {
		ConnectionPool.getInstance();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//add-comment
		String command = request.getParameter("command");
		CommandManager manager = new CommandManager();
		String forwardPage = manager.getCommand(command).execute(request);
		request.getRequestDispatcher(forwardPage).forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		CommandManager manager = new CommandManager();
		String forwardPage = manager.getCommand(command).execute(request);
		if(request.getAttribute("success") == null){
			request.getRequestDispatcher(forwardPage).forward(request, response);
		} else{
			response.sendRedirect(forwardPage);
		}
	}
	public void destroy() {
			ConnectionPool.getInstance().destroyPool();
	}

}

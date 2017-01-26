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


/**Controller.
 * @author Maxim Gadalov
 *
 */
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
		String url = processExecute(request,response);
		request.getRequestDispatcher(url).forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = processExecute(request,response);
		if(request.getAttribute("success") == null){
			request.getRequestDispatcher(url).forward(request, response);
		} else{
			response.sendRedirect(url);
		}
	}
	public void destroy() {
			ConnectionPool.getInstance().destroyPool();
	}
	/**Return url 
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return String url 
	 */
	public String processExecute(HttpServletRequest request, HttpServletResponse response){
		String command = request.getParameter("command");
		CommandManager manager = new CommandManager();
		String url = manager.getCommand(command).execute(request);
		return url;
	}

}

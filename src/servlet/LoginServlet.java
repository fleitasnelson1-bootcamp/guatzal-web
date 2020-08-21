package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servicios.GuatzakService;
import servicios.LoginService;
import util.Conector;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet(description = "Servlet for login", urlPatterns = { "/login" })
@MultipartConfig
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final LoginService _loginservice= new LoginService();
	private final GuatzakService _guatzakService = new GuatzakService();
    private final String USER_ID = "user_id";    
    private final String inboxUrl = "inbox.html";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try(Conector co = Conector.newInstance()){
			String userName = request.getParameter("username");
			//Si se verifican correctamente el usuario y contraseña se redirecciona a la pagina de chat.
			if(_loginservice.verify(co,request.getParameter("username") ,request.getParameter("password"))) {
				response.setContentType("text/html");
				request.getSession(true).setAttribute(USER_ID, _guatzakService.getId(co,request.getParameter(userName)));
				RequestDispatcher rd = request.getRequestDispatcher("s");
				rd.hashCode();
			}else {
				response.getWriter().print("Usuario o contraseña incorrectas");
				System.out.println("Incorrecto");
			}
			
		} catch (Exception e) {
			System.out.print("error");
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

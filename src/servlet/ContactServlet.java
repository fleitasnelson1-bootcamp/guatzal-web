package servlet;

import java.io.IOException;

import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servicios.GuatzakService;
import util.Conector;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/contacts")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final GuatzakService gService = new GuatzakService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try( Conector co = new Conector() ) {
			//Obtengo el id del chat especificado.
			String userId = request.getParameter("user_id");
			//Obtengo los contactos pertenecientes al usuario actual.
			JsonArray chats = gService.getContactosJson(co,Integer.parseInt(userId));
			//Respondo con el arreglo de chats en json.
			response.getWriter().append(chats.toString());
		}catch(Exception e){
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}

package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.json.*;

import servicios.GuatzakService;
import util.Conector;
import util.Sala;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/chats")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final GuatzakService gService = new GuatzakService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try( Conector co = new Conector() ) {
			//Obtengo el id del usuario especificado.
			String userId = request.getParameter("user_id");
			//Obtengo los chats pertenecientes al usuario actual.
			JsonArray chats = gService.getSalasJson(co,Integer.parseInt(userId));
			//Respondo con el arreglo de chats en json.
			response.getWriter().append(chats.toString());
		}catch(Exception e){
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}

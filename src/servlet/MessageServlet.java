package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servicios.GuatzakService;
import util.Conector;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet(description = "Servlet for login", urlPatterns = { "/message" })
@MultipartConfig
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final GuatzakService msgService = new GuatzakService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
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
		final String MSG_PARAM = "message";
		final String UID_PARAM = "user_id";
		final String CHATID_PARAM = "chat_id";
		
		try( Conector co = Conector.newInstance() ){
			int userId = Integer.parseInt(request.getParameter(UID_PARAM));
			int chatId = 19;//Integer.parseInt(request.getParameter(CHATID_PARAM));
			String message = request.getParameter(MSG_PARAM);
			msgService.enviarMensaje(co, userId,chatId, message);
			//Si tuvo exito. Envio un status confirmando que se creo un recurso.
			response.setStatus(response.SC_CREATED);
		}catch(Exception e) {
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}

}

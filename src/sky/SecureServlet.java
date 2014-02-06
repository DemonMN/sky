package sky;
import static util.Constants.USER;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.types.User;

public class SecureServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute(USER);
		
		if (user != null){
			response.setContentType("text/plain");
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getWriter(), user);
			response.getWriter().flush();
		}
		else{
			response.sendRedirect("/error.html?message=login.success");
		}
	}

}

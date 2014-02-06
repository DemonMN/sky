package sky;
import static util.Constants.TOKEN;
import static util.Constants.USER;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import portal.api.FacebookAPI;
import util.Constants;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.restfb.types.User;

public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		userService.createLoginURL("login.html");
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute(USER);
		String token = request.getParameter(TOKEN);
		if (token == null){
			response.sendRedirect("/index.html?message=login.invalid");
		}
		if (user == null){
			user = FacebookAPI.getClient(token).fetchObject("me", User.class);
			session.setAttribute(Constants.USER, user);
			response.sendRedirect("/index.html?message=login.success");
		}
	}

}

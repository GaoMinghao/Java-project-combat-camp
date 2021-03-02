package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/user")
public class UserRegisteredController implements PageController {

    @POST
    @Path("/registered")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
         User user = new User();
         user.setEmail(request.getParameter("email"));
         user.setPassword(request.getParameter("password"));
        UserService userService = new UserServiceImpl();
        userService.register(user);
         return "login-form.jsp";
    }
}

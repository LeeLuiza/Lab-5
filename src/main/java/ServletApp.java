import com.example.model.AccountUser;
import dbService.DBWorker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/my"})
public class ServletApp extends HttpServlet {
    private final DBWorker users = new DBWorker();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        AccountUser user = users.getUser(login);
        if(user == null || !user.getPassword().equals(password)){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Неправильный логин или пароль.");
            return;
        }

        request.getSession().setAttribute("login", login);
        request.getSession().setAttribute("password", password);

        String url = request.getRequestURL().toString();
        response.sendRedirect(url.substring(0, url.lastIndexOf('/')) + "/files");
    }
}
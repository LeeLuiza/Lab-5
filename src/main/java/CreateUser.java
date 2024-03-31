import com.example.model.AccountUser;
import dbService.DBWorker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = {"/registration"})
public class CreateUser extends HttpServlet {
    private final DBWorker users = new DBWorker();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registration.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        AccountUser profile = new AccountUser(login, email, password);
        if (users.getUser(login) == null) { //проверяем существует ли такой логин
            users.addUser(profile);

            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("password", password);

            String path = "D:\\Folder\\" + login;
            File folder = new File(path);
            folder.mkdir();

            String url = request.getRequestURL().toString();
            response.sendRedirect(url.substring(0, url.lastIndexOf("/")) + "/files");
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Такой логин уже используется");
        }
    }
}

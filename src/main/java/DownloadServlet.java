import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns = {"/DownloadServlet"})
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = request.getParameter("path");
        File file = new File(filePath);

        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());

        try (FileInputStream stream = new FileInputStream(file)) {
            int data;
            while ((data = stream.read()) != -1) {
                response.getOutputStream().write(data); //выходной поток
            }
        }
    }
}

package servlets;

import resources.Hibernate.HibernateShell;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SaveMarkServlet")
public class SaveMarkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = (String) req.getParameter("id");
        String type = (String) req.getParameter("type");
        String value = (String) req.getParameter("value");
        if (id == null || type == null || value == null) {
            req
                    .getRequestDispatcher("WEB-INF/pages/NotFound.jsp")
                    .forward(req, resp);
        }
        try {
            if (value.equals(""))
                value="-1";
            if (type.equals("lab")) {
                HibernateShell.updateLabMark(Integer.parseInt(id), Integer.parseInt(value));
            } else if (type.equals("test")) {
                HibernateShell.updateTestMark(Integer.parseInt(id), Integer.parseInt(value));
            } else if (type.equals("bonus")) {
                HibernateShell.updateBonusMark(Integer.parseInt(id), Integer.parseInt(value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}

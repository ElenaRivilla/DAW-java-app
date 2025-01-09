import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/insertarLibro")
public class InsertarLibro extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titol = request.getParameter("titol");
        String isbn = request.getParameter("isbn");
        int any_publicacio = Integer.parseInt(request.getParameter("any_publicacio"));
        int idEditorial = Integer.parseInt(request.getParameter("editorial"));

        try (Connection conn = Connexio.getConnexio()) {
            String sql = "INSERT INTO llibres (titol, isbn, any_publicacio, id_editorial) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, titol);
                stmt.setString(2, isbn);
                stmt.setInt(3, any_publicacio);
                stmt.setInt(4, idEditorial);
                stmt.executeUpdate();
            }
            response.sendRedirect("Consulta");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al insertar el libro.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InsertarLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
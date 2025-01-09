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

@WebServlet("/borrarLibro")
public class BorrarLibro extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idLibro = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = Connexio.getConnexio()) {
            String sql = "DELETE FROM llibres WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idLibro);
                stmt.executeUpdate();
            }
            response.sendRedirect("Consulta");
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al borrar el libro.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BorrarLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
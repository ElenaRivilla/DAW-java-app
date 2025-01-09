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

/**
 * BorrarLibro és una classe Servlet que gestiona les sol·licituds per esborrar un llibre de la base de dades.
 * Aquesta classe respon a les sol·licituds GET enviades a la URL "/borrarLibro".
 */
@WebServlet("/borrarLibro")
public class BorrarLibro extends HttpServlet {
    /**
     * Mètode doGet per gestionar les sol·licituds GET per esborrar un llibre de la base de dades.
     *
     * @param request  l'objecte HttpServletRequest que conté la sol·licitud del client
     * @param response l'objecte HttpServletResponse que conté la resposta del servidor
     * @throws ServletException si ocorre un error específic del servlet
     * @throws IOException si ocorre un error d'entrada/sortida
     */
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
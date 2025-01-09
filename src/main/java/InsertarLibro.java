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
 * Aquest servlet gestiona les peticions POST per inserir un nou llibre a la base de dades.
 * 
 * Aquest servlet rep les dades del llibre a inserir a través dels paràmetres de la petició HTTP POST,
 * crea una connexió a la base de dades i executa una instrucció SQL per inserir el nou llibre.
 * Si la inserció és exitosa, redirigeix l'usuari a la pàgina de consulta. En cas contrari, envia un error.
 * 
 * @see jakarta.servlet.http.HttpServlet
 */
@WebServlet("/insertarLibro")
public class InsertarLibro extends HttpServlet {
    /**
     * Gestiona les peticions POST per inserir un nou llibre a la base de dades.
     *
     * @param request  l'objecte HttpServletRequest que conté la petició del client
     * @param response l'objecte HttpServletResponse que conté la resposta del servidor
     * @throws ServletException si ocorre un error específic del servlet
     * @throws IOException      si ocorre un error d'entrada/sortida
     */
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

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/editarLibro")
public class EditarLibro extends HttpServlet {

    // Maneja la solicitud GET para cargar el libro a editar
    /**
     * Mètode doGet per gestionar les sol·licituds GET del servlet.
     *
     * @param request  l'objecte HttpServletRequest que conté la sol·licitud del client
     * @param response l'objecte HttpServletResponse que conté la resposta del servlet
     * @throws ServletException si ocorre un error específic del servlet
     * @throws IOException      si ocorre un error d'entrada/sortida
     *
     * Aquest mètode obté l'identificador del llibre de la sol·licitud, consulta la base de dades
     * per obtenir les dades del llibre corresponent i estableix aquests valors com a atributs
     * de la sol·licitud. Si el llibre no es troba, retorna un error 404.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idLibro = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = Connexio.getConnexio()) {
            String sql = "SELECT * FROM llibres WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idLibro);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    // Establecemos los valores obtenidos de la base de datos en los atributos de la solicitud
                    request.setAttribute("titol", rs.getString("titol"));
                    request.setAttribute("isbn", rs.getString("isbn"));
                    request.setAttribute("anyPublicacio", rs.getInt("any_publicacio"));
                    request.setAttribute("idEditorial", rs.getInt("id_editorial"));
                    // Redirigimos al JSP para que los valores se muestren en los campos
                    request.getRequestDispatcher("Consulta").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Libro no encontrado.");
                }
            }
        } catch (Exception e) {
            throw new ServletException("Error al obtener los datos del libro.", e);
        }
    }

    // Maneja la solicitud POST para actualizar los datos del libro
    /**
     * Processa les sol·licituds POST per actualitzar la informació d'un llibre a la base de dades.
     *
     * @param request  l'objecte HttpServletRequest que conté la sol·licitud del client
     * @param response l'objecte HttpServletResponse que conté la resposta del servidor
     * @throws ServletException si ocorre un error específic del servlet
     * @throws IOException      si ocorre un error d'entrada/sortida
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idLibro = Integer.parseInt(request.getParameter("id"));
        String titol = request.getParameter("titol");
        String isbn = request.getParameter("isbn");
        int anyPublicacio = Integer.parseInt(request.getParameter("any_publicacio"));
        int editorialId = Integer.parseInt(request.getParameter("editorial"));

        try (Connection conn = Connexio.getConnexio()) {
            String updateQuery = "UPDATE llibres SET titol = ?, isbn = ?, any_publicacio = ?, id_editorial = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                stmt.setString(1, titol);
                stmt.setString(2, isbn);
                stmt.setInt(3, anyPublicacio);
                stmt.setInt(4, editorialId);
                stmt.setInt(5, idLibro);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    response.sendRedirect("Consulta");  // Redirigir a la lista de libros después de actualizar
                } else {
                    request.setAttribute("error", "No se pudo actualizar el libro.");
                    request.getRequestDispatcher("editarLibro.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            throw new ServletException("Error al actualizar el libro.", e);
        }
    }
}

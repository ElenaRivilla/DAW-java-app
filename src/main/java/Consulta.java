import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/Consulta") // URL para acceder al servlet
public class Consulta extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obtener conexión usando Connexio.java
            conn = Connexio.getConnexio();
            System.out.println("Conexión establecida con éxito.");

            // Consulta SQL
            String sql = "SELECT llibres.id, llibres.titol, llibres.isbn, llibres.any_publicacio, editorials.nom AS editorial, autors.nom AS autor " +
                         "FROM llibres " +
                         "LEFT JOIN editorials ON llibres.id_editorial = editorials.id " +
                         "LEFT JOIN llibre_autor ON llibres.id = llibre_autor.id_llibre " +
                         "LEFT JOIN autors ON llibre_autor.id_autor = autors.id";

            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Lista para almacenar los resultados
            List<Map<String, String>> llibres = new ArrayList<>();

            // Recorriendo el ResultSet
            while (rs.next()) {
                Map<String, String> llibre = new HashMap<>();
                llibre.put("id", String.valueOf(rs.getInt("id")));
                llibre.put("titol", rs.getString("titol"));
                llibre.put("autor", rs.getString("autor") != null ? rs.getString("autor") : "Desconegut");
                llibre.put("isbn", rs.getString("isbn"));
                llibre.put("anyPublicacio", String.valueOf(rs.getInt("any_publicacio")));
                llibre.put("editorial", rs.getString("editorial") != null ? rs.getString("editorial") : "Sense editorial");

                llibres.add(llibre);
            }

            // Verificar si se recuperaron libros
            System.out.println("Libros recuperados: " + llibres.size());

            // Pasar los resultados al JSP
            request.setAttribute("llibres", llibres);

            // Redirigir al JSP
            request.getRequestDispatcher("llibreria.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la consulta: " + e.getMessage());
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
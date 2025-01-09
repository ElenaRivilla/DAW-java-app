import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class AppInitializer implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Obtén la conexión usando la clase Connexio
            Connection conn = Connexio.getConnexio();
            // Guarda la conexión en el contexto de la aplicación
            sce.getServletContext().setAttribute("conn", conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cierra la conexión cuando la aplicación se detenga
        Connection conn = (Connection) sce.getServletContext().getAttribute("conn");
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

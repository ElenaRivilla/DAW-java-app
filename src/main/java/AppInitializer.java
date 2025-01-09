import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class AppInitializer implements ServletContextListener {
    
    /**
     * Mètode que s'executa quan el context de l'aplicació es inicialitza.
     * Aquest mètode obté una connexió a la base de dades utilitzant la classe Connexio
     * i la guarda en el context de l'aplicació per a que estigui disponible durant tot el cicle de vida de l'aplicació.
     *
     * @param sce l'esdeveniment de context de servlet que conté el context de l'aplicació
     */
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

    /**
     * Aquest mètode es crida quan l'aplicació es deté i el context de servlet es destrueix.
     * Tanca la connexió a la base de dades si està oberta.
     *
     * @param sce l'esdeveniment de context de servlet que conté el context de servlet
     */
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

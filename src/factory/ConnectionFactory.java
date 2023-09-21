package factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	   private String url = "jdbc:mysql://localhost:3306/hotel_alura?useTimeZone=true&serverTimeZone=UTC";
	    private String username = "root";
	    private String password = "Pakoal42";

	    DataSource dataSource;

	    public ConnectionFactory() {
	        ComboPooledDataSource PooledDataSource = new ComboPooledDataSource();
	        PooledDataSource.setJdbcUrl(url);
	        PooledDataSource.setUser(username);
	        PooledDataSource.setPassword(password);
	        PooledDataSource.setMaxPoolSize(10); // Establece el tamaño máximo del conjunto de conexiones

	        this.dataSource = PooledDataSource;
	    }

	    public Connection recuperarConexion() {
	        try {
	            return dataSource.getConnection();
	        } catch (SQLException ex) {
	            throw new RuntimeException(ex);
	        }
	    }
}

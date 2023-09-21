package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import modelo.Reserva;

public class ReservaDAO {
	  private Connection connection;

	    public ReservaDAO(Connection cn) {
	        this.connection = cn;
	    }

	    //Listar todos
	    public List<Reserva> listar() {
	        List<Reserva> reservas = new ArrayList<>();
	        try {
	            String sql = "SELECT * FROM reservas";

	            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	                pstmt.execute();
	                
	                try (ResultSet rs = pstmt.getResultSet()) {
	                    while (rs.next()) {
	                        Reserva reservaBD = new Reserva(rs.getLong(1), rs.getDate(2), rs.getDate(3), rs.getInt(4), rs.getString(5));
	                        reservas.add(reservaBD);
	                    }
	                }
	                return reservas;
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    //Buscar Reserva
	    public Reserva buscarPorId(Long id) {
	        Reserva reservaBD = null;
	        try {
	            String sql = "SELECT * FROM reservas WHERE id=?";

	            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

	                pstmt.setLong(1, id);

	                pstmt.execute();

	                try (ResultSet rs = pstmt.getResultSet()) {
	                    while (rs.next()) {
	                        reservaBD = new Reserva(rs.getLong(1), rs.getDate(2), rs.getDate(3), rs.getInt(4), rs.getString(5));
	                    }
	                }
	                return reservaBD;
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    //Actualizar Reserva
	    public void actualizar(Long id, Reserva reserva) {
	        try {
	            String sql = "UPDATE reservas SET fecha_entrada=?, fecha_salida=?, valor=?, forma_de_pago=? WHERE id=?";
	            try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	               
	                pstmt.setDate(1, reserva.getFechaEntrada());
	                pstmt.setDate(2, reserva.getFechaSalida());
	                pstmt.setInt(3, reserva.getValor());
	                pstmt.setString(4, reserva.getFormaDePago());
	                pstmt.setLong(5, id);

	                pstmt.executeUpdate();
	                try (ResultSet rs = pstmt.getGeneratedKeys()) {
	                    while (rs.next()) {
	                        reserva.setId(rs.getLong(1));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    //Guardar Reserva
	    public void guardar(Reserva reserva) {
	        try {
	            String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_de_pago) VALUES (?,?,?,?)";

	            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	                pstm.setDate(1, reserva.getFechaEntrada());
	                pstm.setDate(2, reserva.getFechaSalida());
	                pstm.setInt(3, reserva.getValor());
	                pstm.setString(4, reserva.getFormaDePago());

	                pstm.executeUpdate();

	                try (ResultSet rs = pstm.getGeneratedKeys()) {
	                    while (rs.next()) {
	                        reserva.setId(rs.getLong(1));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    //Eliminar Reserva
	    public void eliminar(Long id) {
	        try {
	            String sql = "DELETE FROM reservas WHERE id=?";
	            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	                pstmt.setLong(1, id);
	                pstmt.executeUpdate();

	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Huesped;

public class HuespedDAO {
	private Connection connection;
	private ReservaDAO reserDao;

	public HuespedDAO(Connection cn) {
		this.connection = cn;
	}

	// Listar todos
	public List<Huesped> listar() {
		List<Huesped> huespedes = new ArrayList<>();
		try {
			String sql = "SELECT * FROM huespedes";

			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
				pstmt.execute();

				try (ResultSet rs = pstmt.getResultSet()) {
					while (rs.next()) {
						Huesped huespedBD = new Huesped(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDate(4),
								rs.getString(5), rs.getString(6), rs.getLong(7));
						huespedes.add(huespedBD);
					}
				}
				return huespedes;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Buscar Huesped
	public Huesped buscarPorId(Long id) {
		Huesped huespedBD = null;
		try {
			String sql = "SELECT * FROM huespedes WHERE id=?";

			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

				pstmt.setLong(1, id);

				pstmt.execute();

				try (ResultSet rs = pstmt.getResultSet()) {
					while (rs.next()) {
						huespedBD = new Huesped(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDate(4),
								rs.getString(5), rs.getString(6), rs.getLong(7));
					}
					return huespedBD;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Huesped buscarPorIdReserva(Long id) {
		Huesped huespedBD = null;
		try {
			String sql = "SELECT * FROM huespedes WHERE id_reserva=?";

			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

				pstmt.setLong(1, id);

				pstmt.executeUpdate();

				try (ResultSet rs = pstmt.getResultSet()) {
					while (rs.next()) {
						huespedBD = new Huesped(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDate(4),
								rs.getString(5), rs.getString(6), rs.getLong(6));
					}
					return huespedBD;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Actualizar Huesped
	public void actualizar(Long id, Huesped huesped) {
		try {
			String sql = "UPDATE huespedes SET nombre=?, apellido=?, fecha_nacimiento=?, nacionalidad=?, telefono=? WHERE id=?";
			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

				pstmt.setString(1, huesped.getNombre());
				pstmt.setString(2, huesped.getApellido());
				pstmt.setDate(3, huesped.getFechaNacimiento());
				pstmt.setString(4, huesped.getNacionalidad());
				pstmt.setString(5, huesped.getTelefono());
				pstmt.setLong(6, id);

				pstmt.executeUpdate();

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Guardar Huesped
	public void guardar(Huesped huesped) {
		try {
			String sql = "INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva) VALUES (?,?,?,?,?,?)";

			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

				pstmt.setString(1, huesped.getNombre());
				pstmt.setString(2, huesped.getApellido());
				pstmt.setDate(3, huesped.getFechaNacimiento());
				pstmt.setString(4, huesped.getNacionalidad());
				pstmt.setString(5, huesped.getTelefono());
				pstmt.setLong(6, huesped.getIdReserva());

				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Eliminar Huespes
	public void eliminar(Long id) {
		try {
			String sql = "DELETE FROM huespedes WHERE id=?";
			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
				pstmt.setLong(1, id);
				pstmt.executeUpdate();

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

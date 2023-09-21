package controllers;

import java.util.List;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {
	ConnectionFactory connection = new ConnectionFactory();
	ReservaDAO reservaDAO = new ReservaDAO(connection.recuperarConexion());

	public List<Reserva> listarReservas() {

		return reservaDAO.listar();
	}

	public Reserva buscarPorId(Long id) {

		return reservaDAO.buscarPorId(id);
	}

	public void guardar(Reserva reserva) {
		reservaDAO.guardar(reserva);
	}
	
	public void actualizar(Long id, Reserva reserva) {
		reservaDAO.actualizar(id, reserva);
	}
	
	public void eliminar(Long id) {
		reservaDAO.eliminar(id);
	}

}

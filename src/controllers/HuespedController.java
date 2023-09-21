package controllers;

import java.util.List;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huesped;

public class HuespedController {
	ConnectionFactory connection = new ConnectionFactory();
	HuespedDAO huespedDAO = new HuespedDAO(connection.recuperarConexion());

	public List<Huesped> listarHuesped() {

		return huespedDAO.listar();
	}

	public Huesped buscarPorId(Long id) {

		return huespedDAO.buscarPorId(id);
	}
	
	public Huesped buscarPorIdReserva(Long id) {

		return huespedDAO.buscarPorIdReserva(id);
	}

	public void guardar(Huesped huesped) {
		huespedDAO.guardar(huesped);
	}
	
	public void actualizar(Long id, Huesped huesped) {
		huespedDAO.actualizar(id, huesped);
	}
	
	public void eliminar(Long id) {
		huespedDAO.eliminar(id);
	}

}

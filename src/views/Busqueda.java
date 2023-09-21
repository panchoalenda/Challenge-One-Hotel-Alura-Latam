package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.HuespedController;
import controllers.ReservaController;
import modelo.Huesped;
import modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	String reserva;
	String huespedes;

	ReservaController reservaController;
	HuespedController huespedController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);
		poblarTablaReserva();

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		poblarTablaHuesped();

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Efecto en el bontón Exit
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtBuscar.getText().equals("")) {
					vaciarTablasReservaHuesped();
					poblarTablaReserva();
					poblarTablaHuesped();
				} else {
					vaciarTablasReservaHuesped();
					poblarTablaReserva();
					poblarTablaHuesped();
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtBuscar.getText().equals("")) {
					vaciarTablasReservaHuesped();
					poblarTablaReserva();
					poblarTablaHuesped();
				} else {
					vaciarTablasReservaHuesped();
					Reserva r = reservaController.buscarPorId(Long.decode(txtBuscar.getText()));
					try {

						modelo.addRow(new Object[] { r.getId(), r.getFechaEntrada(), r.getFechaSalida(), r.getValor(),
								r.getFormaDePago() });

					} catch (Exception ex) {
						JOptionPane.showConfirmDialog(null, "El ID de la Resreva no existe en la base de datos.",
								"Error de Reserva", JOptionPane.CLOSED_OPTION);
						vaciarTablasReservaHuesped();
						poblarTablaReserva();
						poblarTablaHuesped();
						// throw new RuntimeException(ex);
					}
					System.out.println("El id: " + Long.decode(txtBuscar.getText()));
				}
			}
		});

		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int numeroFilaTablaReserva = tbReservas.getSelectedRow(); // Devuelve la fila seleccionada. Si no hay
																			// ninguna fila seleccionada devuelve -1
				int numeroFilaTablaHuesped = tbHuespedes.getSelectedRow();// Devuelve la fila seleccionada. Si no hay
																			// ninguna fila seleccionada devuelve -1

				if (numeroFilaTablaReserva >= 0) { // Para verificar que haya una fila seleccionada
					String reservaId = tbReservas.getValueAt(numeroFilaTablaReserva, 0).toString(); // Devuelve, en este
																									// caso, el ID de la
																									// posición
																									// (numeroFilaTablaReserva,
																									// 0)
					String nuevaFechaEntrada = tbReservas.getValueAt(numeroFilaTablaReserva, 1).toString();
					String nuevaFechaSalida = tbReservas.getValueAt(numeroFilaTablaReserva, 2).toString();
					String nuevoValor = tbReservas.getValueAt(numeroFilaTablaReserva, 3).toString();
					String nuevaFormaPago = tbReservas.getValueAt(numeroFilaTablaReserva, 4).toString();

					int preguntaConfirmacion = JOptionPane.showConfirmDialog(null, "¿Desea Modificar la Reserva?");
					if (preguntaConfirmacion == JOptionPane.YES_OPTION) {

						Reserva actualizaReservar = new Reserva(java.sql.Date.valueOf(nuevaFechaEntrada),
								java.sql.Date.valueOf(nuevaFechaSalida), Integer.valueOf(nuevoValor), nuevaFormaPago);

						reservaController.actualizar(Long.valueOf(reservaId), actualizaReservar);

						JOptionPane.showMessageDialog(contentPane, "Se modificó exitosamente la Reserva.");
						vaciarTablasReservaHuesped();
						poblarTablaReserva();
						poblarTablaHuesped();
					}
				}

				else if (numeroFilaTablaHuesped >= 0) {

					huespedes = tbHuespedes.getValueAt(numeroFilaTablaHuesped, 0).toString();
					int confirmarh = JOptionPane.showConfirmDialog(null, "¿Desea Modificar el Huesped?");

					if (confirmarh == JOptionPane.YES_OPTION) {

						Long valor =Long.valueOf(tbHuespedes.getValueAt(numeroFilaTablaHuesped, 0).toString());
						
						String nombreAc = tbHuespedes.getValueAt(numeroFilaTablaHuesped, 1).toString();
						String apellido = tbHuespedes.getValueAt(numeroFilaTablaHuesped, 2).toString();
						String fechaNac = tbHuespedes.getValueAt(numeroFilaTablaHuesped, 3).toString();
						String nacionalidad = tbHuespedes.getValueAt(numeroFilaTablaHuesped, 4).toString();
						String telefono =tbHuespedes.getValueAt(numeroFilaTablaHuesped, 5).toString();
						Long idReserva = Long.valueOf(tbHuespedes.getValueAt(numeroFilaTablaHuesped, 6).toString());
						Huesped huesped = new Huesped(nombreAc, apellido, java.sql.Date.valueOf(fechaNac), nacionalidad,telefono, idReserva);
						
						huespedController.actualizar(valor, huesped);
						
						JOptionPane.showMessageDialog(contentPane, "Se modificó exitosamente el Huesped.");
						vaciarTablasReservaHuesped();
						poblarTablaReserva();
						poblarTablaHuesped();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Error fila no seleccionada, por favor realice una busqueda y seleccione una fila para eliminar");
				}
			}
		});
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int numeroFilaTablaReserva = tbReservas.getSelectedRow(); // Devuelve la fila seleccionada. Si no hay
																			// ninguna fila seleccionada devuelve -1
				int numeroFilaTablaHuesped = tbHuespedes.getSelectedRow();// Devuelve la fila seleccionada. Si no hay
																			// ninguna fila seleccionada devuelve -1

				if (numeroFilaTablaReserva >= 0) { // Para verificar que haya una fila seleccionada
					reserva = tbReservas.getValueAt(numeroFilaTablaReserva, 0).toString(); // Devuelve, en este caso, el
																							// ID de la posición
																							// (numeroFilaTablaReserva,
																							// 0)
					int preguntaConfirmacion = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar la Reserva?");

					if (preguntaConfirmacion == JOptionPane.YES_OPTION) {

						String valor = tbReservas.getValueAt(numeroFilaTablaReserva, 0).toString();
						Huesped huesped = huespedController.buscarPorId(Long.valueOf(valor));
						System.out.println("huesped: " + huesped);

						reservaController.eliminar(Long.valueOf(valor));
						JOptionPane.showMessageDialog(contentPane, "Se elimino la Reserva con éxito");
						vaciarTablasReservaHuesped();
						poblarTablaReserva();
						poblarTablaHuesped();
					}
				}

				else if (numeroFilaTablaHuesped >= 0) {
					JOptionPane.showMessageDialog(null,
							"Para poder eliminar un Huesped, debes eliminar la reserva que tiene asociada.");
					/*huespedes = tbHuespedes.getValueAt(numeroFilaTablaHuesped, 0).toString();
					int confirmarh = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar el Huesped?");

					if (confirmarh == JOptionPane.YES_OPTION) {

						String valor = tbHuespedes.getValueAt(numeroFilaTablaHuesped, 0).toString();
						huespedController.eliminar(Long.valueOf(valor));
						JOptionPane.showMessageDialog(contentPane, "\"Se elimino el huesped con éxito\"");
						vaciarTablasReservaHuesped();
						poblarTablaReserva();
						poblarTablaHuesped();
					}*/
				} /*else {
					JOptionPane.showMessageDialog(null,
							"Error fila no seleccionada, por favor realice una busqueda y seleccione una fila para eliminar");
				}*/
			}

		});

		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

	// Limpiar tablas
	private void vaciarTablasReservaHuesped() {
		((DefaultTableModel) tbHuespedes.getModel()).setRowCount(0);
		((DefaultTableModel) tbReservas.getModel()).setRowCount(0);

	}

	// Tabla Reserva
	public List<Reserva> listarReserva() {
		return this.reservaController.listarReservas();
	}

	private void poblarTablaReserva() {
		List<Reserva> reservas = listarReserva();

		try {
			for (Reserva r : reservas) {
				modelo.addRow(new Object[] { r.getId(), r.getFechaEntrada(), r.getFechaSalida(), r.getValor(),
						r.getFormaDePago() });
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// Tabla Huesped
	public List<Huesped> listarHuesped() {
		return this.huespedController.listarHuesped();
	}

	private void poblarTablaHuesped() {
		List<Huesped> huesped = listarHuesped();

		try {
			for (Huesped r : huesped) {
				modeloHuesped.addRow(new Object[] { r.getId(), r.getNombre(), r.getApellido(), r.getFechaNacimiento(),
						r.getNacionalidad(),r.getTelefono(),r.getIdReserva()});
				System.out.println("ieReserva= " + r.getIdReserva());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}

package modelo;

import java.sql.Date;

public class Reserva {

    private Long id;

    private Date fechaEntrada;

    private Date fechaSalida;

    private int valor;

    private String formaDePago;

    public Reserva(Date fechaEntrada, Date fechaSalida, int valor, String formaDePago) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaDePago = formaDePago;
    }

    public Reserva(Long id, Date fechaEntrada, Date fechaSalida, int valor, String formaDePago) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaDePago = formaDePago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", valor=" + valor + ", formaDePago=" + formaDePago + '}';
    }
}

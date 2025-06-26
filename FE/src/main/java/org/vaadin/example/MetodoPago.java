package org.vaadin.example;

public class MetodoPago {
    private long numeroTarjeta;
    private String nombreAsociado;

    public MetodoPago() {}

    public MetodoPago(long numeroTarjeta, String nombreAsociado) {
        this.numeroTarjeta = numeroTarjeta;
        this.nombreAsociado = nombreAsociado;
    }

    public long getNumeroTarjeta() { return numeroTarjeta; }
    public void setNumeroTarjeta(long numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; }

    public String getNombreAsociado() { return nombreAsociado; }
    public void setNombreAsociado(String nombreAsociado) { this.nombreAsociado = nombreAsociado; }
}

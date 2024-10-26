package com.krakedev.moduloii.evaluacionfinal.entidades;

import java.util.Date;

public class RegistroMovimiento {
	private int id;
	private Articulo idArticulo;
	private int cantidad;
	private Date fechaMovimiento;
	
	public RegistroMovimiento() {
		super();
	}

	public RegistroMovimiento(int id, Articulo idArticulo, int cantidad) {
		super();
		this.id = id;
		this.idArticulo = idArticulo;
		this.cantidad = cantidad;
	}


	public RegistroMovimiento(Articulo idArticulo, int cantidad, Date fechaMovimiento) {
		super();
		this.idArticulo = idArticulo;
		this.cantidad = cantidad;
		this.fechaMovimiento = fechaMovimiento;
	}

	public RegistroMovimiento(int id, Articulo idArticulo, int cantidad, Date fechaMovimiento) {
		super();
		this.id = id;
		this.idArticulo = idArticulo;
		this.cantidad = cantidad;
		this.fechaMovimiento = fechaMovimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Articulo getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Articulo idArticulo) {
		this.idArticulo = idArticulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	@Override
	public String toString() {
		return "RegistroMovimiento [id=" + id + ", idArticulo=" + idArticulo + ", cantidad=" + cantidad
				+ ", fechaMovimiento=" + fechaMovimiento + "]";
	}
	
}

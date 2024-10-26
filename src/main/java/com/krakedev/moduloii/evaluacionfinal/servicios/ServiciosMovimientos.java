package com.krakedev.moduloii.evaluacionfinal.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.krakedev.moduloii.evaluacionfinal.entidades.RegistroMovimiento;
import com.krakedev.moduloii.evaluacionfinal.persistencia.MovimientosBDD;

public class ServiciosMovimientos {

	public void insertar(RegistroMovimiento movimiento) {
		MovimientosBDD MovimientosBDD = new MovimientosBDD();
		MovimientosBDD.insertar(movimiento);
	}
	@Path("actualizar")
	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	public void actualizar(RegistroMovimiento movimiento) {
		MovimientosBDD MovimientosBDD = new MovimientosBDD();
		MovimientosBDD.insertar(movimiento);
	}
}

package com.krakedev.moduloii.evaluacionfinal.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.moduloii.evaluacionfinal.entidades.RegistroMovimiento;
import com.krakedev.moduloii.evaluacionfinal.excepciones.InventarioException;
import com.krakedev.moduloii.evaluacionfinal.persistencia.MovimientosBDD;

@Path("movimientos")
public class ServiciosMovimientos {
	
	@Path("registrar")
	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrar(RegistroMovimiento movimiento) {
		MovimientosBDD movimientosbd = new MovimientosBDD();
		try {
			movimientosbd.registrar(movimiento);
			return Response.ok("Registro hecho correctamente").build();
		} catch (InventarioException e) {
			return Response.serverError().build();			
			
		}
	}
	
}

package com.krakedev.moduloii.evaluacionfinal.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.moduloii.evaluacionfinal.entidades.Grupo;
import com.krakedev.moduloii.evaluacionfinal.excepciones.InventarioException;
import com.krakedev.moduloii.evaluacionfinal.persistencia.GrupoBDD;

@Path ("grupos")
public class ServiciosGrupo {

	public void insertar(Grupo grupo) {
		GrupoBDD gruposbdd = new GrupoBDD();
		gruposbdd.insertar(grupo);
	}
	@Path("actualizar")
	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	public void actualizar(Grupo grupo) {
		GrupoBDD gruposbdd = new GrupoBDD();
		gruposbdd.insertar(grupo);
	}
	
	@Path("agregar")
	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	public Response agregarGrupo(Grupo grupo) {
		GrupoBDD nuevoGrupo= new GrupoBDD();
		try {
			nuevoGrupo.agregarGrupo(grupo);
			return Response.ok("NUEVO GRUPO").build();
		} catch (InventarioException e) {
			return Response.serverError().build();
			
		}
	
	}
}

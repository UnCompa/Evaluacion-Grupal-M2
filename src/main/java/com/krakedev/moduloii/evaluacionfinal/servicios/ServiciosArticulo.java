package com.krakedev.moduloii.evaluacionfinal.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.krakedev.moduloii.evaluacionfinal.entidades.Articulo;
import com.krakedev.moduloii.evaluacionfinal.excepciones.InventarioException;
import com.krakedev.moduloii.evaluacionfinal.persistencia.ArticulosBDD;

@Path("articulos")
public class ServiciosArticulo {
	
	@Path("insertar")
	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertar(Articulo articulo) {
		ArticulosBDD articulosbbd = new ArticulosBDD();
		try {
			articulosbbd.insertar(articulo);
		} catch (InventarioException e) {
			e.printStackTrace();
		}
	}
	@Path("actualizar")
	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	public void actualizar(Articulo articulo) {
		ArticulosBDD articulosbbd = new ArticulosBDD();
		try {
			articulosbbd.insertar(articulo);
		} catch (InventarioException e) {
			e.printStackTrace();
		}
	}
	
}

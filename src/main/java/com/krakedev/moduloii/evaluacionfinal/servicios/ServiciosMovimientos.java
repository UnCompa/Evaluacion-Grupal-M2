package com.krakedev.moduloii.evaluacionfinal.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.moduloii.evaluacionfinal.entidades.RegistroMovimiento;
import com.krakedev.moduloii.evaluacionfinal.excepciones.InventarioException;
import com.krakedev.moduloii.evaluacionfinal.persistencia.MovimientosBDD;

@Path("movimientos") // ruta base para todos los métodos de esta clase
public class ServiciosMovimientos {

    public void insertar(RegistroMovimiento movimiento) {
        MovimientosBDD movimientosBDD = new MovimientosBDD();
        movimientosBDD.insertar(movimiento);
    }

    @Path("actualizar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizar(RegistroMovimiento movimiento) {
        MovimientosBDD movimientosBDD = new MovimientosBDD();
        try {
            movimientosBDD.actualizar(movimiento);
        } catch (InventarioException e) {
            e.printStackTrace();
        }
    }

    @Path("consultarPorArticulo/{idArticulo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarMovimientosPorArticulo(@PathParam("idArticulo") String idArticulo) throws InventarioException {
        MovimientosBDD movimieRegistroMovimientontosBDD = new MovimientosBDD();
        ArrayList<RegistroMovimiento> movimientos = movimieRegistroMovimientontosBDD.consultarMovimientosPorArticulo(idArticulo);
        return Response.ok(movimientos).build();
    }

}
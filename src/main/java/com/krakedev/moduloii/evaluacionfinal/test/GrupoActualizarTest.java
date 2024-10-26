package com.krakedev.moduloii.evaluacionfinal.test;

import java.math.BigDecimal;

import com.krakedev.moduloii.evaluacionfinal.entidades.Articulo;
import com.krakedev.moduloii.evaluacionfinal.entidades.Grupo;
import com.krakedev.moduloii.evaluacionfinal.excepciones.InventarioException;
import com.krakedev.moduloii.evaluacionfinal.persistencia.ArticulosBDD;
import com.krakedev.moduloii.evaluacionfinal.persistencia.GrupoBDD;

public class GrupoActualizarTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GrupoBDD grupobdd = new GrupoBDD();
		Grupo grupo = new Grupo("C004", "Metales");
		try {
			grupobdd.actualizar(grupo);
		} catch (InventarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

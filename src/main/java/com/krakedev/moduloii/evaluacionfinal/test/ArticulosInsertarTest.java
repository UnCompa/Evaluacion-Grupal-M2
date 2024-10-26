package com.krakedev.moduloii.evaluacionfinal.test;

import java.math.BigDecimal;

import com.krakedev.moduloii.evaluacionfinal.entidades.Articulo;
import com.krakedev.moduloii.evaluacionfinal.entidades.Grupo;
import com.krakedev.moduloii.evaluacionfinal.excepciones.InventarioException;
import com.krakedev.moduloii.evaluacionfinal.persistencia.ArticulosBDD;

public class ArticulosInsertarTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArticulosBDD articulosbdd = new ArticulosBDD();
		Grupo grupo = new Grupo("C001", "Prendas");
		Articulo articulo = new Articulo("P0012", "Saco verde", new BigDecimal(10.00), new BigDecimal(10.00), grupo);
		try {
			articulosbdd.insertar(articulo);
		} catch (InventarioException e) {
			e.printStackTrace();
		}
	}

}

package com.krakedev.moduloii.evaluacionfinal.test;

import com.krakedev.moduloii.evaluacionfinal.entidades.Articulo;
import com.krakedev.moduloii.evaluacionfinal.excepciones.InventarioException;
import com.krakedev.moduloii.evaluacionfinal.persistencia.ArticulosBDD;

public class ArticulosBuscarPoridTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArticulosBDD articulosbdd = new ArticulosBDD();
		try {
			Articulo articulo = articulosbdd.buscarArtículoPorId("P0012");
			System.out.println(articulo.toString());
		} catch (InventarioException e) {
			e.printStackTrace();
		}
	}

}

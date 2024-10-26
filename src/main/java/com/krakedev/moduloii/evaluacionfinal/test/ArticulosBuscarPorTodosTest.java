package com.krakedev.moduloii.evaluacionfinal.test;

import java.util.ArrayList;

import com.krakedev.moduloii.evaluacionfinal.entidades.Articulo;
import com.krakedev.moduloii.evaluacionfinal.excepciones.InventarioException;
import com.krakedev.moduloii.evaluacionfinal.persistencia.ArticulosBDD;

public class ArticulosBuscarPorTodosTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArticulosBDD articulosbdd = new ArticulosBDD();
		try {
			ArrayList<Articulo> articulos = articulosbdd.recuperarTodos();
			System.out.println(articulos);
		} catch (InventarioException e) {
			e.printStackTrace();
		}
	}

}

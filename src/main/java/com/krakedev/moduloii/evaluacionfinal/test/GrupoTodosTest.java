package com.krakedev.moduloii.evaluacionfinal.test;

import java.util.ArrayList;

import com.krakedev.moduloii.evaluacionfinal.entidades.Grupo;
import com.krakedev.moduloii.evaluacionfinal.excepciones.InventarioException;

import com.krakedev.moduloii.evaluacionfinal.persistencia.GrupoBDD;

public class GrupoTodosTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GrupoBDD grupobdd = new GrupoBDD();
		try {
			ArrayList<Grupo> grupos = grupobdd.recuperarTodos();
			System.out.println(grupos);
		} catch (InventarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

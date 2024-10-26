package com.krakedev.moduloii.evaluacionfinal.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.moduloii.evaluacionfinal.entidades.Articulo;
import com.krakedev.moduloii.evaluacionfinal.entidades.Grupo;
import com.krakedev.moduloii.evaluacionfinal.entidades.RegistroMovimiento;
import com.krakedev.moduloii.evaluacionfinal.excepciones.InventarioException;
import com.krakedev.moduloii.evaluacionfinal.persistencia.MovimientosBDD;

public class MovimientosRecuperarTodoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MovimientosBDD movimientosbdd = new MovimientosBDD();
		try {
			ArrayList<RegistroMovimiento> movimientos = movimientosbdd.recuperTodos();
			System.out.println(movimientos);
		} catch (InventarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package com.krakedev.moduloii.evaluacionfinal.test;

import java.math.BigDecimal;
import java.util.Date;

import com.krakedev.moduloii.evaluacionfinal.entidades.RegistroMovimiento;
import com.krakedev.moduloii.evaluacionfinal.excepciones.InventarioException;
import com.krakedev.moduloii.evaluacionfinal.persistencia.MovimientosBDD;

public class MovimientosPorIdTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MovimientosBDD movimientosbdd = new MovimientosBDD();
		try {
			RegistroMovimiento movimiento = movimientosbdd.buscarMovimientoPorId(1);
			System.out.println(movimiento.toString());
		} catch (InventarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

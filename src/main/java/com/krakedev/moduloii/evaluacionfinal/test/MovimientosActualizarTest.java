package com.krakedev.moduloii.evaluacionfinal.test;

import java.math.BigDecimal;
import java.util.Date;

import com.krakedev.moduloii.evaluacionfinal.entidades.Articulo;
import com.krakedev.moduloii.evaluacionfinal.entidades.Grupo;
import com.krakedev.moduloii.evaluacionfinal.entidades.RegistroMovimiento;
import com.krakedev.moduloii.evaluacionfinal.persistencia.MovimientosBDD;

public class MovimientosActualizarTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MovimientosBDD movimientosbdd = new MovimientosBDD();
		Grupo grupo = new Grupo("C001", "Prendas");
		Articulo articulo = new Articulo("P0012", "Saco verde", new BigDecimal(10.00), new BigDecimal(10.00), grupo);
		RegistroMovimiento movimiento = new RegistroMovimiento(11, articulo, 10, new Date());
		movimientosbdd.insertar(movimiento);
	}

}

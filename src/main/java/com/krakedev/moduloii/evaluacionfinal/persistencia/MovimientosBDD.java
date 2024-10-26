package com.krakedev.moduloii.evaluacionfinal.persistencia;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakedev.moduloii.evaluacionfinal.entidades.Articulo;
import com.krakedev.moduloii.evaluacionfinal.entidades.Grupo;
import com.krakedev.moduloii.evaluacionfinal.entidades.RegistroMovimiento;
import com.krakedev.moduloii.evaluacionfinal.excepciones.InventarioException;
import com.krakedev.moduloii.evaluacionfinal.utils.ConexionBDD;

public class MovimientosBDD {
	public void insertar(RegistroMovimiento movimientos) {
		Logger logger = LogManager.getLogger(MovimientosBDD.class);
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConexionBDD.conectar();
			Date fechaHoy = new Date();
			Timestamp fechaSQL = new java.sql.Timestamp(fechaHoy.getTime());
			ps = con.prepareStatement(
					"INSERT INTO registro_movimientos (id_articulo, cantidad, fecha_movimiento) VALUES(?,?,?)");
			ps.setString(1, movimientos.getIdArticulo().getId());
			ps.setInt(2, movimientos.getCantidad());
			ps.setTimestamp(3, fechaSQL);
			ps.executeUpdate();
		} catch (InventarioException | SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
	}
	public void actualizar(RegistroMovimiento movimientos) throws InventarioException {
		Logger logger = LogManager.getLogger(GrupoBDD.class);
		Connection con = null;
		PreparedStatement ps = null;
		Timestamp fechaSQL = new java.sql.Timestamp(movimientos.getFechaMovimiento().getTime());
		try {
			con = ConexionBDD.conectar();
			ps = con.prepareStatement(
					"UPDATE articulos set id_articulo = ?, cantidad = ?, fecha_movimiento = ?"
					+ "WHERE id = ?");
			ps.setString(1, movimientos.getIdArticulo().getId());
			ps.setInt(2, movimientos.getCantidad());
			ps.setTimestamp(3, fechaSQL);
			ps.setInt(4, movimientos.getId());
			ps.executeUpdate();
		} catch (InventarioException | SQLException e) {
			logger.error(e.toString());
			throw new InventarioException(e.toString());
		}
	}
	public RegistroMovimiento buscarMovimientoPorId(int id) throws InventarioException {
		Logger logger = LogManager.getLogger(ArticulosBDD.class);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RegistroMovimiento movimiento = null;
		try {
			con = ConexionBDD.conectar();
			ps = con.prepareStatement(
					"SELECT rm.id as id_registro, ar.id as articulo_id, cantidad, fecha_movimiento as fecha, ar.nombre as nombre, ar.precio_venta as precio_venta, ar.precio_compra as precio_compra, gr.id as grupo_id, gr.nombre as grupo_nombre FROM registro_movimientos as rm, articulos as ar, grupos as gr where rm.id_articulo = ar.id AND gr.id = ar.id_grupo AND rm.id = ?;");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				int idRegistro = rs.getInt("id_registro");
				String idArticulo = rs.getString("articulo_id");
				int cantidad = rs.getInt("cantidad");
				Date fecha = rs.getDate("fecha");
				String nombre = rs.getString("nombre");
				String precioVentaStr = rs.getString("precio_venta").replace("$", "").replace(",", ".");
				String precioCompraStr = rs.getString("precio_compra").replace("$", "").replace(",", ".");
				String idGrupo = rs.getString("grupo_id");
				String nombreGrupo = rs.getString("grupo_nombre");
				Grupo grupo = new Grupo(idGrupo, nombreGrupo);
				Articulo articulo = new Articulo(idArticulo, nombre, new BigDecimal(precioVentaStr), new BigDecimal(precioCompraStr), grupo);
				RegistroMovimiento movimientoCreado = new RegistroMovimiento(idRegistro, articulo, cantidad, fecha);
				movimiento = movimientoCreado;
			}
		} catch (InventarioException | SQLException e) {
			logger.error(e.toString());
			throw new InventarioException(e.toString());
		}
		return movimiento;
	}
	public ArrayList<RegistroMovimiento> recuperTodos() throws InventarioException {
		Logger logger = LogManager.getLogger(ArticulosBDD.class);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<RegistroMovimiento> movimientos = new ArrayList<>();
		try {
			con = ConexionBDD.conectar();
			ps = con.prepareStatement(
					"SELECT rm.id as id_registro, ar.id as articulo_id, cantidad, fecha_movimiento as fecha, ar.nombre as nombre, ar.precio_venta as precio_venta, ar.precio_compra as precio_compra, gr.id as grupo_id, gr.nombre as grupo_nombre FROM registro_movimientos as rm, articulos as ar, grupos as gr where rm.id_articulo = ar.id AND gr.id = ar.id_grupo");
			rs = ps.executeQuery();
			while (rs.next()) {
				int idRegistro = rs.getInt("id_registro");
				String idArticulo = rs.getString("articulo_id");
				int cantidad = rs.getInt("cantidad");
				Date fecha = rs.getDate("fecha");
				String nombre = rs.getString("nombre");
				String precioVentaStr = rs.getString("precio_venta").replace("$", "").replace(",", ".");
				String precioCompraStr = rs.getString("precio_compra").replace("$", "").replace(",", ".");
				String idGrupo = rs.getString("grupo_id");
				String nombreGrupo = rs.getString("grupo_nombre");
				Grupo grupo = new Grupo(idGrupo, nombreGrupo);
				Articulo articulo = new Articulo(idArticulo, nombre, new BigDecimal(precioVentaStr), new BigDecimal(precioCompraStr), grupo);
				RegistroMovimiento movimientoCreado = new RegistroMovimiento(idRegistro, articulo, cantidad, fecha);
				movimientos.add(movimientoCreado);
			}
		} catch (InventarioException | SQLException e) {
			logger.error(e.toString());
			throw new InventarioException(e.toString());
		}
		return movimientos;
	}
	public ArrayList<RegistroMovimiento> recuperMovimeintoPorFecha(Date fecha) throws InventarioException {
		Logger logger = LogManager.getLogger(ArticulosBDD.class);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<RegistroMovimiento> movimientos = new ArrayList<>();
		try {
			java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
			con = ConexionBDD.conectar();
			ps = con.prepareStatement(
					"SELECT rm.id as id_registro, ar.id as articulo_id, cantidad, fecha_movimiento as fecha, ar.nombre as nombre, ar.precio_venta as precio_venta, ar.precio_compra as precio_compra, gr.id as grupo_id, gr.nombre as grupo_nombre FROM registro_movimientos as rm, articulos as ar, grupos as gr where rm.id_articulo = ar.id AND gr.id = ar.id_grupo AND fecha_movimiento = ?");
			ps.setDate(1, fechaSQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				int idRegistro = rs.getInt("id_registro");
				String idArticulo = rs.getString("articulo_id");
				int cantidad = rs.getInt("cantidad");
				Date fechaMovimiento = rs.getDate("fecha");
				String nombre = rs.getString("nombre");
				String precioVentaStr = rs.getString("precio_venta").replace("$", "").replace(",", ".");
				String precioCompraStr = rs.getString("precio_compra").replace("$", "").replace(",", ".");
				String idGrupo = rs.getString("grupo_id");
				String nombreGrupo = rs.getString("grupo_nombre");
				Grupo grupo = new Grupo(idGrupo, nombreGrupo);
				Articulo articulo = new Articulo(idArticulo, nombre, new BigDecimal(precioVentaStr), new BigDecimal(precioCompraStr), grupo);
				RegistroMovimiento movimientoCreado = new RegistroMovimiento(idRegistro, articulo, cantidad, fechaMovimiento);
				movimientos.add(movimientoCreado);
			}
		} catch (InventarioException | SQLException e) {
			logger.error(e.toString());
			throw new InventarioException(e.toString());
		}
		return movimientos;
	}
	
	public void registrar(RegistroMovimiento movimiento) throws InventarioException {
		Logger logger = LogManager.getLogger(MovimientosBDD.class);
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConexionBDD.conectar();
			Date fechaHoy = new Date();
			Timestamp fechaSQL = new java.sql.Timestamp(fechaHoy.getTime());
			ps = con.prepareStatement(
					"INSERT INTO registro_movimientos (id_articulo, cantidad, fecha_movimiento) VALUES(?,?,?)");
			ps.setString(1, movimiento.getIdArticulo().getId());
			ps.setInt(2, movimiento.getCantidad());
			ps.setTimestamp(3, fechaSQL);
			ps.executeUpdate();
		} catch (InventarioException | SQLException e) {
			logger.error(e.toString());
			throw new InventarioException("Error al registrar el movimiento" + e.toString());
		}
	}
}

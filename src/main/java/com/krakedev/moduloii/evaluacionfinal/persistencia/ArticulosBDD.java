package com.krakedev.moduloii.evaluacionfinal.persistencia;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakedev.moduloii.evaluacionfinal.entidades.Articulo;
import com.krakedev.moduloii.evaluacionfinal.entidades.Grupo;
import com.krakedev.moduloii.evaluacionfinal.excepciones.InventarioException;
import com.krakedev.moduloii.evaluacionfinal.utils.ConexionBDD;

public class ArticulosBDD {
	public void insertar(Articulo articulo) throws InventarioException {
		Logger logger = LogManager.getLogger(ArticulosBDD.class);
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConexionBDD.conectar();
			ps = con.prepareStatement(
					"INSERT INTO articulos (id, nombre, precio_venta, precio_compra, id_grupo) " + "VALUES (?,?,?,?,?);");
			ps.setString(1, articulo.getId());
			ps.setString(2, articulo.getNombre());
			ps.setBigDecimal(3, articulo.getPrecioVenta());
			ps.setBigDecimal(4, articulo.getPrecioCompra());
			ps.setString(5, articulo.getIdGrupo().getId());
			ps.executeUpdate();
		} catch (InventarioException | SQLException e) {
			logger.error(e.toString());
			throw new InventarioException(e.toString());
		}
	}
	public void actualizar(Articulo articulo) throws InventarioException {
		Logger logger = LogManager.getLogger(ArticulosBDD.class);
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConexionBDD.conectar();
			ps = con.prepareStatement(
					"UPDATE articulos set nombre = ?, precio_venta = ?, precio_compra = ?, id_grupo = ? "
					+ "WHERE id = ?");
			ps.setString(1, articulo.getNombre());
			ps.setBigDecimal(2, articulo.getPrecioVenta());
			ps.setBigDecimal(3, articulo.getPrecioCompra());
			ps.setString(4, articulo.getIdGrupo().getId());
			ps.setString(5, articulo.getId());
			ps.executeUpdate();
		} catch (InventarioException | SQLException e) {
			logger.error(e.toString());
			throw new InventarioException(e.toString());
		}
	}
	public Articulo buscarArtículoPorId(String id) throws InventarioException {
		Logger logger = LogManager.getLogger(ArticulosBDD.class);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Articulo articulo = null;
		try {
			con = ConexionBDD.conectar();
			ps = con.prepareStatement(
					"SELECT ar.id, ar.nombre, ar.precio_venta, ar.precio_compra, gr.id as grupo_id, gr.nombre as grupo_nombre FROM articulos as ar, grupos as gr WHERE ar.id = ?;");
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				String idArticulo = rs.getString("id");
				String nombre = rs.getString("nombre");
				String precioVentaStr = rs.getString("precio_venta").replace("$", "").replace(",", ".");
				String precioCompraStr = rs.getString("precio_compra").replace("$", "").replace(",", ".");
				BigDecimal precioVenta = new BigDecimal(precioVentaStr);
				BigDecimal precioCompra = new BigDecimal(precioCompraStr);
				String grupoId = rs.getString("grupo_id");
				String grupoNombre = rs.getString("grupo_nombre");
				Grupo grupo = new Grupo(grupoId, grupoNombre);
				Articulo articuloCreado = new Articulo(idArticulo, nombre, precioVenta, precioCompra, grupo);
				articulo = articuloCreado;
			}
		} catch (InventarioException | SQLException e) {
			logger.error("El error esta aqui");
			throw new InventarioException(e.toString());
		}
		return articulo;
	}
	
	public ArrayList<Articulo> recuperarTodos() throws InventarioException {
		Logger logger = LogManager.getLogger(ArticulosBDD.class);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Articulo> articulos = new ArrayList<>();
		try {
			con = ConexionBDD.conectar();
			ps = con.prepareStatement(
					"SELECT ar.id, ar.nombre, ar.precio_venta, ar.precio_compra, gr.id as grupo_id, gr.nombre as grupo_nombre FROM articulos as ar, grupos as gr WHERE ar.id_grupo = gr.id;");
			rs = ps.executeQuery();
			while (rs.next()) {
				String idArticulo = rs.getString("id");
				String nombre = rs.getString("nombre");
				String precioVentaStr = rs.getString("precio_venta").replace("$", "").replace(",", ".");
				String precioCompraStr = rs.getString("precio_compra").replace("$", "").replace(",", ".");
				BigDecimal precioVenta = new BigDecimal(precioVentaStr);
				BigDecimal precioCompra = new BigDecimal(precioCompraStr);
				String grupoId = rs.getString("grupo_id");
				String grupoNombre = rs.getString("grupo_nombre");
				Grupo grupo = new Grupo(grupoId, grupoNombre);
				Articulo articuloCreado = new Articulo(idArticulo, nombre, precioVenta, precioCompra, grupo);
				articulos.add(articuloCreado);
			}
		} catch (InventarioException | SQLException e) {
			logger.error("El error esta aqui");
			throw new InventarioException(e.toString());
		}
		return articulos;
	}
}

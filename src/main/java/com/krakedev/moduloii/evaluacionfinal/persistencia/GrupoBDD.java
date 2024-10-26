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

public class GrupoBDD {
	public void insertar(Grupo grupo) {
		Logger logger = LogManager.getLogger(GrupoBDD.class);
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConexionBDD.conectar();
			ps = con.prepareStatement(
					"INSERT INTO grupos (id, nombre) VALUES(?,?)");
			ps.setString(1, grupo.getId());
			ps.setString(2, grupo.getNombre());
			ps.executeUpdate();
		} catch (Exception  e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
	}
	public void actualizar(Grupo grupo) throws InventarioException {
		Logger logger = LogManager.getLogger(GrupoBDD.class);
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConexionBDD.conectar();
			ps = con.prepareStatement(
					"UPDATE grupos set nombre = ? "
					+ "WHERE id = ?");
			ps.setString(1, grupo.getNombre());
			ps.setString(2, grupo.getId());
			ps.executeUpdate();
		} catch (InventarioException | SQLException e) {
			logger.error(e.toString());
			throw new InventarioException(e.toString());
		}
	}
	public Grupo buscarGrupoPorId(String id) throws InventarioException {
		Logger logger = LogManager.getLogger(ArticulosBDD.class);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Grupo grupo = null;
		try {
			con = ConexionBDD.conectar();
			ps = con.prepareStatement(
					"SELECT * FROM grupos where id = ?;");
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				String idGrupo = rs.getString("id");
				String nombre = rs.getString("nombre");
				Grupo grupoEncontrado = new Grupo(idGrupo, nombre);
				grupo = grupoEncontrado;
			}
		} catch (InventarioException | SQLException e) {
			logger.error(e.toString());
			throw new InventarioException(e.toString());
		}
		return grupo;
	}public ArrayList<Grupo> recuperarTodos() throws InventarioException {
		Logger logger = LogManager.getLogger(ArticulosBDD.class);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Grupo> grupos = new ArrayList<>();
		try {
			con = ConexionBDD.conectar();
			ps = con.prepareStatement(
					"SELECT * FROM grupos");
			rs = ps.executeQuery();
			while (rs.next()) {
				String grupoId = rs.getString("id");
				String grupoNombre = rs.getString("nombre");
				Grupo grupo = new Grupo(grupoId, grupoNombre);
				grupos.add(grupo);
			}
		} catch (InventarioException | SQLException e) {
			logger.error("El error esta aqui");
			throw new InventarioException(e.toString());
		}
		return grupos;
	}

	public void agregarGrupo(Grupo grupo) throws InventarioException {
        String sql = "INSERT INTO grupos (id, nombre) VALUES (?, ?)";
        
        try (Connection con = ConexionBDD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, grupo.getId());
            ps.setString(2, grupo.getNombre());
            int filasInsertadas = ps.executeUpdate();

            if (filasInsertadas == 0) {
                throw new InventarioException("No se pudo insertar el grupo: " + grupo);
            }

        } catch (SQLException e) {
            throw new InventarioException("Error al insertar el grupo: " + e.getMessage());
        }
    }


}

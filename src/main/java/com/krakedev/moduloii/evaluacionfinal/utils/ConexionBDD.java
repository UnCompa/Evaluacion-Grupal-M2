package com.krakedev.moduloii.evaluacionfinal.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakedev.moduloii.evaluacionfinal.excepciones.InventarioException;


public class ConexionBDD {
	private final static String DRIVER = "org.postgresql.Driver";
	private final static String URL = "jdbc:postgresql://localhost:5432/ INVENTARIOS_KRAKEDEV";
	private final static String USER = "postgres";
	private final static String PASSWORD = "Amigo 12345";
	private static Logger logger = LogManager.getLogger(ConexionBDD.class);
	
	public static Connection conectar() throws InventarioException{
		Connection connection = null;

		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error("error en la infraestrucutra",e);
			throw new InventarioException("Error en la infraestrucutra");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error al conectarse",e);
			throw new InventarioException("Error al conectarse");
		}
		return connection;
	}
}


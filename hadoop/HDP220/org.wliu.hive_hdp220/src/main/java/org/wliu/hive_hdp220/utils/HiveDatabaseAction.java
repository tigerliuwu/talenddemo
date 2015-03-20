package org.wliu.hive_hdp220.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveDatabaseAction {
	
	public static void createDatabase(Connection conn, String db) throws SQLException {
		Statement statment = conn.createStatement();
		statment.execute("create database if not exists "+ db);
		statment.close();
	}
	
	public static void dropDatabase(Connection conn, String db) throws SQLException {
		Statement statment = conn.createStatement();
		statment.execute("drop database if exists "+ db);
		statment.close();
	}

}

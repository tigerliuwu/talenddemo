package org.wliu.hive_hdp220.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HiveConnection {
	
	private static final String HIVE1_DRIVER = "org.apache.hadoop.hive.jdbc.HiveDriver";
	private static final String HIVE2_DRIVER = "org.apache.hive.jdbc.HiveDriver";
	/**
	 * 
	 * @param hiveserver2
	 * @param username
	 * @param password
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getHive2Connection(String hiveserver2, String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName(HIVE2_DRIVER);
		Connection conn  = null;
		conn = DriverManager.getConnection(hiveserver2, username, password);
		
		return conn;
	}
	
	
	public static void closeHiveConnection(Connection conn) throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}

	}

}

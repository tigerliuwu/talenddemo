package org.wliu.hive_hdp220.utils;

import java.sql.Connection;
import java.sql.SQLException;

import junit.framework.TestCase;

import org.junit.Test;

public class TestHiveConnection extends TestCase {
	
	private String HDP220_HOST = "sandbox.hortonworks.com";
	private int HDP220_HIVESEVER_PORT = 10000;
	private String HDP220_DB="test_wliu"; 
	private String HDP220_TB = "T_TEST_WLIU";
	private Connection conn = null;
	
	/**
	 * 1. preprare the hiveserver 2 connection
	 * 2. prepare the database:test_wliu
	 */
    protected void setUp() throws Exception {
    	String hiveserveruri = "jdbc:hive2://"
    +
				HDP220_HOST + ":"
				+ HDP220_HIVESEVER_PORT + "/"
				;
		try {
			conn  = HiveConnection.getHive2Connection(hiveserveruri, "talend", "talend");
			HiveDatabaseAction.createDatabase(conn, HDP220_DB);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }
	@Test
	public void testGetHive2Connection() {
		
		
	
		assertTrue(true);
	}
	
    protected void tearDown() throws Exception {
    	
		HiveDatabaseAction.dropDatabase(conn, HDP220_DB);
    	
    	HiveConnection.closeHiveConnection(conn);
    	
    }

}

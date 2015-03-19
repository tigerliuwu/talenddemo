package org.wliu.hdfs_hdp220;

import static org.junit.Assert.*;

import org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.wliu.hdfs_hdp220.util.HDFSUtil;

public class TestHDFSPut {
	
	public static final String FS="fs.default.name";
	public static final String namenode = "hdfs://sandbox.hortonworks.com:8020";
	
	private List<List<String>> test_Data = new ArrayList<List<String>>();

	private void prepareData() {
		if (test_Data == null) {
			test_Data = new ArrayList<List<String>>();
		}
		if (test_Data.isEmpty()) {
			for (int i = 0; i< 10; i++) {
				List<String> record = new ArrayList<String>();
				record.add("name"+i);
				record.add("age"+(20+i));
				record.add("addr"+i);
				test_Data.add(record);
			}
			
		}
		
	}
	@Test
	public void testGetBZip2OutStream() {
		prepareData();
		OutputStream outputStream = null;
		try {
			outputStream = HDFSPut.getBZip2OutStream(HDFSUtil.getConfig(FS, namenode), "/test/abc/out1");
			StringBuilder builder = new StringBuilder();
			for (List<String> list: test_Data) {
				for(int i = 0; i< list.size(); i++) {
					builder.append(list.get(i));
					if (i < list.size() - 1) {
						builder.append(";");
					}
				}
				builder.append("\n");
				outputStream.write(builder.toString().getBytes());
			}
			assertTrue(true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				HDFSPut.closeOutputStream(outputStream);
			}
		}
	}
	
	@Test
	public void testgetFSOutputStream() {
		prepareData();
		OutputStream outputStream = null;
		try {
			outputStream = HDFSPut.getFSOutputStream(HDFSUtil.getConfig(FS, namenode), "/test/abc/out2");
			StringBuilder builder = new StringBuilder();
			for (List<String> list: test_Data) {
				for(int i = 0; i< list.size(); i++) {
					builder.append(list.get(i));
					if (i < list.size() - 1) {
						builder.append(";");
					}
				}
				builder.append("\n");
				outputStream.write(builder.toString().getBytes());
			}
			assertTrue(true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				HDFSPut.closeOutputStream(outputStream);
			}
		}
	}
	
	@Test
	public void testgetBZip2OutStream() {
		prepareData();
		OutputStream outputStream = null;
		try {
			outputStream = HDFSPut.getBZip2OutStream(HDFSUtil.getConfig(FS, namenode), "/test/abc/out3");
			StringBuilder builder = new StringBuilder();
			for (List<String> list: test_Data) {
				for(int i = 0; i< list.size(); i++) {
					builder.append(list.get(i));
					if (i < list.size() - 1) {
						builder.append(";");
					}
				}
				builder.append("\n");
				outputStream.write(builder.toString().getBytes());
			}
			assertTrue(true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				HDFSPut.closeOutputStream(outputStream);
			}
		}
	}


}

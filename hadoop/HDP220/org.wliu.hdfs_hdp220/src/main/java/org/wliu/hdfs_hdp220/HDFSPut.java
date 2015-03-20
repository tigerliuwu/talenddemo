package org.wliu.hdfs_hdp220;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.util.ReflectionUtils;

public class HDFSPut {
	
	public static final String FSNAME = "talend";
	
	public static OutputStream getFSOutputStream(Configuration conf, String hdfsDest) throws IOException, InterruptedException, URISyntaxException {
		FileSystem fs = null;
		fs = FileSystem.get(new java.net.URI(conf.get("fs.default.name")), conf, FSNAME);
		
		Path path_dest = new Path(hdfsDest);
		FSDataOutputStream fsOut = fs.create(path_dest, true);
		return fsOut;
	}
	
	public static OutputStream getBZip2OutStream(Configuration conf, String hdfsDest) throws IOException, InterruptedException, URISyntaxException {
		FileSystem fs = null;
		fs = FileSystem.get(new java.net.URI(conf.get("fs.default.name")), conf, FSNAME);
		
		Path path_dest = new Path(hdfsDest);
		FSDataOutputStream fsOut = fs.create(path_dest, true);
		CompressionCodec codecOutput = ReflectionUtils.newInstance(BZip2Codec.class, conf);
		CompressionOutputStream cmprOutput = codecOutput.createOutputStream(fsOut);
		return cmprOutput;
	}
	
	public static OutputStream getGZipOutStream(Configuration conf, String hdfsDest) throws IOException, InterruptedException, URISyntaxException {
		FileSystem fs = null;
		fs = FileSystem.get(new java.net.URI(conf.get("fs.default.name")), conf, FSNAME);
		
		Path path_dest = new Path(hdfsDest);
		FSDataOutputStream fsOut = fs.create(path_dest, true);
		GzipCodec codecOutput = new GzipCodec();
		codecOutput.setConf(conf);
		CompressionOutputStream cmprOutput = codecOutput.createOutputStream(fsOut);
		return cmprOutput;
	}
	
	public static void closeOutputStream(OutputStream outStream) {
		if (outStream != null) {
			try {
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

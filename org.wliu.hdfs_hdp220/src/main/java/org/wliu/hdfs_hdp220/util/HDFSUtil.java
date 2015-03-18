package org.wliu.hdfs_hdp220.util;

import org.apache.hadoop.conf.Configuration;

public class HDFSUtil {
	
	/**
	 * get configuration of the namenode
	 * @param fs
	 * @param nameNodeURL
	 * @return
	 */
	public static Configuration getConfig(String fs, String nameNodeURL) {
		Configuration conf = new Configuration();
		conf.set(fs, nameNodeURL);
		return conf;
	}

}

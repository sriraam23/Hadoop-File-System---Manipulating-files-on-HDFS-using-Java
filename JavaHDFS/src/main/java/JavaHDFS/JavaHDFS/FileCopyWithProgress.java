package JavaHDFS.JavaHDFS;

// cc FileCopyWithProgress Copies a local file to a Hadoop filesystem, and shows progress
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

// vv FileCopyWithProgress
public class FileCopyWithProgress {
	public static void main(String[] args) throws Exception {

		String local[] = new String[10];
		String dst[] = new String[10];

		for (int i = 1; i < 8; i++) {
			local[i] = args[0] + "/file" + i + ".txt.bz2";
			dst[i] = args[1] + "/file" + i + ".txt.bz2";
			if (i == 7) {
				local[i] = args[0] + "/text.txt";
				dst[i] = args[1] + "/text.txt";
			}
			InputStream in = new BufferedInputStream(new FileInputStream(
					local[i]));
			Configuration conf = new Configuration();
			conf.addResource(new Path(
					"/usr/local/hadoop-2.4.1/etc/hadoop/core-site.xml"));
			conf.addResource(new Path(
					"/usr/local/hadoop-2.4.1/etc/hadoop/hdfs-site.xml"));

			FileSystem fs = FileSystem.get(URI.create(dst[i]), conf);
			OutputStream out = fs.create(new Path(dst[i]), new Progressable() {
				public void progress() {
					System.out.print(".");
				}
			});
			IOUtils.copyBytes(in, out, 4096, true);
		}
	}
}
// ^^ FileCopyWithProgress

package JavaHDFS.JavaHDFS;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.URI;
import java.net.URL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.jobcontrol.Job;
import org.apache.hadoop.util.Progressable;
import org.apache.hadoop.conf.Configured;
import org.apache.log4j.BasicConfigurator;

public class DownloadfromURL extends Configured {

	public static void main(String[] args) throws IOException {
		System.setProperty("http.agent", "Chrome");
		BasicConfigurator.configure();

		String url1 = "http://www.utdallas.edu/~axn112530/cs6350/lab2/input/20417.txt.bz2";
		String url2 = "http://www.utdallas.edu/~axn112530/cs6350/lab2/input/5000-8.txt.bz2";
		String url3 = "http://www.utdallas.edu/~axn112530/cs6350/lab2/input/132.txt.bz2";
		String url4 = "http://www.utdallas.edu/~axn112530/cs6350/lab2/input/1661-8.txt.bz2";
		String url5 = "http://www.utdallas.edu/~axn112530/cs6350/lab2/input/972.txt.bz2";
		String url6 = "http://www.utdallas.edu/~axn112530/cs6350/lab2/input/19699.txt.bz2";
		String url7 = "http://corpus.byu.edu/wikitext-samples/text.zip";

		String file1 = args[0] + "/file1.txt.bz2";
		String file2 = args[0] + "/file2.txt.bz2";
		String file3 = args[0] + "/file3.txt.bz2";
		String file4 = args[0] + "/file4.txt.bz2";
		String file5 = args[0] + "/file5.txt.bz2";
		String file6 = args[0] + "/file6.txt.bz2";
		String file7 = args[0] + "/file7.zip";

		try {
			DownloadURL(url1, file1);
			DownloadURL(url2, file2);
			DownloadURL(url3, file3);
			DownloadURL(url4, file4);
			DownloadURL(url5, file5);
			DownloadURL(url6, file6);
			DownloadURL(url7, file7);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void DownloadURL(String fullurl, String file)
			throws IOException {
		URL url = new URL(fullurl);
		BufferedInputStream bis = new BufferedInputStream(url.openStream());
		FileOutputStream fis = new FileOutputStream(file);
		byte[] bytes = new byte[1024];
		int check = 0;
		while ((check = bis.read(bytes, 0, 1024)) != -1) {
			fis.write(bytes, 0, check);
		}
		bis.close();
		fis.close();
	}
}

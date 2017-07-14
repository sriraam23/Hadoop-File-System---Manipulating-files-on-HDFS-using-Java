package JavaHDFS.JavaHDFS;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;

public class FileDecompressor {

	public static void main(String[] args) throws Exception {

		for (int i = 1; i <= 6; i++) {
			String uri = args[0] + "/file" + i + ".txt.bz2";
			Configuration conf = new Configuration();
			FileSystem fs = FileSystem.get(URI.create(uri), conf);
			Path inputPath = new Path(uri);
			CompressionCodecFactory factory = new CompressionCodecFactory(conf);
			CompressionCodec codec = factory.getCodec(inputPath);
			if (codec == null) {
				System.err.println("No codec found for " + uri);
				System.exit(1);
			}

			String outputUri = CompressionCodecFactory.removeSuffix(uri,
					codec.getDefaultExtension());

			InputStream in = null;
			OutputStream out = null;
			try {
				in = codec.createInputStream(fs.open(inputPath));
				out = fs.create(new Path(outputUri));
				IOUtils.copyBytes(in, out, conf);

			} finally {
				IOUtils.closeStream(in);
				fs.delete(inputPath, true);
				IOUtils.closeStream(out);
			}
		}
	}
}

// ^^ FileDecompressor


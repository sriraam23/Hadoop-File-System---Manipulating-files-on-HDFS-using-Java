package JavaHDFS.JavaHDFS;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;

public class Unzipping_file {
	public static void main(String[] args) throws IOException {

		 String path = args[0] + "/file7.zip";
		 File Dir = new File(args[0]);
	        if (!Dir.exists()) {
	            Dir.mkdir();
	        }
	        ZipInputStream zis = new ZipInputStream(new FileInputStream(path));
	        ZipEntry entry = zis.getNextEntry();
	       
	        while (entry != null) {
	            String filePath = args[0] + File.separator + entry.getName();
	            if (!entry.isDirectory()) {	              
	                getFile(zis, filePath);
	            } else {	               
	                File dir = new File(filePath);
	                dir.mkdir();
	            }
	            zis.closeEntry();
	            entry = zis.getNextEntry();
	        }
	        zis.close();
	    }
	    
	    private static void getFile(ZipInputStream zis, String filePath) throws IOException {
	        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
	        byte[] bytesIn = new byte[4096];
	        int read = 0;
	        while ((read = zis.read(bytesIn)) != -1) {
	            bos.write(bytesIn, 0, read);
	        }
	        bos.close();
	    }
}

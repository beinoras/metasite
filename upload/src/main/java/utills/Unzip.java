package utills;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.web.multipart.MultipartFile;

public class Unzip {
	
	
	public static String unZipIt(MultipartFile zipFile) {
		
		StringBuilder sb = new StringBuilder();
		byte[] buffer = new byte[1024];

		try {
			
			ZipInputStream zis = new ZipInputStream(zipFile.getInputStream());

			ZipEntry entry = null;
			int read = 0;
			String name = null;
			while ((entry = zis.getNextEntry())!= null) {
				name = entry.getName();
				if(name.substring(name.lastIndexOf(".") + 1).equals("txt")){
					while ((read = zis.read(buffer, 0, 1024)) >= 0) {
						sb.append(new String(buffer, 0, read).replace('\n', ' ').replace('\r', ' ') + ' ');
					}
				}
			}
			
			zis.closeEntry();
			zis.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}
}

package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipHelper {
	
	/**
	 * Adapted from http://www.mkyong.com/java/how-to-decompress-files-from-a-zip-file/
	 * @param zipFile
	 * @param outputFolder
	 */
	public static void unZip(String zipFile, String outputFolder) {

		byte[] buffer = new byte[1024];

		try {
			// create output directory is not exists
			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}
			// get the zip file content
			ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {

				String fileName = ze.getName();
				File newFile = new File(outputFolder + File.separator + fileName);

				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();
				if (!newFile.exists()) {
					if (newFile.isDirectory()) {
						newFile.mkdir();
					} else {
						newFile.createNewFile();
					}
				}
				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

			System.out.println("Done");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Adapted from https://examples.javacodegeeks.com/core-java/util/zip/create-zip-file-from-directory-with-zipoutputstream/
	 * @param source
	 * @param destination
	 * @throws IOException
	 */
	public static void Zip(String source, String destination) throws IOException {
		File sourceFile = new File(source);
		if (sourceFile.exists() && sourceFile.isDirectory()) {
			try {
				// create byte buffer
				byte[] buffer = new byte[1024];
				FileOutputStream fos = new FileOutputStream(destination);
				ZipOutputStream zos = new ZipOutputStream(fos);
				File dir = new File(source);
				File[] files = dir.listFiles();
				for (int i = 0; i < files.length; i++) {
					FileInputStream fis = new FileInputStream(files[i]);
					// begin writing a new ZIP entry, positions the stream to
					// the start of the entry data
					zos.putNextEntry(new ZipEntry(files[i].getName()));
					int length;
					while ((length = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}
					zos.closeEntry();
					// close the InputStream
					fis.close();
				}
				// close the ZipOutputStream
				zos.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		} else {
			throw new InvalidObjectException("Source file " + sourceFile.getName() + "is not a directory.");
		}
	}
}

package rocks.huwi.liberatepdf.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BatchProcessor {
	private File createZip(File[] files) throws IOException {
		// String zipPath = "PDFs.zip";
		File zipFile = new File("PDFs.zip");

		// out put file
		ZipOutputStream zipStream = new ZipOutputStream(new FileOutputStream(zipFile));

		for (File file : files) {
			// input file
			FileInputStream fileStream = new FileInputStream(file);

			// name the file inside the zip file
			String fileName = file.getName();
			zipStream.putNextEntry(new ZipEntry(fileName));

			// buffer size
			byte[] b = new byte[1024];
			int count;

			while ((count = fileStream.read(b)) > 0) {
				System.out.println();
				zipStream.write(b, 0, count);
			}

			fileStream.close();
		}

		zipStream.close();

		return zipFile;
	}

	public File RemoveRestrictions(File[] filesOriginal, String password) {
		RestrictionsRemover restrictionsRemover = new RestrictionsRemover();

		File[] filesNew = restrictionsRemover.RemoveRestrictions(filesOriginal, password);

		File zipFile = null;
		try {
			zipFile = this.createZip(filesNew);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return zipFile;
	}

}

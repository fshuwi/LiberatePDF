package rocks.huwi.liberatepdf.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BatchProcessor {

    private final Logger Logger = LoggerFactory.getLogger(BatchProcessor.class);

	private File createZip(File[] files) throws IOException {
            Logger.info("Creating ZIP file");
		// String zipPath = "PDFs.zip";
                Path temporaryPath = Files.createTempDirectory("LiberatePDF_zip");
		File zipFile = new File(temporaryPath+"/PDFs.zip");

		// out put file
		ZipOutputStream zipStream = new ZipOutputStream(new FileOutputStream(zipFile));

		for (File file : files) {
                    Logger.info("Adding file \"{}\" to ZIP", file.getName());
                    
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

                Logger.info("Closing ZIP file");
		zipStream.close();

		return zipFile;
	}

	public File RemoveRestrictions(File[] filesOriginal, String password) {
		RestrictionsRemover restrictionsRemover = new RestrictionsRemover();

		File[] filesNew = restrictionsRemover.RemoveRestrictions(filesOriginal, password);

                if (filesNew.length == 0)
                {
                    Logger.error("RestrictionsRemover returned zero files.");
                }
                else if (filesNew.length == 1)
                {
                    return filesNew[0];
                }
                else if (filesNew.length > 1)
                {
                    File zipFile = null;
                    try {
                            zipFile = this.createZip(filesNew);
                    } catch (IOException e) {
                        Logger.error("Exception occured during removing restrictions:", e);
                    }
                    
                    return zipFile;
                }
                
                Logger.error("This cannot happen.");
                return null;
	}

}

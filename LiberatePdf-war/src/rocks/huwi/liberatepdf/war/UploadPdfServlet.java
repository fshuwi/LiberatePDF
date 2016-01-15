package rocks.huwi.liberatepdf.war;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rocks.huwi.liberatepdf.ejb.RemoteRemoveRestrictionsInterface;
import rocks.huwi.liberatepdf.ejb.RemoveRestrictionsService;

@WebServlet("/UploadPdf")
@MultipartConfig
public class UploadPdfServlet extends HttpServlet {
	private static final long serialVersionUID = 2615577553835700429L;
        private final Logger Logger = LoggerFactory.getLogger(UploadPdfServlet.class);

	@EJB
	RemoteRemoveRestrictionsInterface remoteRemoveRestrictionsInterface;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String password = request.getParameter("pdfPassword");
		Collection<Part> fileParts = request.getParts().stream().filter(p -> p.getName().equals("pdfFile"))
				.collect(Collectors.toList());
		// Part filePart = request.getPart("pdfFile");

		Path temporaryPath = Files.createTempDirectory("LiberatePDF_incoming");
                Logger.info("Created temporary directory \"{}\"", temporaryPath);

		List<File> temporaryFiles = new ArrayList<>();
		for (Part filePart : fileParts) {
			Logger.info("Submitted filename: \"{}\"", filePart.getSubmittedFileName());
                        Logger.info("Name: \"{}\"", filePart.getName());

			String fileName = filePart.getSubmittedFileName();
			File temporaryFile = new File(temporaryPath.toFile(), fileName);
			temporaryFiles.add(temporaryFile);

			try (InputStream inputStream = filePart.getInputStream()) {
				Files.copy(inputStream, temporaryFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
		}

		File newFile = this.remoteRemoveRestrictionsInterface.removeRestrictions(temporaryFiles.toArray(new File[] {}),
				password);

		this.serveFile(request, response, newFile);
	}

	private void serveFile(HttpServletRequest request, HttpServletResponse response, File file)
			throws ServletException, IOException {
		// String filename = URLDecoder.decode(fileName, "UTF-8");
		// File file = new File("/path/to/files", filename);
                Logger.info("Serve file");
                
		response.setHeader("Content-Type", this.getServletContext().getMimeType(file.getAbsolutePath()));
		response.setHeader("Content-Length", String.valueOf(file.length()));
		response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
                
                Logger.info("Content-Type: {}", this.getServletContext().getMimeType(file.getAbsolutePath()));
		Logger.info("Content-Length: {}", String.valueOf(file.length()));
		Logger.info("Content-Disposition: {}", "inline; filename=\"" + file.getName() + "\"");

		Files.copy(file.toPath(), response.getOutputStream());
	}

}
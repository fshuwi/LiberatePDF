package de.debuglevel.liberatepdf.library;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RestrictionsRemover {

	public File RemoveRestrictions(File fileOriginal, String password) {
		// String exePath = "C:\\Program Files (x86)\\PDFtk\\bin\\pdftk.exe";
		// String exePath = "/usr/bin/pdftk";
		String pdftkExecutable = "pdftk";
		File fileNew = new File(fileOriginal + " (liberated).pdf");

		String passwordArgument = "";
		if ((password != null) && !password.isEmpty()) {
			passwordArgument = "input_pw";
		}

		try {
			String stdoutLog = "";
			String stderrLog = "";
			String s;

			// run command
			ProcessBuilder processBuilder = new ProcessBuilder(pdftkExecutable, fileOriginal.getAbsolutePath(),
					passwordArgument, password, "output", fileNew.getAbsolutePath(), "allow", "AllFeatures");
			Process p = processBuilder.start();

			BufferedReader stdOutput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			while ((s = stdOutput.readLine()) != null) {
				stdoutLog += s + "\n";
			}

			// read any errors from the attempted command
			while ((s = stdError.readLine()) != null) {
				stderrLog += s + "\n";
			}

			System.out.println(stdoutLog);
			System.out.println(stderrLog);
		} catch (IOException e) {
			System.out.println("exception happened - here's what I know: ");
			e.printStackTrace();
			System.exit(-1);
		}

		return fileNew;
	}

	public File[] RemoveRestrictions(File[] filesOriginal, String password) {
		ArrayList<File> filesNew = new ArrayList<File>();

		for (File fileOriginal : filesOriginal) {
			File fileNew = this.RemoveRestrictions(fileOriginal, password);
			filesNew.add(fileNew);
		}

		return filesNew.toArray(new File[] {});
	}
}

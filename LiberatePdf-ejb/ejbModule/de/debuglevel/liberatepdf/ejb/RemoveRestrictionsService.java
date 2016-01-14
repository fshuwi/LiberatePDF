package de.debuglevel.liberatepdf.ejb;

import java.io.File;

import javax.ejb.Stateless;

import de.debuglevel.liberatepdf.library.BatchProcessor;

@Stateless
public class RemoveRestrictionsService implements RemoteRemoveRestrictionsInterface {

	@Override
	public File removeRestrictions(File file, String password) {
		BatchProcessor batchProcessor = new BatchProcessor();
		File[] files = new File[] { file };
		File newFile = batchProcessor.RemoveRestrictions(files, password);

		return newFile;
	}

	@Override
	public File removeRestrictions(File[] files, String password) {
		BatchProcessor batchProcessor = new BatchProcessor();
		File newFile = batchProcessor.RemoveRestrictions(files, password);

		return newFile;
	}

}

package rocks.huwi.liberatepdf.ejb;

import java.io.File;

import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rocks.huwi.liberatepdf.library.BatchProcessor;

@Stateless
public class RemoveRestrictionsService implements RemoteRemoveRestrictionsInterface {
    
        private final Logger Logger = LoggerFactory.getLogger(RemoveRestrictionsService.class);

	@Override
	public File removeRestrictions(File file, String password) {
            Logger.info("Remove restrictions from file \"{}\" with password \"{}\"", file, password);
            
		BatchProcessor batchProcessor = new BatchProcessor();
		File[] files = new File[] { file };
		File newFile = batchProcessor.RemoveRestrictions(files, password);

		return newFile;
	}

	@Override
	public File removeRestrictions(File[] files, String password) {
            Logger.info("Remove restrictions from multiple files \"{}\" with password \"{}\"", files, password);
            
		BatchProcessor batchProcessor = new BatchProcessor();
		File newFile = batchProcessor.RemoveRestrictions(files, password);

		return newFile;
	}

}

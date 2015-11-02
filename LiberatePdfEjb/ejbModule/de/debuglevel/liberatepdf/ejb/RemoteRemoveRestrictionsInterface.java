package de.debuglevel.liberatepdf.ejb;

import java.io.File;

import javax.ejb.Remote;

@Remote
public interface RemoteRemoveRestrictionsInterface {
	public File removeRestrictions(File file, String password);

	public File removeRestrictions(File[] files, String password);
}

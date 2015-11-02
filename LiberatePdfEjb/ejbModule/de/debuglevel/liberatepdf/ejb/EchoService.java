package de.debuglevel.liberatepdf.ejb;

import javax.ejb.Stateless;

@Stateless
public class EchoService implements RemoteEchoInterface {

	@Override
	public String echo(String name) {
		return this.getClass().getName() + " says echoes \"" + name + "\"";
	}

}

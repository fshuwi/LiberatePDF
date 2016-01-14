package rocks.huwi.liberatepdf.ejb;

import javax.ejb.Remote;

@Remote
public interface RemoteEchoInterface {
	public String echo(String name);
}

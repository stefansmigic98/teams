package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiServer {

	public static void main(String[] args) {
		
		try {
			FileUploader fu = new FileUploader();
			LocateRegistry.createRegistry(1099);
			Naming.rebind("//localhost:1099/Server", fu);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

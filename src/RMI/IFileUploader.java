package RMI;

import java.io.InputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFileUploader extends Remote {
	
	public int uploadFile(String path,byte[] data, int length) throws RemoteException;
	

}

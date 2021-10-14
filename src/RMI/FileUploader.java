package RMI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FileUploader extends UnicastRemoteObject implements IFileUploader {

	protected FileUploader() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int uploadFile(String path,byte[] data, int length) throws RemoteException {
		// TODO Auto-generated method stub
		  File file = new File(path);
		  FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file, false);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    
        try {
				outputStream.write(data);
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		return 0;
	}

}

package Services;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;




public class FilesManager {

	public static List<Models.File> getFilesForSubjectId(String subjectId, int parrentId){
		List<Models.File> files = new ArrayList<Models.File>();
		try {
			File folder  = new File(".\\FAX\\Eclipse\\Classroom\\src\\Resources\\"+subjectId);
			 System.out.println(folder.getName());
			 if(folder.list().length >0)
			 {
				
				for (final File fileEntry : folder.listFiles()) {
			            System.out.println(fileEntry.getName());
			            if(fileEntry.isFile())
			            {
			            	files.add(new Models.File(fileEntry.getName(), "",false));
			            }
			            else
			            {
			            	files.add(new Models.File(fileEntry.getName(), "",true));
			            }
			            
			            
			        }
				return files;
				}
			 return null;
			}
		
			catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				return null;
			}
		
	}
	
}

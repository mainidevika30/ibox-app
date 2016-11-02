package edu.csupomona.cs585.ibox;

import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.*;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;
import edu.csupomona.cs585.ibox.sync.GoogleDriveServiceProvider;
import junit.framework.TestCase;

public class IntGoogleFile extends TestCase{
	GoogleDriveFileSyncManager obj = new GoogleDriveFileSyncManager(
			GoogleDriveServiceProvider.get().getGoogleDriveClient());
	File localFile = new File("/Users/Devika/Desktop/myFileDevika.rtf");
	String fileName = "myFileDevika.rtf";

	public void testAadd() throws IOException {
		obj.addFile(localFile);
		String id = obj.getFileId(fileName);
		assertNotNull(id);
		System.out.println("File added");
	}

	public void testBupdate() throws Exception {
		obj.updateFile(localFile);
		String id = obj.getFileId(fileName);
		assertNotNull(id);
		System.out.println("File Updated");
	}

	public void testCdelete() throws Exception {
		obj.deleteFile(localFile);
		String id = obj.getFileId(fileName);
		assertNull(id);
		System.out.println("File Deleted");
	}
}

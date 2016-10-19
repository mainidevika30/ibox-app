package edu.csupomona.cs585.ibox;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.*;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;
import edu.csupomona.cs585.ibox.sync.GoogleDriveServiceProvider;
import junit.framework.TestCase;

public class IntGoogleFile {
	GoogleDriveFileSyncManager obj = new GoogleDriveFileSyncManager(
			GoogleDriveServiceProvider.get().getGoogleDriveClient());
	java.io.File localFile = new java.io.File("/Users/Devika/Desktop/devika.rtf");

	@Test
	public void FileAddTest() throws IOException {
		obj.addFile(localFile);
	}
	@Test
	public void FileUpdateTest() throws Exception {
	obj.updateFile(localFile);
}
	@Test
	public void FileDeleteTest() throws Exception {
	obj.deleteFile(localFile);
}
}

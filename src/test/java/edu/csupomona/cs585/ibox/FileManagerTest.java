package edu.csupomona.cs585.ibox;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Delete;
import com.google.api.services.drive.Drive.Files.Insert;
import com.google.api.services.drive.Drive.Files.List;
import com.google.api.services.drive.Drive.Files.Update;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;
import junit.framework.TestCase;

public class FileManagerTest extends TestCase {

	GoogleDriveFileSyncManager googleDFS;
	Drive drive;

	@BeforeClass
	public void setUp() {
		drive = mock(Drive.class);
		googleDFS = new GoogleDriveFileSyncManager(drive);

	}

	public void testAddFile() throws IOException {
		java.io.File localFile = mock(java.io.File.class);
		File basic = new File();
		Files files = mock(Files.class);
		Insert insert = mock(Insert.class);

		when(drive.files()).thenReturn(files);
		when(files.insert(isA(File.class), isA(AbstractInputStreamContent.class))).thenReturn(insert);
		when(insert.execute()).thenReturn(basic);

		googleDFS.addFile(localFile);

		verify(drive).files();
		verify(files).insert(isA(File.class), isA(AbstractInputStreamContent.class));
		verify(insert).execute();
	}

	// update file
	public void testUpdateFile() throws IOException {
		java.io.File localFile = mock(java.io.File.class);
														
		ArrayList<File> arrayListFile = new ArrayList<File>(); 
															
		List list = mock(List.class); 
		FileList fileList = new FileList();
		File body = new File(); 
		File sBody = new File();

		body.setTitle("devika.txt");
		body.setId("SET09");
		arrayListFile.add(body);
		fileList.setItems(arrayListFile);
		Files files = mock(Files.class);
		Update update = mock(Update.class);
		
		
		when(localFile.getName()).thenReturn("devika.txt");
		when(files.list()).thenReturn(list);
		when(list.execute()).thenReturn(fileList);
		when(drive.files()).thenReturn(files);
		when(files.update(isA(String.class), isA(File.class), isA(AbstractInputStreamContent.class)))
				.thenReturn(update);
		when(update.execute()).thenReturn(sBody);

		googleDFS.updateFile(localFile);

		verify(localFile, atLeast(1)).getName();
		verify(files).list();
		verify(list).execute();
		verify(drive, atLeast(1)).files();
		verify(files).update(isA(String.class), isA(File.class), isA(AbstractInputStreamContent.class));
		verify(update).execute();
	}

	// deleteFile
	public void testDeleteFile() throws Exception {
		java.io.File localFile = mock(java.io.File.class);
		ArrayList<File> arrayListFile = new ArrayList<File>();
		List list = mock(List.class); 
		FileList fileList = new FileList();
		File body = new File(); 
		body.setTitle("devika.txt");
		body.setId("SET09");
		arrayListFile.add(body);
		fileList.setItems(arrayListFile);
		Files files = mock(Files.class);
		Delete delete = mock(Delete.class);
		
		when(localFile.getName()).thenReturn("devika.txt");
		when(files.list()).thenReturn(list);
		when(list.execute()).thenReturn(fileList);

		when(drive.files()).thenReturn(files);
		when(files.delete(isA(String.class)))
				.thenReturn(delete);
		when(delete.execute()).thenReturn(null);

		googleDFS.deleteFile(localFile);

		verify(localFile, atLeast(1)).getName();
		verify(files).list();
		verify(list).execute();
		verify(drive, atLeast(1)).files();
		verify(files).delete(isA(String.class));
		verify(delete).execute();

	}

}

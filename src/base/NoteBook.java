package base;

import java.util.ArrayList;
import java.util.List;

public class NoteBook {

	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public void sortFolders() {
		for (Folder folder : folders)
			folder.sortNotes();
	}
	
	public List<Note> searchNotes(String keywords) {
		List<Note> result = new ArrayList<>();
		for (Folder folder : folders) {
			List<Note> temp = folder.searchNotes(keywords);
			if (!temp.isEmpty())
				result.addAll(temp);
		}
		return result;
	}
	
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	
	public boolean insertNote(String folderName, Note note) {
		Folder f = null;
		for (Folder f1 : folders) {
			if (f1.getName().equals(folderName)) {
				f = f1;
				break;
			}
		}
		
		if (f == null) {
			f = new Folder(folderName);
			folders.add(f);
		}
		
		for (Note n : f.getNotes()) {
			if (n.equals(note)) {
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
		}
		f.addNote(note);
		return true;
	}
	
 	public ArrayList<Folder> getFolders() {
 		return folders;
 	}
 	
}

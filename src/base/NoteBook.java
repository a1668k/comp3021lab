package base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NoteBook implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Folder> folders;	
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	@SuppressWarnings("unchecked")
	public NoteBook(String file) {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			folders = (ArrayList<Folder>) in.readObject();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public boolean save(String file) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			/*
			for (Folder f : folders) {
				for (Note n : f.getNotes()) {
					out.writeObject(n);
				}
			}*/
			out.writeObject(folders);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
 	public ArrayList<Folder> getFolders() {
 		return folders;
 	}
 	
 	public void setFolders(ArrayList<Folder> input) {
 		folders.clear();
 		folders.addAll(input);
 	}
 	
}

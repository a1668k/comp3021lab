package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TextNote extends Note implements Serializable {

	String content;
	
	public TextNote(String title) {
		super(title);
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(absolutePath);
			in = new ObjectInputStream(fis);
			result = (String) in.readObject();
			in.close();
		} catch (Exception e) {
			return "";
		}
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
		if (pathFolder == "")
			pathFolder = ".";
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		File file = new File(pathFolder + File.separator + this.getTitle().replaceAll(" ", "_") + ".txt");
		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(content);
			out.close();
		} catch (Exception e) {
			return;
		}
	}
	
	public String getTextNoteContent() {
		return content;
	}
	
	public void setTextNoteContent(String content) {
		this.content = content;
	}
}

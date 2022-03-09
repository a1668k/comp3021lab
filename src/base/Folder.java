package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>{

	private ArrayList<Note> notes;
	private String name;
	
	@Override
	public int compareTo(Folder o) {
		return this.name.compareTo(o.name);
	}
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords) {
		String[] keyword = keywords.split(" ");
		
		List<Note> result = notes;
		
		for (int i = 0; i < keyword.length; i++) {
			List<Note> current = new ArrayList<>();
			if (i + 1 < keyword.length && (keyword[i+1].equals("OR") || keyword[i+1].equals("or"))) { // OR operation
				for (Note n : result) {
					if (n instanceof ImageNote) {
						// for ImageNote
						if (n.getTitle().toLowerCase().contains(keyword[i].toLowerCase()) || n.getTitle().toLowerCase().contains(keyword[i+2].toLowerCase()))
							current.add(n);
					} else {
						// for TextNote
						if (n.getTitle().toLowerCase().contains(keyword[i].toLowerCase()) || n.getTitle().toLowerCase().contains(keyword[i+2].toLowerCase()) ||
							   ((TextNote)n).content.toLowerCase().contains(keyword[i].toLowerCase()) || ((TextNote)n).content.toLowerCase().contains(keyword[i+2].toLowerCase()))
							current.add(n);
					}
				}
				
				i += 2;
			} else { // And operation
				for (Note n : result) {
					if (n instanceof ImageNote) {
						if (! (n.getTitle().toLowerCase().contains(keyword[i].toLowerCase())) )
							current.add(n);
					} else {
						if (n.getTitle().toLowerCase().contains(keyword[i].toLowerCase()) || ((TextNote)n).content.toLowerCase().contains(keyword[i].toLowerCase()))
							current.add(n);
					}
				}
			}
			result = current;
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		for (Note note : notes) {
			if (note instanceof TextNote) {
				nText++;
			} else if (note instanceof ImageNote) {
				nImage++;
			}
		}
		return name + ':' + nText + ':' + nImage; 
	}
	
}

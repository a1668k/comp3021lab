package base;

public class TextNote extends Note {

	private String content;
	
	public TextNote(String title) {
		super(title);
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	@Override
	public String getContent() {
		return content;
	}
	
}

package application;

public abstract class Question {
	private String myText;
	public Question(String myText) {
		this.myText = myText;
	}
	public String getText() {
		return myText;
	}
	public abstract String getQuestion();
	public abstract String getAnswer();
	public abstract String checkAnswer(Object ob);
	public abstract void setAnswer(String answer);

	
	
}

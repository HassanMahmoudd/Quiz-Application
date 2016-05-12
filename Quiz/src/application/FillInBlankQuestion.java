package application;

public class FillInBlankQuestion extends Question{
	private String myAnswer;
	
	public FillInBlankQuestion(String text)
	{
		super(text);
	}

	@Override
	public String getQuestion() {
		String text = super.getText();
		text = text + "\nFill in the blank.";
		return text;
	}

	@Override
	public String getAnswer() {
		return myAnswer;
	}

	@Override
	public String checkAnswer(Object ob) {
		if(myAnswer.equals(ob))
			return "true";
		return "false";
	}
	@Override
	public void setAnswer(String answer)
	{
		myAnswer = answer;
	}
}

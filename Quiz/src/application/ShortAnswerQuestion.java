package application;

public class ShortAnswerQuestion extends Question {
	private String myAnswer;
	
	public ShortAnswerQuestion(String text)
	{
		super(text);
	}

	@Override
	public String getQuestion() {
		String text = super.getText();
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

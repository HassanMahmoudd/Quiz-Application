package application;

public class TrueFalseQuestion extends Question{
	private boolean myAnswer;
	
	public TrueFalseQuestion(String text)
	{
		super(text);
	}

	@Override
	public String getQuestion() {
		String text = super.getText();
		text = text + "\nTrue or False Question";
		return text;
	}

	@Override
	public String getAnswer() {
		String text1 = new Boolean(myAnswer).toString();
		return text1;
	}

	@Override
	public String checkAnswer(Object ob) {
		String text1 = new Boolean(myAnswer).toString();
		if(text1.equals(ob))
			return "true";
		return "false";
	}
	@Override
	public void setAnswer(String answer)
	{
		boolean bool = Boolean.parseBoolean(answer);
		myAnswer = bool;
	}

}

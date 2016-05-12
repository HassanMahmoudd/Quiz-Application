package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.sql.ResultSet;

public class MainController{
	@FXML
	RadioButton r1 = new RadioButton();
	@FXML
	RadioButton r2 = new RadioButton();
	@FXML
	PasswordField p1 = new PasswordField();
	@FXML
	TextField name = new TextField();
	@FXML
	Button getAnswer = new Button();
	@FXML
	Button getQuestion = new Button();
	@FXML
	Button submitStudent = new Button();
	@FXML
	Button getScore = new Button();
	@FXML
	TextField t1 = new TextField();
	@FXML
	TextField t2 = new TextField();
	//@FXML
	//TextField t3 = new TextField();
	@FXML
	RadioButton r3 = new RadioButton();
	@FXML
	RadioButton r4 = new RadioButton();
	@FXML
	RadioButton r5 = new RadioButton();
	@FXML
	Label question = new Label();
	@FXML
	Label type = new Label();
	@FXML
	Label Type = new Label();
	@FXML
	Label Question = new Label();
	@FXML
	Label AnswerLabel = new Label();
	@FXML
	TextField answer = new TextField();
	@FXML
	RadioButton r6 = new RadioButton();
	@FXML
	RadioButton r7 = new RadioButton();
	@FXML
	RadioButton trueProf = new RadioButton();
	@FXML
	RadioButton falseProf = new RadioButton();
	@FXML
	Label scoreLabel = new Label();
	@FXML
	Label passLabel = new Label();
	@FXML
	Label quizLabel = new Label();
	@FXML
	ImageView imageViewLike = new ImageView();
	//String nameSaved = name.getText();;
	public void radioButtonMain()
	{
		ToggleGroup group = new ToggleGroup();
		r1.setToggleGroup(group);
		r2.setToggleGroup(group);
		p1.setVisible(false);
		name.setVisible(false);
		if(r1.isSelected())
		{
			p1.setVisible(true);
			name.setVisible(false);
		}
		if(r2.isSelected())
		{
			p1.setVisible(false);
			name.setVisible(true);
		}
		
			
	}
	public void buttonEnter(ActionEvent event) throws IOException
	{
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;
		//System.out.println(p1.getText().equals("Yes"));
		
		if(r2.isSelected() == true && !name.getText().equals(""))
		{
			System.out.println(r2.isSelected() + "Student");
			
			Parent root = FXMLLoader.load(getClass().getResource("Student.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
			//stage.hide();
			stage1.setScene(scene);
			stage1.show();
			Quiz.icount = 0;
			Quiz.icountGet = 0;
			
			
		}
		else if(r1.isSelected() && p1.getText().equals("Yes"));
		{
			System.out.println(r1.isSelected() + "Professor");
			Parent root = FXMLLoader.load(getClass().getResource("Next.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
			//stage.hide();
			stage2.setScene(scene);
			stage2.show();
			//t2.setVisible(false);
		}
		if(r1.isSelected() && !p1.getText().equals("Yes"))
		{
			Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			JOptionPane.showMessageDialog(null, "Password is wrong, Try again");
		}
		if(r2.isSelected() && name.getText().equals(""))
		{
			Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			JOptionPane.showMessageDialog(null, "Please enter a name!");
		}
		if(!r1.isSelected() && !r2.isSelected())
		{
			Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			JOptionPane.showMessageDialog(null, "Please choose an option!");
		}
		
		
	}
	
	public void radioButtonProfessor()
	{
		ToggleGroup group = new ToggleGroup();
		r3.setToggleGroup(group);
		r4.setToggleGroup(group);
		r5.setToggleGroup(group);
		t2.setVisible(false);
		
		ToggleGroup group1 = new ToggleGroup();
		trueProf.setToggleGroup(group1);
		falseProf.setToggleGroup(group1);
		trueProf.setVisible(false);
		falseProf.setVisible(false);
		if(r3.isSelected() || r4.isSelected())
		{
			t2.setVisible(true);
		}
		if(r5.isSelected())
		{
			trueProf.setVisible(true);
			falseProf.setVisible(true);
		}
		
	}
	
	public void buttonSubmit(ActionEvent event1) throws IOException
	{
		Stage stage;
		try
		{
			
			Connection myConn = JDBCConnector.connect();
			String query = "INSERT INTO quizone (Question, Answer, Type) VALUES (?,?,?)";
			PreparedStatement pst = myConn.prepareStatement(query);
			if(t1.getText() != "" && t2.getText() != "" && (r3.isSelected() || r4.isSelected() || r5.isSelected()))
			{
			pst.setString(1, t1.getText());
			if(r3.isSelected() || r4.isSelected())
			{
				pst.setString(2, t2.getText());
			}
			if(r5.isSelected())
			{
				if(trueProf.isSelected())
					pst.setString(2, trueProf.getText());
				if(falseProf.isSelected())
					pst.setString(2, falseProf.getText());
			}
			
			if(r3.isSelected())
			{
				pst.setString(3, r3.getText());
				Quiz.questions[Quiz.icount] = new ShortAnswerQuestion(t1.getText());
				Quiz.questions[Quiz.icount].setAnswer(t2.getText());
				System.out.println("The question is " + Quiz.questions[Quiz.icount].getQuestion());
				System.out.println("The answer is " + Quiz.questions[Quiz.icount].getAnswer());
				Quiz.icount++;
			}
			if(r4.isSelected())
			{
				pst.setString(3, r4.getText());
				Quiz.questions[Quiz.icount] = new FillInBlankQuestion(t1.getText());
				Quiz.questions[Quiz.icount].setAnswer(t2.getText());
				System.out.println("The question is " + Quiz.questions[Quiz.icount].getQuestion());
				System.out.println("The answer is " + Quiz.questions[Quiz.icount].getAnswer());
				Quiz.icount++;
			}
			if(r5.isSelected())
			{
				pst.setString(3, r5.getText());
				Quiz.questions[Quiz.icount] = new TrueFalseQuestion(t1.getText());
				Quiz.questions[Quiz.icount].setAnswer(t2.getText());
				System.out.println("The question is " + Quiz.questions[Quiz.icount].getQuestion());
				System.out.println("The answer is " + Quiz.questions[Quiz.icount].getAnswer());
				Quiz.icount++;
			}
			pst.execute();
			myConn.close();
			pst.close();
			JOptionPane.showMessageDialog(null, "Data saved!");
			}
			else
				JOptionPane.showMessageDialog(null, "Please don't leave any entry empty!");
				
			Parent root = FXMLLoader.load(getClass().getResource("Next.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage = (Stage) ((Node) event1.getSource()).getScene().getWindow();
			//stage.hide();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void buttonMainMenu(ActionEvent event2) throws IOException
	{
		Stage stage;
		Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage = (Stage) ((Node) event2.getSource()).getScene().getWindow();
		//stage.hide();
		stage.setScene(scene);
		stage.show();
		
	}
	int count=0;
	int score=0;
	
	int buttonClicked = 0;
	
	public int getTotalCount()
	{
		int totalCount = 0;
		try
		{
			Connection myConn = JDBCConnector.connect();
			String query = "SELECT Question, Type FROM quizone";
			PreparedStatement pst = myConn.prepareStatement(query);
			ResultSet rst = pst.executeQuery();
			while(rst.next())
				totalCount++;
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return totalCount;
	}
	int j=0;
	public void buttonGetQuestion(ActionEvent event3) throws IOException
	{
		getAnswer.setVisible(false);
		buttonSubmitClicked = 0;
		int totalCount = getTotalCount();
		buttonClicked++;
		//System.out.println(totalCount);
		//System.out.println(buttonClicked);
		try
		{
		Connection myConn1 = JDBCConnector.connect();
		String query1 = "SELECT Question,Answer, Type FROM quizone";
		PreparedStatement pst1 = myConn1.prepareStatement(query1);
		ResultSet rst1 = pst1.executeQuery();
		while(rst1.next())
		{
			//System.out.println(Quiz.icount);
			if(rst1.getString("Type").equals("ShortAnswerQuestion"))
			{
				Quiz.questions[Quiz.icount] = new ShortAnswerQuestion(rst1.getString("Question"));
				Quiz.questions[Quiz.icount].setAnswer(rst1.getString("Answer"));
			}
			if(rst1.getString("Type").equals("FillintheBlankQuestion"))
			{
				Quiz.questions[Quiz.icount] = new FillInBlankQuestion(rst1.getString("Question"));
				Quiz.questions[Quiz.icount].setAnswer(rst1.getString("Answer"));
			}
			if(rst1.getString("Type").equals("TrueorFalseQuestion"))
			{
				Quiz.questions[Quiz.icount] = new TrueFalseQuestion(rst1.getString("Question"));
				Quiz.questions[Quiz.icount].setAnswer(rst1.getString("Answer"));
			}
			Quiz.icount++;
			//System.out.println(Quiz.icount);
				
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(buttonClicked <= totalCount)
		{
		Quiz.icountGet = 0;
		try
		{
			
		 	Connection myConn = JDBCConnector.connect();
			String query = "SELECT Question, Type FROM quizone";
			PreparedStatement pst = myConn.prepareStatement(query);
			ResultSet rst = pst.executeQuery();
			count++;
			int i;
			for(i=0; i<count; i++)
			{
				rst.next();
				question.setText(rst.getString("Question"));
				type.setText(rst.getString("Type"));
			}
			
			if(type.getText().equals("ShortAnswerQuestion"))
			{
				//System.out.println(Quiz.icount);
				System.out.println(Quiz.questions[j].getQuestion());
				System.out.println(Quiz.questions[j].getAnswer());
				r6.setVisible(false);
				r7.setVisible(false);
				answer.setVisible(true);
				answer.setEditable(true);
				//answer.clear();
			}
			if(type.getText().equals("TrueorFalseQuestion"))
			{
				System.out.println(Quiz.questions[j].getQuestion());
				System.out.println(Quiz.questions[j].getAnswer());
				answer.setVisible(false);
				r6.setVisible(true);
				r7.setVisible(true);
			}
			if(type.getText().equals("FillintheBlankQuestion"))
			{
				System.out.println(Quiz.questions[j].getQuestion());
				System.out.println(Quiz.questions[j].getAnswer());
				r6.setVisible(false);
				r7.setVisible(false);
				answer.setVisible(true);
				answer.setEditable(true);
				//answer.clear();
			}
			j++;
		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		else
		{
			//JOptionPane.showMessageDialog(null, "Quiz is finished!");
			getQuestion.setVisible(false);
			submitStudent.setVisible(false);
			getScore.setVisible(true);
			quizLabel.setVisible(true);
			buttonSubmitClicked++;
		}
	}
	int buttonSubmitClicked = 0;
	public void buttonSubmitStudent()
	{
		
		if(buttonSubmitClicked == 0)
		{
		try
		{
			Connection myConn = JDBCConnector.connect();
			String query = "SELECT Answer FROM quizone";
			PreparedStatement pst = myConn.prepareStatement(query);
			ResultSet rst = pst.executeQuery();
			int i;
			for(i=0; i<count; i++)
			{
				rst.next();
			}
			if(type.getText().equals("ShortAnswerQuestion"))
			{
				if(answer.getText().equals(rst.getString("Answer")))
					score++;
				//if(questions[i-1].checkAnswer(answer.getText()).equals("true"))
					//score++;
			}
			if(type.getText().equals("TrueorFalseQuestion"))
			{
				//if(r6.isSelected() && Quiz.questions[i-1].checkAnswer(answer.getText()).equals("true"))
					//score++;
				if(r6.isSelected() && r6.getText().equalsIgnoreCase(rst.getString("Answer")))
				{
					//System.out.println("True");
					score++;
				}
				//if(r7.isSelected() && Quiz.questions[i-1].checkAnswer(answer.getText()).equals("true"))
					//score++;
				if(r7.isSelected() && r7.getText().equalsIgnoreCase(rst.getString("Answer")))
				{
					//System.out.println("False");
					score++;
				}
			}
			if(type.getText().equals("FillintheBlankQuestion"))
			{
				if(answer.getText().equals(rst.getString("Answer")))
					score++;
				//if(Quiz.questions[i-1].checkAnswer(answer.getText()).equals("true"))
					//score++;
			}
			//System.out.println(score);
			buttonSubmitClicked++;
			JOptionPane.showMessageDialog(null, "Data is saved!");
			getAnswer.setVisible(true);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		else
			JOptionPane.showMessageDialog(null, "Data is already saved!");
		
	}
	
	public void radioButtonStudent()
	{
		ToggleGroup group = new ToggleGroup();
		r6.setToggleGroup(group);
		r7.setToggleGroup(group);
	}
	
	
	public void buttonMainMenuStudent(ActionEvent event2) throws IOException
	{
		Stage stage;
		Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage = (Stage) ((Node) event2.getSource()).getScene().getWindow();
		//stage.hide();
		stage.setScene(scene);
		stage.show();
		
	}
	public void buttonScore(ActionEvent event) throws IOException
	{
		//System.out.println(name.getText());
		type.setVisible(false);
		Type.setVisible(false);
		question.setVisible(false);
		Question.setVisible(false);
		AnswerLabel.setVisible(false);
		answer.setVisible(false);
		r6.setVisible(false);
		r7.setVisible(false);
		quizLabel.setVisible(false);
		
		/*Stage stage;
		Parent root = FXMLLoader.load(getClass().getResource("Score.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		//stage.hide();
		stage.setScene(scene);
		stage.show();
		*/
		scoreLabel.setVisible(true);
		passLabel.setVisible(true);
		scoreLabel.setText("You have scored " + score + " out of " + getTotalCount());
		scoreLabel.setVisible(true);
		if(score >= getTotalCount()/2)
		{
			passLabel.setText("You have passed Congratulations !");
			imageViewLike.setVisible(true);
		}
		else
		{
			passLabel.setText("You have failed try again !");
			imageViewLike.setRotate(180);
			imageViewLike.setVisible(true);
		}
		//JOptionPane.showMessageDialog(null, "You has scored " + score + " out of " + getTotalCount());
	}
	public void buttonGetAnswer()
	{
		try
		{
			Connection myConn = JDBCConnector.connect();
			String query = "SELECT Answer FROM quizone";
			PreparedStatement pst = myConn.prepareStatement(query);
			ResultSet rst = pst.executeQuery();
			int i;
			for(i=0; i<count; i++)
			{
				rst.next();
			}
			//String answer = questions[i-1].getAnswer();
			//System.out.println(answer);
			JOptionPane.showMessageDialog(null, "The answer is " + rst.getString("Answer"));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	public void connect()
	{
		try
		{
			Connection myConn = JDBCConnector.connect();
			Statement mystat = myConn.createStatement();
			ResultSet rst = mystat.executeQuery("SELECT * FROM quizone");
			while(rst.next())
			{
				System.out.println(rst.getString("ID") + rst.getString("Question"));
			}
			myConn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}

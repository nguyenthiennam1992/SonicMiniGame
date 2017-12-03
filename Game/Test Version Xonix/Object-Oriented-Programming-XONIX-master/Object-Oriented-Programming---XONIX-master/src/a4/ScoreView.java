package a4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
public class ScoreView extends JPanel implements IObserver { 
	
	private JPanel top = new JPanel();
	private JPanel bottom = new JPanel();
	private JLabel currentLvlLabel ;
	private JLabel remainingTimeLabel;
	private JLabel livesLeftLabel;
	private JLabel currentScoreLabel;
	private JLabel requiredScoreLabel;
	private JLabel soundLabel;
	private JLabel currentLvl;
	private JLabel space = new JLabel(" ");// Just creates the space needed for scoreView
	private JLabel remainingTime;
	private JLabel livesLeft;
	private JLabel currentScore;
	private JLabel requiredScore;
	private Integer time;
	private Integer lvl;
	private Integer lives;
	private Double score;
	private Integer reqScore;
	private Boolean theSound;
	//True/False?
	private GameWorldProxy gameWorldProxy;
	private JLabel sound;
	 public void update (IObservable o, Object arg) { 
	 // code here to update JLabels from data in the Observable 
		// code here to output current map information (based on 
		 // the data in the Observable) to the console
		 gameWorldProxy =(GameWorldProxy) o;
		 lvl = gameWorldProxy.getLvl();
		 this.currentLvl.setText(lvl.toString());
		
		 time =(gameWorldProxy.getTime()/10);
		 this.remainingTime.setText(time.toString());
		 
		 lives = gameWorldProxy.getNumberOfLives();
		 this.livesLeft.setText(lives.toString());
		
		 score = (double) Math.round(gameWorldProxy.getScore()*100)/100.0;
		 
		 this.currentScore.setText(score.toString()+"%");
		 
		 reqScore = gameWorldProxy.getRequiredScore();
		 this.requiredScore.setText(reqScore.toString());
		 
		 theSound = gameWorldProxy.getSound();
		if (theSound == true)
		{
			this.sound.setText("ON");
		}else {
			this.sound.setText("OFF");
			
		}
		//Print everytime something changes
		//gameWorldProxy.printObjects();
		 
	 }
	 //ScoreView(Observable myModel)??
	 public ScoreView()
	 {
 
	  this.setBorder(new LineBorder(Color.blue,2));
	  this.setLayout(new BorderLayout());
	  
	//Need extra space below scoreview
	//this.setPreferredSize(new Dimension(900,60));
	  
	  //LABELS!!! not Values
	  currentLvlLabel = new JLabel("Current level: ");
	  remainingTimeLabel = new JLabel("Remaining Time: ");
	  livesLeftLabel = new JLabel("Lives Left: ");
	  currentScoreLabel = new JLabel("Current Score: ");
	  requiredScoreLabel = new JLabel("Required Score: ");
	  soundLabel =new JLabel("Sound:");
	  //Values
	  top.add(currentLvlLabel);
	  currentLvl= new JLabel("0");
	  top.add(currentLvl);
	 
	  top.add(remainingTimeLabel);
	  remainingTime= new JLabel("");
	  top.add(remainingTime);
	 
	  top.add(livesLeftLabel);
	  livesLeft = new JLabel("");
	  top.add(livesLeft);
	 
	  top.add(currentScoreLabel);
	  currentScore = new JLabel("");
	  top.add(currentScore);
	  
	  top.add(requiredScoreLabel);
	  requiredScore = new JLabel("");
	  top.add(requiredScore);
	 
	  top.add(soundLabel);
	  sound= new JLabel("");
	  top.add(sound);
	  //top.setLayout(new GridLayout(0, 12, 0, 0));
	  this.add(top,BorderLayout.NORTH);
	  bottom.add(space);
	 // bottom.setLayout(new GridLayout(0,12,0,0));
	  this.add(bottom,BorderLayout.SOUTH);
		 
	 }

	} 


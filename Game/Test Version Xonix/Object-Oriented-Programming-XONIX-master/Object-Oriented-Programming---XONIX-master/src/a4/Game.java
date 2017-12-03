package a4;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

public class Game extends JFrame implements ActionListener {
    private GameWorld gw;
	private MapView mapView;
	private GameWorldProxy gwProxy;
	private ScoreView scoreView;
	private String input;
	private char inputLetter;
	private int exit = 0;
	private Timer timer;
	private HullDrawCommand hullDrawCommand = new HullDrawCommand();
	private AddSmartBallCommand smartBallCommand = new AddSmartBallCommand();
	private CarHitSmartBallCommand carHitSmartBallCommand = new CarHitSmartBallCommand();
	private CarOwnsSquareCommand carOwnsSquareCommand = new CarOwnsSquareCommand();
	private CarOwnsSquaresCommand carOwnsSquaresCommand = new CarOwnsSquaresCommand();
	private CarHitTicketCommand carHitTicketCommand = new CarHitTicketCommand();
	private CarHitBallCommand carHitBallCommand = new CarHitBallCommand();
	private SwitchStrategyCommand switchStrategyCommand = new SwitchStrategyCommand();
	private QuitCommand quitCommand = new QuitCommand();
	private TickCommand tickCommand = new TickCommand();
	private AboutCommand aboutCommand = new AboutCommand();
	private AddMonsterBallCommand addMonsterBallCommand = new AddMonsterBallCommand();
	private AddTimeTicketCommand addTimeTicketCommand = new AddTimeTicketCommand();
	private SwitchSoundCommand switchSoundCommand = new SwitchSoundCommand();
	private PauseCommand pauseCommand = new PauseCommand();
	private DeleteCommand deleteCommand = new DeleteCommand();
	
	//Do all these need to be private??
	private JMenuBar bar;
	private JMenu file;
	private JMenu command;
	private JMenuItem b;
	private JMenuItem k;
	private JMenuItem g;
	//private JMenuItem one;
	//private JMenuItem two;
	//private JMenuItem three;
	private JMenuItem newGame;
	private JMenuItem save;
	private JMenuItem undo;
	private JMenuItem quit;
	private JMenuItem sound;
	private JMenuItem about;
	private JMenuItem hull;
	private JPanel leftPanel;
	private JButton quit1;
	//private JButton tick;
	private JButton delete;
	private JButton pause;
	//private JButton switchStrategy;
	//private JButton carHitSmartBall;
	//private JButton carHitTicket;
	private JMenuItem addSmartBall;
	//private JButton carHitBall;
	//private JButton carOwnsSquare;
	private Scanner in;
	private final int MS_SEC = 20;
	public Game() {
	
		gw = new GameWorld(MS_SEC);//Creates the Observable
		gwProxy = new GameWorldProxy(gw);
		
		mapView = new MapView(gwProxy);//Creates an Observer for the map
		
		scoreView =new ScoreView();//creates Observer for the game state
		
		gw.addObserver(scoreView);//Registers the score observer
		gw.addObserver(mapView);// Registers the map Observer
		//mapView.addMouseListener(this);
		//mapView.addMouseMotionListener(this);
		//mapView.addMouseWheelLustener(this);
		
		// code here to create menus, create Command objects for each command, 
		 // add commands to Command menu, create a control panel for the buttons, 
		 // add buttons to the control panel, add commands to the buttons, and 
		 // add control panel, MapView panel, and ScoreView panel to the frame 		
		buildGUI();
		play();	
	}


	private void play() {
		/*
		 * Notifys Observers that the game has started and enters a while look asking for commands
		 */
		 timer = new Timer(MS_SEC, this);
		  //((Object) timer.schedule(new Sweeper(), 0, 10000);
		 
		 setTimer(timer);
		 timer.start();
		
		gw.notifyObservers();
		
		/*
		do {
			inputLetter = getCommand();
			exit = controller(inputLetter);
		} while (exit != 1);
		System.out.println("Goodbye :)");
		System.exit(0);
		*/
	}
	public void setTimer(Timer timer) {
		// TODO Auto-generated method stub
		this.timer=timer;
	}

	public Timer getTimer()
	{
		return timer;
	}
	private char getCommand() {
		
		// * Keeps asking for a command and initiates the comand
		 
		
		in = new Scanner(System.in);
		while (((input = in.nextLine()).length() > 1)) {
			System.out.print("Give me a 1 command   ");
		}
		inputLetter = input.charAt(0);
		return inputLetter;
	}

/*
	private int controller(char inputLetter) {
		
		 Like a controller it pushes the button that is requested
		 
		switch (inputLetter) {
		case 'n':
			;
			gw.setHeading(0);
			break;
		case 's':
			;
			gw.setHeading(180);// Fixed
			break;
		case 'e':
			;
			gw.setHeading(90);// Fixed
			break;
		case 'w':
			;
			gw.setHeading(270);// Fixed
			break;
		case 'i':
			;
			gw.incSpeed(1);//Fixed
			break;
		case 'l':
			;
			gw.incSpeed(-1);//Fixed
			break;
		case 'b':
			;
			gw.addMonsterBall();//Fixed
			break;
		case 'k':
			;
			gw.addTimeTicket();//Fixed
			break;
		case 'g':
			;
			// Own group of tiles(random)
			gw.iOwnTheseTiles();// Need to fix the lvls
			break;
		case '1':
			;
			// Car has collided with MonsterBall
			gw.crashedIntoMonsterBall();
			break;
		case '2':
			;
			// Car has driven over a new quare and owns it
			gw.iOwnThisOneTile();
			break;
		case '3':
			;
			// Car has hit a Time Ticket
			gw.carHitATimeTicket();
			break;
		case 't':
			;
			gw.tick(MS_SEC);
			break;
		case 'd':
			;
			gw.currentState();
			break;
			
	//	case 'm':;
			// Should print out the toStrings for all objects in game
		//	gw.printObjects();
		//	break;
			
		case 'q':
			;
			System.out.println("Are you sure you want to quit? y/n");
			inputLetter = getCommand();
			if (inputLetter == 'y') {
				exit = 1;
			} else {
				System.out.println("Then lets keep playing");
			}
			break;
		default:
			;
			System.out.println(inputLetter + " command doesn't exist");
			break;
		}
		return exit;
	}
*/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//	timer = new Timer(MS_SEC,tickCommand);
		//	gw.setTimer(timer);
		gw.tick(20);
		//timer.start();
		getPause();
			
		
	}
	public void getPause()
	{
		if(gw.getPause())
		{
		//	gw.setPause(!gw.getPause());
			pause.setText(" Play ");
			
			//timer.stop();
			//command.setEnabled(false);
			tickCommand.setEnabled(false);
			addMonsterBallCommand.setEnabled(false);
			carHitSmartBallCommand.setEnabled(false);
			carOwnsSquareCommand.setEnabled(false);
			carHitTicketCommand.setEnabled(false);
			carHitBallCommand.setEnabled(false);
			switchStrategyCommand.setEnabled(false);
			smartBallCommand.setEnabled(false);
			carOwnsSquaresCommand.setEnabled(false);
			addTimeTicketCommand.setEnabled(false);
			//switchSoundCommand.setEnabled(false);
			carHitBallCommand.setEnabled(false);
			deleteCommand.setEnabled(true);
			hullDrawCommand.setEnabled(false);
			
		}
		else
		{
			//gw.setPause(gw.getPause());
			//timer.start();
			pause.setText("Pause");
			deleteCommand.setEnabled(false);
			command.setEnabled(true);
			tickCommand.setEnabled(true);
			addMonsterBallCommand.setEnabled(true);
			carHitSmartBallCommand.setEnabled(true);
			carOwnsSquareCommand.setEnabled(true);
			carHitTicketCommand.setEnabled(true);
			carHitBallCommand.setEnabled(true);
			switchStrategyCommand.setEnabled(true);
			smartBallCommand.setEnabled(true);
			carOwnsSquaresCommand.setEnabled(true);
			addTimeTicketCommand.setEnabled(true);
		//	switchSoundCommand.setEnabled(true);
			carHitBallCommand.setEnabled(true);
			hullDrawCommand.setEnabled(true);
		}
		this.repaint();
	}
	public void buildGUI()
	{
		/*
		 * Builds a part of the GUI
		 */
		tickCommand.setTarget(gw);
		addMonsterBallCommand.setTarget(gw);
		carHitSmartBallCommand.setTarget(gw);
		carOwnsSquareCommand.setTarget(gw);
		carHitTicketCommand.setTarget(gw);
		carHitBallCommand.setTarget(gw);
		switchStrategyCommand.setTarget(gw);
		smartBallCommand.setTarget(gw);
		carOwnsSquaresCommand.setTarget(gw);
		addTimeTicketCommand.setTarget(gw);
		switchSoundCommand.setTarget(gw);
		pauseCommand.setTarget(gw);
		deleteCommand.setTarget(gw);
		hullDrawCommand.setTarget(gw);
		
		setTitle("My Xonix Game");
		setSize(650,618);
		setLocation(300,0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());		
		JPanel mapPanel = mapView;
		int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap iMap = mapPanel.getInputMap(mapName);		
		ActionMap myAction =mapPanel.getActionMap();	
		//Have to give the whole frame focus so when the user goes to a diffent screen the commands dont get pushed
		UpCommand north = UpCommand.getInstance();
		north.setTarget(gw);
		LeftCommand left = LeftCommand.getInstance();
		left.setTarget(gw);
		RightCommand right =RightCommand.getInstance();
		right.SetTarget(gw);
		DownCommand south =DownCommand.getInstance();
		south.SetTarget(gw);
		IncreaseSpeedCommand incSpeed = IncreaseSpeedCommand.getInstance();
		incSpeed.setTarget(gw);
		DecreaseSpeedCommand decSpeed =DecreaseSpeedCommand.getInstance();
		decSpeed.setTarget(gw);
		//Doesnt Need to be Proxy
		SwitchStrategyCommand switchStrategy =SwitchStrategyCommand.getInstance();
		switchStrategy.setTarget(gw);	
		KeyStroke upKey =KeyStroke.getKeyStroke("UP");
		KeyStroke downKey =KeyStroke.getKeyStroke("DOWN");
		KeyStroke leftKey =KeyStroke.getKeyStroke("LEFT");
		KeyStroke rightKey =KeyStroke.getKeyStroke("RIGHT");
		KeyStroke iKey =KeyStroke.getKeyStroke('i');
		KeyStroke lKey = KeyStroke.getKeyStroke('l');
		KeyStroke spaceKey =KeyStroke.getKeyStroke("SPACE");
		//UP
		iMap.put(upKey,'w');
		myAction.put('w',north);
		//DOwn
		iMap.put(downKey,'s');
		myAction.put('s', south);
		//LEFT
		iMap.put(leftKey,'a');
		myAction.put('a', left);
		//RIGHT
		iMap.put(rightKey,'d');
		myAction.put('d', right);
		//Increase Speed
		iMap.put(iKey,'i');
		myAction.put('i',incSpeed);
		//Decrease Speed
		iMap.put(lKey,'l');
		myAction.put('l',decSpeed);
		//Space
		iMap.put(spaceKey, ' ');
		myAction.put(' ', switchStrategy);
		this.requestFocus();
		myMenu();
	}
	public void myMenu()
	{		
		/*
		 * Builds the top pary of the GUI. File,edit, etc..
		 */
		bar = new JMenuBar();
		file = new JMenu("File");
		bar.add(file);
		command = new JMenu("Command");
		bar.add(command);
		b = new JMenuItem();
		command.add(b);
		b.setAction(addMonsterBallCommand);
		b.setText("b");
		
		k = new JMenuItem("k");
		k.setAction(addTimeTicketCommand);
		k.setText("k");
		command.add(k);
		
		hull =new JMenuItem();
		hull.setAction(hullDrawCommand);
		hull.setText("Curve");
		command.add(hull);
		
		
		g = new JMenuItem();
		command.add(g);
		g.setAction(carOwnsSquaresCommand);
		g.setText("g");
		
		addSmartBall = new JMenuItem();
		command.add(addSmartBall);
		addSmartBall.setAction(smartBallCommand);
		addSmartBall.setText("Add Smart Ball");
		
		//one = new JMenuItem();// Collided with Monster Ball
		//command.add(one);
		//one.setAction(carHitBallCommand);
		//one.setText("1");
		
		//two = new JMenuItem();// I Own this one tile
		//command.add(two);
		//two.setAction(carOwnsSquareCommand);
		//two.setText("2");
		//three = new JMenuItem();// I crashed into a time ticket
		//command.add(three);
		//three.setAction(carHitTicketCommand);
		//three.setText("3");
		newGame = new JMenuItem("New");
		file.add(newGame);
		save = new JMenuItem("Save");// These will be implemented next Time
		file.add(save);
		undo = new JMenuItem("Undo");
		file.add(undo);
		sound = new JCheckBoxMenuItem("Sound", false);
		file.add(sound);
		sound.addActionListener(this);
		sound.setAction(switchSoundCommand);
		about = new JMenuItem("About");
		about.addActionListener(this);
		about.setAction(aboutCommand);
		file.add(about);
		quit = new JMenuItem("Quit");
		quit.addActionListener(this);
		file.add(quit);
		quit.setAction(quitCommand);
		setJMenuBar(bar);
		setJMenuBar(bar);
		this.add(scoreView, BorderLayout.NORTH);
		add(mapView,BorderLayout.CENTER);
		leftPanel = new JPanel();
		leftPanel.setBorder(new TitledBorder("Commands:"));
		
		//addSmartBall = new JButton();
		//leftPanel.add(addSmartBall);		
		//addSmartBall.setAction(smartBallCommand);
		//addSmartBall.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		//addSmartBall.setText("Add Smart Bomb");
		//carHitBall = new JButton();
		//carHitBall.setAction(carHitBallCommand);
		//carHitBall.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		//carHitBall.setText("Car Hit Ball");
		//leftPanel.add(carHitBall);

		pause = new JButton();
		pause.setAction(pauseCommand);
		pause.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		pause.setText("Pause");
		leftPanel.add(pause);
		delete = new JButton();
		delete.setAction(deleteCommand);
		delete.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
		delete.setText("Delete");
		leftPanel.add(delete);
		//carOwnsSquare = new JButton("Car Owns Square");
		//leftPanel.add(carOwnsSquare);
		//carOwnsSquare.setAction(carOwnsSquareCommand);
		//carOwnsSquare.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		//carHitTicket = new JButton("Car Hit Ticket");
		//carHitTicket.setAction(carHitTicketCommand);
		//carHitTicket.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		//leftPanel.add(carHitTicket);
		//carHitSmartBall = new JButton();
		//leftPanel.add(carHitSmartBall);
		//carHitSmartBall.setAction(carHitSmartBallCommand);
		//carHitSmartBall.setText("Car Hit Smart Bomb");
		//carHitSmartBall.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		//switchStrategy = new JButton("Switch Strategy");
		//leftPanel.add(switchStrategy);
		//switchStrategy.setAction(switchStrategyCommand);
		
		//tick = new JButton("Tick");
		//leftPanel.add(tick);
	//	tick.setAction(tickCommand);
		//tick.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		quit1 = new JButton("Quit");
		leftPanel.add(quit1);
		quit1.setAction(quitCommand);
		quit1.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		leftPanel.setLayout(new GridLayout(25, 1));
		add(leftPanel, BorderLayout.WEST);
		setVisible(true);
	}


	

}

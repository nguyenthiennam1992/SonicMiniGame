package a4;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;//Random

import javax.swing.Action;
import javax.swing.Timer;
public class GameWorld implements IObservable,IGameWorld {
	//private ArrayList<GameObject> objects;
	private ObjectCollection objects=new ObjectCollection();
	//Double check if we can allow that the gameowlrd knows how the obersvers are being held
	private ArrayList<IObserver> observers = new ArrayList<IObserver>();
	private Iterator itr ;
	private Timer timer;
	private GameObject object;
	private TimeTicket timeTicket;
	private Random rand = new Random();
	private final int NUMBEROFTOTALTILES = 10000;
	private int numberOfOwnedTiles;
	private int numberOfBalls=0;
	private int numberOfSmartBalls=0;
	private int numberOfLives;
	private static int lvl;
	private int time;
	private final int initialTime = 1000;//Seconds*10
	private double timeCounter =0;
	private Car player;
	private double score;
	private int numberOfTilesNeededToWin;
	private int minimum = 5000;
	private Boolean sound = false;
	private Boolean timerBoolean;
    private Boolean paused =false;
    private boolean prevSound;
	private boolean collided=false;
	private Sound smartBallCollision, monsterBallCollision, timeTicketCollision,backgroundMusic;
	private int sweeperTime =0;
	private boolean drawCurve = true;
    // Code here to hold and manipulate world objects
	// and related game state data
public GameWorld(double mS_SEC2) {
		/*Creates a new gameworld with the defaults it needs to begin
		 * 
		 */
		
		numberOfTilesNeededToWin = minimum + (lvl * 1000);	
		// Add balls These should be commands
     	smartBallCollision= new Sound("boom_SmartBall.wav");	
		
		monsterBallCollision= new Sound("crashMonsterBall.wav");	
		timeTicketCollision= new Sound("bloop_TimeTicket.wav");		
		backgroundMusic = new Sound("ghost.wav");
		
		//Giving error??
		numberOfBalls =0;		
		//player = new Car();
		//objects.add(player);
		numberOfLives = 3;
		time = initialTime - (lvl * 2);		
		addSquares();
		player = new Car();
		objects.add(player);	

	}
	public void checkEnd()
	{
		/*Resets the end of a Lvl to a new Lvl
		 * Resets the number of owned tiles and numerOfTilesNeeededToWin
		 */
		if(numberOfLives==0)
		{
			System.out.println("GAME OVER!!");
			System.exit(0);
		}		
		time = initialTime - (lvl * 2);
		//numberOfTilesNeededToWin = minimum + (lvl * 1000);
		notifyObservers();
	   flush();
	}
	private void flush() {
		/*
		 * Flushes out the collection and adds the deafult needs
		 */
		// TODO Auto-generated method stub
		objects=new ObjectCollection();
		numberOfTilesNeededToWin = minimum + (lvl * 1000);	
		addSquares();
		objects.add(player);
		//Add tiles next assignment maybe
	}
	public double getScore() {
		/*
		 * Gets the score of the GameWorld
		 */
		score = ((double) numberOfOwnedTiles / (double) NUMBEROFTOTALTILES) * 100;
		return score;

	}
	public int getTime()
	{
		/*
		 * Gets the time
		 */
	return time;
	}
	public int getNumberOfLives()
	{
		/*
		 * Gets the number of Lives Left
		 */
		return numberOfLives;
	}
	public int getNumberOfTilesNeededToWin()
	{
		/*
		 * Gets the number of Tiles needed to Win
		 */
		return numberOfTilesNeededToWin;
	}
	public void addMonsterBall() {
		/*
		 * Adds a monster ball to the collection and adds a ball to the count
		 */
		numberOfBalls += 1;
		MonsterBall ball = new MonsterBall();
		objects.add(ball );
		
		

		
		System.out.println("Added Monster Ball to the Field");
		notifyObservers();
	}
public void addSweeper()
{
	Sweeper sweep = new Sweeper(drawCurve,time*5);
	//sweep.translate(rand.nextInt(450)+10, rand.nextInt(450)+10);
	sweep.getTranslation().setToTranslation(rand.nextInt(450)+10, rand.nextInt(450)+10);
   // sweep.scale(-.5,.5);
    sweep.getScale().setToScale(-.5, .5);
	objects.add(sweep);
}
	public void addTimeTicket() {
		/*
		 * Adds a time ticket
		 */
		timeTicket = new TimeTicket();
		timeTicket.getTranslation().setToTranslation(rand.nextInt(450)+10, rand.nextInt(450)+10);
		
	    timeTicket.getScale().setToScale(-.5, .5);
		objects.add(timeTicket);
		System.out.println("TIme Ticket added to FIeld");
		notifyObservers();
	}
	public void addSquares()
	{
		
		numberOfOwnedTiles =0;
		FieldSquares tiles =new FieldSquares();
		//add field Squares
		for(double i=2.5;i<= 500;i += (double)tiles.getWidth())
		{
			for(double j =2.5; j<=500;j +=(double)tiles.getHeight())
			{
					if( i==2.5f|| j==2.5f||i==497.5f|| j==497.5f)
					{
					tiles = new FieldSquares();
					tiles.getTranslation().setToTranslation(i, j);
					tiles.getScale().setToScale(-.5, .5);
					//tiles.setX((float)i);
					//tiles.setY((float)j);
					tiles.setOwned(true);
					objects.add(tiles);
					iOwnThisOneTile();
					}
				}
			
		}
		
	}

	public void setHeading(int degree) {
		/*
		 * Sets headings to any gameobject that is Steerable or
		 * That is a smartball
		 */
		if(!isPaused())
		{
		//itr = objects.iterator();
		
		//for (int i = 0; i < objects.getSize(); i++) {
		//	object = (GameObject) itr.next();
		//	if (object instanceof ISteerable) {
		//		ISteerable player = (ISteerable) object;
				// I found the car but now how to set heading
				player.setHeading(degree);
		//	}
		//}
		}
	}

	public void incSpeed(int additionalSpeed) {
		// Should we limit the speed??
		/*
		 * Looks for the player(car) and either increases or decreases depeneding on what is passed in the additionSpeed value
		 
		 itr =objects.iterator();
		for (int i = 0; i < objects.getSize(); i++) {
			object =(GameObject) itr.next();
			if (object instanceof ISteerable) {
				ISteerable player = (ISteerable) object;
				// I found the car but now how to set heading
				
			}
		}
	}
	*/
		if(!isPaused())
		{
		player.setSpeed(additionalSpeed + player.getSpeed());
		if (player.getSpeed() == -1)// I like the catch but double check
		{
			System.out.println("Speed cannot go below 0");
			player.setSpeed(0);

		}
		if(player.getSpeed() >2)
		{
			player.setSpeed(2);
		}
		}
	}

/*
	public void printObjects() {
		
		 * Goes through the collection and prints every object in it
		
		 itr =objects.iterator();
		
		while (itr.hasNext())
		{
				System.out.println(itr.next().toString());
		}
	}
*/
	public boolean isPaused()
	{
		if(paused)
		{
			return true;
		}else {
		return false;
		}
	}
	public void tick(double mS_SEC2) {				
		//Needs a new iterator to start over again	
		collided=false;
		if(!isPaused())
		{
		Iterator itr2 =objects.iterator();
		
		timeCounter = timeCounter + (double)mS_SEC2;
		if((timeCounter %50)==0)
		{		
			time -=1;
			timeCounter =0;	
			sweeperTime +=1;
			
		}	
		 if(sweeperTime ==100)
		 {
			 addSweeper();
			 sweeperTime=0;
		 }
		if (time == 0) {	
			numberOfLives -=1;
			checkEnd();
		} else {
		while(itr2.hasNext()){
			GameObject object =(GameObject)itr2.next();
			if(object instanceof FieldSquares){
				continue;
			}
			if (object instanceof Car ) {				
					//moveableObject.move(((Car) object).getHeight(),MS_SEC);
				//Check before and after
				
				
				findASquare(player.getXTran(),player.getYTran());
				player =(Car) object;	
				player.move(mS_SEC2);
					
			}
			else if(object instanceof Fireball)
			{
				Fireball smartObject = (Fireball) object;
				smartObject.move(smartObject.getHeight(),mS_SEC2,player.getXTran(),player.getYTran());
				
				//((IStrategy) object).apply(moveableObject);
				//moveableObject.move(moveableObject.getHeight(),MS_SEC);
			} else if(object instanceof Sweeper)
			{
				//MoveableObject moveableObject = (MoveableObject) object;
				((MoveableObject) object).move(object.getHeight(),mS_SEC2);
				((Sweeper) object).setlifeTime(((Sweeper)object).getLifeTime()-1);
			//	System.out.println("1 minus");
				int time =((Sweeper) object).getLifeTime();
				if(time <0)
				{
					((Sweeper) object).setDeath(true);
				}
			}

			else if(object instanceof MoveableObject){
				
				MoveableObject moveableObject = (MoveableObject) object;
				moveableObject.move(moveableObject.getHeight(),mS_SEC2);			
				}
		}
			itr2 = objects.iterator();
			while(itr2.hasNext()) {		
				Iterator itr3 =objects.iterator();
				ICollider object = (ICollider) itr2.next();
				while(itr3.hasNext())
				{
					ICollider tmp = (ICollider) itr3.next();
					
					if(tmp != object){
						
						
						/*
						if(object instanceof FieldSquares)
						{
							if(object.collidesWith(tmp))
							{
								if(tmp instanceof Sweeper)
								{
									System.out.println("Sweeper hit a field Square");
								}
							}
							continue;
							*/
							
					
						//else {

						if(object.collidesWith(tmp))
						{
							
						if(object instanceof Sweeper && !(tmp instanceof Sweeper) )
						{
								
								
								
						if (tmp instanceof FieldSquares)
							
						{
							if(((FieldSquares) tmp).isAlmostOwned())
							{
							soClose();
							}
							object.handleCollision(tmp);
						}
						if(tmp instanceof Fireball || tmp instanceof TimeTicket)
						{
							tmp.handleCollision(object);
							
						}
						
						
						
								}
						if(object instanceof MonsterBall )
						{
							
							object.handleCollision(tmp);
							//tmp.handleCollision(object);	
							
						}

						if(tmp instanceof Car)
						{

							
							
						if(object instanceof Fireball || object instanceof Flame)
						{
						//	smartBallCollision.play();
							object.handleCollision(tmp);
							tmp.handleCollision(object);
							carHitSmartBall();
							
							//itr2.remove();
							//((GameObject) object).setDeath(true);
							//collided=true;
						}
						
						if(object instanceof TimeTicket)
						{
							object.handleCollision(tmp);
							carHitATimeTicket();
							//itr2.remove();
							((GameObject) object).setDeath(true);
							
						}

					    	
						if(object instanceof MonsterBall)
						{
							tmp.handleCollision(object);
							
						//itr2.remove();
							//((GameObject) tmp).setDeath(true);
							//collided=true;
							crashedIntoMonsterBall();
						}	
						
						}
				//	} From the fieldSquRWE CHECK
						
						}
						
}
				}
				
				}	
			collectTheDead();//Removes then index gets thrown off!!!!
		}	
		}
		notifyObservers();
			}	
public void soClose()
{
	Iterator itr4 =objects.iterator();

	 while(itr4.hasNext()) 
	{
	GameObject obj =(GameObject) itr4.next();
	//Search the world for a FieldSquare containing the Car’s new location.
	if(obj instanceof FieldSquares)
	{
		FieldSquares field =(FieldSquares) obj;
		
			if(field.isAlmostOwned()==true)
			{
				//field.setOwned(false);
				//field.setAlmostOwned(false);
				itr4.remove();
				itr4=objects.iterator();
			}
		}
	}
	}	
public void findASquare(float newX,float newY)
{
	Iterator itr4 =objects.iterator();
	 Boolean exists =false;
	 while(itr4.hasNext()) 
	{
	GameObject obj =(GameObject) itr4.next();
	//Search the world for a FieldSquare containing the Car’s new location.
	if(obj instanceof FieldSquares)
	{
		FieldSquares field =(FieldSquares) obj;
			if(field.contains(newX, newY))
			{
		//	if(!field.isAlmostOwned()|| !field.isOwned())
				if(!field.isAlmostOwned())
			{
		//Doesnt nothing because there already exists a Square that contains
		//that location	
		exists =true;	
			}
				if(field.isOwned()== true)
				{
				Iterator itr5 =objects.iterator();
				while(itr5.hasNext())
				{
					GameObject obj2 =(GameObject) itr5.next();
					if(obj2 instanceof FieldSquares)
					{
						FieldSquares nowOwned =(FieldSquares) obj2;
						if(nowOwned.isAlmostOwned()==true)
						{
							nowOwned.setOwned(true);
							nowOwned.setAlmostOwned(false);
							iOwnThisOneTile();
						}
					}
				}
				}
			}
	}	
	}
	 if(exists==false)
	 {
	 FieldSquares tiles =new FieldSquares();
		//add field Squares
		for(double i=7.5;i<= 495;i += (double)tiles.getWidth())
		{
			for(double j =7.5; j<=495;j +=(double)tiles.getHeight())
			{
					if(Math.abs(newX-i)<=2.5 && Math.abs(newY-j)<=2.5)
					{	
						tiles = new FieldSquares();
						tiles.getTranslation().setToTranslation(i, j);
						tiles.getScale().setToScale(-.5,.5);
						//tiles.setX((float)(i));
						//tiles.setY((float)(j));				
					tiles.setAlmostOwned(true);
					objects.add(tiles);					
					}							
				}	
		}	
	 }	
	notifyObservers();
}
	public void collectTheDead()
	{
		 Iterator itr4 =objects.iterator();	 
		 while(itr4.hasNext()) 
		{
		GameObject obj =(GameObject) itr4.next();
		if(obj.getDeath() ==true)
		{
			itr4.remove();	
			itr4=objects.iterator();
		}	
		 }
	}
	public void currentState() {
		/*Display current lvl, number of lives left, current clock countdown
		 * value, current score in % of squares, minimum score required to beat
		 *the lvl	 
		 */
		System.out.format("Current level %d  Number of Lives Left %d Clock  %d  Score %.2f percent   Score needed %d ",
						lvl, numberOfLives, time, getScore(),
						(numberOfTilesNeededToWin / 100));
		System.out.println("%");
	}
	public void iOwnTheseTiles() {
		/*
		 *This random number cant exceed the number of tiles left..well i would
		 *think
		 */
		int r = Math.abs(rand.nextInt() % numberOfOwnedTiles) + 1;
		System.out.println("random number of tiles " + r);
		this.numberOfOwnedTiles += r;
		if (numberOfOwnedTiles >= numberOfTilesNeededToWin) {
			System.out.println("Congratualtion ,Next Lvl");
			lvl += 1;
			// Next lvl call
			
			checkEnd(); // new game or gameworld??? new game does create a new
						// gameworld.. double check
		} 
		// random numbers to see how many newly owned
		// updateOwnedTiles();
		notifyObservers();
	}

	// Could make a method that checks if won
	public void iOwnThisOneTile() {
		numberOfOwnedTiles += 1;
		if (numberOfOwnedTiles >= numberOfTilesNeededToWin) {
			lvl += 1;
			System.out.println("Congratz...Next Lvl");
			// Next lvl call			
			//new GameWorld(lvl, NUMBEROFTOTALTILES, NUMBEROFTOTALTILES);
		System.out.println("I own " + numberOfOwnedTiles);
		notifyObservers();
	}
		notifyObservers();
	}
	public void carHitATimeTicket() {
		/*
		 * Looks for a time ticket to hit
		 */
		
		 itr =objects.iterator();
		for (int i = 0; i < objects.getSize(); i++) {
			object=(GameObject) itr.next();
			if (object instanceof TimeTicket) {
				// I found an existing Ticket
				System.out.println("Grabbed TimeTicket");
				TimeTicket ticket = (TimeTicket) object;
				time += ticket.getTime() - lvl;	
				if(getSound())
				{
				timeTicketCollision.play();
				}
				break;
			}
			if( i == objects.getSize()-1)
			{
				System.out.println("There is no Time Tickets :'(");
			}
		}
		notifyObservers();
	}	
	public void crashedIntoMonsterBall() {
		/*
		 * loses life and car back at initial position
		 */	
		
		if (numberOfBalls != 0) {	
			soClose();
			numberOfLives -= 1;		
			if(getSound())
			{
			monsterBallCollision.play();
			}
			notifyObservers();
		} 
			if (numberOfLives == 0) {
				System.out.println("Game over, Out of lives");
				System.exit(0);
				// redirct to ask if they want to play again
				// Ask if they want to play again
			}		
	}
	public void addSmartBall()
	{
		/*
		 * Adds a smartball to the world and adds to the count
		 */
		numberOfSmartBalls += 1;
		Fireball ball = new Fireball();

		ball.getTranslation().setToTranslation(rand.nextInt(450)+10, rand.nextInt(450)+10);
		ball.getRotation().setToRotation(45);
	    ball.getScale().setToScale(1,-1);
		
		objects.add(ball);	
		notifyObservers();
	}
	public void carHitSmartBall()
	{
		/*
		 * Find the firstsmartball and removes it from the collection, takes a life and resets the Cars Location
		 */
		soClose();
		if(getSound())
		{
		smartBallCollision.play();
		}
		if (numberOfSmartBalls == 0) {
			//SSystem.out.println("Theres no Smart Bombs to crash into");
		} else
		{	
		numberOfSmartBalls -=1;
		numberOfLives -= 1;
		if (numberOfLives == 0) {
			System.out.println("Game over, Out of lives");
			System.exit(0);
			// redirct to ask if they want to play again
			// Ask if they want to play again		
		}
		notifyObservers();	
		}
		/*
		else {			
			for (int i = 0; i < objects.getSize(); i++) {
				object = (GameObject) itr.next();
				if (object instanceof Car) {
					Car car = (Car) object;
					car.setY();
					car.setX();
					car.setHeading(0);
					car.setSpeed(0);
				}
				if (object instanceof SmartBall) {
					//SmartBall ball = (SmartBall) object;
					// I found the car but now how to set heading
				//	itr.remove();				
					break;
				}
*/	
	}
	/* Dont need for a3
	public void changeSmartBallStrategy()
	{
		//Either chasing or normal or maybe cirlces and back and forth-- next time.. go to sleep
		itr = objects.iterator();
		if (numberOfSmartBalls == 0) {
			System.out.println("Theres no SmartBalls to switch Strategy");
		} else {			
			for (int i = 0; i < objects.getSize(); i++) {
				object = (GameObject) itr.next();
				if (object instanceof SmartBall) {
					SmartBall ball = (SmartBall) object;
					curStrat = ball.getStrategy();
					if(curStrat == normalStrategy)
					{
						ball.setStrategy(chasePlayer);
					}
					if( curStrat == chasePlayer)
					{
						ball.setStrategy(normalStrategy);
					}
				}
			}
		}		
		notifyObservers();
	}
	*/
	@Override
	public void addObserver(IObserver observer) {
		// TODO Auto-generated method stub
		//Add to the list of observers , check if needs to be in a collection
		/*
		 * Adds and observer to the list of observers to notify
		 */
		observers.add(observer);		
	}
	@Override
	public void notifyObservers() {
		/*
		 * Notifys the observers that something in the world has changed
		 */
		// TODO Auto-generated method stub
		GameWorldProxy gwproxy = new GameWorldProxy(this);
		Object object = new Object();
		for(int i =0; i< observers.size(); i++)
		{
			observers.get(i).update(gwproxy, object);;			
		}	
	}
	public Integer getLvl() {
		// TODO Auto-generated method stub
		return lvl;
	}
	public Integer getRequiredScore() {
		// TODO Auto-generated method stub
		return numberOfTilesNeededToWin;
	}
	public boolean getSound() {
		/*
		 * Gets the sound and returns it at a request
		 */
		// TODO Auto-generated method stub
		return sound;
	}
	public void setSound(Boolean sound)
	{
		/*
		 * Sets the sound to either T or F
		 * and notifys the Oberservers that something has changed
		 */
		setPrevSound(sound);
		this.sound=sound;
		if(sound == true)
		{
			backgroundMusic.loop();
		}
		else{
			backgroundMusic.stop();
		}
		notifyObservers();
	}
public ObjectCollection getCollection()
{
	//Gets the objectCollection!!!!!!!!!
 return objects;
}
public Iterator getIterator()
{
	return itr;	
}
public boolean getPause()
{
	return isPaused();
}
public void togglePause(GameWorld gw)
{	
	System.out.println("1st"+paused);
	if(paused)
	{
		setPause(false);
			
		
		//timer.start();
		itr =objects.iterator();
		while(itr.hasNext())
		{
			GameObject tmp = (GameObject) itr.next();
			if(tmp instanceof ISelectable)
			{
			ISelectable tmp2 = (ISelectable) tmp;
			tmp2.setSelected(false);
			tmp.setDeath(false);
			}		
		}
		System.out.println(paused);
		//Play the background sound
	}else {
		setPause(true);
		
		//paused =true;
		// timer.stop();
		
		System.out.println(paused);		
		//Stop the bkground sound
	}	
	if(getSound() == true && paused != true)
	{
		backgroundMusic.loop();
	}
	if(paused ==true)
	{
		backgroundMusic.stop();
	}
	
	
}
public boolean getPrevSound() {
	// TODO Auto-generated method stub	
	return prevSound;
}
public void setPrevSound(boolean prev)
{
prevSound =prev;
}
public void setPause(boolean paused2) {
	// TODO Auto-generated method stub
	paused = paused2;
}
public void addSweeper(Float transPoint) {
	// TODO Auto-generated method stub
	Sweeper sweep = new Sweeper(transPoint,drawCurve,time*5);
	sweep.getTranslation().setToTranslation(transPoint.x, transPoint.y);
	
    sweep.getScale().setToScale(-.5,.5);
    
	objects.add(sweep);
}
public void drawSweep()
{
	 itr =objects.iterator();
		for (int i = 0; i < objects.getSize(); i++) {
			object=(GameObject) itr.next();
			if (object instanceof Sweeper) {
				// I found an existing Ticket
				
				((Sweeper) object).setDraw(drawCurve);
			}
		}
}
public boolean getDraw()
{
	return drawCurve;
	
}
public void setDrawHull()
{
	drawCurve = !drawCurve;
	drawSweep();
}
}
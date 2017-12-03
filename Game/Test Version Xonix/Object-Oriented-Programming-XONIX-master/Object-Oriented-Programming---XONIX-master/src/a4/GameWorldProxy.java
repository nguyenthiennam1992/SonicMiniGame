package a4;

import java.awt.geom.Point2D.Float;


public class GameWorldProxy implements IObservable, IGameWorld { 
	 // code here to accept and hold a GameWorld, provide implementations 
	 // of all the public methods in a GameWorld, forward allowed 
	 // calls to the actual GameWorld, and reject calls to methods 
	 // which the outside should not be able to access in the GameWorld. 
	private GameWorld realGameWorld;
	public GameWorldProxy(GameWorld gw)
	{
		this.realGameWorld = gw;
	}
	@Override
	public void addObserver(IObserver observer) {
		// TODO Auto-generated method stub
		//WHATS IN HERE??
		
	}
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		//Check what should be in here
	}
	public double getScore()
	{
	return realGameWorld.getScore();
	}


	public int getTime() {
		// TODO Auto-generated method stub
		return realGameWorld.getTime();
	}


	public Integer getLvl() {
		// TODO Auto-generated method stub
		return realGameWorld.getLvl();
	}


	public int getNumberOfLives() {
		// TODO Auto-generated method stub
		return realGameWorld.getNumberOfLives();
	}
	public Integer getRequiredScore() {
		// TODO Auto-generated method stub
		return realGameWorld.getRequiredScore();
	}
	public boolean getSound() {
		// TODO Auto-generated method stub
		return realGameWorld.getSound();
	}
	public void tick(double MS_SEC)
	{
		//Does nada
	}


	public void changeSmartBallStrategy() {
		// TODO Auto-generated method stub
		
	}

	public void addMonsterBall() {
		// TODO Auto-generated method stub
		
	}
	public void iOwnTheseTiles() {
		// TODO Auto-generated method stub
			}
	public void iOwnThisOneTile() {
		// TODO Auto-generated method stub
		
	}
	public void carHitATimeTicket() {
		// TODO Auto-generated method stub
	
	}
	public void carHitSmartBall() {
		// TODO Auto-generated method stub
		
	}
	public void crashedIntoMonsterBall() {
		// TODO Auto-generated method stub
		
	}
	public void addTimeTicket() {
		// TODO Auto-generated method stub
		
	}
	public void addSmartBall() {
		// TODO Auto-generated method stub
		//Left blank or should i toss it out?
	}
	public void setSound(Boolean sound) {
		// TODO Auto-generated method stub
		realGameWorld.setSound(sound);
	}
	@Override
	public void incSpeed(int additionalSpeed) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ObjectCollection getCollection() {
		// TODO Auto-generated method stub
		return realGameWorld.getCollection();
	}
	public boolean isPaused() {
		// TODO Auto-generated method stub
		return realGameWorld.isPaused();
	}
	public void addSweeper() {
		// TODO Auto-generated method stub
		
		realGameWorld.addSweeper();
	}
	public void addSweeper(Float transPoint) {
		// TODO Auto-generated method stub
		realGameWorld.addSweeper(transPoint);
	}






	


	} 
	
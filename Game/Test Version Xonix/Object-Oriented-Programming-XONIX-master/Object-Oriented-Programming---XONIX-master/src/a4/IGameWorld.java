package a4;


public interface IGameWorld {
public void addObserver(IObserver observer);
public void notifyObservers();
public double getScore();
public int getTime();
public int getNumberOfLives();

public Integer getLvl() ;
public boolean getSound();
public void setSound(Boolean sound);
public void tick(double MS_SEC);
public void carHitATimeTicket();
public void iOwnTheseTiles();
public void incSpeed(int additionalSpeed);
public void addTimeTicket();
public void addMonsterBall();
public Integer getRequiredScore();
public void carHitSmartBall();
public void addSmartBall();
public void crashedIntoMonsterBall();
public ObjectCollection getCollection();
}

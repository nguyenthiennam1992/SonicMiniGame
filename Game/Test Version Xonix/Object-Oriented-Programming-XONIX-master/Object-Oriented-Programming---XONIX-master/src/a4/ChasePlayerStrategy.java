package a4;


public class ChasePlayerStrategy implements IStrategy {
private	GameWorld gw;
//Does it also need to be in gwProxy??
private int newHeading;
private double theta;
//Need to pass in gameworld
	public ChasePlayerStrategy()
	{
		this.gw=gw;
	}
	public void apply(SmartBall ball,float playerX,float playerY) 
	{
		// TODO Auto-generated method stub
		//Chase player
		//System.out.println("Balls x : " +ball.getLocation().x);
		//System.out.println("Balls y : "+ ball.getLocation().y);
		 theta = Math.toDegrees(Math.atan2(playerY-ball.getYTran(),playerX-ball.getXTran()));
		//System.out.println("Theta is ="+ theta);
		theta = (90-theta);
		newHeading =(int)theta;
		//System.out.println("New theta is ="+ theta);
		ball.setHeading(newHeading);
		//System.out.println("new heading =" +newHeading);
		//changeSmartBallHeadingTowardsCar();	
			}

}
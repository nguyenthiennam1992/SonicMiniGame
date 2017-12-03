import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alex Backer on 12/2/2016.
 */
public class GameWorld
{
    private Iterator itr ;
    private ArrayList objectsList;
    private int numberOfOwnedTiles;
    private int numberOfTilesNeededToWin;
    private int numberOfBalls;
    private int numberOfLives;
    private MonsterBall mBall;
    private TimeTicket timeTicket;
    private Random rand;
    private static int level;
    private static int playTime;
    private Car player;
    private double score;
    private int minimum;
    private  int passtime;
    //private  Calendar b1 ;

    Calendar calendar = Calendar.getInstance();
   // int second = calendar.get(Calendar.SECOND);
    int minute = calendar.get(Calendar.MINUTE);
    public GameWorld()
    {
        rand = new Random();
        minimum = 5000;
        // tru 396 o ngoai cung 100+99+99+98
        numberOfOwnedTiles = 396;
        numberOfTilesNeededToWin = minimum + level * 1000;
        objectsList = new ArrayList();
        player =  Car.getInstance();
        objectsList.add(player);
        numberOfLives = 3;
        // tang do kho dan len
     //   playTime = 100 - level * 2;
        playTime = minute*60+ calendar.get(Calendar.SECOND);
       // playTime =  b1.getTime().getSeconds();

        passtime = 0;

    }
    // di huong nao
    public void setHeading(int degree)
    {
        for(int i = 0; i < objectsList.size(); i++)
            if(objectsList.get(i) instanceof ISteerable)
            {
                ISteerable player = (ISteerable) objectsList.get(i);
                player.setHeading(degree);
            }

    }
    public double getScore()
    {
        score = ((double)numberOfOwnedTiles / 10000D) * 100D;
        return score;
    }

    public void addMonsterBalls()
    {

        numberOfBalls++;
        mBall = new MonsterBall();
        objectsList.add(mBall);
    }

    public void addTimeTicket()
    {
        timeTicket = new TimeTicket();
        objectsList.add(timeTicket);
    }



    public void incSpeed(int additionalSpeed)
    {
        for(int i = 0; i < objectsList.size(); i++)
            if(objectsList.get(i) instanceof ISteerable)
            {
                ISteerable player = (ISteerable) objectsList.get(i);
                player.setSpeed(additionalSpeed + player.getSpeed());
                if(player.getSpeed() == -1)
                {
                    System.out.println("Khong the di xuong duoi 0");
                    player.setSpeed(0);
                }
            }

    }
    // hien thi thong so hien tai cua xe
    public String toString(String s)
    {
        for(int i = 0; i < objectsList.size(); i++)
            if(objectsList.get(i) instanceof GameObject)
            {
                GameObject object = (GameObject) objectsList.get(i);
                s = object.toString();
                System.out.println(s);
            }

        return s;
    }
    public void addMonsterBallsTimer(int _passtime)
    {


        for(int i = 0; i < _passtime;i++)
        {
            mBall = new MonsterBall();
            objectsList.add(mBall);
            numberOfBalls++;
        }
//
    }
    public void tickTimer()
    {

        passtime++;
        //int second = calendar.get(Calendar.SECOND);
        //int minute  = calendar.get(Calendar.SECOND);
        Calendar calendar1 = Calendar.getInstance();
        int current =  calendar1.get(Calendar.MINUTE)*60 +  calendar1.get(Calendar.SECOND);
        playTime = playTime - (current-playTime);
        System.out.println(current);
        addMonsterBallsTimer(current-playTime);
        if(playTime == 0 || playTime < 0)
        {
            System.out.println("GAME OVER");
            System.exit(0);
        } else
        {
            for(int i = 0; i < objectsList.size(); i++)
                if(objectsList.get(i) instanceof IMoveable)
                {
                    IMoveable object = (IMoveable) objectsList.get(i);

                    if (object instanceof Car ) {

                        player =(Car) object;
                        player.move();

                    }
                    else if(object instanceof MonsterBall)
                    {
                        MonsterBall monsterBall = (MonsterBall) object;
                        monsterBall.move();
                    }

                    //System.out.print("move");
                    if(!(objectsList.get(i) instanceof Car) && player.getLocation() == object.getLocation()) {
                        MonsterBallCollied();
                    }
                }

        }
    }

    // so huu mot o
    public void iOwnThisTile()
    {
        numberOfOwnedTiles++;
        if(numberOfOwnedTiles >= numberOfTilesNeededToWin)
        {
            level++;
            System.out.println("Congratulation... go to the Next Lvl");
            //new Game();
        }
        System.out.println((new StringBuilder("Toi so huu ")).append(numberOfOwnedTiles).toString());
    }
// so huu random mot so o nhat dinh
    public void iOwnRandomTiles()
    {
        int r = Math.abs(rand.nextInt() % numberOfOwnedTiles) + 1;
        System.out.println((new StringBuilder("So obat ki la ")).append(r).toString());
        numberOfOwnedTiles += r;
        if(numberOfOwnedTiles >= numberOfTilesNeededToWin)
        {
            System.out.println("Congratualtion ,Next Lvl");
            level++;
            new Game();
        } else
        {
            System.out.println((new StringBuilder("I own ")).append(numberOfOwnedTiles).append("/10000").toString());
        }
    }



    public void playAgain()
    {
        level = 0;
        numberOfLives = 3;
    }

    public void playerCarHitATimeTicket()
    {
        for(int i = 0; i < objectsList.size(); i++)
        {
            if(!(objectsList.get(i) instanceof TimeTicket))
                continue;
            TimeTicket object = (TimeTicket) objectsList.get(i);
            playTime += object.getTime() - level;
            objectsList.remove(i);

            if( i == objectsList.size()-1)
            {
                System.out.println("Khong con time ticket :'(");
            }
            break;
        }

    }

    public void MonsterBallCollied()
    {
        if(numberOfBalls == 0)
        {
            System.out.println("Theres no balls to crash into");
        } else
        {
            for(int i = 0; i < objectsList.size(); i++)
            {
                if(objectsList.get(i) instanceof MonsterBall)
                {
                    MonsterBall ball = (MonsterBall) objectsList.get(i);
                    ball.setRandColor();
                }
                if(objectsList.get(i) instanceof Car)
                {
                    Car car = (Car) objectsList.get(i);
                    car.setLocation();
                    car.setHeading(0);
                }
            }

            numberOfLives--;
            if(numberOfLives == 0)
            {
                System.out.println("Game over, Khong con mang nao ca");
                System.exit(0);
            }
        }
    }
    // trang thai hien tai
    public void playerCurrentState()
    {
        System.out.format("Current level %d  Number of Lives Left %d Clock  %d  Score %.2f percent   Score needed %d ", new Object[] {
                Integer.valueOf(level), Integer.valueOf(numberOfLives), Integer.valueOf(playTime), Double.valueOf(getScore()), Integer.valueOf(numberOfTilesNeededToWin / 100)
        });
        System.out.println("%");
    }

}
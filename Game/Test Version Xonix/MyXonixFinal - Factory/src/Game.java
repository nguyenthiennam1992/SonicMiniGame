import java.util.Scanner;

/**
 * Created by Alex Backer on 12/2/2016.
 */
public class Game
{

    private GameWorld gw;
    private String input;
    private char inputLetter;
    private int exit;
    public Game()
    {
        exit = 0;
        gw = new GameWorld();
        play();
    }

    private void play()
    {
        do
        {
            inputLetter = getCommand();
            exit = controller(inputLetter);
        } while(exit != 1);
        System.out.println("Goodbye :)");
        System.exit(0);
    }

    private char getCommand()
    {
        System.out.print("Cho mot lenh ");
        Scanner in = new Scanner(System.in);
        for(; (input = in.nextLine()).length() > 1; System.out.print("Cho toi mot lenh  "));
        inputLetter = input.charAt(0);
        return inputLetter;
    }

    private int controller(char inputLetter)
    {
        switch(inputLetter)
        {
            case 'n':
                ;
                gw.setHeading(0);
                System.out.println("Ban dang nhin ve huong Bac");
                break;
            case 's':
                ;
                gw.setHeading(180);
                System.out.println("Ban dang nhin ve huong Nam");
                break;
            case 'e':
                ;
                gw.setHeading(90);
                System.out.println("Ban dang nhin ve huong Dong");
                break;
            case 'w':
                ;
                gw.setHeading(270);
                System.out.println("Ban dang nhin ve huong Tay");
                break;
            case 'i':
                ;
                gw.incSpeed(1);
                System.out.println("Tang toc!");
                break;
            case 'l':
                ;
                gw.incSpeed(-1);
                System.out.println("Giam toc do");
                break;
            case 'b':
                ;
                gw.addMonsterBalls();
                break;
            case 'k':
                ;
                gw.addTimeTicket();
                break;
            case 'g':
                gw.iOwnRandomTiles();
                ;
            case '1': // '1'
                gw.MonsterBallCollied();
                break;

            case '2': // '2'
                gw.iOwnThisTile();
                break;

            case '3': // '3'
                gw.playerCarHitATimeTicket();
                break;

            case 't': // 't'
                gw.tickTimer();
                break;

            case 'd': // 'd'
                gw.playerCurrentState();
                break;

            case 'm': // 'm'
                gw.toString(input);
                break;

            case 'q': // 'q'
                System.out.println("Ban co chac muon nghi? y/n");
                inputLetter = getCommand();
                if(inputLetter == 'y')
                    exit = 1;
                else
                    System.out.println("Choi tiep nao");
                break;

            default:
                System.out.println((new StringBuilder(String.valueOf(inputLetter))).append(" Khong ton tai lenh nay ").toString());
                System.out.println("Nguoi choi chi nen n/s/w/e cho 4 huong" +'\n' +
                "i/l de tang va giam toc do" + '\n' + "m de mo ban do" + '\n'+ "b de them mot monster ball" + '\n'+
                        "1 de crash vao monster ball"+'\n'  );
                break;
        }
        return exit;
    }

}
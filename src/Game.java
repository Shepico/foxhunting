import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private Fox fox;
    private Flag flag;
    private int foxCount;
    private int stepCount;
    private int foxMax;
    private Timer timerGame;
    private int durationGame;
    private double rating;

    public GameState getState() {
        return state;
    }

    private GameState state;
    //MatrixGame foxMap;

    public Game(int cols, int rows, int foxs){
        Ranges.setSize(new Coord(cols, rows));
        fox = new Fox(foxs);
        flag = new Flag();
        foxMax = foxs;
        timerGame = new Timer();

        //foxCount = foxs;
        //stepCount = 0;
    }

    public Box getBox (Coord coord){
        //return Box.values()[(coord.getX() + coord.getY()) % Box.values().length];
        //return fox.get(coord);
        if (flag.get(coord) == Box.open) {
            return fox.get(coord);
        } else {
            return flag.get(coord);
        }
    }

    public void start(){
        fox.start();
        flag.start();
        state = GameState.PLAYED;
        foxCount = foxMax;
        stepCount = 0;
        rating = 0;
        durationGame=0;
        timerGame.schedule(new TimerTask() {

            @Override
            public void run() {
                durationGame++;
            }
        },0,1000);

        //foxMap = new MatrixGame(Box.empty);
        //foxMap.set(new Coord(0,1),Box.fox);
    }

    public void pressLeftButton(Coord coord) {
        if (state != GameState.WINNER) {
            openBox(coord);
        }
        //flag.setOpenedToBox(coord);
    }

    private void openBox(Coord coord) {
        switch (flag.get(coord)) {
            case open : return;
            case flag : return;
            case empty : {
                switch (fox.get(coord)) {
                    case fox : {
                        foxCount--;
                        stepCount++;
                        flag.setOpenedToBox(coord);
                        checkWinner();
                        return;
                    }
                    default : {
                        stepCount++;
                        flag.setOpenedToBox(coord);
                        return;
                    }

                }
            }

        }
    }

    public void pressRightButton(Coord coord) {
        if (state != GameState.WINNER) {
            flag.toggleFlagToBox(coord);
        }
    }

    private void checkWinner() {
        if (foxCount == 0) {
            state = GameState.WINNER;
            timerGame.cancel();
            rating = stepCount / durationGame * foxMax;
        }
    }

    public double getRating(){
        return rating;
    }

    public int getTimer(){
        return durationGame;
    }

    public int getFoxs() {
        return foxCount;
    }

    public int getStep() {
        return stepCount;
    }
}

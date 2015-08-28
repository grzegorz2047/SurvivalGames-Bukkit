package pl.grzegorz2047.survivalgames;

import pl.grzegorz2047.survivalgames.runnable.Counter;
import pl.grzegorz2047.survivalgames.spawn.Spawn;
import pl.grzegorz2047.survivalgames.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class Game {

    SurvivalGames sg;
    Spawn spawn;

    public Game(SurvivalGames sg){
        this.sg = sg;
        this.spawn = new Spawn(sg);
    }

    List<User> players = new ArrayList<User>();

    public Spawn getSpawn() {
        return spawn;
    }

    public enum GameState {WAITING, STARTING, INGAME, RESTARTING};

    private GameState state = GameState.WAITING;

    public GameState getGameState(){
        return this.state;
    }
    public void setGameState(GameState state){
        this.state = state;
    }

    public void start(){
        if(sg.getGame().getGameState().equals(Game.GameState.WAITING)){
            Counter counter = new Counter(sg, 10);
            counter.start();
            sg.getGame().setGameState(Game.GameState.STARTING);
        }
    }
    public boolean isInGame(){
        return state.equals(GameState.INGAME);
    }

}

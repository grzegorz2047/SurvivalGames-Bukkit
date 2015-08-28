package pl.grzegorz2047.survivalgames.votesystem;

import pl.grzegorz2047.survivalgames.Game;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class Vote {

    List<String> voters = new ArrayList<String>();

    SurvivalGames sg;

    public Vote(SurvivalGames sg) {
        this.sg = sg;
    }

    public boolean addVote(User user) {
        if (this.voters.contains(user.getUsername())) {
            return false;
        }
        return this.voters.add(user.getUsername());
    }

    public void resetVote() {
        this.voters.clear();
    }

    public boolean isEnoughToStart() {
        if(sg.getGame().getGameState().equals(Game.GameState.WAITING)){

        }

        return true;
    }

}

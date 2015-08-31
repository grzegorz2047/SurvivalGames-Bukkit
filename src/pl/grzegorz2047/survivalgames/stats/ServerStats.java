package pl.grzegorz2047.survivalgames.stats;

import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.user.User;

/**
 * Created by Grzegorz2047. 31.08.2015.
 */
public class ServerStats {

    private SurvivalGames sg;

    public ServerStats(SurvivalGames sg) {
        this.sg = sg;
    }

    public int getActivePlayers() {
        int activePlayers = 0;
        //MsgManager.debug("Liczba userow "+playerSize);
        for (User u : sg.getGame().getPlayers().values()) {
            if (!u.isSpectator()) {
                //MsgManager.debug("Gracz "+u.getUsername()+" gra ");
                activePlayers++;
            }
        }

        return activePlayers;
    }

    public String getMinMaxPlayers(){
        return "["+this.getActivePlayers()+"/"+sg.getGame().getSpawn().getSpawnPoints().size()+"]";
    }

}

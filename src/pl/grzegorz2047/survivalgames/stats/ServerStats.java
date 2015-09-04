package pl.grzegorz2047.survivalgames.stats;

import org.bukkit.entity.Player;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.user.User;

/**
 * Created by Grzegorz2047. 31.08.2015.
 */
public class ServerStats {

    private SurvivalGames sg;
    private int activePlayers;
    private int specPlayers;

    public ServerStats(SurvivalGames sg) {
        this.sg = sg;
    }

    public void updateStats() {
        int activePlayers = 0;
        int specPlayers = 0;
        //MsgManager.debug("Liczba userow "+playerSize);
        for (User u : sg.getGameManager().getPlayers().values()) {
            if (!u.isSpectator()) {
                //MsgManager.debug("Gracz "+u.getUsername()+" gra ");
                activePlayers++;
            }else{
                specPlayers++;
            }
        }

        this.activePlayers = activePlayers;
        this.specPlayers = specPlayers;
    }
    public int getSpectatingPlayers() {
        return this.specPlayers;
    }

    public String getMinMaxPlayers(boolean isDuringJoin) {
        if(isDuringJoin){
            return "(" + (this.activePlayers+1) + "/" + sg.getGameManager().getSpawnManager().getSpawnPoints().size() + ")";
        }
        return "(" + this.activePlayers + "/" + sg.getGameManager().getSpawnManager().getSpawnPoints().size() + ")";
    }

    public Player getLastActivePlayer() {
        int activePlayers = this.activePlayers;
        //MsgManager.debug("Liczba userow "+playerSize);
        if (activePlayers == 1) {
            for (User u : sg.getGameManager().getPlayers().values()) {
                if (!u.isSpectator()) {
                    return u.getPlayer();
                    //MsgManager.debug("Gracz "+u.getUsername()+" gra ");
                }
            }
        }
        return null;
    }

    public int getActivePlayers() {
        return activePlayers;
    }
}

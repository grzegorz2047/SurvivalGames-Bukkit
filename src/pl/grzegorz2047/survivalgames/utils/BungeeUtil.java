package pl.grzegorz2047.survivalgames.utils;

/**
 * Created by Grzegorz2047. 31.08.2015.
 */
import org.bukkit.entity.Player;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class BungeeUtil {

    public static String lobbyServer = "LobbySG1";

    public static void changeServer(SurvivalGames sg, Player player, String server) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("Connect");
            out.writeUTF(server);
            player.sendPluginMessage(sg, "BungeeCord", b.toByteArray());

        } catch (Exception e) {
            MsgManager.debug(e.getMessage());
        }
    }
}
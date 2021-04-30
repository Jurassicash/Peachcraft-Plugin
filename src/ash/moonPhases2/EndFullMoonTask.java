package ash.moonPhases2;

import java.util.Collection;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class EndFullMoonTask extends BukkitRunnable {

    private final JavaPlugin plugin;

    public EndFullMoonTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        // What you want to schedule goes here
        plugin.getServer().broadcastMessage(ChatColor.GREEN + "The Full Moon falls behind the horizon.");
        ((MoonPhasePlugin) plugin).SetFullMoon(false);
        Collection<? extends Player> players = plugin.getServer().getOnlinePlayers();
        for (Entity player : players) {
        	List<MetadataValue> pkclass = player.getMetadata("Peachykeen_Class");
        	if(pkclass.contains("Werewolf")) {
	    		plugin.getServer().broadcastMessage("Your fangs and claws shrink away, and all beastly thoughts start to fade.");
	    		((LivingEntity) player).removePotionEffect(PotionEffectType.NIGHT_VISION);
        	}
        }
    }
}

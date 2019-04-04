package ash.moonPhases2;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class WolfHuntTask extends BukkitRunnable {
	 private final JavaPlugin plugin;
	 

    public WolfHuntTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
    	for (World world : plugin.getServer().getWorlds()) {
	    	long time = world.getTime();
	    	System.out.println(world.getName() + ": " + time);
	    	if(time < 12000) {
	    		System.out.println("canceling wolves.");
	    		this.doWolvesCheckForPlayers(world, false);
	    		this.cancel();
	    	}
	    	else {
	    		this.doWolvesCheckForPlayers(world, true);
	    	}
    	}
    }
    
	private void doWolvesCheckForPlayers(World world, Boolean areWolvesAngry) {
		// Loop through mobs, if wolf, look for players to target
// code from https://github.com/ralphhogaboom/OutComeTheWolves
		for (Wolf wolf :world.getEntitiesByClass(org.bukkit.entity.Wolf.class)) {
			wolf.setAngry(areWolvesAngry);
			if(areWolvesAngry) {
				System.out.println("making wolves angry...");
				for (Entity nearbyEntity : wolf.getNearbyEntities(25, 25, 25)) {
					if (nearbyEntity instanceof Player) {
						Player target = (Player)nearbyEntity;
						wolf.setTarget(target);
					}
				}
			}
			else {
				System.out.println("calming wolves down...");
				wolf.setTarget(null);
			}
		}
	}
}

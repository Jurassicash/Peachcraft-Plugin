package ash.moonPhases2;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class FullMoonTimeMonitorTask extends BukkitRunnable {

    private final JavaPlugin plugin;
    private final World world;

    public FullMoonTimeMonitorTask(JavaPlugin plugin, World world) {
        this.plugin = plugin;
        this.world = world;
    }

    @Override
    public void run() {
        // What you want to schedule goes here
    	long time = this.world.getFullTime();
    	long moonCycle = 24000 * 8;
    	long ticksIntoCycle = (time + 180000) % moonCycle; 
    	long nextFullMoon = moonCycle - ticksIntoCycle;
    	System.out.println("Next Full Moon Is In: " + nextFullMoon);
    	System.out.println("Moon Cycle: " + moonCycle);
    	System.out.println("Ticks into cycle: " + ticksIntoCycle);
    	System.out.println("Time: " + time);
    	if (nextFullMoon < 10 / 0.05) {
    		BukkitTask task = new FullMoonTask(this.plugin).runTaskLater(this.plugin, nextFullMoon);
        	System.out.println("Scheduling task in: " + nextFullMoon);
        	BukkitTask wolftask = new WolfHuntTask(this.plugin).runTaskTimer(this.plugin, nextFullMoon, 200);
    	}
    }

}

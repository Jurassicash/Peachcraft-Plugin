package ash.moonPhases2;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class MoonPhasePlugin extends JavaPlugin{
	
	 @Override
	    public void onEnable() {
	        // Register command "moon"
	        this.getCommand("moon").setExecutor(new MoonCommand());
	        //Full moon event timer
	        List <World> worlds = Bukkit.getWorlds();
	        for(World world: worlds) {
	        	if (world.getEnvironment()== World.Environment.NORMAL) {
	        		BukkitTask task = new FullMoonTimeMonitorTask(this, world).runTaskTimer(this, 200, 200);
	        	}
	        }
	}
	 
		@Override
	    public void onDisable() {
	    }
}

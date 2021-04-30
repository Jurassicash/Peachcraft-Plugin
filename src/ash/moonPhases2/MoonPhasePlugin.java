package ash.moonPhases2;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class MoonPhasePlugin extends JavaPlugin{
	private werewolf werewolfManager;
	private boolean FullMoon;
	
	 @Override
	    public void onEnable() {
		 	werewolfManager = new werewolf(this);
	        // Register commands
	        this.getCommand("moon").setExecutor(new MoonCommand());
	        this.getCommand("ww").setExecutor(werewolfManager);
	        getServer().getPluginManager().registerEvents(werewolfManager,this);
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
	
	public void SetFullMoon(boolean newValue) {
		FullMoon = newValue;
	}
	public boolean IsMoonFull()	{
		return FullMoon;
	}
		
}

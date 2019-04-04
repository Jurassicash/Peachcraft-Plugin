package ash.moonPhases2;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class FullMoonTask extends BukkitRunnable {

    private final JavaPlugin plugin;

    public FullMoonTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        // What you want to schedule goes here
        plugin.getServer().broadcastMessage(ChatColor.RED + "A full moon rises...");
    }

}

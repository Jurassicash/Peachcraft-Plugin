package ash.moonPhases2;

import java.util.Collection;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class FullMoonTask extends BukkitRunnable {

    private final JavaPlugin plugin;
    private EndFullMoonTask EndFullMoon;

    public FullMoonTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        // What you want to schedule goes here
        plugin.getServer().broadcastMessage(ChatColor.RED + "A full moon rises...");
        ((MoonPhasePlugin) plugin).SetFullMoon(true);
        EndFullMoon.runTaskLater(plugin, 12000);
        Collection<? extends Player> players = plugin.getServer().getOnlinePlayers();
        for (Entity player : players) {
        	List<MetadataValue> pkclass = player.getMetadata("Peachykeen_Class");
        	if(pkclass.contains("Werewolf")) {
        		Inventory inv =  ((HumanEntity) player).getInventory();
        		plugin.getServer().broadcastMessage(ChatColor.RED + "Your form begins to shift...");
        		((LivingEntity) player).addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 2, false));
        		ItemStack[] armor = ((PlayerInventory) inv).getArmorContents();
        		for (ItemStack stack : armor) {
        			((LivingEntity) player).getWorld().dropItemNaturally(player.getLocation(), stack);
        		}
        	}
        }
    }

}

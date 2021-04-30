package ash.moonPhases2;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ArmorListener implements Listener {
	
	private final JavaPlugin plugin;

    public ArmorListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }
	
	@EventHandler
	public void onInventoryClick(final InventoryClickEvent event) {
		if (((MoonPhasePlugin) plugin).IsMoonFull()) {
			if (event.getSlotType() != InventoryType.SlotType.ARMOR) {
				Player p = (Player) event.getWhoClicked();
				p.sendMessage("You can't wear armor when transformed!");
				event.setCancelled(true);
			}
		}
		
	}
	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {
		//items = event.getItem();
		
	}
}

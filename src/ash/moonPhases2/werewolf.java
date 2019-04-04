package ash.moonPhases2;

import org.bukkit.Bukkit;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public final class werewolf implements Listener {
	@EventHandler
	public void onWolfKill(EntityDamageByEntityEvent event) {
		if (event.getEntity().getType() == EntityType.PLAYER && event.getDamager().getType() == EntityType.WOLF) {
			Player p = (Player) event.getEntity();
			Damageable dmg = p;
			if (dmg.getHealth() - event.getDamage() <= 0D) {
				Bukkit.broadcastMessage("Turning into werewolf...");
			}
		}
	 
	}


}

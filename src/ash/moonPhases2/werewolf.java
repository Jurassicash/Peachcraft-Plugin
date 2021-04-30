package ash.moonPhases2;

import java.util.List;
import java.util.concurrent.Callable;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.LazyMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public final class werewolf implements Listener, CommandExecutor {
	 private final JavaPlugin plugin;
	 private boolean isHungry;
	 private float damageMultiplier;
	 public werewolf(JavaPlugin plugin) {
	        this.plugin = plugin;
	        this.isHungry = false;
	        this.damageMultiplier = 1.0f;
	    }
	@EventHandler
	public void onWolfKill(EntityDamageByEntityEvent event) {
		if (event.getEntity().getType() == EntityType.PLAYER && event.getDamager().getType() == EntityType.WOLF) {
			Player p = (Player) event.getEntity();
			Damageable dmg = p;
			if (dmg.getHealth() - event.getDamage() <= 0D) {
				turnWerewolf(p);
			}
		}
	 
	}
	@EventHandler
	public void onTakeDamage(EntityDamageEvent event) {
		if (event.getEntity().getType() == EntityType.PLAYER) {
			Player p = (Player) event.getEntity();
			Damageable dmg = p;
			event.setDamage(event.getDamage()* this.damageMultiplier);
		}
	 
	}
	@EventHandler
	public void onPlayerEat(PlayerItemConsumeEvent event) {
			Player p = (Player) event.getPlayer();
			ItemStack items = event.getItem();
			switch(items.getType()) { 
				case BEEF:
				case CHICKEN:	 
				case COD:	 
				case MUTTON: 
				case PORKCHOP:	 
				case RABBIT: 
				case SALMON:
				case COOKED_BEEF:	 
				case COOKED_CHICKEN:	 
				case COOKED_COD:	 
				case COOKED_MUTTON: 
				case COOKED_PORKCHOP:	 
				case COOKED_RABBIT: 
				case COOKED_SALMON:
				case PUFFERFISH:
				case ROTTEN_FLESH:
				case RABBIT_STEW:
					p.sendMessage("Yum!");
					break;
				default:
					p.sendMessage("you cant eat that!");
					event.setCancelled(true);
					p.getInventory().removeItem(items);
					p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 20*20, 0, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5*20, 0, false));
					break;
					
			}
		}
	 
	@EventHandler
	public void onHungerChange(FoodLevelChangeEvent event) {
		HumanEntity p = event.getEntity();
		int foodLevel = event.getFoodLevel();
		List<MetadataValue> metadataList = p.getMetadata("Peachykeen_Class");
		if(metadataList.size()> 0) {
			String PKclass = metadataList.get(0).asString();
			if(PKclass == "Werewolf") {
				if(foodLevel <= 6) {
					p.sendMessage("Your hunger renders you powerless...");
					this.isHungry = true;
					deactivateHungerPowers((Player)p);
				}
				else {
					this.isHungry = false;
					activateHungerPowers((Player)p);
				}
			}
		}
	}
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if (sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length > 0) {
            	if(args[0].compareTo("set")== 0) { 
            		turnWerewolf(p);
            	}
            }
    	}
    	return true;
  }
	private void turnWerewolf(Player p)
	{
		p.sendMessage("Turning you into badass werewolf...");
		Callable<Object> getClassMetadata = () -> { return "Werewolf";};
		p.setMetadata("Peachykeen_Class", new LazyMetadataValue(this.plugin, getClassMetadata));
		Callable<Object> getHungerMetadata = () -> { return this.isHungry;};
		p.setMetadata("Peachykeen_Hungry", new LazyMetadataValue(this.plugin, getHungerMetadata));
		//activateBasePowers(p);
	}
	
	//private void activateBasePowers(Player p) {

	//}
	//private void deactivateBasePowers(Player p) {

	//}
	
	private void activateHungerPowers(Player p) {
		p.setWalkSpeed(0.4f);
		p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2, false));
		this.damageMultiplier = 0.6f;
	}
	private void deactivateHungerPowers(Player p) {
		p.setWalkSpeed(0.2f);
		p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
		this.damageMultiplier = 1.0f;
	}
}

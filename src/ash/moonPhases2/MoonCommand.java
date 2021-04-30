package ash.moonPhases2;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoonCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if (sender instanceof Player) {
            Player player = (Player) sender;
            World world = player.getLocation().getWorld();
            long time = world.getFullTime();
            long worldDays = time / 24000;
            //moon is full on day 0 and all multiples of 8, sun sets and moon rises at time 13500
            player.sendMessage("Number of days: " + worldDays);
            long moonPhaseLong = (worldDays % 8); 
            String moonPhase = "";
            switch(Math.toIntExact(moonPhaseLong)) {
            case 0: 
            	moonPhase = new String("Full Moon");
            	break;
            case 1:
            	moonPhase = new String("Waning Gibbous");
            	break;
            case 2:
            	moonPhase = new String("Last Quarter");
            	break;
            case 3:
            	moonPhase = new String("Waning Crescent");
            	break;
            case 4:
            	moonPhase = new String("New Moon");
            	break;
            case 5:
            	moonPhase = new String("Waxing Crescent");
            	break;
            case 6:
            	moonPhase = new String("First Quarter");
            	break;
            case 7:
            	moonPhase = new String("Waxing Gibbous");
            	break;
            }
            time = world.getTime();
            player.sendMessage("The World time is: " + time); 
            player.sendMessage("The moon phase is: " + moonPhase);
        }
    	return true;
    }
}

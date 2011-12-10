package net.aesircraft.Commands;

import net.aesircraft.TrueEconomy.Data.Players;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class exp implements CommandExecutor
{
	public static TrueEconomy plugin;
	 public exp(TrueEconomy instance){
		 plugin=instance;
	 }
	@Override
	public boolean onCommand(CommandSender player, Command command, String cmd,
			String[] comA) {
		Player p=(Player) player;
		player.sendMessage("§2Your exp is §b"+p.getTotalExperience()+"§2 and your level is §b"+p.getLevel()+"§2.");
		return true;
	}
}

package net.aesircraft.Commands;

import net.aesircraft.TrueEconomy.Bounties;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class target implements CommandExecutor
{
	public static TrueEconomy plugin;
	 public target(TrueEconomy instance){
		 plugin=instance;
	 }
	@Override
	public boolean onCommand(CommandSender player, Command command, String cmd,
			String[] comA) {
		Player p=(Player) player;
		Bounties.target(p);
		return true;
	}
}

package net.aesircraft.Commands;

import net.aesircraft.TrueEconomy.Data.Players;
import net.aesircraft.TrueEconomy.Exchange;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class stocks implements CommandExecutor
{
	public static TrueEconomy plugin;
	 public stocks(TrueEconomy instance){
		 plugin=instance;
	 }
	@Override
	public boolean onCommand(CommandSender player, Command command, String cmd,
			String[] comA) {
		Player p=(Player) player;
		Players.upgrade(player.getName().toLowerCase());
		Exchange.stocks(p);
		return true;
	}
}

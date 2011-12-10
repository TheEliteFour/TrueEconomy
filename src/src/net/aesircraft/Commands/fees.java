package net.aesircraft.Commands;

import net.aesircraft.TrueEconomy.Data.Sign;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class fees implements CommandExecutor
{
	public static TrueEconomy plugin;
	 public fees(TrueEconomy instance){
		 plugin=instance;
	 }
	@Override
	public boolean onCommand(CommandSender player, Command command, String cmd,
			String[] comA) {
		Player p=(Player) player;
		Sign.startupc(p);
		return true;
	}
}

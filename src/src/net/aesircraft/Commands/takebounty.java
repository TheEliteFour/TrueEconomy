package net.aesircraft.Commands;

import net.aesircraft.TrueEconomy.Bounties;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class takebounty implements CommandExecutor
{
	public static TrueEconomy plugin;
	 public takebounty(TrueEconomy instance){
		 plugin=instance;
	 }
	@Override
	public boolean onCommand(CommandSender player, Command command, String cmd,
			String[] comA) {
		Player p=(Player) player;
		if (comA.length<1)
			return false;
		if (comA[0]==null)
			return false;
		Bounties.accept(p, comA[0]);
		return true;
	}
}

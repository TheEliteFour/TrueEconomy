package net.aesircraft.Commands;

import net.aesircraft.TrueEconomy.Bounties;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class setbounty implements CommandExecutor
{
	public static TrueEconomy plugin;
	 public setbounty(TrueEconomy instance){
		 plugin=instance;
	 }
	@Override
	public boolean onCommand(CommandSender player, Command command, String cmd,
			String[] comA) {
		Player p=(Player) player;
		if (comA.length<2)
			return false;
		if (comA[0]==null)
			return false;
		if (comA[1]==null)
			return false;
		double amount=00.00;
		amount=Integer.parseInt(comA[1].replaceAll("[^\\d\\.]*", ""));		
		if (amount<=0)
			return false;
		boolean hidden=false;
		if (comA.length>=3){
			if (comA[2].toLowerCase().equals("true")){
				hidden=true;
			}
		}
		Bounties.create(p, comA[0], amount, hidden);
		return true;
	}
}

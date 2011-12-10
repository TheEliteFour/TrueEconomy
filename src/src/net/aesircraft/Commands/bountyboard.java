package net.aesircraft.Commands;

import net.aesircraft.TrueEconomy.Bounties;
import net.aesircraft.TrueEconomy.Data.Players;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class bountyboard implements CommandExecutor
{
	public static TrueEconomy plugin;
	 public bountyboard(TrueEconomy instance){
		 plugin=instance;
	 }
	@Override
	public boolean onCommand(CommandSender player, Command command, String cmd,
			String[] comA) {
		int page = 0;
		Player p=(Player) player;
		Players.upgrade(player.getName().toLowerCase());
		if (comA.length>1){
		if (comA[0]==null)
			page=1;
		else
		page=Integer.parseInt(comA[1].replaceAll("[^\\d]*", ""));
		}				
		if (page==0)	
			page=1;		
	Bounties.list(p, page);
		return true;
	}
}

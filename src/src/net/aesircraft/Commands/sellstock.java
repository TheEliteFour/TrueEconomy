package net.aesircraft.Commands;

import net.aesircraft.TrueEconomy.Data.Players;
import net.aesircraft.TrueEconomy.Exchange;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class sellstock implements CommandExecutor
{
	public static TrueEconomy plugin;
	 public sellstock(TrueEconomy instance){
		 plugin=instance;
	 }
	@Override
	public boolean onCommand(CommandSender player, Command command, String cmd,
			String[] comA) {
		Player p=(Player) player;
		Players.upgrade(player.getName().toLowerCase());
		if (comA.length<2)
			return false;
		if (comA[0]==null)
			return false;
		if (!comA[0].toUpperCase().equals("GOLD")&&!comA[0].toUpperCase().equals("IRON")&&!comA[0].toUpperCase().equals("DIAMOND")&&!comA[0].toUpperCase().equals("EXP"))	
			return false;		
		if (comA[1]==null)
			return false;
		int amount=Integer.parseInt(comA[1].replaceAll("[^\\d\\.]*", ""));		
		if (amount<=0)
			return false;
		if (comA[0].toUpperCase().equals("GOLD")){
				Exchange.sellGold(p, amount);
				return true;
		}
		if (comA[0].toUpperCase().equals("IRON")){
				Exchange.sellIron(p, amount);
				return true;
		}
		if (comA[0].toUpperCase().equals("DIAMOND")){
				Exchange.sellDiamond(p, amount);
				return true;
		}	
		if (comA[0].toUpperCase().equals("EXP")){
				Exchange.sellExp(p, amount);
				return true;
		}
		return false;
	}
}

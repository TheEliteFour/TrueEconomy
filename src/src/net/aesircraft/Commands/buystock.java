package net.aesircraft.Commands;

import net.aesircraft.TrueEconomy.Data.Players;
import net.aesircraft.TrueEconomy.Exchange;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class buystock implements CommandExecutor
{
	public static TrueEconomy plugin;
	 public buystock(TrueEconomy instance){
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
				Exchange.buyGold(p, amount);
				return true;
		}
		if (comA[0].toUpperCase().equals("IRON")){
				Exchange.buyIron(p, amount);
				return true;
		}
		if (comA[0].toUpperCase().equals("DIAMOND")){
				Exchange.buyDiamond(p, amount);
				return true;
		}	
		if (comA[0].toUpperCase().equals("EXP")){
				Exchange.buyEXP(p, amount);
				return true;
		}
		return false;
	}
}

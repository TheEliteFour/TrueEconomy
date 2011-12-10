package net.aesircraft.Commands;

import net.aesircraft.TrueEconomy.Data.Bank;
import net.aesircraft.TrueEconomy.Data.Form;
import net.aesircraft.TrueEconomy.Data.Players;
import net.aesircraft.TrueEconomy.Exchange;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class pay implements CommandExecutor
{
	public static TrueEconomy plugin;
	 public pay(TrueEconomy instance){
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
		if (Bukkit.getServer().getPlayer(comA[0])==null){
			return false;
		}
		double amount=00.00;
		amount=Double.parseDouble(comA[1].replaceAll("[^\\d\\.]*", ""));		
		if (amount<=0)
			return false;
		if (amount>Players.get(p.getName().toLowerCase())){
			player.sendMessage("§4You do not have enough!");
			return true;
		}
		Players.sub(p.getName().toLowerCase(), amount);
		player.sendMessage("§2You sent §b"+comA[0]+" "+Form.form(amount)+"§2!");
		Players.add(comA[0].toLowerCase(), amount);
		Bukkit.getServer().getPlayer(comA[0]).sendMessage("§2You recieved §b"+Form.form(amount)+" §2from §b"+p.getName()+"§2!");
		Exchange.tax(Bukkit.getServer().getPlayer(comA[0]), amount);
		return true;
	}
}

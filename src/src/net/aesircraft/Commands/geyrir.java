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



public class geyrir implements CommandExecutor
{
	public static TrueEconomy plugin;
	 public geyrir(TrueEconomy instance){
		 plugin=instance;
	 }@Override
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
		if (!p.isOp()){
			player.sendMessage("§4No.");
			return true;
		}
		double amount=00.00;
		amount=Double.parseDouble(comA[1].replaceAll("[^\\d\\.]*", ""));		
		if (amount<=0)
			return false;
		if (amount>Bank.get()||Bank.locked()){
			player.sendMessage("§4The bank does not have enough or is locked!");
			return true;
		}
		Players.add(comA[0].toLowerCase(), amount);
		player.sendMessage("§2You gave §b"+Form.form(amount)+" §2to§b "+comA[0]+"§2!");
		Bukkit.getServer().getPlayer(comA[0]).sendMessage("§2You recieved §b"+Form.form(amount)+" §2from §bThe Bank§2!");
		Exchange.tax(Bukkit.getServer().getPlayer(comA[0]), amount);
		return true;
	}
}

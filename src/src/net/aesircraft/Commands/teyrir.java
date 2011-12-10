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



public class teyrir implements CommandExecutor
{
	public static TrueEconomy plugin;
	 public teyrir(TrueEconomy instance){
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
		Bank.add(Players.sub(comA[0].toLowerCase(), amount));		
		player.sendMessage("§2You took §b"+Form.form(amount)+" §2from§b "+comA[0]+"§2!");
		Bukkit.getServer().getPlayer(comA[0]).sendMessage("§4You lost §b"+Form.form(amount)+"§4!");
		return true;
	}
}

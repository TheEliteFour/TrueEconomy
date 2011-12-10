package net.aesircraft.TrueEconomy;

import net.aesircraft.TrueEconomy.Data.Bank;
import net.aesircraft.TrueEconomy.Data.Bounty;
import net.aesircraft.TrueEconomy.Data.Form;
import net.aesircraft.TrueEconomy.Data.Players;
import org.bukkit.entity.Player;


public class Bounties
{
	public static void create(Player player,String target,double price,boolean hidden){
		String name=player.getName().toLowerCase();
		double credit=Players.c(player.getName().toLowerCase());
		double startup=((1000-credit)*2)+price;
		double start=((1000-credit)*2);
		if (startup>Players.get(name)){
			player.sendMessage("§4You do not have enough for that!");
			return;
		}
		if (Bounty.exists(name)){
			player.sendMessage("§4You already have a bounty!");
			return;
		}
		if (!Players.getk(target.toLowerCase()).equals("_null")){
			player.sendMessage("§4You cannot place this bounty!");
			return;
		}
		Bounty.create(name, target.toLowerCase(), price, hidden);
		Players.setk(target.toLowerCase(), name);
		Players.sub(name, startup);	
		Bank.add(start);
		player.sendMessage("§2You created a bounty on §b"+target+"§2!");
		player.sendMessage("§4You paid a startup up fee of §b"+Form.form(start)+"§4 based on your credit!");
	}
	public static void cancel(Player player){
		String name=player.getName().toLowerCase();
		if (!Bounty.exists(name)){
			player.sendMessage("§4You do not have a bounty!");
			return;
		}
		String f=Bounty.getf(name).toLowerCase();
		if (!f.equals("_null")){
			Players.remt(f);
		}
		String target=Bounty.gett(name);
		Players.remk(Bounty.gett(name));
		Bank.add(Bounty.geta(name));
		Players.subc(name, 100);
		Bounty.rem(name);
		player.sendMessage("§4You cancled your bounty on §b"+target+"§4!");
		player.sendMessage("§4The amount was given to the bank and you lost §b"+100+"§4 credit!");
	}
	public static void quit(Player player){
		String name=player.getName().toLowerCase();
		String bounty=Players.getk(Players.gett(name));
		if (bounty.equals("_null")){
			player.sendMessage("§4You have not taken a bounty!");
			return;
		}
		String target=Bounty.gett(bounty);
		Players.remt(name);
		Players.subc(name, 50);
		Bounty.setf(bounty, "_null");
		player.sendMessage("§4You quit your bounty on §b"+target+"§4!");
		player.sendMessage("§4You lost §b"+50+"§4 credit!");
	}
	public static void kick(Player player){
		String name=player.getName().toLowerCase();
		if (!Bounty.exists(name)){
			player.sendMessage("§4You do not have a bounty!");
			return;
		}
		String f=Bounty.getf(name).toLowerCase();
		if (f.equals("_null")){
			player.sendMessage("§4No one accepted your bounty!");
			return;
		}
		Players.remt(f);
		Bounty.setf(name, "_null");
		Players.subc(name, 10);
		player.sendMessage("§4You kicked the accepter off your bounty!");
		player.sendMessage("§4You lost §b"+10+"§4 credit!");
	}
	public static void accept(Player player, String bounty){
		String name=player.getName().toLowerCase();
		bounty=bounty.toLowerCase();
		if (!Bounty.exists(bounty)){
			player.sendMessage("§4There is no bounty by that person!");
			return;
		}
		if (!Bounty.getf(bounty).equals("_null")){
			player.sendMessage("§4That bounty is already taken!");
			return;
		}
		if (name.equals(bounty.toLowerCase())||name.equals(Bounty.gett(bounty).toLowerCase())){
			player.sendMessage("§4You cannot accept this bounty!");
			return;
		}
		String target=Bounty.gett(bounty).toLowerCase();
		Players.sett(name,target);
		Bounty.setf(bounty, name);
		player.sendMessage("§4You accepted a bounty on §b"+target+"§4!");
	}
	public static void check(Player player){
		String name=player.getName().toLowerCase();
		if (!Bounty.exists(name)){
			player.sendMessage("§4You do not have a bounty!");
			return;
		}
		String f=Bounty.getf(name).toLowerCase();
		if (f.equals("_null")){
			player.sendMessage("§4No one accepted your bounty!");
		}
		player.sendMessage("§2Someone accepted your bounty!");
	}
	public static void target(Player player){
		String name=player.getName().toLowerCase();
		if (Players.gett(name).equals("_null")){
			player.sendMessage("§4You do not have a target!");
			return;
		}
		String t=Players.gett(name);
		double amount=Bounty.geta(Players.getk(t.toLowerCase()).toLowerCase());
		player.sendMessage("§2Your target is §b"+t+"§2 for §b"+Form.form(amount));
	}
	public static void list(Player player,int page){
		String[] list=Bounty.getall();
		if (((page*5)-4)>list.length){
			page=1;
		}		
		int i=start(page);
		int i2=i+5;
		int pages = pages(list.length);
		player.sendMessage("§2Bounty Board Page §b"+page+" of "+pages);
		player.sendMessage("§2-----------------------------");
		player.sendMessage("§2Requester - Target - Reward");
		player.sendMessage("§2-----------------------------");
		for (i=start(page);i<(i2);i++){
			if (i>=list.length){
				break;
			}			
			if (!Bounty.getf(list[i].substring(0,list[i].length()-4)).equals("_null")){
				player.sendMessage("§b"+list[i].substring(0,list[i].length()-4)+"§4 - TAKEN");
			}
			else if(Bounty.geth(list[i].substring(0,list[i].length()-4))){
				player.sendMessage("§b"+list[i].substring(0,list[i].length()-4)+"§e - §bHIDDEN §e- §b"+Bounty.geta(list[i].substring(0,list[i].length()-4)));
			}
			else if(!Bounty.geth(list[i].substring(0,list[i].length()-4))){
				player.sendMessage("§b"+list[i].substring(0,list[i].length()-4)+"§2 - §b"+Bounty.gett(list[i].substring(0,list[i].length()-4)) +" §2- §b"+Form.form2(Bounty.geta(list[i].substring(0,list[i].length()-4))));
			}
		}
		player.sendMessage("§2-----------------------------");
		player.sendMessage("§2Use /takebounty name to take a bounty!");
		
		
		
	}
	public static int pages(int num)
	{
		if (num >= 5)
		{
			return num / 5;
		}
		else
		{
			return 1;
		}
	}

	public static int start(int page)
	{
		return (page * 5) - 5;
	}
}

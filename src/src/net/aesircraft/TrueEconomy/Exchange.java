package net.aesircraft.TrueEconomy;

import java.math.BigDecimal;
import net.aesircraft.TrueEconomy.Data.Bank;
import net.aesircraft.TrueEconomy.Data.Diamond;
import net.aesircraft.TrueEconomy.Data.EXP;
import net.aesircraft.TrueEconomy.Data.Form;
import net.aesircraft.TrueEconomy.Data.Gold;
import net.aesircraft.TrueEconomy.Data.InventoryWorkaround;
import net.aesircraft.TrueEconomy.Data.Iron;
import net.aesircraft.TrueEconomy.Data.Players;
import net.aesircraft.TrueEconomy.Data.Stat;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Exchange
{
	public static void stocks(Player player){
		player.sendMessage("§2Ore Stock Martket");
		player.sendMessage("§2-----------------------------------------------------");
		player.sendMessage("§2Ore - Ratio - Value - Old Value");
		player.sendMessage("§2-----------------------------------------------------");
		double[] v=Players.getl(player.getName());
		double[] nr=new double[5];
		nr[0]=Diamond.value();
		nr[1]=Iron.value();
		nr[2]=Gold.value();
		nr[3]=EXP.value();
		nr[4]=EXP.sellvalue();
		if (v[3]<nr[3]){
			player.sendMessage("§2EXP Buy - §b"+EXP.ratio()+" §2- §b"+Form.form2(nr[3])+" §2- §b"+Form.form2(v[3]));
		}		
		if (v[3]>nr[3]){
			player.sendMessage("§4EXP Buy - §b"+EXP.ratio()+" §4- §b"+Form.form2(nr[3])+" §4- §b"+Form.form2(v[3]));
		}
		if (v[3]==nr[3]){
			player.sendMessage("§eEXP Buy - §b"+EXP.ratio()+" §e- §b"+Form.form2(nr[3])+" §e- §b"+Form.form2(v[3]));
		}
		if (v[4]<nr[4]){
			player.sendMessage("§2EXP Sell- §b"+EXP.ratio()+" §2- §b"+Form.form2(nr[4])+" §2- §b"+Form.form2(v[4]));
		}		
		if (v[4]>nr[4]){
			player.sendMessage("§4EXP Sell- §b"+EXP.ratio()+" §4- §b"+Form.form2(nr[4])+" §4- §b"+Form.form2(v[4]));
		}
		if (v[4]==nr[4]){
			player.sendMessage("§eEXP Sell- §b"+EXP.ratio()+" §e- §b"+Form.form2(nr[4])+" §e- §b"+Form.form2(v[4]));
		}
		if (v[2]<nr[2]){
			player.sendMessage("§2Gold    - §b"+Gold.ratio()+" §2- §b"+Form.form2(nr[2])+" §2- §b"+Form.form2(v[2]));
		}		
		if (v[2]>nr[2]){
			player.sendMessage("§4Gold    - §b"+Gold.ratio()+" §4- §b"+Form.form2(nr[2])+" §4- §b"+Form.form2(v[2]));
		}
		if (v[2]==nr[2]){
			player.sendMessage("§eGold    - §b"+Gold.ratio()+" §e- §b"+Form.form2(nr[2])+" §e- §b"+Form.form2(v[2]));
		}
		if (v[0]<nr[0]){
			player.sendMessage("§2Diamond - §b"+Diamond.ratio()+" §2- §b"+Form.form2(nr[0])+" §2- §b"+Form.form2(v[0]));
		}		
		if (v[0]>nr[0]){
			player.sendMessage("§4Diamond - §b"+Diamond.ratio()+" §4- §b"+Form.form2(nr[0])+" §4- §b"+Form.form2(v[0]));
		}
		if (v[0]==nr[0]){
			player.sendMessage("§eDiamond - §b"+Diamond.ratio()+" §e- §b"+Form.form2(nr[0])+" §e- §b"+Form.form2(v[0]));
		}
		if (v[1]<nr[1]){
			player.sendMessage("§2Iron    - §b"+Iron.ratio()+" §2- §b"+Form.form2(nr[1])+" §2- §b"+Form.form2(v[1]));
		}		
		if (v[1]>nr[1]){
			player.sendMessage("§4Iron    - §b"+Iron.ratio()+" §4- §b"+Form.form2(nr[1])+" §4- §b"+Form.form2(v[1]));
		}
		if (v[1]==nr[1]){
			player.sendMessage("§eIron    - §b"+Iron.ratio()+" §e- §b"+Form.form2(nr[1])+" §e- §b"+Form.form2(v[1]));
		}			
		player.sendMessage("§2-----------------------------------------------------");
		player.sendMessage("§2[/buystock ore amount] or [/sellstock ore amount]");
		player.sendMessage("§2-----------------------------------------------------");
		Players.setl(player.getName(), nr);
	}
	public static void tax(Player player,double amount){
		String name= player.getName();
		double r=Bank.reverse();
		if (r>1.8)
			r=1.8;
		if (r<0.05)
			r=0.05;
		double tax=(amount*0.05)*r;
		if (tax==0.00){
			return;
		}
		player.sendMessage("§4You have been taxed §b"+Form.form(tax)+"§4.");
		Players.sub(name, tax);
		Stat.addt(tax);
	}
	public static void taxs(String name,double amount){
		double r=Bank.reverse();
		if (r>1.8)
			r=1.8;
		if (r<0.05)
			r=0.05;
		double tax=(amount*0.05)*r;
		if (tax==0.00){
			return;
		}
		Players.sub(name, tax);
		Stat.addt(tax);
	}
	public static boolean check(Player player,double amount,double tocheck){
		if (amount < tocheck){
			player.sendMessage("§4You do not have enough money for that!");
			return false;
		}
		return true;
	}
	public static boolean world(Player player){
		String world=player.getWorld().getName().toLowerCase();
		if (world.equals("creativ")||world.equals("ctfw")||world.equals("builder")){
			player.sendMessage("§4You cannot do that in this world!");
			return true;
		}
		return false;
	}
	public static boolean bcheck(Player player){
		if (Bank.locked()){
			player.sendMessage("§4The bank is currently locked out!");
			return false;
		}
		return true;
	}
	public static boolean dlh(Player player){
		double amount=Diamond.get();
		if (amount >=(amount+(amount*2.5))){
			player.sendMessage("§4The bank currently owns to much Diamond!");
			return false;
		}
		return true;
		
	}
	public static boolean ilh(Player player){
		double amount=Iron.get();
		if (amount >=(amount+(amount*2.5))){
			player.sendMessage("§4The bank currently owns to much Iron!");
			return false;
		}
		return true;
		
	}
	public static boolean elh(Player player){
		double amount=EXP.get();
		if (amount >=(amount+(amount*2.5))){
			player.sendMessage("§4The bank currently owns to much EXP!");
			return false;
		}
		return true;
		
	}
	public static boolean el(Player player){
		if (EXP.locked()){
			player.sendMessage("§4The bank currently doesn't have enough EXP!");
			return true;
		}
		return false;
	}
	public static boolean dl(Player player){
		if (Diamond.locked()){
			player.sendMessage("§4The bank currently doesn't have enough Diamond!");
			return true;
		}
		return false;
	}
	public static boolean il(Player player){
		if (Iron.locked()){
			player.sendMessage("§4The bank currently doesn't have enough Iron!");
			return true;
		}
		return false;
	}
	public static boolean gl(Player player){
		if (Gold.locked()){
			player.sendMessage("§4The bank currently doesn't have enough Gold!");
			return true;
		}
		return false;
	}
	public static boolean glh(Player player){
		double amount=Gold.get();
		if (amount >=(amount+(amount*2.5))){
			player.sendMessage("§4The bank currently owns to much Gold!");
			return false;
		}
		return true;
		
	}
	public static void buyDiamond(Player player,int amount){
		String name= player.getName();
		double val=Diamond.value()*amount;
		double bal=Players.get(name);
		if (world(player)){
			return;
		}
		if (!check(player,bal,val))
			return;
		if (!isSpace(player,amount))
			return;
		if (dl(player)){
			return;
		}
		add(player,264,amount);
		Diamond.sub(amount);
		Players.sub(name, val);
		Bank.add(val);
		Stat.addi(val);
		player.sendMessage("§2You have bought §b"+amount+"§b Diamond §2for §b"+Form.form(val)+"§2.");
	}
	public static void sellDiamond(Player player,int amount){
		String name= player.getName();
		double val=Diamond.value()*amount;
		if (world(player)){
			return;
		}
		if (!has(player,264,amount))
			return;
		if (!bcheck(player)){
			return;
		}
		if (!dlh(player)){
			return;
		}
		Stat.addo(val);
		sub(player,264,amount);
		Diamond.add(amount);
		Bank.sub(val);
		Players.add(name, val);
		player.sendMessage("§2You have sold §b"+amount+"§b Diamond §2for §b"+Form.form(val)+"§2.");
		tax(player,val);
	}
	public static void buyIron(Player player,int amount){
		String name= player.getName();
		double val=Iron.value()*amount;
		double bal=Players.get(name);
		if (world(player)){
			return;
		}
		if (!check(player,bal,val))
			return;
		if (!isSpace(player,amount))
			return;
		if (il(player)){
			return;
		}
		Stat.addi(val);
		add(player,265,amount);
		Iron.sub(amount);
		Players.sub(name, val);
		Bank.add(val);
		player.sendMessage("§2You have bought §b"+amount+"§b Iron §2for §b"+Form.form(val)+"§2.");
	}
	public static void sellIron(Player player,int amount){
		String name= player.getName();
		double val=Iron.value()*amount;
		if (world(player)){
			return;
		}
		if (!has(player,265,amount))
			return;
		if (!bcheck(player)){
			return;
		}
		if (!ilh(player)){
			return;
		}
		Stat.addo(val);
		sub(player,265,amount);
		Iron.add(amount);
		Bank.sub(val);
		Players.add(name, val);
		player.sendMessage("§2You have sold §b"+amount+"§b Iron §2for §b"+Form.form(val)+"§2.");
		tax(player,val);
	}
	public static void buyEXP(Player player,int amount){
		String name= player.getName();
		double val=EXP.value()*amount;
		double bal=Players.get(name);
		if (world(player)){
			return;
		}
		if (!check(player,bal,val))
			return;
		if (il(player)){
			return;
		}
		Stat.addi(val);
		for (int expctr=0;expctr<amount;expctr++){
				player.giveExp(1);
			}
		EXP.sub(amount);
		Players.sub(name, val);
		Bank.add(val);
		player.sendMessage("§2You have bought §b"+amount+"§b EXP §2for §b"+Form.form(val)+"§2.");
	}
	public static void sellExp(Player player,int amount){
		String name= player.getName();
		double val=EXP.sellvalue()*amount;
		if (world(player)){
			return;
		}
		if (!(player.getTotalExperience()>amount))
			return;
		if (!bcheck(player)){
			return;
		}
		if (!elh(player)){
			return;
		}
		Stat.addo(val);
		int exp=player.getTotalExperience()-amount-7;			
			player.setExp(7);
			player.setTotalExperience(7);
			player.setLevel(1);
			for (int expctr=0;expctr<exp;expctr++){
				player.giveExp(1);
			}
		EXP.add(amount);
		Bank.sub(val);
		Players.add(name, val);
		player.sendMessage("§2You have sold §b"+amount+"§b EXP §2for §b"+Form.form(val)+"§2.");
		tax(player,val);
	}
	public static void buyGold(Player player,int amount){
		String name= player.getName();
		double val=Gold.value()*amount;
		double bal=Players.get(name);
		if (world(player)){
			return;
		}
		if (!check(player,bal,val))
			return;
		if (!isSpace(player,amount))
			return;
		if (gl(player)){
			return;
		}
		Stat.addi(val);
		add(player,266,amount);
		Gold.sub(amount);
		Bank.add(val);
		Players.sub(name, val);
		player.sendMessage("§2You have bought §b"+amount+"§b Gold §2for §b"+Form.form(val)+"§2.");
	}
	public static void sellGold(Player player,int amount){
		String name= player.getName();
		double val=Gold.value()*amount;
		if (world(player)){
			return;
		}
		if (!has(player,266,amount))
			return;
		if (!bcheck(player)){
			return;
		}
		if (!glh(player)){
			return;
		}
		Stat.addo(val);
		sub(player,266,amount);
		Gold.add(amount);
		Bank.sub(val);
		Players.add(name, val);
		player.sendMessage("§2You have sold §b"+amount+"§b Gold §2for §b"+Form.form(val)+"§2.");
		tax(player,val);
	}
	public static void add(Player player,int id,int amount){
		ItemStack item = new ItemStack(id,amount);
		InventoryWorkaround.addItem(player.getInventory(), false, item);			
		}	
	public static void sub(Player player,int id,int amount){
		ItemStack item = new ItemStack(id,amount);
		InventoryWorkaround.removeItem(player.getInventory(), false, item);			
		}	
	public static boolean has(Player player, int id, int amount){
		ItemStack item=new ItemStack(id,amount);
		if (InventoryWorkaround.containsItem(player.getInventory(), false, item))
			return true;
		else{
			player.sendMessage("§4You do not have enough for that!");
			return false;}
		
	}
	public static boolean isSpace(Player player, int amount){
			ItemStack[] stacks;
			stacks=player.getInventory().getContents();
			int space=0,stackCount=0;
			for (int ctr = 0;ctr<36;ctr++){
				if (stacks[ctr]==null)
					space=space+1;
			}
			if (amount>64){
				BigDecimal stacky=new BigDecimal(amount/64).setScale(0,BigDecimal.ROUND_UP);
				stackCount=Integer.parseInt(stacky.toString());
			}
			else
				stackCount=1;
			if (stackCount<=space)
				return true;
		player.sendMessage("§4You do not have room in your inventory!");
		return false;
	}
}

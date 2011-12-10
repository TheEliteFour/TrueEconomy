package net.aesircraft.TrueEconomy.Data;

import net.aesircraft.TrueEconomy.Exchange;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;


public class SignListener extends PlayerListener
{
	public static TrueEconomy plugin;
	public SignListener(TrueEconomy instance)
	{
		plugin = instance;
	}
	@Override
	public void onPlayerInteract(PlayerInteractEvent event)
{
	if (event.getAction()==Action.RIGHT_CLICK_BLOCK || event.getAction()==Action.RIGHT_CLICK_AIR){
		return;
	}
	if (event.isCancelled()){
			return;
		}
	if (event.getClickedBlock()==null){
	return;
    }
	int mid=event.getClickedBlock().getTypeId();	
	if (!(mid==63) &&!( mid==68)){
		return;
	}
	
		
	
	CraftSign sign=new CraftSign(event.getClickedBlock());
	Player player=event.getPlayer();
	if (sign.getLine(0).length()<=1)
			return;
	String name=player.getName().toLowerCase();

	if (!sign.getLine(0).substring(0,2).toLowerCase().equals("b-")&&!sign.getLine(0).substring(0,2).toLowerCase().equals("s-")){
		return;
	}
	String[] details=sign.getLine(0).split("-");
	String name2=details[1].toLowerCase();
	
	if (sign.getLine(0).substring(0,2).toLowerCase().equals("b-")&&details[1].toLowerCase().equals(name)){
		int ihid=player.getItemInHand().getTypeId();
		int iham=player.getItemInHand().getAmount();
		String[] amt=sign.getLine(3).split(":");
		int id=Integer.parseInt(amt[0]);
		if (!(ihid==id))
			return;
		ItemStack item=new ItemStack(ihid,iham);
		InventoryWorkaround.removeItem(player.getInventory(), false, item);
		int a=Integer.parseInt(amt[1]);
		a=a+iham;
		sign.setLine(3,amt[0]+":"+a);
		sign.update();
	}
	if (sign.getLine(0).substring(0,2).toLowerCase().equals("b-")&&!details[1].toLowerCase().equals(name)){
		String[] amt=sign.getLine(3).split(":");
		int id=Integer.parseInt(amt[0]);
		int amnt=Integer.parseInt(amt[1]);
		if (amnt==0){
			player.sendMessage("§4This shop is empty!");
			return;
		}
		ItemStack item=new ItemStack(id,1);
		if (!Exchange.isSpace(player, amnt)){
			return;
		}
		double price=Double.parseDouble(sign.getLine(1).replace(",",""));
		if (price>Players.get(name)){
			player.sendMessage("§4You do not have enough money!");
			return;
		}
		Players.add(name2, price);
		Exchange.taxs(name2, price);
		Players.sub(name, price);
		player.sendMessage("§2You have been charged §b"+Form.form(price)+"§2.");
		InventoryWorkaround.addItem(player.getInventory(), false, item);
		amnt=amnt-1;
		sign.setLine(3,amt[0]+":"+amnt);
		sign.update();
	}
	if (sign.getLine(0).substring(0,2).toLowerCase().equals("s-")&&details[1].toLowerCase().equals(name)){
		String[] amt=sign.getLine(3).split(":");
		int id=Integer.parseInt(amt[0]);
		int amnt=Integer.parseInt(amt[2]);
		if (amnt==0){
			return;
		}
		if (!Exchange.isSpace(player, amnt)){
			return;
		}
		ItemStack item=new ItemStack(id,amnt);
		InventoryWorkaround.addItem(player.getInventory(), false, item);
		sign.setLine(3,amt[0]+":"+amt[1]+":0");
		sign.update();
	}
	if (sign.getLine(0).substring(0,2).toLowerCase().equals("s-")&&!details[1].toLowerCase().equals(name)){
		String[] amt=sign.getLine(3).split(":");
		double price =Double.parseDouble(sign.getLine(1).replace(",",""));
		int id=Integer.parseInt(amt[0]);
		int amnt=Integer.parseInt(amt[2]);
		if (amnt==255){
			player.sendMessage("§4This shop is full!");
			return;
		}
		if (amt[1].equals("0")){
			player.sendMessage("§4This shop is no longer buying this!");
			return;
		}
		if (price>Players.get(name2)){
			player.sendMessage("§4The shop doesn't have enough to buy that!");
			return;
		}
		ItemStack item=new ItemStack(id,1);
		if (!InventoryWorkaround.containsItem(player.getInventory(),false, item)){
			player.sendMessage("§4You do not have that item!");
			return;
		}
		Players.sub(name2, price);
		Players.add(name, price);
		Exchange.tax(player, price);
		player.sendMessage("§2You made §b"+Form.form(price)+"§2!");
		InventoryWorkaround.removeItem(player.getInventory(), false, item);
		Location loc=sign.getLocation();
		loc.setY(loc.getY()+1);
		Block block=player.getWorld().getBlockAt(loc);
		if (!(block.isEmpty())){
		if (block.getTypeId()==63||block.getTypeId()==68){
			CraftSign buy=new CraftSign(block);
			if (buy.getLine(0).toLowerCase().equals("b-"+details[1].toLowerCase())){
				String[] check=buy.getLine(3).split(":");
				if (!check[1].equals("255")){
					int newa=Integer.parseInt(check[1]);
					buy.setLine(3, check[0]+":"+(newa+1));
					sign.setLine(3,amt[0]+":"+(Integer.parseInt(amt[1])-1)+":0");
					sign.update();
					buy.update();
					return;
				}
			}
		}
		}
		sign.setLine(3,amt[0]+":"+(Integer.parseInt(amt[1])-1)+":"+(Integer.parseInt(amt[2])+1));
		sign.update();
	}
}
}

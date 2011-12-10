package net.aesircraft.TrueEconomy.Data;

import net.aesircraft.TrueEconomy.Exchange;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.Material;
import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.inventory.ItemStack;


public class Sign extends BlockListener
{
	public static TrueEconomy plugin;
	public Sign(TrueEconomy instance)
	{
		plugin = instance;
	}
	public static double startup(Player player){
		double credit=Players.c(player.getName().toLowerCase());
		return (1000-credit)*2;
	}
	public static void startupc(Player player){
		double credit=Players.c(player.getName().toLowerCase());
		player.sendMessage("§2Your startup is §b"+Form.form((1000-credit)*2)+"§4 based on your credit!");
	}
	@Override
	public void onBlockBreak(final BlockBreakEvent event)
{
    int mid=event.getBlock().getTypeId();     if (mid==63 || mid==68){
		CraftSign sign=new CraftSign(event.getBlock());
		Player player=event.getPlayer();
		if (player==null){
			return;
		}
		if (event.isCancelled()){
			return;
		}
		if (sign.getLine(0).length()<=1)
			return;
		if (!sign.getLine(0).substring(0,2).toLowerCase().equals("b-")&&!sign.getLine(0).substring(0,2).toLowerCase().equals("s-")){
			return;
		}
		String[] sp=sign.getLine(0).split("-");
		if (!player.getName().toLowerCase().equals(sp[1].toLowerCase())&&!player.isOp()){
			player.sendMessage("§4You cannot break another persons shop!");
			event.setCancelled(true);
			return;
		}
		if (sign.getLine(0).substring(0,2).toLowerCase().equals("s-")){
			String[] sp2=sign.getLine(3).split(":");
			int amount=Integer.parseInt(sp2[2]);
			if (amount<=0){
				return;
			}
			ItemStack returns=new ItemStack(Integer.parseInt(sp2[0]),amount);
			if (!Exchange.isSpace(player, amount)){
				event.setCancelled(true);
				sign.update();
			}
			InventoryWorkaround.addItem(player.getInventory(), false, returns);
			return;
		}
		String[] i=sign.getLine(3).split(":");
		ItemStack item=new ItemStack(Integer.parseInt(i[0]),Integer.parseInt(i[1]));
		if (!Exchange.isSpace(player, Integer.parseInt(i[1]))){
				event.setCancelled(true);
				sign.update();
			}
		InventoryWorkaround.addItem(player.getInventory(), false, item);		
	}
}
	@Override
	public void onSignChange(final SignChangeEvent event)
{
	int mid=event.getBlock().getTypeId();     if (mid==63 || mid==68){
		CraftSign sign=new CraftSign(event.getBlock());
		Player player=event.getPlayer();
		if (player==null){
			return;
		}
		if (event.isCancelled()){
			return;
		}
		if (sign.getLine(0).length()>1){
		if (sign.getLine(0).substring(0,2).toLowerCase().equals("b-")||sign.getLine(0).substring(0,2).toLowerCase().equals("s-")){
			event.setCancelled(true);
			return;
		}
		}
		if (player==null){
			return;
		}
		if (event.isCancelled()){
			return;
		}
		if (event.getLine(0).length()<=1)
			return;
		if (event.getLine(0).substring(0,2).toLowerCase().equals("b-")||event.getLine(0).substring(0,2).toLowerCase().equals("s-")){
			event.setLine(0, "Cheating");
				event.setLine(1, "Is Against");
				event.setLine(2, "The");
				event.setLine(3, "ToS, §4LOGGED");
				return;
		}
		if (event.getLine(0).toLowerCase().equals("buy")){
			if (player.getWorld().getName().toLowerCase().equals("creativ")||player.getWorld().getName().toLowerCase().equals("ctfw")){
				event.setLine(0, "Cheating");
				event.setLine(1, "Is Against");
				event.setLine(2, "The");
				event.setLine(3, "ToS, §4LOGGED");
				return;
			}
			if (event.getLine(1).isEmpty()){
				event.setLine(0, "");
				event.setLine(1, "§4Bad itm/amt");
				event.setLine(2, "");
				event.setLine(3, "");
				return;
			}
			if (event.getLine(2).isEmpty()){
				event.setLine(0, "");
				event.setLine(1, "§4Bad itm/amt");
				event.setLine(2, "");
				event.setLine(3, "");
				return;
			}
			String[] item=new String[2];
			item[0]=event.getLine(1);
			item[1]=event.getLine(2);			
			if (item[0]==null || item[0].equals("")||item[1]==null || item[1].equals("")){
			event.setLine(0, "");
				event.setLine(1, "§4Bad itm/amt");
				event.setLine(2, "");
				event.setLine(3, "");
				return;
			}
			int id=findMaterial(item[0]).getId();
			int amount=Integer.parseInt(item[1]);
			if (id==0 || amount<=0){
				event.setLine(0, "");
				event.setLine(1, "§4Bad itm/amt");
				event.setLine(2, "");
				event.setLine(3, "");
				return;
			}
			ItemStack i=new ItemStack(id,amount);
		    if (!InventoryWorkaround.containsItem(player.getInventory(), false, i)){
				event.setLine(0, "");
				event.setLine(1, "§4Not Enough");
				event.setLine(2, "");
				event.setLine(3, "");
				return;
			}
			if (event.getLine(3).isEmpty()){
				event.setLine(0, "");
				event.setLine(1, "§4Bad price");
				event.setLine(2, "");
				event.setLine(3, "");
				return;
			}
			double price=Double.parseDouble(event.getLine(3));
			if (price<=0.00){
				event.setLine(0, "");
				event.setLine(1, "§4Bad price");
				event.setLine(2, "");
				event.setLine(3, "");
				return;
			}	
			if (startup(player)>Players.get(player.getName().toLowerCase())){
				event.setLine(0, "§4Not Enough");
				event.setLine(1, "§4For Startup");
				event.setLine(2, "§4Use /fees");
				event.setLine(3, "");
				return;
			}
				event.setLine(0, "b-"+player.getName().toLowerCase());				
				event.setLine(1, ""+Form.form2(price));				
				event.setLine(2, i.getType().name());
				event.setLine(3, (id+":"+amount));
				double credit=Players.c(player.getName().toLowerCase());
		        player.sendMessage("§4You have been charged §b"+Form.form((1000-credit)*2)+"§4 based on your credit!");
				Players.sub(player.getName().toLowerCase(), startup(player));
				Bank.add(startup(player));
				InventoryWorkaround.removeItem(player.getInventory(), false, i);			
		}
	
	
	
	
	
	
	if (event.getLine(0).toLowerCase().equals("sell")){
		if (player.getWorld().getName().toLowerCase().equals("creativ")||player.getWorld().getName().toLowerCase().equals("ctfw")){
				event.setLine(0, "Cheating");
				event.setLine(1, "Is Against");
				event.setLine(2, "The");
				event.setLine(3, "ToS, §4LOGGED");
				return;
			}
			if (event.getLine(1).isEmpty()){
				event.setLine(0, "");
				event.setLine(1, "§4Bad itm/amt");
				event.setLine(2, "");
				event.setLine(3, "");
				return;
			}
			if (event.getLine(2).isEmpty()){
				event.setLine(0, "");
				event.setLine(1, "§4Bad itm/amt");
				event.setLine(2, "");
				event.setLine(3, "");
				return;
			}
			String[] item=new String[2];
			item[0]=event.getLine(1);
			item[1]=event.getLine(2);
			if (item[0]==null || item[0].equals("")||item[1]==null || item[1].equals("")){
			event.setLine(0, "");
				event.setLine(1, "§4Bad itm/amt");
				event.setLine(2, "");
				event.setLine(3, "");
				return;
			}
			int id=findMaterial(item[0]).getId();
			int amount=Integer.parseInt(item[1]);
			if (id==0 || amount<=0){
				event.setLine(0, "");
				event.setLine(1, "§4Bad itm/amt");
				event.setLine(2, "");
				event.setLine(3, "");
				return;
			}
			ItemStack i=new ItemStack(id,amount);
			double price=Double.parseDouble(event.getLine(3));
			if (price<=0.00){
				event.setLine(0, "");
				event.setLine(1, "§4Bad price");
				event.setLine(2, "");
				event.setLine(3, "");
				return;
			}	
			if (startup(player)>Players.get(player.getName().toLowerCase())){
				event.setLine(0, "§4Not Enough");
				event.setLine(1, "§4For Startup");
				event.setLine(2, "§4Use /fees");
				event.setLine(3, "");
				return;
			}
				event.setLine(0, "s-"+player.getName().toLowerCase());				
				event.setLine(1, ""+Form.form2(price));				
				event.setLine(2, i.getType().name());
				event.setLine(3, (id+":"+amount+":0"));
				Players.sub(player.getName().toLowerCase(), startup(player));
				Bank.add(startup(player));
				double credit=Players.c(player.getName().toLowerCase());
		        player.sendMessage("§4You have been charged §b"+Form.form((1000-credit)*2)+"§4 based on your credit!");
		
	
		}	
	}
}
	public static Material findMaterial(String m) throws IllegalArgumentException {
        try {
            // try to parse as int type id.
            int i = Integer.decode(m);
            Material material = Material.getMaterial(i);
            if (material==null) throw new IllegalArgumentException("Unknown material type id: " + m);
            else return material;
        } catch (NumberFormatException ne) {
            try{// try as material name
            Material material = Material.getMaterial(m.toUpperCase());
            if (material==null) throw new IllegalArgumentException("Unknown material name: " + m);
            else return material;
			}catch (IllegalArgumentException ne2) {
            // try as material name
            Material material = Material.getMaterial("STONE");
            return material;        }
        }
    }
}

package net.aesircraft.TrueEconomy.Data;

import net.aesircraft.TrueEconomy.Exchange;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;


public class MonsterListener extends EntityListener
{
	public static TrueEconomy plugin;

	public MonsterListener(TrueEconomy instance)
	{
		plugin = instance;
	}
	
	@Override
	public void onEntityDamage(EntityDamageEvent event)
	{
		if ((event instanceof EntityDamageByEntityEvent))
		{
		if (((EntityDamageByEntityEvent)event).getDamager() instanceof Player){
			if (((Player)((EntityDamageByEntityEvent)event).getDamager()).isOp()){
				event.setDamage(1000);
			}
			}
		}		
	}

	@Override
	public void onEntityDeath(EntityDeathEvent event)
	{
		if ((event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent))
		{
			EntityDamageByEntityEvent e=(EntityDamageByEntityEvent)event.getEntity().getLastDamageCause();
			if (e.getDamager() instanceof Player){
			Player damager=(Player)e.getDamager();
			if (damager==null){
				return;
			}
			if (event.getEntity() instanceof Player){
				procpvp(event.getEntity(), damager);				
			}
			if (!(event.getEntity() instanceof Player)){
				proc(event.getEntity(), damager);
			}
			int exp = event.getDroppedExp();
					for (int ctr = 0; ctr < exp; ctr++)
					{
						damager.giveExp(1);
					}
					damager.sendMessage("§2You recieved §b" + exp + "§2 Exp.");
					event.setDroppedExp(0);
			}
		}		
		

	}


	public static void proc(Entity monster, Player player)
	{
		double price = 0;
		if (monster instanceof Zombie)
		{
			price = 20;
		}
		if (monster instanceof Ghast)
		{
			price = 100;
		}
		if (monster instanceof Skeleton)
		{
			price = 25;
		}
		if (monster instanceof Spider)
		{
			price = 30;
		}
		if (monster instanceof Creeper)
		{
			price = 50;
		}
		if (monster instanceof PigZombie)
		{
			price = 35;
		}
		if (monster instanceof Slime)
		{
			price = 40;
		}
		if (monster instanceof Giant)
		{
			price = 200;
		}
		if (monster instanceof Wolf)
		{
			price = 50;
		}
		if (monster instanceof EnderDragon)
		{
			price = 200;
		}
		if (monster instanceof Blaze)
		{
			price = 50;
		}
		if (monster instanceof MagmaCube)
		{
			price = 50;
		}
		if (monster instanceof Enderman)
		{
			price = 50;
		}
		if (monster instanceof Snowman)
		{
			price = 50;
		}
		if (monster instanceof Silverfish)
		{
			price = 100;
		}
		if (monster instanceof CaveSpider)
		{
			price = 50;
		}
		if (price == 0)
		{
			return;
		}
		if (Bank.locked())
		{
			return;
		}
		double ratio = Bank.ratio();
		if (ratio > 1.5)
		{
			ratio = 1.5;
		}
		if (ratio < 0.05)
		{
			ratio = 0.05;
		}
		price = price * ratio;
		Players.add(player.getName().toLowerCase(), price);
		player.sendMessage("§2You recieved §b" + Form.form(price) + "§2!");
		Exchange.tax(player, price);
		Bank.sub(price);
		return;
	}

	public static void procpvp(Entity entity, Player killer)
	{
		Player killed = (Player)entity;
		if (Players.getk(killed.getName()).equals("_null"))
		{
			return;
		}
		if (!killed.getName().toLowerCase().equals(Players.gett(killer.getName().toLowerCase()))){
			return;
		}
		String bounty = Players.getk(killed.getName().toLowerCase()).toLowerCase();
		double amount = Bounty.geta(bounty);
		Players.add(killer.getName().toLowerCase(), amount);
		Players.addc(killer.getName().toLowerCase(), 10);
		killer.sendMessage("§2You earned §b" + Form.form(amount) + "§2 and §b10 §2credit!");
		Exchange.tax(killer, amount);
		killed.sendMessage("§4A bounty was claimed on you for §b" + Form.form(amount));
		Players.remk(killed.getName().toLowerCase());
		Players.remt(killer.getName().toLowerCase());
		Bounty.rem(bounty);
		return;
	}
}

package net.aesircraft.TrueEconomy.Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.util.config.Configuration;

public class Bounty
{
	public static void create(String player,String target, double amount, boolean hidden){
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bounties" +File.separator +player.toLowerCase()+".efd");
		File db1 = new File(TrueEconomy.getStatic().getDataFolder().toString());
		File db2 = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bounties");
		if (!db1.exists())
			db1.mkdir();
		if (!db2.exists())
			db2.mkdir();
		if (db.exists())
			return;
		FileWriter fileWriter = null;
		BufferedWriter bufferWriter = null;
		try
		{
			fileWriter = new FileWriter(db);
			bufferWriter = new BufferedWriter(fileWriter);
			bufferWriter.append("t: "+target);
			bufferWriter.newLine();			
			bufferWriter.append("a: "+amount);
			bufferWriter.newLine();
			bufferWriter.append("h: "+hidden);
			bufferWriter.newLine();
			bufferWriter.append("c: _null");
			bufferWriter.newLine();
			bufferWriter.append("f: _null");
			bufferWriter.flush();
			bufferWriter.close();
			fileWriter.close();
		}
		catch (IOException ex)
		{
		}				
	}
	public static String[] getall(){
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bounties");
		if (!db.exists())
		{
			db.mkdir();
		}
		String[] array = db.list();
		return array;
	}
	public static String gett(String player){
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bounties" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String t =config.getString("t");
		return t;
	}
	public static boolean geth(String player){
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bounties" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String t =config.getString("h");
		return Boolean.parseBoolean(t);
	}
	public static double geta(String player){
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bounties" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String t =config.getString("a");
		return Double.parseDouble(t);
	}
	public static String getc(String player){
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bounties" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String t =config.getString("c");
		return t;
	}
	public static String getf(String player){
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bounties" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String t =config.getString("f");
		return t;
	}
	public static boolean exists(String player){
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bounties" +File.separator +player.toLowerCase()+".efd");
		return db.exists();
	}
	public static void setc(String player, String name){
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bounties" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);	
		config.load();
		config.setProperty("c", name.toLowerCase());
		config.save();
	}
	public static void setf(String player, String name){
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bounties" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);	
		config.load();
		config.setProperty("f", name.toLowerCase());
		config.save();
	}
	public static void rem(String player){
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bounties" +File.separator +player.toLowerCase()+".efd");
		if (db.exists())
			db.delete();
	}
}

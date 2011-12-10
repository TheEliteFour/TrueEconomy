package net.aesircraft.TrueEconomy.Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;


public class Players
{
	public static void write(String player){
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		File db1 = new File(TrueEconomy.getStatic().getDataFolder().toString());
		File db2 = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players");
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
			bufferWriter.append("a: 0");
			bufferWriter.newLine();			
			bufferWriter.append("c: 0");
			bufferWriter.newLine();
			bufferWriter.append("t: _null");
			bufferWriter.newLine();
			bufferWriter.append("k: _null");
			bufferWriter.newLine();
			bufferWriter.append("ld: 1");
			bufferWriter.newLine();
			bufferWriter.append("li: 1");
			bufferWriter.newLine();
			bufferWriter.append("lg: 1");
			bufferWriter.newLine();
			bufferWriter.append("le: 1");
			bufferWriter.newLine();
			bufferWriter.append("les: 1");
			bufferWriter.flush();
			bufferWriter.close();
			fileWriter.close();
		}
		catch (IOException ex)
		{
		}				
	}
	public static void add(String player,double amount){
		write(player);
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String a =config.getString("a");
		amount=amount+Double.parseDouble(a);
		config.setProperty("a", amount);
		config.save();
	}
	public static double sub(String player,double amount){
		write(player);
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String a =config.getString("a");
		amount=Double.parseDouble(a)-amount;
		double checked=0;
		if (amount<0){
			checked=Double.parseDouble(a);
			amount=0;
		}
		else{
			checked=amount;
		}
		config.setProperty("a", amount);
		config.save();
		return checked;
	}
	public static void addc(String player,double amount){
		write(player);
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String a =config.getString("c");
		amount=amount+Double.parseDouble(a);
		if (amount>1000){
			amount=1000;
		}
		config.setProperty("c", amount);
		config.save();
	}
	public static void setl(String player,double val[]){
		write(player);
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		config.setProperty("ld", val[0]);
		config.setProperty("li", val[1]);
		config.setProperty("lg", val[2]);
		config.setProperty("le", val[3]);
		config.setProperty("les", val[4]);
		config.save();
	}
	public static void upgrade(String player){
		write(player);
		String[] val=new String[4];
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		val[0]=config.getString("ld");
		val[1]=config.getString("li");
		val[2]=config.getString("lg");
		String a=Double.toString(get(player.toLowerCase()));
		String c=Double.toString(c(player.toLowerCase()));
		String t=gett(player.toLowerCase());
		String k=getk(player.toLowerCase());
		String check="";
		String check2="";
		if (config.getString("le")==null)
			check="";
		else
			check=config.getString("le");
		if (config.getString("les")==null)
			check2="";
		else
			check2=config.getString("les");
		
		if (check.equals("") || check2.equals("") || check.equals("1")|| check2.equals("1")){
			
		File db2 = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		File db3 = new File(TrueEconomy.getStatic().getDataFolder().toString());
		File db4 = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players");
		if (!db3.exists())
			db3.mkdir();
		if (!db4.exists())
			db4.mkdir();
		if (db2.exists())
			db.delete();
		FileWriter fileWriter = null;
		BufferedWriter bufferWriter = null;
		try
		{
			fileWriter = new FileWriter(db);
			bufferWriter = new BufferedWriter(fileWriter);
			bufferWriter.append("a: "+a);
			bufferWriter.newLine();			
			bufferWriter.append("c: "+c);
			bufferWriter.newLine();
			bufferWriter.append("t: "+t);
			bufferWriter.newLine();
			bufferWriter.append("k: "+k);
			bufferWriter.newLine();
			bufferWriter.append("ld: "+val[0]);
			bufferWriter.newLine();
			bufferWriter.append("li: "+val[1]);
			bufferWriter.newLine();
			bufferWriter.append("lg: "+val[2]);
			bufferWriter.newLine();
			bufferWriter.append("le: 5000");
			bufferWriter.newLine();
			bufferWriter.append("les: 10");
			bufferWriter.flush();
			bufferWriter.close();
			fileWriter.close();
		}
		catch (IOException ex)
		{
		}				
	
		}
		
	}
	public static double[] getl(String player){
		write(player);
		double[] val=new double[5];
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		val[0]=Double.parseDouble(config.getString("ld"));
		val[1]=Double.parseDouble(config.getString("li"));
		val[2]=Double.parseDouble(config.getString("lg"));
		val[3]=Double.parseDouble(config.getString("le"));
		val[4]=Double.parseDouble(config.getString("les"));
		return val;
	}
	public static void subc(String player,double amount){
		write(player);
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String a =config.getString("c");
		amount=-Double.parseDouble(a)-amount;
		if (amount<0){
			amount=0;
		}
		config.setProperty("c", amount);
		config.save();
	}
	public static double get(String player){
		write(player);
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String a =config.getString("a");
		double amount=Double.parseDouble(a);
		return amount;
	}
	public static double c(String player){
		write(player);
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String a =config.getString("c");
		double amount=Double.parseDouble(a);
		return amount;
	}
	public static String gett(String player){
		write(player);
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String t =config.getString("t");
		return t;
	}
	public static void sett(String player, String name){
		write(player);
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		config.setProperty("t",name);
		config.save();
		return;
	}
	public static void remt(String player){
		write(player);
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		config.setProperty("t","_null");
		config.save();
		return;
	}
	
	public static String getk(String player){
		write(player);
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String t =config.getString("k");
		return t;
	}
	public static void setk(String player, String name){
		write(player);
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		config.setProperty("k",name);
		config.save();
		return;
	}
	public static void remk(String player){
		write(player);
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "players" +File.separator +player.toLowerCase()+".efd");
		Configuration config = new Configuration(db);
		config.load();
		config.setProperty("k","_null");
		config.save();
		return;
	}
	public static void bal(Player player){
		player.sendMessage("§2Your balance is §b"+Form.form(get(player.getName())));
	}
	public static void balo(Player player,String player2){
		player.sendMessage("§2The balance of §b"+player2+"§2 is §b"+Form.form(get(player2.toLowerCase())));
	}
	public static void co(Player player,String player2){
		player.sendMessage("§2The credit of §b"+player2+"§2 is §b"+Form.form(get(player2.toLowerCase())));
	}
	public static void credit(Player player){
		player.sendMessage("§2Your credit is §b"+c(player.getName()));
	}
}

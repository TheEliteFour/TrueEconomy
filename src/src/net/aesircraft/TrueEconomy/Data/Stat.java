package net.aesircraft.TrueEconomy.Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;


public class Stat
{
	public static void write(){
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bank" +File.separator +"stat.efd");
		File db1 = new File(TrueEconomy.getStatic().getDataFolder().toString());
		File db2 = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bank");
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
			bufferWriter.append("i: 0");
			bufferWriter.newLine();			
			bufferWriter.append("o: 0");
			bufferWriter.newLine();
			bufferWriter.append("t: 0");
			bufferWriter.newLine();
			bufferWriter.flush();
			bufferWriter.close();
			fileWriter.close();
		}
		catch (IOException ex)
		{
		}				
	}
	public static void print(Player player){
		double[] vals=Stat.get();
		player.sendMessage("§2Bank's State");
		player.sendMessage("§2----------------------");
		player.sendMessage("§2Start  - §b"+Form.form2(1000000000));
		player.sendMessage("§2Banked - §b"+Form.form2(Bank.get()));
		player.sendMessage("§2Ratio  - §b"+Form.form2(Bank.ratio()));
		player.sendMessage("§2Income - §b"+Form.form2(vals[0]));
		player.sendMessage("§4Spent  - §b"+Form.form2(vals[1]));
		player.sendMessage("§eTaxes  - §b"+Form.form2(vals[2]));
		player.sendMessage("§3Gross  - §b"+Form.form2(vals[3]));
	}
	public static double[] get(){
		write();
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bank"+ File.separator +"stat"+".efd");
		Configuration config = new Configuration(db);
		config.load();
		double[] amount=new double[4];
		amount[0]=Double.parseDouble(config.getString("i"));
		amount[1]=Double.parseDouble(config.getString("o"));
		amount[2]=Double.parseDouble(config.getString("t"));
		amount[3]=((amount[0]+amount[2])-amount[1]);
		return amount;
	}
	
	public static void addi(double amount){
		write();
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bank"+ File.separator +"stat"+".efd");
		Configuration config = new Configuration(db);
		config.load();
		config.setProperty("i",config.getDouble("i",0)+amount);
		config.save();
	}
	public static void addo(double amount){
		write();
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bank"+ File.separator +"stat"+".efd");
		Configuration config = new Configuration(db);
		config.load();
		config.setProperty("o",config.getDouble("o",0)+amount);
		config.save();
	}
	public static void addt(double amount){
		write();
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "bank"+ File.separator +"stat"+".efd");
		Configuration config = new Configuration(db);
		config.load();
		config.setProperty("t",config.getDouble("t",0)+amount);
		config.save();
	}
}

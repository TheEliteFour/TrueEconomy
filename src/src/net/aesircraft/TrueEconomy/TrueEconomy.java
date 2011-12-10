package net.aesircraft.TrueEconomy;

import java.util.logging.Logger;
import net.aesircraft.Commands.*;
import net.aesircraft.TrueEconomy.Data.MonsterListener;
import net.aesircraft.TrueEconomy.Data.Sign;
import net.aesircraft.TrueEconomy.Data.SignListener;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author TheElite4
 * @version 1.0
 * Licensed Under CreativeCommons Attribution 3.0 Unported (CC BY 3.0)
 */
public class TrueEconomy extends JavaPlugin
{
    private static TrueEconomy instance = null;
	public static final Logger logger = Logger.getLogger("Minecraft");
	private final Sign Sign = new Sign(this);
	private final SignListener SignListener = new SignListener(this);
	private final MonsterListener MonsterListener = new MonsterListener(this);
	public static TrueEconomy getStatic()
			
	{
		return instance;
	}
	private void setStatic()
	{
		instance = this;
	}
	@Override
	public void onDisable()
	{
		logger.info("[TrueEconomy] Unloaded!");
	}

	@Override
	public void onEnable()
	{
		setStatic();
		PluginManager pm = getServer().getPluginManager();
		logger.info("[TrueEconomy] Loaded " + this.getDescription().getName() + " build " + this.getDescription().getVersion() + "!");
		getCommand("bank").setExecutor(new bank(this));
		getCommand("buystock").setExecutor(new buystock(this));
		getCommand("sellstock").setExecutor(new sellstock(this));
		getCommand("bal").setExecutor(new bal(this));
		getCommand("stocks").setExecutor(new stocks(this));
		getCommand("bounty").setExecutor(new bounty(this));
		getCommand("bountyboard").setExecutor(new bountyboard(this));
		getCommand("quitbounty").setExecutor(new quitbounty(this));
		getCommand("takebounty").setExecutor(new takebounty(this));
		getCommand("kickbounty").setExecutor(new kickbounty(this));
		getCommand("closebounty").setExecutor(new closebounty(this));
		getCommand("target").setExecutor(new target(this));
		getCommand("setbounty").setExecutor(new setbounty(this));
		getCommand("pay").setExecutor(new pay(this));
		getCommand("geyrir").setExecutor(new geyrir(this));
		getCommand("teyrir").setExecutor(new teyrir(this));
		getCommand("fees").setExecutor(new fees(this));
		getCommand("credit").setExecutor(new credit(this));
		getCommand("exp").setExecutor(new exp(this));
		pm.registerEvent(Event.Type.BLOCK_BREAK, Sign, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.SIGN_CHANGE, Sign, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, SignListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.ENTITY_DEATH, MonsterListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, MonsterListener, Event.Priority.Highest, this);
	}
	
	
}

package tw.longcat.lab.BrainMining;

import org.bukkit.plugin.java.JavaPlugin;


public class MAIN extends JavaPlugin {
	ClickListener clickListener;
	public void onEnable(){
		clickListener = new ClickListener();
		getServer().getPluginManager().registerEvents(clickListener, this);
	}
}

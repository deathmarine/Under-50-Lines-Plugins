package com.modcrafting.under50;

import java.io.File;
import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
public class DatLeave extends JavaPlugin implements Listener {
	File folder;
	public void onEnable(){
		this.getDataFolder().mkdir();
		File b = new File(this.getDataFolder(), "config.yml");
		if(!b.exists())
			try {
				b.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		folder = new File(this.getConfig().getString("path", "./"+this.getServer().getWorlds().get(0).getName()+"/players/"));
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		final String name = event.getPlayer().getName();
		this.getServer().getScheduler().runTaskLaterAsynchronously(this, new Runnable(){
			@Override
			public void run() {
				for(File file : folder.listFiles()){
					if(!file.isDirectory() && file.getName().toLowerCase().contains(name.toLowerCase()) && file.delete()){
						getLogger().info("Deleted: "+file.getName());
					}
				}
			}
		}, 10L);
	}
}

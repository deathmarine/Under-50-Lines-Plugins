package com.modcrafting.under50;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PWingTips extends JavaPlugin{
	Random gen = new Random();
	public void onEnable(){
		this.getDataFolder().mkdir();
		this.saveDefaultConfig();
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			@Override
			public void run() {
				List<String> list = getConfig().getStringList("MessageList");
				if(list==null||list.isEmpty())
					return;
				String message = list.get(gen.nextInt(list.size()));
				for(Player p:Bukkit.getOnlinePlayers()){
					if(p.hasPermission("pwingtips.announce")){
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
					}
				}
			}
			
		},2400L, 2400L);
	}
}

package com.modcrafting.under50;

import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathBan extends JavaPlugin implements Listener{
	HashMap<String,Long> names = new HashMap<String,Long>();
	String message;
	int punish;
	public void onEnable(){
        this.saveDefaultConfig();
		this.getServer().getPluginManager().registerEvents(this, this);
		message = this.getConfig().getString("Message","&4You must wait %time% mins before returning");
		punish=this.getConfig().getInt("Time.Minutes",5);
	}
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event){
		if(names.containsKey(event.getPlayer().getName())){
			long time = names.get(event.getPlayer().getName());
			if((System.currentTimeMillis()-time)/1000>punish*60){
				names.remove(event.getPlayer().getName());
			}else{
				int i = (int) Math.abs((((System.currentTimeMillis()-time)-(punish*60*1000))/1000)/60);
				if(i==0)i=1;
				event.disallow(Result.KICK_OTHER,ChatColor.translateAlternateColorCodes("&".toCharArray()[0], message.replaceAll("%time%",String.valueOf(i))));
			}
		}
	}
	@EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
		if(event.getEntity().hasPermission("deathban.override")) return;
		names.put(event.getEntity().getName(),System.currentTimeMillis());
		event.getEntity().kickPlayer(ChatColor.translateAlternateColorCodes("&".toCharArray()[0], message.replaceAll("%time%",String.valueOf(punish))));
	}
}

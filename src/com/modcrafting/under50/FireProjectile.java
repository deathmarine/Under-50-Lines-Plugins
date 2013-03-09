package com.modcrafting.under50;

import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FireProjectile extends JavaPlugin implements Listener{
	public void onEnable(){
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	@EventHandler
	public void onPlayer(PlayerInteractEvent event){
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR)){
			ThrownPotion tp = event.getPlayer().launchProjectile(ThrownPotion.class);
		}
	}

}

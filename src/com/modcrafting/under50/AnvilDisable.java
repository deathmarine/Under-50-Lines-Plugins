package com.modcrafting.under50;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AnvilDisable extends JavaPlugin implements Listener{
	public void onEnable(){
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		if(event.getAction()==Action.RIGHT_CLICK_BLOCK){
			Block b = event.getClickedBlock();
			if(b.getType()==Material.ANVIL
					&&!event.getPlayer().hasPermission("anvildisable.override")){
				event.getPlayer().closeInventory();
				event.setCancelled(true);
			}
		}
	}

}

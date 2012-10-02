package com.modcrafting.under50;
public class BedDigger extends org.bukkit.plugin.java.JavaPlugin implements org.bukkit.event.Listener{
	public void onEnable(){
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	@org.bukkit.event.EventHandler
	public void onBlockDamage(org.bukkit.event.block.BlockDamageEvent event){
		if(event.getBlock().getType().getId()==7&&event.getPlayer().hasPermission("beddigger.use")) event.setInstaBreak(true);
	}
}

package com.modcrafting.under50;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class DeadMoney extends JavaPlugin implements Listener{
	public net.milkbowl.vault.economy.Economy economy = null;
	public void onEnable(){
        this.saveDefaultConfig();
		this.getServer().getPluginManager().registerEvents(this, this);
		RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) economy = economyProvider.getProvider();
	}
	@EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
		String world = event.getEntity().getLocation().getWorld().getName();
		if(event.getEntity().hasPermission("deadmoney.override")) return;
		String name = event.getEntity().getName();
		double bal = this.economy.getBalance(name);
		double amtd = 0;
		if (this.getConfig().getBoolean(world+".percent", false)){
			amtd = bal * (Double.valueOf(this.getConfig().getDouble(world+".amount", 100) / 100));
		}else{
			amtd = Double.valueOf(this.getConfig().getDouble(world+".amount", 1000));
		}
		if(amtd < bal){
			economy.withdrawPlayer(name, amtd);
			String message = this.getConfig().getString(world+".playerdeathmessage","&8[&7Money&8]&7 %amt% was removed from your account on death.");
			if(message.contains("%amt%")) message = message.replaceAll("%amt%", String.valueOf((int) amtd));
			event.getEntity().sendMessage(ChatColor.translateAlternateColorCodes("&".toCharArray()[0], message));
			return;
		}
	}
}

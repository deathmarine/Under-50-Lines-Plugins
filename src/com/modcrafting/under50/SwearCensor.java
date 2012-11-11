package com.modcrafting.under50;

import java.util.List;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
public class SwearCensor extends JavaPlugin implements Listener{
	List<String> censor;
	String command;
	String replace;
	int level;
	public void onEnable(){
		this.saveDefaultConfig();
		this.getServer().getPluginManager().registerEvents(this, this);
		censor = this.getConfig().getStringList("Dictionary");
		command = this.getConfig().getString("Command","kick %player% Swearing");
		level = this.getConfig().getInt("ViolationLevel",1);
		replace = this.getConfig().getString("Replace","****");
	}
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onPlayerChat(AsyncPlayerChatEvent e){
		if(e.getPlayer().hasPermission("swearcensor.override")) return;
		String msg = new String(e.getMessage());
		int vlvl = 0;
		for(String word:censor){
			if(msg.toLowerCase().contains(word.toLowerCase())){
				msg=msg.replaceAll("(?i)"+word, replace);
				vlvl++;
			}
		}
		if(vlvl<=level) return;
		e.setMessage(msg);
		String cmd = new String(command);
		if(command.contains("%player%")) cmd=cmd.replace("%player%", e.getPlayer().getName());
		this.getServer().dispatchCommand(this.getServer().getConsoleSender(), cmd);
	}
}

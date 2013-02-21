package com.modcrafting.under50;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ClearItems extends JavaPlugin{
	@Override
    public boolean onCommand(CommandSender sender, Command command,
            String commandLabel, String[] args)
    {
		if(sender instanceof Player){
			Player player = (Player) sender;
			double space = 10;
			if(args.length>0){
				try{
					space = Double.parseDouble(args[0]);
				}catch(NumberFormatException nfe){
				}
			}
			int i=0;
			for(Entity e:player.getNearbyEntities(space, space, space)){
				if(!(e instanceof Player)){
					e.remove();
					i++;
				}
			}
			sender.sendMessage(ChatColor.GREEN+"Removed "+String.valueOf(i)+" Entities!");
			return true;
		}
		return false;
    }
}

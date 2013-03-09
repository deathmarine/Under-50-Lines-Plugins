package com.modcrafting.under50;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GriefNotify extends JavaPlugin{
	@Override
    public boolean onCommand(CommandSender sender, Command command,
            String commandLabel, String[] args)
    {
		if(!sender.hasPermission(command.getPermission()))
			sender.sendMessage("You do not have the required permissions.");
		
		if(sender instanceof Player){
			for(Player pr:this.getServer().getOnlinePlayers())
				if(pr.hasPermission(this.getName()+".Announce"))
					pr.sendMessage(ChatColor.AQUA+"[GriefNotify]]"
							+ChatColor.DARK_AQUA+sender.getName()
							+": @ "+((Player) sender).getLocation().toString());
			sender.sendMessage(ChatColor.AQUA+"[GriefNotify]]"
					+ChatColor.DARK_AQUA+"Your report has been sent.");
			
			return true;
		}
		return false;
    }

}

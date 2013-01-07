package com.modcrafting.under50;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
public class Durable extends JavaPlugin{
	public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
		if(!sender.hasPermission(command.getPermission())){
			sender.sendMessage(ChatColor.RED+"You do not have the required permissions.");
			return true;
		}
		if(!(sender instanceof Player)){
			sender.sendMessage(ChatColor.RED+"You might want to get in game to do that.");
			return true;
		}
		if(args.length<1)
			return true;
		Player player = (Player) sender;
		if(args[0].equalsIgnoreCase("get")){
			sender.sendMessage(ChatColor.GREEN+"Your item is : "+
		String.valueOf(player.getItemInHand().getDurability())+" / "+
		String.valueOf(player.getItemInHand().getType().getMaxDurability()));
		}
		if(args[0].equalsIgnoreCase("repair")){
			player.getItemInHand().setDurability((short) 0);
			sender.sendMessage(ChatColor.GREEN+"Your item is repaired.");
		}
		return true;	
	}
}
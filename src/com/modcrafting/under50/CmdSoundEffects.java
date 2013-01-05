package com.modcrafting.under50;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
public class CmdSoundEffects extends JavaPlugin{
	public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
		if(!sender.hasPermission(command.getPermission())){
			sender.sendMessage(ChatColor.RED + "You do not have the required permissions.");
			return true;
		}
		Location loc = null;
		if(sender instanceof BlockCommandSender)
			loc = ((BlockCommandSender) sender).getBlock().getLocation();
		else if(sender instanceof Player)
			loc = ((Player) sender).getLocation();
		if(loc==null){
			sender.sendMessage("You might want to get in game to do that.");
			return true;
		}
		if(args.length>1&&args[0].equalsIgnoreCase("list")){
			int i = 1;
			try{
				if(args.length>1) i=Integer.parseInt(args[1]);
			}catch(NumberFormatException nfe){}
			sender.sendMessage(ChatColor.GRAY+"---Sound ID List ---");
			for(int ii=(i-1)*10;ii<(10)*i;ii++)
				if(ii<159)
					sender.sendMessage(ChatColor.GREEN+"Sound ID#"+ChatColor.AQUA+String.valueOf(ii)+" "+Sound.values()[ii].toString());
			sender.sendMessage(ChatColor.GRAY+"Page ["+String.valueOf(i)+" of "+String.valueOf(16)+"]");
		}
		if(args.length>2){
			int id = 0;
			float vol = 0,pitch = 0;
			try{
				id=Integer.parseInt(args[0]);
				vol=Float.parseFloat(args[1]);
				pitch=Float.parseFloat(args[2]);
			}catch(NumberFormatException nfe){}
			for(Player player:this.getServer().getOnlinePlayers())
				player.playSound(loc, Sound.values()[id], vol, pitch);
		}
		return true;	
	}
}
//112 100 0 Scared the shit out of me.

package com.modcrafting.under50;

import java.util.Calendar;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
public class Fireworks extends JavaPlugin{
	int timer,id = 0;
	Random gen = new Random();
	public void onEnable(){
		id = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			@Override
			public void run() {
				if(Calendar.getInstance().get(Calendar.YEAR)>2012&&timer<600&&Bukkit.getServer().getOnlinePlayers().length>0){
					for(Player player:Bukkit.getServer().getOnlinePlayers()){
						for(int i = gen.nextInt(10);i>0;i--){
							Location loc = player.getLocation();
							Firework item = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
							FireworkMeta fwm = item.getFireworkMeta();
							fwm.setPower(gen.nextInt(4)+1);
							fwm.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.values()[gen.nextInt(FireworkEffect.Type.values().length)]).flicker(gen.nextBoolean()).trail(gen.nextBoolean()).withColor(Color.fromRGB(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255))).withFade(Color.fromRGB(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255))).build());
							item.setFireworkMeta(fwm);
						}
					}
					timer++;
					if(timer>600){
						Bukkit.getScheduler().cancelTask(id);
						Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("Fireworks"));
					}
				}
			}
		}, 20L, 20L);
	}
}

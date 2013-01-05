package com.modcrafting.under50;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class EchantmentRestriction extends JavaPlugin implements Listener{
	public void onEnable(){
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	@EventHandler
	public void onEnchant(EnchantItemEvent e){
		Map<Enchantment, Integer> ec=e.getEnchantsToAdd();
		if(ec.containsKey(Enchantment.LOOT_BONUS_MOBS)){
			//Limit level of enchantment
			int lvl = ec.get(Enchantment.LOOT_BONUS_MOBS);
			if(lvl>5)lvl=5;
			/*
			 * OR
			 */
		}else if(ec.containsKey(Enchantment.LOOT_BONUS_BLOCKS)){
			//Remove enchantment
			ec.remove(Enchantment.LOOT_BONUS_BLOCKS);
			
		}
	}
}

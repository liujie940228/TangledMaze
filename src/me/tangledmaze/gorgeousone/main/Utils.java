package me.tangledmaze.gorgeousone.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public abstract class Utils {

	private static ArrayList<Vector> DIRECTIONS = new ArrayList<>(Arrays.asList(
			new Vector( 1, 0,  0),
			new Vector( 1, 0,  1),
			new Vector( 0, 0,  1),
			new Vector(-1, 0,  1),
			new Vector(-1, 0,  0),
			new Vector(-1, 0, -1),
			new Vector( 0, 0, -1),
			new Vector( 1, 0, -1)));

	private static ArrayList<Material> UNSATABLE_SOLIDS = new ArrayList<>(Arrays.asList(
			Material.BROWN_MUSHROOM,
			Material.CACTUS,
			Material.COCOA,
			Material.CARPET,
			Material.CARROT,
			Material.DEAD_BUSH,
			Material.DOUBLE_PLANT,
			Material.FIRE,
			Material.GOLD_PLATE,
			Material.IRON_PLATE,
			Material.LADDER,
			Material.LONG_GRASS,
			Material.MELON_STEM,
			Material.POTATO,
			Material.PUMPKIN_STEM,
			Material.RED_MUSHROOM,
			Material.RED_ROSE,
			Material.SAPLING,
			Material.SIGN_POST,
			Material.SNOW,
			Material.STANDING_BANNER,
			Material.STONE_BUTTON,
			Material.STONE_PLATE,
			Material.SUGAR_CANE_BLOCK,
			Material.VINE,
			Material.WALL_BANNER,
			Material.WALL_SIGN,
			Material.WATER_LILY,
			Material.WHEAT,
			Material.WOOD_PLATE,
			Material.WOOD_BUTTON,
			Material.YELLOW_FLOWER));
	
	public static int maxBlockY(List<Location> points) {
		int maxY = -1;
		
		for(Location point : points)
			if(point.getBlockY() > maxY)
				maxY = point.getBlockY();
		
		return maxY;
	}
	
	public static boolean isReallySolid(Block b) {
		return b.getType().isSolid() && !UNSATABLE_SOLIDS.contains(b.getType());
	}
	
	public static void sendBlockLater(Player p, Location loc, Material mat) {
		BukkitRunnable r = new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				p.sendBlockChange(loc, mat, (byte) 0);
			}
		};
		r.runTask(TangledMain.getPlugin());
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Vector> getDirs() {
		return (ArrayList<Vector>) DIRECTIONS.clone();
	}
	
	public static Location getNearestSurface(Location loc) {
		Location iter = loc.clone();
		
		if(isReallySolid(iter.getBlock()))
			while(iter.getY() <= 255) {
				iter.add(0, 1, 0);
				
				if(!isReallySolid(iter.getBlock())) {
					iter.add(0, -1, 0);
					return iter;
				}
			}
		else 
			while(iter.getY() >= 0) {
				iter.add(0, -1, 0);
				
				if(isReallySolid(iter.getBlock()))
					return iter;
			}
		
		return loc;
	}
}
package me.tangledmaze.gorgeousone.shapes;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Chunk;
import org.bukkit.Location;

public class Brush implements Shape {

	@Override
	public HashMap<Chunk, ArrayList<Location>> getBorder() {
		return null;
	}

	@Override
	public HashMap<Chunk, ArrayList<Location>> getFill() {
		return null;
	}

	@Override
	public boolean contains(Location point) {
		return false;
	}

	@Override
	public boolean borderContains(Location point) {
		return false;
	}
}
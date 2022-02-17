package net.minecraft.src;

import java.util.List;
import java.util.HashMap;

public class TSGArrowManager{
	public static final TSGArrowManager instance = new TSGArrowManager();
	private static HashMap<Integer, Class> arrows;
	private static HashMap<Integer, Class> items;
	public
	TSGArrowManager(){
		arrows = new HashMap<Integer, Class>();
		items = new HashMap<Integer, Class>();
	}
	public static TSGArrowManager
	getInstance(){
		return instance;
	}
	public static void
	RegisterArrow(int id, int itemid, Class arr){
		arrows.put(id, arr);
		items.put(itemid,arr);
	}
	public static boolean
	MatchArrow(int id){
		if(arrows.get(id) != null){
			return true;
		}
		return false;
	}
	public static boolean
	MatchArrowById(int id){
		if(items.get(id) != null){
			return true;
		}
		return false;
	}
	public static Object
	CreateArrow(int id, World wc, double x, double y, double z){
		if(arrows.get(id) != null){
			return EntityList.createEntityOfType(arrows.get(id), wc, x,y,z);
		} else if(items.get(id) != null){
			return EntityList.createEntityOfType(items.get(id), wc, x,y,z);

		} else {
			return null;
		}
	}
	public static Object
	CreateArrow(World world, EntityPlayer player, int id, float str){
		Class arr = items.get(id);
		if( arr != null){
			return EntityList.createEntityOfType(arr, world, player, str * 3.0F );
		} else {
			return null;
		}
	}

}

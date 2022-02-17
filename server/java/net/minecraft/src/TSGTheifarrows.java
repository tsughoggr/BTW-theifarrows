package net.minecraft.src;


import net.minecraft.server.MinecraftServer;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;

public class TSGTheifarrows extends FCAddOn{
	public static TSGTheifarrows instance;
	public static final String theifarrowsVersion = "0.1";
	private Map<String, String> confprop;

	
	public static Item taItemArrowEnder;
	public static Item taItemArrowIgnite;
	public static Item taItemArrowRope;
	public static Item taItemArrowTorch;
	public static Item taItemArrowDousing;
	public static Item taItemArrowTorchNether;
	public static Item taItemArrowOrange;
	public static Item taItemFishDynamite;
	public static Item taItemWarcrime;
	public static Item taItemArrowTorchRedstone;
	public static Item taItemArrowPotion;
	public static Item taItemArrowPotionEmpty;

	public static Item taItemArrowSnow;
	public static Item taItemArrowSplitting;
	public static Item taItemArrowRising;
	public static Item taItemArrowAccelerator;

	public static Block taBlockAnchor;
	public
	TSGTheifarrows(){
		super("Theif Arrows", theifarrowsVersion, "TA");
		instance = this;
	}


	private void
	registerProperty(String st, int in){
		registerProperty(st, Integer.toString(in));
	}
	private void
	normalizeProperty(String prop, int def){
		if(confprop.get(prop) == null){
			confprop.put(prop, Integer.toString(def));
		}
		try { 
			Integer.parseInt(confprop.get(prop));
		} catch (NumberFormatException e){
			confprop.put(prop, Integer.toString(def));
		}
		

	}
	private int
	getIdFromProp(String prop){
		if(confprop == null)
			throw new InvalidConfigException("Config map is null after sanitization");
		if(confprop.get(prop) == null)
			throw new InvalidConfigException("Config property: " + prop +" is called but not defined");
		return Integer.parseInt(confprop.get(prop)); /*We would actually want this to crash if it throws NFE*/
	}



	public void
	PreInitialize(){
		registerProperty("taItemArrowEnder.itemID", 24022);
		registerProperty("taItemArrowIgnite.itemID", 24023);
		registerProperty("taItemArrowRope.itemID", 24024);
		registerProperty("taItemArrowTorch.itemID", 24025);
		registerProperty("taItemArrowDousing.itemID", 24026);
		registerProperty("taItemArrowTorchNether.itemID", 24027);
		registerProperty("taItemArrowOrange.itemID", 24028);
		registerProperty("taItemFishDynamite.itemID", 24029);
		registerProperty("taItemWarcrime.itemID", 24030);
		registerProperty("taItemArrowTorchRedstone.itemID", 24031);
		registerProperty("taItemArrowPotion.itemID", 24032);
		registerProperty("taItemArrowSnow.itemID", 24033);
		registerProperty("taItemArrowSplitting.itemID", 24034);
		registerProperty("taItemArrowRising.itemID", 24035);
		registerProperty("taItemArrowAccelerator.itemID", 24036);
		registerProperty("taItemArrowPotionEmpty.itemID", 24037);

		registerProperty("taEntityArrowEnder.entityID", 240);
		registerProperty("taEntityArrowIgnite.entityID", 241);
		registerProperty("taEntityArrowRope.entityID", 242);
		registerProperty("taEntityArrowTorch.entityID", 243);
		registerProperty("taEntityArrowDousing.entityID", 244);
		registerProperty("taEntityArrowTorchNether.entityID", 245);
		registerProperty("taEntityArrowOrange.entityID", 246);
		registerProperty("taEntityArrowWarcrime.entityID", 247);
		registerProperty("taEntityArrowTorchRedstone.entityID", 248);
		registerProperty("taEntityArrowPotion.entityID", 249);
		registerProperty("taEntityArrowSnow.entityID", 250);
		registerProperty("taEntityArrowRising.entityID", 251);
		registerProperty("taEntityArrowAccelerator.entityID", 252);
		registerProperty("taEntityArrowSplitting.entityID", 253);

		registerProperty("taBlockAnchor.blockID", 1753);

		registerProperty("enableBadRecipes", "false");

	}
	private void
	SanitizeConfigMap(){
		if(confprop == null){
			confprop = new HashMap<String, String>();
		}
		normalizeProperty("taItemArrowEnder.itemID", 24022);
		normalizeProperty("taItemArrowIgnite.itemID", 24023);
		normalizeProperty("taItemArrowRope.itemID", 24024);
		normalizeProperty("taItemArrowTorch.itemID", 24025);
		normalizeProperty("taItemArrowDousing.itemID", 24026);
		normalizeProperty("taItemArrowTorchNether.itemID", 24027);
		normalizeProperty("taItemArrowOrange.itemID", 24028);
		normalizeProperty("taItemFishDynamite.itemID", 24029);
		normalizeProperty("taItemWarcrime.itemID", 24030);
		normalizeProperty("taItemArrowTorchRedstone.itemID", 24031);
		normalizeProperty("taItemArrowPotion.itemID", 24032);
		normalizeProperty("taItemArrowSnow.itemID", 24033);
		normalizeProperty("taItemArrowSplitting.itemID", 24034);
		normalizeProperty("taItemArrowRising.itemID", 24035);
		normalizeProperty("taItemArrowAccelerator.itemID", 24036);
		normalizeProperty("taItemArrowPotionEmpty.itemID", 24037);

		normalizeProperty("taEntityArrowEnder.entityID", 240);
		normalizeProperty("taEntityArrowIgnite.entityID", 241);
		normalizeProperty("taEntityArrowRope.entityID", 242);
		normalizeProperty("taEntityArrowTorch.entityID", 243);
		normalizeProperty("taEntityArrowDousing.entityID", 244);
		normalizeProperty("taEntityArrowTorchNether.entityID", 245);
		normalizeProperty("taEntityArrowOrange.entityID", 246);
		normalizeProperty("taEntityArrowWarcrime.entityID", 247);
		normalizeProperty("taEntityArrowTorchRedstone.entityID", 248);
		normalizeProperty("taEntityArrowPotion.entityID", 249);
		normalizeProperty("taEntityArrowSnow.entityID", 250);
		normalizeProperty("taEntityArrowRising.entityID", 251);
		normalizeProperty("taEntityArrowAccelerator.entityID", 252);
		normalizeProperty("taEntityArrowSplitting.entityID", 253);

		normalizeProperty("taBlockAnchor.blockID", 1753);


	}
	public void
	Initialize(){
		FCAddOnHandler.LogMessage("Theif arrows  " + theifarrowsVersion + " initializing");
		confprop = loadConfigProperties();
		this.InitializeBlocks();
		this.InitializeItems();
		this.AddEntityMappings();
		this.RegisterArrows();
		this.replaceBlocks();
		TSGTheifarrowRecipes.addAllRecipes();
		if(confprop.get("enableBadRecipes").equals("true")){
			TSGTheifarrowRecipes.addBadRecipes();
		}
		FCAddOnHandler.LogMessage("Theifarrows initializaiton complete");
	}
	public void
	replaceBlocks(){
		Block.replaceBlock(FCBetterThanWolves.fcRopeBlock.blockID, TSGBlockRope.class, this, new Object[]{});
	}
	public void
	InitializeBlocks(){
		taBlockAnchor = new TSGBlockAnchor(getIdFromProp("taBlockAnchor.blockID")).setUnlocalizedName("taBlockAnchor").setCreativeTab(CreativeTabs.tabDecorations);
		
		new ItemBlock(getIdFromProp("taBlockAnchor.blockID") - 256);

	}
	public void
	InitializeItems(){
		Item.replaceItem(FCBetterThanWolves.fcItemCompositeBow.itemID,  TSGItemCompositeBow.class,this, new Object[]{});
		Item.replaceItem(FCBetterThanWolves.fcItemRope.itemID, TSGItemRope.class, this, new Object[]{});
		taItemArrowEnder = new TSGItemArrow(getIdFromProp("taItemArrowEnder.itemID")).setUnlocalizedName("taItemArrowEnder");
		taItemArrowIgnite = new TSGItemArrow(getIdFromProp("taItemArrowIgnite.itemID")).setUnlocalizedName("taItemArrowIgnite");
		taItemArrowRope = new TSGItemArrow(getIdFromProp("taItemArrowRope.itemID")).setUnlocalizedName("taItemArrowRope");
		taItemArrowTorch = new TSGItemArrow(getIdFromProp("taItemArrowTorch.itemID")).setUnlocalizedName("taItemArrowTorch");
		taItemArrowDousing = new TSGItemArrow(getIdFromProp("taItemArrowDousing.itemID")).setUnlocalizedName("taItemArrowDousing");
		taItemArrowTorchNether = new TSGItemArrow(getIdFromProp("taItemArrowTorchNether.itemID")).setUnlocalizedName("taItemArrowTorchNether");
		taItemArrowOrange = new TSGItemArrow(getIdFromProp("taItemArrowOrange.itemID")).setUnlocalizedName("taItemArrowOrange");
		taItemFishDynamite = new FCItemDynamite(getIdFromProp("taItemFishDynamite.itemID")).setUnlocalizedName("taItemFishDynamite");
		taItemWarcrime = new TSGItemArrow(getIdFromProp("taItemWarcrime.itemID")).setUnlocalizedName("taItemWarcrime");
		taItemArrowTorchRedstone = new TSGItemArrow(getIdFromProp("taItemArrowTorchRedstone.itemID")).setUnlocalizedName("taItemArrowTorchRedstone");

		taItemArrowPotion = new TSGItemArrowPotion(getIdFromProp("taItemArrowPotion.itemID")).setUnlocalizedName("taItemArrowPotionFilled");


		taItemArrowSnow = new TSGItemArrow(getIdFromProp("taItemArrowSnow.itemID")).setUnlocalizedName("taItemArrowSnow");
		taItemArrowSplitting = new TSGItemArrow(getIdFromProp("taItemArrowSplitting.itemID")).setUnlocalizedName("taItemArrowSplitting");
		taItemArrowRising = new TSGItemArrow(getIdFromProp("taItemArrowRising.itemID")).setUnlocalizedName("taItemArrowRising");
		taItemArrowAccelerator = new TSGItemArrow(getIdFromProp("taItemArrowAccelerator.itemID")).setUnlocalizedName("taItemArrowAccelerator");
		taItemArrowPotionEmpty = new TSGItemArrow(getIdFromProp("taItemArrowPotionEmpty.itemID")).setUnlocalizedName("taItemArrowPotion");

	}
	public void
	AddEntityMappings(){
		EntityList.addMapping(TSGEntityArrowEnder.class, "taEntityArrowEnder", getIdFromProp("taEntityArrowEnder.entityID"));
		EntityList.addMapping(TSGEntityArrowIgnite.class, "taEntityArrowIgnite", getIdFromProp("taEntityArrowIgnite.entityID"));
		EntityList.addMapping(TSGEntityArrowRope.class, "taEntityArrowRope", getIdFromProp("taEntityArrowRope.entityID"));
		EntityList.addMapping(TSGEntityArrowTorch.class, "taEntityArrowTorch", getIdFromProp("taEntityArrowTorch.entityID"));
		EntityList.addMapping(TSGEntityArrowDousing.class, "taEntityArrowDousing", getIdFromProp("taEntityArrowDousing.entityID"));
		EntityList.addMapping(TSGEntityArrowTorchNether.class, "taEntityArrowTorchNether", getIdFromProp("taEntityArrowTorchNether.entityID"));
		EntityList.addMapping(TSGEntityArrowOrange.class, "taEntityArrowOrange", getIdFromProp("taEntityArrowOrange.entityID"));
		EntityList.addMapping(TSGEntityArrowWarcrime.class, "taEntityArrowWarcrime", getIdFromProp("taEntityArrowWarcrime.entityID"));
		EntityList.addMapping(TSGEntityArrowTorchRedstone.class, "taEntityArrowTorchRedstone", getIdFromProp("taEntityArrowTorchRedstone.entityID"));
		EntityList.addMapping(TSGEntityArrowPotion.class, "taEntityArrowPotion", getIdFromProp("taEntityArrowPotion.entityID"));
		EntityList.addMapping(TSGEntityArrowSnow.class, "taEntityArrowSnow", getIdFromProp("taEntityArrowSnow.entityID"));
		EntityList.addMapping(TSGEntityArrowRising.class, "taEntityArrowRising", getIdFromProp("taEntityArrowRising.entityID"));
		EntityList.addMapping(TSGEntityArrowAccelerator.class, "taEntityArrowAccelerator", getIdFromProp("taEntityArrowAccelerator.entityID"));
		EntityList.addMapping(TSGEntityArrowSplitting.class, "taEntityArrowSplitting", getIdFromProp("taEntityArrowSplitting.entityID"));

	}
	public void
	RegisterArrows(){
		TSGArrowManager am = TSGArrowManager.getInstance();
		am.RegisterArrow(TSGEntityArrowEnder.GetVehicleSpawnPacketType(), taItemArrowEnder.itemID, TSGEntityArrowEnder.class);
		am.RegisterArrow(TSGEntityArrowIgnite.GetVehicleSpawnPacketType(), taItemArrowIgnite.itemID, TSGEntityArrowIgnite.class);
		am.RegisterArrow(TSGEntityArrowRope.GetVehicleSpawnPacketType(), taItemArrowRope.itemID, TSGEntityArrowRope.class);
		am.RegisterArrow(TSGEntityArrowTorch.GetVehicleSpawnPacketType(), taItemArrowTorch.itemID, TSGEntityArrowTorch.class);
		am.RegisterArrow(TSGEntityArrowDousing.GetVehicleSpawnPacketType(), taItemArrowDousing.itemID, TSGEntityArrowDousing.class);
		am.RegisterArrow(TSGEntityArrowTorchNether.GetVehicleSpawnPacketType(), taItemArrowTorchNether.itemID, TSGEntityArrowTorchNether.class);
		am.RegisterArrow(TSGEntityArrowOrange.GetVehicleSpawnPacketType(), taItemArrowOrange.itemID, TSGEntityArrowOrange.class);
		am.RegisterArrow(TSGEntityArrowWarcrime.GetVehicleSpawnPacketType(), taItemWarcrime.itemID, TSGEntityArrowWarcrime.class);
		am.RegisterArrow(TSGEntityArrowTorchRedstone.GetVehicleSpawnPacketType(), taItemArrowTorchRedstone.itemID, TSGEntityArrowTorchRedstone.class);
		am.RegisterArrow(TSGEntityArrowPotion.GetVehicleSpawnPacketType(), taItemArrowPotion.itemID, TSGEntityArrowPotion.class);
		am.RegisterArrow(TSGEntityArrowSnow.GetVehicleSpawnPacketType(), taItemArrowSnow.itemID, TSGEntityArrowSnow.class);
		am.RegisterArrow(TSGEntityArrowRising.GetVehicleSpawnPacketType(), taItemArrowRising.itemID, TSGEntityArrowRising.class);
		am.RegisterArrow(TSGEntityArrowAccelerator.GetVehicleSpawnPacketType(), taItemArrowAccelerator.itemID, TSGEntityArrowAccelerator.class);
		am.RegisterArrow(TSGEntityArrowSplitting.GetVehicleSpawnPacketType(), taItemArrowSplitting.itemID, TSGEntityArrowSplitting.class);

	}

	public void
	OnLanguageLoaded(StringTranslate st){
		Properties t = st.GetTranslateTable();

		t.put(taItemArrowEnder.getUnlocalizedName() + ".name", "Ender Arrow");
		t.put(taItemArrowIgnite.getUnlocalizedName() + ".name", "Ignition Arrow");
		t.put(taItemArrowRope.getUnlocalizedName() + ".name", "Rope Arrow");
		t.put(taBlockAnchor.getUnlocalizedName() + ".name", "Rope Arrow Anchor Block");
		t.put(taItemArrowTorch.getUnlocalizedName() + ".name", "Torch Arrow");
		t.put(taItemArrowDousing.getUnlocalizedName() + ".name", "Dousing Arrow");
		t.put(taItemArrowTorchNether.getUnlocalizedName() + ".name", "Torch Arrow");
		t.put(taItemArrowOrange.getUnlocalizedName() + ".name", "Defoliant Arrow");
		t.put(taItemFishDynamite.getUnlocalizedName() + ".name", "Fish Dynamite");
		t.put(taItemWarcrime.getUnlocalizedName() + ".name", "TKMIZ Arrow");
		t.put(taItemArrowTorchRedstone.getUnlocalizedName() + ".name", "Redstone Torch Arrow");
		t.put(taItemArrowPotion.getUnlocalizedName() + ".name", "Potion Arrow");

		t.put(taItemArrowSnow.getUnlocalizedName() + ".name", "Snow Arrow");
		t.put(taItemArrowSplitting.getUnlocalizedName() + ".name", "Splinter Arrow");
		t.put(taItemArrowRising.getUnlocalizedName() + ".name", "Rising Arrow");
		t.put(taItemArrowAccelerator.getUnlocalizedName() + ".name", "Dragon Arrow");

		t.put(taItemArrowPotionEmpty.getUnlocalizedName() + ".name", "Empty Potion Arrow");
	}

	/*CLIENT ONLY*/

}

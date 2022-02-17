package net.minecraft.src;

public class TSGGloryhole extends FCAddOn{
	public static Item ghItemBlowpipeWorkable;
	public TSGGloryhole(){
		super("Dummy GH ", "0.0", "GH");
	}
	public void
	Initialize(){
		FCAddOnHandler.LogMessage("If you see this message you forgot to remove the dummy Gloryholes mod");
		ghItemBlowpipeWorkable = new Item(25000).setUnlocalizedName("apple");

	}

}

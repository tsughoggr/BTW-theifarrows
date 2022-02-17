package net.minecraft.src;

public class TSGItemArrowPotion extends ItemPotion {
	private Icon icon;

	public
	TSGItemArrowPotion(int id){
		super(id);
	 	SetBuoyant();
	 	SetBellowsBlowDistance( 1 );
	 	SetIncineratedInCrucible();
		this.maxStackSize = 8;
	 	SetFurnaceBurnTime( FCEnumFurnaceBurnTime.SHAFT );
	 	SetFilterableProperties( m_iFilterable_Narrow );
	 		 	
	 	setCreativeTab( CreativeTabs.tabCombat );
	}


	public ItemStack
	onItemEaten(ItemStack is, World world, EntityPlayer player){
		return is;
	}
	public ItemStack
	onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
		return par1ItemStack;
	}
	public boolean
	onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
		return false;
	}
	public String
	getItemDisplayName(ItemStack is){
		String rs = super.getItemDisplayName(is);
		if(rs != null){
			return rs + " Arrow";
		} else {
			return null;
		}
	}


}

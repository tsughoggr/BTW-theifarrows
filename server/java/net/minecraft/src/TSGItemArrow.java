package net.minecraft.src;


public class TSGItemArrow extends Item {

	 public TSGItemArrow( int iItemID )
	 {
	 	super( iItemID );
	 	
	 	SetBuoyant();
	 	SetBellowsBlowDistance( 1 );
	 	SetIncineratedInCrucible();
	 	SetFurnaceBurnTime( FCEnumFurnaceBurnTime.SHAFT );
	 	SetFilterableProperties( m_iFilterable_Narrow );
	 		 	
	 	setCreativeTab( CreativeTabs.tabCombat );
	 }
}

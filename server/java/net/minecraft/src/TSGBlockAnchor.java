package net.minecraft.src;

import java.util.Random;

public class TSGBlockAnchor extends Block{ 

	public
	TSGBlockAnchor(int id){
		super(id, Material.wood);
		setTickRandomly(true);
		SetFireProperties(FCEnumFlammability.WICKER);
	}
	public boolean
	renderAsNormalBlock() {
		return false;
	}
	public boolean
	isOpaqueCube(){
		return false;
	}
	public boolean
	HasLargeCenterHardPointToFacing(IBlockAccess ba, int i, int j, int k, int face, boolean bt){
		return false;
	}
	public void
	updateTick(World world, int i, int j, int k, Random rn){
		if(world.getBlockId(i+1,j,k) == 0 &&
			world.getBlockId(i-1,j,k) == 0 &&
			world.getBlockId(i,j+1,k) == 0 &&
			world.getBlockId(i,j,k+1) == 0 &&
			world.getBlockId(i,j,k-1) == 0){
				world.setBlockToAir(i,j,k);
				return;
			}

		int md = world.getBlockMetadata(i,j,k);
		if(md==15){
			world.setBlockToAir(i,j,k);
		}else{
			world.setBlockMetadataWithNotify(i,j,k,md + 1);
			world.scheduleBlockUpdate(i,j,k,blockID, 40);
		}
	}
	public void
	RandomUpdateTick(World world, int i, int j, int k, Random rn){
		if(!world.IsUpdateScheduledForBlock(i,j,k,blockID)){
			world.scheduleBlockUpdate(i,j,k,blockID, 40);
		}
	}
	public void
	onNeighborBlockChange(World world, int i, int j, int k, int bid){
		if(world.getBlockId(i+1,j,k) == 0 &&
			world.getBlockId(i-1,j,k) == 0 &&
			world.getBlockId(i,j+1,k) == 0 &&
			world.getBlockId(i,j,k+1) == 0 &&
			world.getBlockId(i,j,k-1) == 0){
				world.setBlockToAir(i,j,k);
		}
	}
	public int 
	idDropped(int id, Random rn, int fm){
		return 0;
	}
	/*Taken from the Anchor class which is not polymorphic enough for me to reuse*/
	public boolean onBlockActivated( World world, int i, int j, int k, EntityPlayer player, int iFacing, float fXClick, float fYClick, float fZClick )
	{
		ItemStack playerEquippedItem = player.getCurrentEquippedItem();
		
		if ( playerEquippedItem != null )
		{
			return false;
		}
		
		RetractRope( world, i, j, k, player );
		
		return true;		
	}
	void RetractRope( World world, int i, int j, int k, EntityPlayer entityPlayer )
	{
		// scan downward towards bottom of rope
		
		for ( int tempj = j - 1; tempj >= 0; tempj-- )
		{
			int iTempBlockID = world.getBlockId( i, tempj, k );
			
			if ( iTempBlockID == FCBetterThanWolves.fcRopeBlock.blockID )
			{
				if ( world.getBlockId( i, tempj - 1, k ) != FCBetterThanWolves.fcRopeBlock.blockID )
				{
					// we've found the bottom of the rope
					
					AddRopeToPlayerInventory( world, i, j, k, entityPlayer );
					
					Block targetBlock = FCBetterThanWolves.fcRopeBlock;
					
					if ( !world.isRemote )
					{					
						// destroy the block
						
						world.playAuxSFX( 2001, i, j, k, iTempBlockID );
						
						world.setBlockWithNotify( i, tempj, k, 0 );
					}					
					
					break;
				}
			}
			else
			{
				break;
			}
		}		
	}
	
	private void AddRopeToPlayerInventory( World world, int i, int j, int k, EntityPlayer entityPlayer )
	{
		ItemStack ropeStack = new ItemStack( FCBetterThanWolves.fcItemRope );
		
		if ( entityPlayer.inventory.addItemStackToInventory( ropeStack ) )
		{
			world.playSoundAtEntity( entityPlayer, "random.pop", 0.2F, 
				( ( world.rand.nextFloat() - world.rand.nextFloat() ) * 0.7F + 1F ) * 2F);
		}
		else
		{
			FCUtilsItem.EjectStackWithRandomOffset( world, i, j, k, ropeStack );
		}
	}

	public MovingObjectPosition
	collisionRayTrace( World world, int i, int j, int k, Vec3 startRay, Vec3 endRay ){
		FCModelBlock model;
		model = new FCModelBlock();
		model.AddBox(0D, 7D/16D, 7D/16D, 7D/16D,9D/16D, 9D/16D );
		model.AddBox(7D/16D, 7D/16D, 0D, 9D/16D, 9D/16D, 7D/16D);
		model.AddBox(9D/16D, 7D/16D,7D/16D,1D,9D/16D,9D/16D );
		model.AddBox(7D/16D,7D/16D,9D/16D,9D/16D, 9D/16D,1D);
		model.AddBox(7D/16D,0D,7D/16D,9D/16D,9D/16D,9D/16D);
		return model.CollisionRayTrace(world,i,j,k,startRay, endRay);
	}

	public AxisAlignedBB
	GetBlockBoundsFromPoolBasedOnState( IBlockAccess blockAccess, int i, int j, int k ){
		return AxisAlignedBB.getAABBPool().getAABB(7D/16D,7D/16D,7D/16D,9D/16D,9D/16D,9D/16D);
	}
}

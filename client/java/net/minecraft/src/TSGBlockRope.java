package net.minecraft.src;

public class TSGBlockRope extends FCBlockRope {
	public TSGBlockRope(int id){
		super(id);
	}
	public void
	onNeighborBlockChange(World world, int i, int j, int k, int bid){
		int abid = world.getBlockId(i,j+1,k);
		if(!(abid == TSGTheifarrows.taBlockAnchor.blockID)){
			super.onNeighborBlockChange(world,i,j,k,bid);
		}
	}
	public boolean
	canPlaceBlockAt(World world, int i, int j, int k){
		if(!(world.getBlockId(i,j+1,k) == TSGTheifarrows.taBlockAnchor.blockID)){
			return super.canPlaceBlockAt(world, i,j,k);
		}
		return true;
	}


}

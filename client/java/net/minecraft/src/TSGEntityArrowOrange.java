package net.minecraft.src;

import java.util.*;
import java.util.stream.IntStream;

public class TSGEntityArrowOrange extends TSGEntityArrow implements FCIEntityPacketHandler {

	public 
	TSGEntityArrowOrange(World world){
		super(world);
		this.canBePickedUp = 0;

	}
	public
	TSGEntityArrowOrange(World world, double d, double d1, double d2){
		super(world, d,d1,d2);
		this.canBePickedUp = 0;

	}
	public
	TSGEntityArrowOrange(World world, EntityLiving el, float f){
		super(world, el, f);
		this.canBePickedUp = 0;

	}


	public Item GetCorrespondingItem(){
		return TSGTheifarrows.taItemArrowOrange;
	}

	 
	public int GetTrackerViewDistance()
	{
		return 64;
	}
	
	
	public int GetTrackerUpdateFrequency()
	{
		return 20;
	}
	
	
	public boolean GetTrackMotion()
	{
		return false;
	}
	
	
	public boolean ShouldServerTreatAsOversized()
	{
		return false;
	}
	protected float
	GetDamageMultiplier(){
		return 0.8F;
	}
	public void
	defoliate_do(int x, int y, int z){
		if(Block.blocksList[worldObj.getBlockId(x,y,z)] instanceof FCBlockLeaves ||Block.blocksList[worldObj.getBlockId(x,y,z)] instanceof FCBlockVine || Block.blocksList[worldObj.getBlockId(x,y,z)] instanceof FCBlockPlants  ){
			worldObj.setBlockToAir(x,y,z);
		} else if(Block.blocksList[worldObj.getBlockId(x,y,z)] instanceof FCBlockFarmland || worldObj.getBlockId(x,y,z) == Block.grass.blockID){
			worldObj.setBlock(x,y,z,Block.dirt.blockID);
		} else if(worldObj.getBlockId(x,y,z) == FCBetterThanWolves.fcBlockGrassSlab.blockID){
			worldObj.setBlock(x,y,z,FCBetterThanWolves.fcBlockDirtSlab.blockID);
		}
	}
	public void
	defoliate(int x, int y, int z){
		int[] ja = IntStream.rangeClosed(-2,2).toArray();
		int[] ka = IntStream.rangeClosed(-2,2).toArray();
		int[] ia = IntStream.rangeClosed(-2,2).toArray();
		for(int j:ja){
			for(int k:ka){
				for(int i:ia){
					defoliate_do(x+i,y+j,z+k);
				}
			}
		}
	}

	public void
	onBlockImpact(MovingObjectPosition pos){
		defoliate(pos.blockX, pos.blockY, pos.blockZ );
		for (int var2 = 0; var2 < 32; ++var2)
		{
			this.worldObj.spawnParticle("flame", this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ, this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian());
		}
	}

	public void
	onEntityImpact(Entity ent){
		defoliate((int)ent.posX, (int)ent.posY, (int)ent.posZ );
		this.setDead();

		for (int var2 = 0; var2 < 32; ++var2)
		{
			this.worldObj.spawnParticle("flame", this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ, this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian());
		}
	}

	public Packet 
	GetSpawnPacketForThisEntity(){
		return new Packet23VehicleSpawn(this, this.GetVehicleSpawnPacketType(), shootingEntity == null ?  entityId : shootingEntity.entityId);
	}
	public static int
	GetVehicleSpawnPacketType(){
		return 116;
	}

}

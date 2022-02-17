package net.minecraft.src;

import java.util.*;
import java.util.stream.IntStream;

public class TSGEntityArrowSnow extends TSGEntityArrow implements FCIEntityPacketHandler {

	public 
	TSGEntityArrowSnow(World world){
		super(world);
	}
	public
	TSGEntityArrowSnow(World world, double d, double d1, double d2){
		super(world, d,d1,d2);
	}
	public
	TSGEntityArrowSnow(World world, EntityLiving el, float f){
		super(world, el, f);

	}


	public Item GetCorrespondingItem(){
		return TSGTheifarrows.taItemArrowSnow;
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
	extinguishBlockAt(int x, int y,int z){
		if(FCBlockSnowCover.CanSnowCoverReplaceBlock(worldObj, x,y,z) && Block.blocksList[Block.snow.blockID].canPlaceBlockAt(worldObj,x,y,z)){
			worldObj.setBlock(x,y,z,Block.snow.blockID);
		} else if(worldObj.getBlockId(x,y,z) == Block.waterMoving.blockID){
			worldObj.setBlock(x,y,z,Block.ice.blockID);
		}
			

	}
	public void
	extinguishBlocksAndEntitiesInRange(int x, int y, int z, int fc){
		List e2e = worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getAABBPool().getAABB(x - ((fc != 4 && fc != 5)?2:0), y - ((fc != 0 && fc != 1)?2:0), z - ((fc!=3 &&fc!=2)?2:0),x + ((fc != 4 && fc != 5)?2:1), y + ((fc != 0 && fc != 1)?2:1), z + ((fc!=3 &&fc!=2)?2:1 )));
		for(Object o:e2e){
			Entity e = (Entity)o;
			e.extinguish();
			if(e instanceof EntityBlaze || e instanceof EntityMagmaCube){
				e.attackEntityFrom(DamageSource.causeArrowDamage(this, ((this.shootingEntity == null)?this:this.shootingEntity)), 8);
			}
		}
		int[] ja;
		int[] ka;
		int[] ia;
		/**/
		switch(fc){
			case 0:
				ja = IntStream.rangeClosed(-2,0).toArray();
				ka = IntStream.rangeClosed(-2,2).toArray();
				ia = IntStream.rangeClosed(-2,2).toArray();
				break;
			case 1:
				ja = IntStream.rangeClosed(0,2).toArray();
				ka = IntStream.rangeClosed(-2,2).toArray();
				ia = IntStream.rangeClosed(-2,2).toArray();
				break;
			case 2:
				ja = IntStream.rangeClosed(-2,2).toArray();
				ka = IntStream.rangeClosed(-2,0).toArray();
				ia = IntStream.rangeClosed(-2,2).toArray();
				break;
			case 3:
				ja = IntStream.rangeClosed(-2,2).toArray();
				ka = IntStream.rangeClosed(0,2).toArray();
				ia = IntStream.rangeClosed(-2,2).toArray();
				break;
			case 4:
				ja = IntStream.rangeClosed(-2,2).toArray();
				ka = IntStream.rangeClosed(-2,2).toArray();
				ia = IntStream.rangeClosed(-2,0).toArray();
				break;
			default:
				ja = IntStream.rangeClosed(-2,2).toArray();
				ka = IntStream.rangeClosed(-2,2).toArray();
				ia = IntStream.rangeClosed(0,2).toArray();
				break;

		}
		for(int i:ia){
			for( int j:ja){
				for(int k:ka){
					extinguishBlockAt(x + i, y + j, z + k);
				}
			}
		}
	}
	public void
	onBlockImpact(MovingObjectPosition pos){
		if(!worldObj.isRemote){
			extinguishBlocksAndEntitiesInRange(pos.blockX, pos.blockY, pos.blockZ, pos.sideHit);
			this.setDead();
		}
		for (int var2 = 0; var2 < 32; ++var2)
		{
			this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ, this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian());
		}
	}

	public void
	onEntityImpact(Entity ent){
		if(!worldObj.isRemote){
			extinguishBlocksAndEntitiesInRange((int)ent.posX, (int)ent.posY, (int)ent.posZ, 1); /*Always treat entities as facing down*/
			if(ent instanceof EntityBlaze || ent instanceof EntityMagmaCube){
				ent.attackEntityFrom(DamageSource.causeArrowDamage(this, ((this.shootingEntity == null)?this:this.shootingEntity)), 100);
			}
			this.setDead();
		}
		for (int var2 = 0; var2 < 32; ++var2)
		{
			this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ, this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian());
		}
	}

	public Packet 
	GetSpawnPacketForThisEntity(){
		return new Packet23VehicleSpawn(this, this.GetVehicleSpawnPacketType(), shootingEntity == null ?  entityId : shootingEntity.entityId);
	}
	public static int
	GetVehicleSpawnPacketType(){
		return 120;
	}

}

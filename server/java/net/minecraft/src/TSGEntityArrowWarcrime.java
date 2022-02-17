package net.minecraft.src;

import java.util.*;
import java.util.stream.IntStream;

public class TSGEntityArrowWarcrime extends TSGEntityArrow implements FCIEntityPacketHandler {

	public 
	TSGEntityArrowWarcrime(World world){
		super(world);
		this.canBePickedUp = 0;

	}
	public
	TSGEntityArrowWarcrime(World world, double d, double d1, double d2){
		super(world, d,d1,d2);
		this.canBePickedUp = 0;

	}
	public
	TSGEntityArrowWarcrime(World world, EntityLiving el, float f){
		super(world, el, f);
		this.canBePickedUp = 0;

	}


	public Item GetCorrespondingItem(){
		return TSGTheifarrows.taItemWarcrime;
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
	impactAtPoint(int x, int y, int z){
		Random rn = new Random();
		int[] ia = rn.ints(7,2,6).toArray();
		for(int i:ia){
			FCUtilsBlockPos offsetPos = new FCUtilsBlockPos(0,0,0,i);

			FCEntityDynamite ent = (FCEntityDynamite)EntityList.createEntityOfType(FCEntityDynamite.class, worldObj, x + ( offsetPos.i * 0.6D ) + 0.5D ,y + ( offsetPos.j * 0.6D ) + 0.5D ,z + ( offsetPos.k * 0.6D ) + 0.5D,TSGTheifarrows.taItemFishDynamite.itemID );
			double ang = Math.toRadians(360D * rn.nextGaussian());
			int mag = rn.nextInt(3) + 1;
			ent.setThrowableHeading( Math.cos(ang) / mag  , .2F , Math.sin(ang) / mag ,1.11F,3F);
			ent.m_iFuse = 100 + rn.nextInt(250);
			worldObj.spawnEntityInWorld(ent);
		}
	}
	public void
	onBlockImpact(MovingObjectPosition pos){
 		if(!worldObj.isRemote){
			if(worldObj.getBlockId(pos.blockX, pos.blockY+1, pos.blockZ) == 0){	
				impactAtPoint(pos.blockX, pos.blockY, pos.blockZ);
			}
			this.setDead();
		}
	}

	public void
	onEntityImpact(Entity ent){

		if(!worldObj.isRemote){
			impactAtPoint((int)ent.posX, (int)ent.posY, (int)ent.posZ);
			this.setDead();
		}
	}

	public Packet 
	GetSpawnPacketForThisEntity(){
		return new Packet23VehicleSpawn(this, this.GetVehicleSpawnPacketType(), shootingEntity == null ?  entityId : shootingEntity.entityId);
	}
	public static int
	GetVehicleSpawnPacketType(){
		return 117;
	}

}

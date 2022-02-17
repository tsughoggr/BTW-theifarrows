package net.minecraft.src;

import java.util.*;
import java.util.stream.IntStream;

public class TSGEntityArrowTorch extends TSGEntityArrow implements FCIEntityPacketHandler {
	private int
	numrope;
	public 
	TSGEntityArrowTorch(World world){
		super(world);
	}
	public
	TSGEntityArrowTorch(World world, double d, double d1, double d2){
		super(world, d,d1,d2);
	}
	public
	TSGEntityArrowTorch(World world, EntityLiving el, float f){
		super(world, el, f);

	}


	public Item GetCorrespondingItem(){
		return TSGTheifarrows.taItemArrowTorch;
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
	onBlockImpact(MovingObjectPosition pos){
		if(!worldObj.isRemote){
			if(worldObj.getBlockId(pos.blockX, pos.blockY, pos.blockZ) == 0){
				worldObj.setBlock(pos.blockX, pos.blockY, pos.blockZ, FCBetterThanWolves.fcBlockTorchFiniteBurning.blockID);
			} else {
				int xh, yh, zh;
				xh=yh=zh=0;
				switch(pos.sideHit){
					case 0:
						--yh;
						break;
					case 1:
						++yh;
						break;
					case 2:
						--zh;
						break;
					case 3:
						++zh;
						break;
					case 4:
						--xh;
						break;
					default:
						++xh;
						break;
		
				}
				if(worldObj.getBlockId(pos.blockX + xh, pos.blockY + yh, pos.blockZ + zh) == 0){
					worldObj.setBlock(pos.blockX + xh, pos.blockY + yh, pos.blockZ + zh, FCBetterThanWolves.fcBlockTorchFiniteBurning.blockID);
				}
			}
			this.setDead();
		}
	}

	public void
	onEntityImpact(Entity ent){}

	public Packet 
	GetSpawnPacketForThisEntity(){
		return new Packet23VehicleSpawn(this, this.GetVehicleSpawnPacketType(), shootingEntity == null ?  entityId : shootingEntity.entityId);
	}
	public static int
	GetVehicleSpawnPacketType(){
		return 113;
	}

}

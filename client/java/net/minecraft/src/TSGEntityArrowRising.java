package net.minecraft.src;

import java.util.*;
import java.util.stream.IntStream;

public class TSGEntityArrowRising extends TSGEntityArrow implements FCIEntityPacketHandler {
	private int
	numrope;
	public 
	TSGEntityArrowRising(World world){
		super(world);
	}
	public
	TSGEntityArrowRising(World world, double d, double d1, double d2){
		super(world, d,d1,d2);
	}
	public
	TSGEntityArrowRising(World world, EntityLiving el, float f){
		super(world, el, f);

	}


	public Item GetCorrespondingItem(){
		return TSGTheifarrows.taItemArrowRising;
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
		return 1.2F;
	}

	public void
	onBlockImpact(MovingObjectPosition pos){}

	public void
	onEntityImpact(Entity ent){}

	public void
	onUpdate(){
		if(!this.inGround){
			this.motionY *= 1.01F;
			this.motionY += 0.301F;
		}
		super.onUpdate();
	}
	public Packet 
	GetSpawnPacketForThisEntity(){
		return new Packet23VehicleSpawn(this, this.GetVehicleSpawnPacketType(), shootingEntity == null ?  entityId : shootingEntity.entityId);
	}
	public static int
	GetVehicleSpawnPacketType(){
		return 121;
	}

}

package net.minecraft.src;

import java.util.*;
import java.util.stream.IntStream;

public class TSGEntityArrowAccelerator extends TSGEntityArrow implements FCIEntityPacketHandler {
	private int
	numrope;
	public 
	TSGEntityArrowAccelerator(World world){
		super(world);
		this.setKnockbackStrength(5);
	}
	public
	TSGEntityArrowAccelerator(World world, double d, double d1, double d2){
		super(world, d,d1,d2);
		this.setKnockbackStrength(5);
	}
	public
	TSGEntityArrowAccelerator(World world, EntityLiving el, float f){
		super(world, el, f);
		this.setKnockbackStrength(5);

	}


	public Item GetCorrespondingItem(){
		return TSGTheifarrows.taItemArrowAccelerator;
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
			this.motionX *= 1.4F;
			this.motionZ *= 1.4F;
			this.motionY *= 1.3F;

		}
		super.onUpdate();
	}
	public Packet 
	GetSpawnPacketForThisEntity(){
		return new Packet23VehicleSpawn(this, this.GetVehicleSpawnPacketType(), shootingEntity == null ?  entityId : shootingEntity.entityId);
	}
	public static int
	GetVehicleSpawnPacketType(){
		return 122;
	}

}

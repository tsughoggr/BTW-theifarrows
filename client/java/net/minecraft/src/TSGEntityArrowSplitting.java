package net.minecraft.src;

import java.util.*;
import java.util.stream.IntStream;

public class TSGEntityArrowSplitting extends TSGEntityArrow implements FCIEntityPacketHandler {

	private ItemStack[]
	subarrows;
	private int
	timer;
	private boolean
	forever;

	public 
	TSGEntityArrowSplitting(World world){
		super(world);

	}
	public
	TSGEntityArrowSplitting(World world, double d, double d1, double d2){
		super(world, d,d1,d2);

	}
	public
	TSGEntityArrowSplitting(World world, EntityLiving el, float f){
		super(world, el, f);


	}

	public void
	writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setInteger("numsar", subarrows.length);
		tag.setInteger("timer", timer);
		tag.setInteger("forever", forever?1:0);
		for(int i=0;i<subarrows.length;++i){
			tag.setCompoundTag("sar_" + i, subarrows[i].writeToNBT(new NBTTagCompound()));
		}

	}
	public void
	readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		subarrows = new ItemStack[tag.getInteger("numsar")];
		for(int i=0;i<subarrows.length;++i){
			subarrows[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("sar_" + i));
		}
		timer = tag.getInteger("timer");
	}

	public Item GetCorrespondingItem(){
		return TSGTheifarrows.taItemArrowSplitting;
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
	onBlockImpact(MovingObjectPosition pos){
		if(subarrows != null && subarrows.length > 0){
			for(int i=0;i<subarrows.length;++i){
				FCUtilsItem.EjectStackWithRandomOffset(worldObj, pos.blockX, pos.blockY, pos.blockZ, subarrows[i]);
			}
			FCUtilsItem.EjectStackWithRandomOffset(worldObj, pos.blockX, pos.blockY, pos.blockZ, new ItemStack(GetCorrespondingItem()));
			this.setDead();
		}
	}

	public void
	onEntityImpact(Entity ent){
		if(subarrows != null && subarrows.length > 0){
			for(int i=0;i<subarrows.length;++i){
				FCUtilsItem.EjectStackWithRandomOffset(worldObj, (int)ent.posX, (int)ent.posY, (int)ent.posZ, subarrows[i]);
			}
			FCUtilsItem.EjectStackWithRandomOffset(worldObj, (int)ent.posX, (int)ent.posY, (int)ent.posZ, new ItemStack(GetCorrespondingItem()));
			this.setDead();
		}
	}

	public void
	onUpdate(){
		super.onUpdate();
		if(!this.inGround && this.ticksInAir > timer){
			Random rn = new Random();
			if(subarrows == null || subarrows.length == 0){
				return;
			}
			for(int i=0;i<subarrows.length;++i){
				EntityArrow arr = (EntityArrow)TSGArrowManager.getInstance().CreateArrow(subarrows[i].itemID, worldObj, posX + rn.nextGaussian(),  posY + rn.nextGaussian(), posZ + rn.nextGaussian());
				if(arr == null){
					arr = (EntityArrow)EntityList.createEntityOfType(EntityArrow.class, worldObj, posX + rn.nextGaussian(),  posY + rn.nextGaussian(), posZ + rn.nextGaussian());
				}
				arr.setCurrentItemOrArmor(0, subarrows[i]);
				arr.motionX = this.motionX + rn.nextGaussian() * 0.1;
				arr.motionY = this.motionY + rn.nextGaussian() * 0.1;
				arr.motionZ = this.motionZ + rn.nextGaussian() * 0.1;
				arr.shootingEntity = this.shootingEntity;
				worldObj.spawnEntityInWorld(arr);

			}
			if(!forever){
				this.setDead();
			}
		}
	}
	public Packet 
	GetSpawnPacketForThisEntity(){
		return new Packet23VehicleSpawn(this, this.GetVehicleSpawnPacketType(), shootingEntity == null ?  entityId : shootingEntity.entityId);
	}
	public void
	setCurrentItemOrArmor( int slot, ItemStack cont){
		if(cont.hasTagCompound()){
			NBTTagCompound tag = cont.getTagCompound();
			subarrows = new ItemStack[tag.getInteger("numsar")];
			for(int i=0;i<subarrows.length;++i){
				subarrows[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("sar_" + i));
			}
			timer = tag.getInteger("timer");
			forever = (tag.getInteger("forever") == 0)?false:true;
		}

	}
	public static int
	GetVehicleSpawnPacketType(){
		return 123;
	}

}

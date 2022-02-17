package net.minecraft.src;

import java.util.*;
import java.util.stream.IntStream;

public class TSGEntityArrowRope extends TSGEntityArrow implements FCIEntityPacketHandler {
	private int
	numrope;
	public 
	TSGEntityArrowRope(World world){
		super(world);
	}
	public
	TSGEntityArrowRope(World world, double d, double d1, double d2){
		super(world, d,d1,d2);
	}
	public
	TSGEntityArrowRope(World world, EntityLiving el, float f){
		super(world, el, f);
		if(!worldObj.isRemote && el instanceof EntityPlayer){
			for(int i = 0;i<9;++i){
				ItemStack is = ((EntityPlayer)el).inventory.getStackInSlot(i);
				if(is != null && is.itemID == FCBetterThanWolves.fcItemRope.itemID){
					if(is.stackSize > 19){
						is.splitStack(20);
						numrope = 20;
					} else {
						numrope = is.stackSize;
						((EntityPlayer)el).inventory.setInventorySlotContents(i, null);
					}
					break;	
				}
			}
		}
	}
	public void
	writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setInteger("numrope", numrope);
	}
	public void
	readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		numrope = tag.getInteger("numrope");
	}

	public Item GetCorrespondingItem(){
		return TSGTheifarrows.taItemArrowRope;
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
	private void
	placeBlockAndRope(int x, int y, int z){
		if(!worldObj.isRemote){

			worldObj.setBlock(x,y,z,TSGTheifarrows.taBlockAnchor.blockID);
			if(numrope == 0){
				return;
			}
			for(int i = 1;numrope > 0;++i){
				if(worldObj.getBlockId(x,y - i,z) == 0){
					--numrope;
					worldObj.setBlock(x,y - i,z,FCBetterThanWolves.fcRopeBlock.blockID);
				} else {
					FCUtilsItem.EjectStackWithRandomOffset(worldObj, x,y-i+1,z,new ItemStack(FCBetterThanWolves.fcItemRope, numrope));
					numrope = 0;
				}
			}
		}
	}
	public void
	onBlockImpact(MovingObjectPosition pos){
		int hit = worldObj.getBlockId(pos.blockX, pos.blockY, pos.blockZ);
		if(hit == 0){
			placeBlockAndRope(pos.blockX, pos.blockY, pos.blockZ);
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
				placeBlockAndRope(pos.blockX + xh, pos.blockY + yh, pos.blockZ + zh);
			}
		}
		this.setDead();
	}

	public void
	onEntityImpact(Entity ent){
		if(!worldObj.isRemote){
			FCUtilsItem.EjectStackWithRandomOffset(worldObj, (int)(ent.posX),(int)(ent.posY),(int)(ent.posZ),new ItemStack(FCBetterThanWolves.fcItemRope, numrope));
		}
	}

	public Packet 
	GetSpawnPacketForThisEntity(){
		return new Packet23VehicleSpawn(this, this.GetVehicleSpawnPacketType(), shootingEntity == null ?  entityId : shootingEntity.entityId);
	}
	public static int
	GetVehicleSpawnPacketType(){
		return 112;
	}

}

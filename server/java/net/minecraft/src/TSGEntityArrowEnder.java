package net.minecraft.src;

import java.util.*;
import java.util.stream.IntStream;

public class TSGEntityArrowEnder extends TSGEntityArrow implements FCIEntityPacketHandler {

	public 
	TSGEntityArrowEnder(World world){
		super(world);
	}
	public
	TSGEntityArrowEnder(World world, double d, double d1, double d2){
		super(world, d,d1,d2);
	}
	public
	TSGEntityArrowEnder(World world, EntityLiving el, float f){
		super(world, el, f);
	}

	public Item GetCorrespondingItem(){
		return TSGTheifarrows.taItemArrowEnder;
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
		if(pos == null || pos.blockY < 1 || (pos.blockX == 0 && pos.blockY == 0)){
			this.setDead();
			return;
		}
		for (int var2 = 0; var2 < 32; ++var2)
		{
			this.worldObj.spawnParticle("portal", this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ, this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian());
		}
		if(!this.worldObj.isRemote &&  this.shootingEntity != null && (this.shootingEntity.worldObj == this.worldObj)){
			if(this.shootingEntity.ridingEntity != null){
 				((EntityPlayer)this.shootingEntity).mountEntity(null);
			}

			if(!(this.shootingEntity instanceof EntityPlayerMP) || ((!((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.connectionClosed) && (((EntityPlayerMP)this.shootingEntity).worldObj == this.worldObj) )){

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
				if(worldObj.getBlockId(pos.blockX + xh, pos.blockY + yh, pos.blockZ + zh) == 0 && worldObj.getBlockId(pos.blockX + xh, pos.blockY + yh + 1, pos.blockZ + zh) == 0  ){
					((EntityLiving)(this.shootingEntity)).setPositionAndUpdate(pos.blockX + xh, pos.blockY + yh, pos.blockZ + zh);
					FCUtilsItem.EjectStackWithRandomOffset(worldObj, pos.blockX + xh, pos.blockY + yh, pos.blockZ + zh, new ItemStack(GetCorrespondingItem()));
					this.setDead();
					return;
	
				} else if(worldObj.getBlockId(pos.blockX + xh, pos.blockY + yh, pos.blockZ + zh) == 0 && worldObj.getBlockId(pos.blockX + xh, pos.blockY + yh - 1, pos.blockZ + zh) == 0  ){
					((EntityLiving)(this.shootingEntity)).setPositionAndUpdate(pos.blockX + xh, pos.blockY + yh - 1, pos.blockZ + zh);
					FCUtilsItem.EjectStackWithRandomOffset(worldObj, pos.blockX + xh, pos.blockY + yh - 1 , pos.blockZ + zh, new ItemStack(GetCorrespondingItem()));
					this.setDead();
					return;
				}

				int[] ja = IntStream.rangeClosed(-1,1).toArray();
				int[] ka = IntStream.rangeClosed(-1,1).toArray();
				int[] ia = IntStream.rangeClosed(0,2).toArray();
				for(int i:ia){
					for( int j:ja){
						for(int k:ka){
							if(worldObj.getBlockId((int)pos.blockX + j, (int) pos.blockY + i, (int)pos.blockZ + k) == 0 && worldObj.getBlockId((int)pos.blockX + j, (int) pos.blockY + i + 1, (int)pos.blockZ + k) == 0){
								((EntityLiving)(this.shootingEntity)).setPositionAndUpdate(pos.blockX + j, pos.blockY + i, pos.blockZ + k);
								FCUtilsItem.EjectStackWithRandomOffset(worldObj, pos.blockX + j, pos.blockY + i, pos.blockZ + k, new ItemStack(GetCorrespondingItem()));
								this.setDead();
	
	
								return;
							}
						}
					}
				}
				((EntityLiving)(this.shootingEntity)).setPositionAndUpdate(pos.blockX , pos.blockY, pos.blockZ );

			}
			FCUtilsItem.EjectStackWithRandomOffset(worldObj, (int)posX, (int)posY, (int)posZ, new ItemStack(GetCorrespondingItem()));

		}
		this.setDead();
	}
	public void
	onEntityImpact(Entity ent){
		for (int var2 = 0; var2 < 32; ++var2)
		{
			this.worldObj.spawnParticle("portal", this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ, this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian());
		}
		if( !(ent instanceof EntityLiving) || ent.posY < 1 ){
			this.setDead();
			return;
		}
 		Entity pre;

		if(ent != null && this.shootingEntity != null && (this.shootingEntity.worldObj == this.worldObj)){
			Entity ere;
			ere = null;
			pre = null;

			double epx,epy,epz;
			epx = ent.posX;	
			epy = ent.posY;
			epz = ent.posZ;

			if(ent.ridingEntity != null){
				ere = ent.ridingEntity;
				ent.mountEntity(null);
			}
			if(this.shootingEntity.ridingEntity != null){
				pre = this.shootingEntity.ridingEntity;
				((EntityPlayer)this.shootingEntity).mountEntity(null);
			}
			((EntityLiving)ent).setPositionAndUpdate(shootingEntity.posX, shootingEntity.posY, shootingEntity.posZ);

			if(!(this.shootingEntity instanceof EntityPlayerMP) || ((!((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.connectionClosed) && (((EntityPlayerMP)this.shootingEntity).worldObj == this.worldObj) )){
				((EntityLiving)(this.shootingEntity)).setPositionAndUpdate(epx, epy, epz);

				if(ere != null){
					this.shootingEntity.mountEntity(ere);
				} 
				
			}
			if(pre != null){
				ent.mountEntity(pre);
			}
			if(!worldObj.isRemote){
				FCUtilsItem.EjectStackWithRandomOffset(worldObj, (int)epx, (int)epy, (int)epz, new ItemStack(GetCorrespondingItem()));
			}
		}
		this.setDead();


	}
	public Packet 
	GetSpawnPacketForThisEntity(){
		return new Packet23VehicleSpawn(this, this.GetVehicleSpawnPacketType(), shootingEntity == null ?  entityId : shootingEntity.entityId);
	}
	public static int
	GetVehicleSpawnPacketType(){
		return 110;
	}

}

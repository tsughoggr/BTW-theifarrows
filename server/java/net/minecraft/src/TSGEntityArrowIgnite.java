package net.minecraft.src;

import java.util.*;
import java.util.stream.IntStream;

public class TSGEntityArrowIgnite extends TSGEntityArrow implements FCIEntityPacketHandler {
	public 
	TSGEntityArrowIgnite(World world){
		super(world);
	}
	public
	TSGEntityArrowIgnite(World world, double d, double d1, double d2){
		super(world, d,d1,d2);
	}
	public
	TSGEntityArrowIgnite(World world, EntityLiving el, float f){
		super(world, el, f);
	}
	public Item GetCorrespondingItem(){
		return TSGTheifarrows.taItemArrowIgnite;
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
	public Packet 
	GetSpawnPacketForThisEntity(){
		return new Packet23VehicleSpawn(this, this.GetVehicleSpawnPacketType(), shootingEntity == null ?  entityId : shootingEntity.entityId);
	}
	public static int
	GetVehicleSpawnPacketType(){
		return 111;
	}

	public void
	onBlockImpact(MovingObjectPosition pos){
		if(!this.worldObj.isRemote){
			int hit = worldObj.getBlockId(pos.blockX, pos.blockY, pos.blockZ);

			if(hit ==  0){
				worldObj.setBlock(pos.blockX, pos.blockY, pos.blockZ, Block.fire.blockID);
				FCUtilsItem.EjectStackWithRandomOffset(worldObj, pos.blockX, pos.blockY, pos.blockZ, new ItemStack(GetCorrespondingItem()));
				this.setDead();
			}else if (  hit == Block.tnt.blockID) {
				((BlockTNT)Block.tnt).func_94391_a(worldObj, pos.blockX, pos.blockY, pos.blockZ,1, (EntityLiving)this.shootingEntity);
				worldObj.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
				FCUtilsItem.EjectStackWithRandomOffset(worldObj, pos.blockX, pos.blockY, pos.blockZ, new ItemStack(GetCorrespondingItem()));
				this.setDead();
			}else if (  hit == FCBetterThanWolves.fcBlockMiningCharge.blockID) {
				((FCBlockMiningCharge)FCBetterThanWolves.fcBlockMiningCharge).CreatePrimedEntity(worldObj, pos.blockX, pos.blockY, pos.blockZ, worldObj.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ));
				worldObj.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
				FCUtilsItem.EjectStackWithRandomOffset(worldObj, pos.blockX, pos.blockY, pos.blockZ, new ItemStack(GetCorrespondingItem()));
				this.setDead();
			}else if(Block.blocksList[hit].GetCanBeSetOnFireDirectlyByItem(worldObj,pos.blockX, pos.blockY, pos.blockZ )){
				Block.blocksList[hit].SetOnFireDirectly(worldObj, pos.blockX, pos.blockY, pos.blockZ);
				FCUtilsItem.EjectStackWithRandomOffset(worldObj, pos.blockX, pos.blockY, pos.blockZ, new ItemStack(GetCorrespondingItem()));
				this.setDead();
			}else{
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
								worldObj.setBlock(pos.blockX + xh, pos.blockY + yh, pos.blockZ + zh, Block.fire.blockID);
								FCUtilsItem.EjectStackWithRandomOffset(worldObj, pos.blockX +xh, pos.blockY + yh, pos.blockZ + zh, new ItemStack(GetCorrespondingItem()));
								this.setDead();
								return;

				}
				int[] ja = IntStream.rangeClosed(-1,1).toArray();
				int[] ka = IntStream.rangeClosed(-1,1).toArray();
				int[] ia = IntStream.rangeClosed(0,2).toArray();
				for(int i:ia){
					for( int j:ja){
						for(int k:ka){
							if(worldObj.getBlockId(pos.blockX + j, pos.blockY + i, pos.blockZ + k) == 0){
								worldObj.setBlock(pos.blockX + j, pos.blockY + i, pos.blockZ + k, Block.fire.blockID);
								FCUtilsItem.EjectStackWithRandomOffset(worldObj, pos.blockX +j, pos.blockY + i, pos.blockZ + k, new ItemStack(GetCorrespondingItem()));
								this.setDead();
								return;
							}
						}
					}
				}
			}
		
		}
	}
	public void
	onEntityImpact(Entity ent){
		if(!worldObj.isRemote && !ent.isImmuneToFire()){
			ent.dealFireDamage(3);
			ent.setFire(10);
		}
		this.setDead();
	}

}

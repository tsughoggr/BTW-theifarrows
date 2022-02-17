package net.minecraft.src;

import java.util.*;
import java.util.stream.IntStream;

public class TSGEntityArrowPotion extends TSGEntityArrow implements FCIEntityPacketHandler {

	private ItemStack
	potionStack;

	public 
	TSGEntityArrowPotion(World world){
		super(world);
	}
	public
	TSGEntityArrowPotion(World world, double d, double d1, double d2){
		super(world, d,d1,d2);
	}
	public
	TSGEntityArrowPotion(World world, EntityLiving el, float f){
		super(world, el, f);

	}


	public Item GetCorrespondingItem(){
		return TSGTheifarrows.taItemArrowPotion;
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
	potionSplashImpact(double x, double y, double z,int fc){

                AxisAlignedBB var3 = AxisAlignedBB.getAABBPool().getAABB(
			x - ((fc == 5)?1D:6D),
			y - ((fc == 1)?2D:6D),
			z - ((fc == 3)?1D:6D),
			x + ((fc == 4)?1D:6D),
			y + ((fc == 0)?2D:6D),
			z + ((fc == 2)?1D:6D)
		);
                List var4 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, var3);
		List pe = Item.potion.getEffects(this.potionStack);
		if(pe == null || pe.isEmpty() || var4 == null || var4.isEmpty()){
			return;
		}
		for(Iterator var5 = var4.iterator();var5.hasNext();){
			EntityLiving var6 = (EntityLiving)var5.next();
			double dist = Math.sqrt( ((var6.posX - x) * (var6.posX - x)) + ((var6.posY - y) * (var6.posY - y)) + ((var6.posZ - z) * (var6.posZ - z)));
			if(dist < 32D){
				for(Iterator pei = pe.iterator();pei.hasNext();){
					PotionEffect eff = (PotionEffect)pei.next();

					var6.addPotionEffect(new PotionEffect(eff.getPotionID(), (int)((1.0D - dist/4.0D) * ((double)eff.getDuration()) + 0.5D), eff.getAmplifier()));
				}
			}
		}
	}

	public void
	onBlockImpact(MovingObjectPosition pos){
		if(potionStack == null){
			this.setDead();
			return;
		}
		if(ItemPotion.isSplash(potionStack.getItemDamage())){
			potionSplashImpact((double)pos.blockX + ((pos.sideHit == 4)? - 1:((pos.sideHit == 5)?1:0) ),(double)pos.blockY + ((pos.sideHit == 0)? - 1:((pos.sideHit == 1)?1:0) ) ,(double)pos.blockZ + ((pos.sideHit == 2)? - 1:((pos.sideHit == 3)?1:0) ), pos.sideHit);
		}
		this.worldObj.playAuxSFX(2002, pos.blockX + ((pos.sideHit == 4)? - 1:((pos.sideHit == 5)?1:0) ), pos.blockY + ((pos.sideHit == 0)? - 1:((pos.sideHit == 1)?1:0) ) , pos.blockZ + ((pos.sideHit == 2)? - 1:((pos.sideHit == 3)?1:0) ), potionStack.getItemDamage());
		this.setDead();
	}

	public void
	onEntityImpact(Entity ent){
		if(potionStack == null){
			this.setDead();
			return;
		}
		if(ItemPotion.isSplash(potionStack.getItemDamage())){
			potionSplashImpact(ent.posX, ent.posY, ent.posZ, 1);
		}else{
			if(!(ent instanceof EntityLiving)){
				this.setDead();
				return;
			} 
			List pe = Item.potion.getEffects(this.potionStack);
			if(pe == null || pe.isEmpty()){
				this.setDead();
				return;
			}
			for(Iterator pei = pe.iterator();pei.hasNext();){
				PotionEffect eff = (PotionEffect)pei.next();
				((EntityLiving)ent).addPotionEffect(new PotionEffect(eff.getPotionID(),  eff.getDuration(), eff.getAmplifier()));
			}
		}
		this.worldObj.playAuxSFX(2002, (int)Math.round(ent.posX), (int)Math.round(ent.posY), (int)Math.round(ent.posZ), potionStack.getItemDamage());
		this.setDead();
	}

	public Packet 
	GetSpawnPacketForThisEntity(){
		return new Packet23VehicleSpawn(this, this.GetVehicleSpawnPacketType(), shootingEntity == null ?  entityId : shootingEntity.entityId);
	}
	public static int
	GetVehicleSpawnPacketType(){
		return 119;
	}


	public void
	setCurrentItemOrArmor( int slot, ItemStack cont){
		potionStack = cont;
	}
	public void
	writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setCompoundTag("potionStack", potionStack.writeToNBT(new NBTTagCompound()));
	}
	public void
	readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		potionStack = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("potionStack"));
	}
}

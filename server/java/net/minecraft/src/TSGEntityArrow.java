package net.minecraft.src;

import java.util.List;

public class TSGEntityArrow extends EntityArrow {
	public int ticksInAir = 0; /*Why is this private in EntityArrow*/
	public int ticksInGround = 0; /*Same*/
	public 
	TSGEntityArrow(World world){
		super(world);
	}
	public
	TSGEntityArrow(World world, double d, double d1, double d2){
		super(world, d,d1,d2);
	}
	public
	TSGEntityArrow(World world, EntityLiving el, float f){
		super(world, el, f);
	}

	public void
	onBlockImpact(MovingObjectPosition pos){

	}
	public void
	onEntityImpact(Entity ent){

	}
	public void
	onUpdate(){

		if(!this.inGround){
			++this.ticksInAir;
			Vec3 var17 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
			Vec3 var3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition var4 = this.worldObj.rayTraceBlocks_do_do(var17, var3, false, true);
			var17 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
			var3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

			if (var4 != null)
			{
				var3 = this.worldObj.getWorldVec3Pool().getVecFromPool(var4.hitVec.xCoord, var4.hitVec.yCoord, var4.hitVec.zCoord);
			}

			Entity var5 = null;
			List var6 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(0.01D, 0.01D, 0.01D));
			double var7 = 0.0D;
			int var9;
			float var11;

			for (var9 = 0; var9 < var6.size(); ++var9)
			{
				Entity var10 = (Entity)var6.get(var9);

				if (var10.canBeCollidedWith() && (var10 != this.shootingEntity || this.ticksInAir >= 5))
				{
					var11 = 0.3F;
					AxisAlignedBB var12 = var10.boundingBox.expand((double)var11, (double)var11, (double)var11);
					MovingObjectPosition var13 = var12.calculateIntercept(var17, var3);

					if (var13 != null)
					{
						double var14 = var17.distanceTo(var13.hitVec);

						if (var14 < var7 || var7 == 0.0D)
						{
							var5 = var10;
							var7 = var14;
						}
					}
				}
			}

			if (var5 != null)
			{
				var4 = new MovingObjectPosition(var5);
			}
			if (var4 != null && var4.entityHit != null && var4.entityHit instanceof EntityLiving){
				this.onEntityImpact(var4.entityHit);
			} else if (var4 != null){
				this.onBlockImpact(var4);
			}
		}

		super.onUpdate();
	}

}

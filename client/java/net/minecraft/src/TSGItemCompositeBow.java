// FCMOD

package net.minecraft.src;

public class TSGItemCompositeBow extends FCItemBow
{
	private static int m_iMaxDamage =  576;
	
	public TSGItemCompositeBow( int iItemID )
	{
		super( iItemID );
		
		setMaxDamage( m_iMaxDamage );
		
		setUnlocalizedName( "fcItemBowComposite" );
	}

	@Override
	public boolean
	CanItemBeFiredAsArrow( int iItemID )
	{
		return iItemID == Item.arrow.itemID || iItemID == FCBetterThanWolves.fcItemRottenArrow.itemID || iItemID == FCBetterThanWolves.fcItemBroadheadArrow.itemID || TSGArrowManager.getInstance().MatchArrowById(iItemID);
	}

	@Override
	public float GetPullStrengthToArrowVelocityMultiplier()
	{
		return 3.0F;
	}
	
	@Override
	protected EntityArrow CreateArrowEntityForItem( World world, EntityPlayer player, int iItemID, float fPullStrength )
	{

		if ( iItemID == FCBetterThanWolves.fcItemBroadheadArrow.itemID )
		{
			return (EntityArrow) EntityList.createEntityOfType(FCEntityBroadheadArrow.class, world, player, fPullStrength * GetPullStrengthToArrowVelocityMultiplier() );
		}
		else if ( iItemID == FCBetterThanWolves.fcItemRottenArrow.itemID )
		{
			world.playSoundAtEntity( player, "random.break", 0.8F, 0.8F + world.rand.nextFloat() * 0.4F);
			
			if ( world.isRemote )
			{
				float motionX = -MathHelper.sin((player.rotationYaw / 180F) * (float)Math.PI) * MathHelper.cos((player.rotationPitch / 180F) * (float)Math.PI) * fPullStrength;
				float motionZ = MathHelper.cos((player.rotationYaw / 180F) * (float)Math.PI) * MathHelper.cos((player.rotationPitch / 180F) * (float)Math.PI) * fPullStrength;
				float motionY = -MathHelper.sin((player.rotationPitch / 180F) * (float)Math.PI) * fPullStrength;
				
				for (int i = 0; i < 32; i++)
				{
					// spew boat particles
					
					world.spawnParticle( "iconcrack_333", player.posX, player.posY + player.getEyeHeight(), player.posZ, 
						motionX + (double)((float)(Math.random() * 2D - 1.0D) * 0.4F), 
						motionY + (double)((float)(Math.random() * 2D - 1.0D) * 0.4F), 
						motionZ + (double)((float)(Math.random() * 2D - 1.0D) * 0.4F) );
				}
			}
			
			return null;
		} else if(TSGArrowManager.getInstance().MatchArrowById(iItemID)){
			ItemStack arrowStack = GetFirstArrowStackInHotbar(player);
			EntityArrow entarr = (EntityArrow) TSGArrowManager.getInstance().CreateArrow( world, player, iItemID, fPullStrength);

			/*Theoretically this is faster than checking whether the NBT tag exists first*/
			entarr.setCurrentItemOrArmor(0, arrowStack); 
			return entarr;
		}
		else
		{
			return super.CreateArrowEntityForItem( world, player, iItemID, fPullStrength );
		}
		
	}

	@Override
	protected void PlayerBowSound( World world, EntityPlayer player, float fPullStrength )
	{
		world.playSoundAtEntity( player, "random.bow", 1.0F, 1.5F / (itemRand.nextFloat() * 0.4F + 1.2F) + fPullStrength * 0.5F );
	}

	//------------- Class Specific Methods ------------//
	
	//----------- Client Side Functionality -----------//
	
	private Icon[] drawIconArray = new Icon[3];
	
	@Override
	public void registerIcons( IconRegister register )
	{
		itemIcon = register.registerIcon( "fcItemBowComposite" );		
		
		drawIconArray[0] =  register.registerIcon( "fcItemBowComposite_pull_0" );
		drawIconArray[1] =  register.registerIcon( "fcItemBowComposite_pull_1" );
		drawIconArray[2] =  register.registerIcon( "fcItemBowComposite_pull_2" );		
	}

	public Icon getDrawIcon( int iItemInUseDuration )
	{
		if ( iItemInUseDuration >= 18 )
		{
			return drawIconArray[2];
		}
		else if ( iItemInUseDuration > 12 )
		{
			return drawIconArray[1];
		}
		else if ( iItemInUseDuration > 0 )
		{
			return drawIconArray[0];
		}
		
		return itemIcon; 
	}
}

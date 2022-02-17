package net.minecraft.src;

public abstract class TSGTheifarrowRecipes {
	public static void
	addAllRecipes(){
		addPotionRecipes(); /*Call this before crafting because we are going to override a few*/
		addCraftingRecipes();
		addSplittingArrows();
	}
	public static void
	addPotionRecipes(){
		for(int i =0;i<14;++i){
			FCCraftingManagerCauldronStoked.getInstance().AddRecipe(

				new ItemStack[]{
					new ItemStack(Item.glassBottle),
					new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 8193 + i)
				},
				new ItemStack[]{
					new ItemStack(Item.potion, 1, 8193 + i),
					new ItemStack(TSGTheifarrows.taItemArrowPotionEmpty)
				}

			);
			FCCraftingManagerCauldronStoked.getInstance().AddRecipe(
				new ItemStack[]{
					new ItemStack(Item.glassBottle),
					new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 8225 + i)
				},
				new ItemStack[]{
					new ItemStack(Item.potion, 1, 8225 + i),
					new ItemStack(TSGTheifarrows.taItemArrowPotionEmpty)
				}

			);
			FCCraftingManagerCauldronStoked.getInstance().AddRecipe(
				new ItemStack[]{
					new ItemStack(Item.glassBottle),
					new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 8257 + i)
				},
				new ItemStack[]{
					new ItemStack(Item.potion, 1, 8257 + i),
					new ItemStack(TSGTheifarrows.taItemArrowPotionEmpty)
				}

			);
			FCCraftingManagerCauldronStoked.getInstance().AddRecipe(
				new ItemStack[]{
					new ItemStack(Item.glassBottle),
					new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 8289 + i)
				},
				new ItemStack[]{
					new ItemStack(Item.potion, 1, 8289 + i),
					new ItemStack(TSGTheifarrows.taItemArrowPotionEmpty)
				}

			);

			FCCraftingManagerCauldronStoked.getInstance().AddRecipe(
				new ItemStack[]{
					new ItemStack(Item.glassBottle),
					new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 16385 + i)
				},
				new ItemStack[]{
					new ItemStack(Item.potion, 1, 16385 + i),
					new ItemStack(TSGTheifarrows.taItemArrowPotionEmpty)
				}

			);
			FCCraftingManagerCauldronStoked.getInstance().AddRecipe(
				new ItemStack[]{
					new ItemStack(Item.glassBottle),
					new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 16417 + i)
				},
				new ItemStack[]{
					new ItemStack(Item.potion, 1, 16417 + i),
					new ItemStack(TSGTheifarrows.taItemArrowPotionEmpty)
				}

			);
			FCCraftingManagerCauldronStoked.getInstance().AddRecipe(
				new ItemStack[]{
					new ItemStack(Item.glassBottle),
					new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 16449 + i)
				},

				new ItemStack[]{
					new ItemStack(Item.potion, 1, 16449 + i),
					new ItemStack(TSGTheifarrows.taItemArrowPotionEmpty)
				}

			);
			FCCraftingManagerCauldronStoked.getInstance().AddRecipe(
				new ItemStack[]{
					new ItemStack(Item.glassBottle),
					new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 16481 + i)
				},
				new ItemStack[]{
					new ItemStack(Item.potion, 1, 16481 + i),
					new ItemStack(TSGTheifarrows.taItemArrowPotionEmpty)
				}

			);



			NBTTagCompound spt = new NBTTagCompound();
			spt.setInteger("numsar", 5);
			for(int j=0;j<5;++j){
				spt.setCompoundTag("sar_" + j, new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 8193 + i).writeToNBT(new NBTTagCompound()));
			}
			spt.setInteger("timer", 30);
			spt.setInteger("forever", 0);
			ItemStack sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
			sps.setTagCompound(spt);
	
			CraftingManager.getInstance().addRecipe(
				sps,
				new Object[] {
					"III",
					"ISI",
					" D ",
					'I', new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 8193 + i),
					'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
					'D', new ItemStack(FCBetterThanWolves.fcItemDynamite)
				}
			);
			spt = new NBTTagCompound();
			spt.setInteger("numsar", 5);
			for(int j=0;j <5;++j){
				spt.setCompoundTag("sar_" + j, new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 8225 + i).writeToNBT(new NBTTagCompound()));
			}
			spt.setInteger("timer", 30);
			spt.setInteger("forever", 0);
			sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
			sps.setTagCompound(spt);
	
			CraftingManager.getInstance().addRecipe(
				sps,
				new Object[] {
					"III",
					"ISI",
					" D ",
					'I', new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 8225 + i),
					'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
					'D', new ItemStack(FCBetterThanWolves.fcItemDynamite)
				}
			);

			spt = new NBTTagCompound();
			spt.setInteger("numsar", 5);
			for(int j=0;j<5;++j){
				spt.setCompoundTag("sar_" + j, new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 16385 + i).writeToNBT(new NBTTagCompound()));
			}
			spt.setInteger("timer", 30);
			spt.setInteger("forever", 0);
			sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
			sps.setTagCompound(spt);
	
			CraftingManager.getInstance().addRecipe(
				sps,
				new Object[] {
					"III",
					"ISI",
					" D ",
					'I', new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 16385 + i),
					'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
					'D', new ItemStack(FCBetterThanWolves.fcItemDynamite)
				}
			);
			spt = new NBTTagCompound();
			spt.setInteger("numsar", 5);
			for(int j=0;j<5;++j){
				spt.setCompoundTag("sar_" + j, new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 16417 + i).writeToNBT(new NBTTagCompound()));
			}
			spt.setInteger("timer", 30);
			spt.setInteger("forever", 0);
			sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
			sps.setTagCompound(spt);
	
			CraftingManager.getInstance().addRecipe(
				sps,
				new Object[] {
					"III",
					"ISI",
					" D ",
					'I', new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 16417 + i),
					'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
					'D', new ItemStack(FCBetterThanWolves.fcItemDynamite)
				}
			);


			spt = new NBTTagCompound();
			spt.setInteger("numsar", 5);
			for(int j=0;j<5;++j){
				spt.setCompoundTag("sar_" + j, new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 8257 + i).writeToNBT(new NBTTagCompound()));
			}
			spt.setInteger("timer", 30);
			spt.setInteger("forever", 0);
			sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
			sps.setTagCompound(spt);
	
			CraftingManager.getInstance().addRecipe(
				sps,
				new Object[] {
					"III",
					"ISI",
					" D ",
					'I', new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 8257 + i),
					'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
					'D', new ItemStack(FCBetterThanWolves.fcItemDynamite)
				}
			);
			spt = new NBTTagCompound();
			spt.setInteger("numsar", 5);
			for(int j=0;j <5;++j){
				spt.setCompoundTag("sar_" + j, new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 8289 + i).writeToNBT(new NBTTagCompound()));
			}
			spt.setInteger("timer", 30);
			spt.setInteger("forever", 0);
			sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
			sps.setTagCompound(spt);
	
			CraftingManager.getInstance().addRecipe(
				sps,
				new Object[] {
					"III",
					"ISI",
					" D ",
					'I', new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 8289 + i),
					'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
					'D', new ItemStack(FCBetterThanWolves.fcItemDynamite)
				}
			);

			spt = new NBTTagCompound();
			spt.setInteger("numsar", 5);
			for(int j=0;j<5;++j){
				spt.setCompoundTag("sar_" + j, new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 16449 + i).writeToNBT(new NBTTagCompound()));
			}
			spt.setInteger("timer", 30);
			spt.setInteger("forever", 0);
			sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
			sps.setTagCompound(spt);
	
			CraftingManager.getInstance().addRecipe(
				sps,
				new Object[] {
					"III",
					"ISI",
					" D ",
					'I', new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 16449 + i),
					'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
					'D', new ItemStack(FCBetterThanWolves.fcItemDynamite)
				}
			);
			spt = new NBTTagCompound();
			spt.setInteger("numsar", 5);
			for(int j=0;j<5;++j){
				spt.setCompoundTag("sar_" + j, new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 16481 + i).writeToNBT(new NBTTagCompound()));
			}
			spt.setInteger("timer", 30);
			spt.setInteger("forever", 0);
			sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
			sps.setTagCompound(spt);
	
			CraftingManager.getInstance().addRecipe(
				sps,
				new Object[] {
					"III",
					"ISI",
					" D ",
					'I', new ItemStack(TSGTheifarrows.taItemArrowPotion, 1, 16481 + i),
					'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
					'D', new ItemStack(FCBetterThanWolves.fcItemDynamite)
				}
			);


		}
	}
	private static void
	addCraftingRecipes(){
		CraftingManager.getInstance().addRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowIgnite),
			new Object[] {
				" F ",
				"IBI",
				"ESE",
				'F', new ItemStack(Item.flint),
				'I', new ItemStack(FCBetterThanWolves.fcItemNuggetIron),
				'B', new ItemStack(Block.stoneButton),
				'E', new ItemStack(Item.feather),
				'S', new ItemStack(Item.stick)
			}

		);
		CraftingManager.getInstance().addRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowRope),
			new Object[] {
				"CSC",
				"SAS",
				"GRG",
				'C', new ItemStack(FCBetterThanWolves.fcBlockWoodCornerItemStubID , 1, FCUtilsInventory.m_iIgnoreMetadata),
				'S', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID  , 1, FCUtilsInventory.m_iIgnoreMetadata),
				'A', new ItemStack(Item.arrow),
				'G', new ItemStack(FCBetterThanWolves.fcItemGlue),
				'R', new ItemStack(FCBetterThanWolves.fcItemRope)
			}

		);

		
		CraftingManager.getInstance().AddShapelessRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowTorch),
			new Object[] {
				new ItemStack(Item.arrow),
				new ItemStack(FCBetterThanWolves.fcBlockTorchFiniteUnlit),
				new ItemStack(FCBetterThanWolves.fcItemGlue)
			}

		);
		CraftingManager.getInstance().AddShapelessRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowTorchNether),
			new Object[] {
				new ItemStack(Item.arrow),
				new ItemStack(FCBetterThanWolves.fcBlockTorchNetherUnlit),
				new ItemStack(FCBetterThanWolves.fcItemGlue)
			}

		);
		CraftingManager.getInstance().AddShapelessRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowTorchRedstone),
			new Object[] {
				new ItemStack(Item.arrow),
				new ItemStack(Block.torchRedstoneIdle),
				new ItemStack(FCBetterThanWolves.fcItemGlue)
			}

		);
		CraftingManager.getInstance().AddShapelessRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowTorchRedstone),
			new Object[] {
				new ItemStack(Item.arrow),
				new ItemStack(Block.torchRedstoneActive),
				new ItemStack(FCBetterThanWolves.fcItemGlue)
			}

		);
		CraftingManager.getInstance().AddShapelessRecipe(
			new ItemStack(TSGTheifarrows.taItemFishDynamite),
			new Object[]{
				new ItemStack(Item.fishRaw),
				new ItemStack(FCBetterThanWolves.fcItemDynamite)
			}
		);
		CraftingManager.getInstance().addRecipe(
			new ItemStack(TSGTheifarrows.taItemWarcrime),
			new Object[]{
				"FFF",
				"FDF",
				"GEG",
				'F', new ItemStack(TSGTheifarrows.taItemFishDynamite),
				'D', new ItemStack(FCBetterThanWolves.fcItemIngotDiamond),
				'G', new ItemStack(FCBetterThanWolves.fcItemGlue),
				'E', new ItemStack(FCBetterThanWolves.fcItemDynamite)
			}
		);
		CraftingManager.getInstance().addRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowRising),
			new Object[]{
				"PAP",
				"GPG",
				" G ",
				'P', new ItemStack(Item.paper),
				'G', new ItemStack(Item.gunpowder),
				'A', new ItemStack(Item.arrow)
			}
		);
		CraftingManager.getInstance().addRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowAccelerator),
			new Object[]{
				" A ",
				" P ",
				"PGP",
				'P', new ItemStack(Item.paper),
				'G', new ItemStack(Item.gunpowder),
				'A', new ItemStack(Item.arrow)
			}
		);
		CraftingManager.getInstance().addRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowSnow),
			new Object[]{
				"SSS",
				"SDS",
				"SAS",
				'S', new ItemStack(Item.snowball),
				'D', new ItemStack(FCBetterThanWolves.fcItemDynamite),
				'A', new ItemStack(Item.arrow)
			}
		);
		CraftingManager.getInstance().AddShapelessRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowDousing),
			new Object[]{
				new ItemStack(Item.potion, 1, 0),
				new ItemStack(FCBetterThanWolves.fcItemGlue),
				new ItemStack(Item.arrow)
			}
		);

		CraftingManager.getInstance().AddShapelessRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowOrange),
			new Object[]{
				new ItemStack(FCBetterThanWolves.fcItemGlue),
				new ItemStack(Item.fermentedSpiderEye ),
				new ItemStack(Item.potion, 1, 8196),
				new ItemStack(Item.arrow)
			}
		);
		CraftingManager.getInstance().AddShapelessRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowOrange),
			new Object[]{
				new ItemStack(FCBetterThanWolves.fcItemGlue),
				new ItemStack(Item.fermentedSpiderEye ),
				new ItemStack(Item.potion, 1, 16388),
				new ItemStack(Item.arrow)
			}
		);
		CraftingManager.getInstance().AddShapelessRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowOrange),
			new Object[]{
				new ItemStack(FCBetterThanWolves.fcItemGlue),
				new ItemStack(Item.fermentedSpiderEye ),
				new ItemStack(Item.potion, 1, 16420),
				new ItemStack(Item.arrow)
			}
		);
		CraftingManager.getInstance().AddShapelessRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowOrange),
			new Object[]{
				new ItemStack(FCBetterThanWolves.fcItemGlue),
				new ItemStack(Item.fermentedSpiderEye ),
				new ItemStack(Item.potion, 1, 8228),
				new ItemStack(Item.arrow)
			}
		);
		CraftingManager.getInstance().AddShapelessRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowOrange),
			new Object[]{
				new ItemStack(FCBetterThanWolves.fcItemGlue),
				new ItemStack(Item.fermentedSpiderEye ),
				new ItemStack(Item.potion, 1, 8260),
				new ItemStack(Item.arrow)
			}
		);
		CraftingManager.getInstance().AddShapelessRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowOrange),
			new Object[]{
				new ItemStack(FCBetterThanWolves.fcItemGlue),
				new ItemStack(Item.fermentedSpiderEye ),
				new ItemStack(Item.potion, 1, 16452),
				new ItemStack(Item.arrow)
			}
		);
		CraftingManager.getInstance().AddShapelessRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowOrange),
			new Object[]{
				new ItemStack(FCBetterThanWolves.fcItemGlue),
				new ItemStack(Item.fermentedSpiderEye ),
				new ItemStack(Item.potion, 1, 8292),
				new ItemStack(Item.arrow)
			}
		);
		CraftingManager.getInstance().AddShapelessRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowOrange),
			new Object[]{
				new ItemStack(FCBetterThanWolves.fcItemGlue),
				new ItemStack(Item.fermentedSpiderEye ),
				new ItemStack(Item.potion, 1, 16484),
				new ItemStack(Item.arrow)
			}
		);
		try {
			Class.forName("net.minecraft.src.TSGGloryhole");
			NBTTagCompound blw = new NBTTagCompound();
			NBTTagCompound res = new NBTTagCompound();
			ItemStack rsl = new ItemStack(TSGGloryhole.ghItemBlowpipeWorkable);
			rsl.setItemDamage(3999);
			res.setCompoundTag( "result",
				(new ItemStack(TSGTheifarrows.taItemArrowPotionEmpty)).writeToNBT(new NBTTagCompound())
			);
			res.setInteger("time", 150);
			blw.setCompoundTag("Blowing", res);
			rsl.setTagCompound(blw);
	
			CraftingManager.getInstance().addRecipe(
				rsl ,
				new Object[]{
					" S ", "SRS", " A ",
					'S', new ItemStack(FCBetterThanWolves.fcItemPileSand),
					'R', new ItemStack(TSGGloryhole.ghItemBlowpipeWorkable),
					'A', new ItemStack(Item.arrow)
				}
			);
	
			res = null;
			blw = null;
			rsl = null;
	
		} catch (ClassNotFoundException e){
			CraftingManager.getInstance().addRecipe(
				new ItemStack(TSGTheifarrows.taItemArrowPotionEmpty),
				new Object[]{
					" B ",
					"GAG",
					" G ",
					'G', new ItemStack(Block.glass),
					'A', new ItemStack(Item.arrow),
					'B', new ItemStack(Item.blazePowder)
				}
			);
		}
		FCCraftingManagerCrucibleStoked.getInstance().AddRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowEnder),
			new ItemStack[]{
				new ItemStack(FCBetterThanWolves.fcBlockAxle),
				new ItemStack(Item.flint),
				new ItemStack(Item.enderPearl),
				new ItemStack(FCBetterThanWolves.fcItemSoulFlux),
				new ItemStack(FCBetterThanWolves.fcItemSoulFlux)
			},
			false
		);
		CraftingManager.getInstance().addRecipe(
			new ItemStack(TSGTheifarrows.taItemArrowSplitting),
			new Object[]{
				"CCC",
				"SAS",
				"DGD",
				'C', new ItemStack(FCBetterThanWolves.fcBlockWoodCornerItemStubID , 1, FCUtilsInventory.m_iIgnoreMetadata),
				'S', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID  , 1, FCUtilsInventory.m_iIgnoreMetadata),
				'A', new ItemStack(Item.arrow),
				'D', new ItemStack(FCBetterThanWolves.fcItemDynamite),
				'G', new ItemStack(FCBetterThanWolves.fcItemGlue)
			}
		);
	}

	private static void
	addSplittingArrows(){
		NBTTagCompound spt = new NBTTagCompound();
		spt.setInteger("numsar", 5);
		for(int i=0;i<5;++i){
			spt.setCompoundTag("sar_" + i, new ItemStack(TSGTheifarrows.taItemArrowIgnite).writeToNBT(new NBTTagCompound()));
		}
		spt.setInteger("timer", 30);
		spt.setInteger("forever", 0);
		ItemStack sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
		sps.setTagCompound(spt);

		CraftingManager.getInstance().addRecipe(
			sps,
			new Object[] {
				"III",
				"ISI",
				" D ",
				'I', new ItemStack(TSGTheifarrows.taItemArrowIgnite),
				'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
				'D', new ItemStack(FCBetterThanWolves.fcItemDynamite)
			}
		);

		

		spt = new NBTTagCompound();
		spt.setInteger("numsar", 5);
		for(int i=0;i<5;++i){
			spt.setCompoundTag("sar_" + i, new ItemStack(TSGTheifarrows.taItemArrowTorch).writeToNBT(new NBTTagCompound()));
		}
		spt.setInteger("timer", 30);
		spt.setInteger("forever", 0);
		sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
		sps.setTagCompound(spt);

		CraftingManager.getInstance().addRecipe(
			sps,
			new Object[] {
				"III",
				"ISI",
				" D ",
				'I', new ItemStack(TSGTheifarrows.taItemArrowTorch),
				'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
				'D', new ItemStack(FCBetterThanWolves.fcItemDynamite)
			}
		);
		spt = new NBTTagCompound();
		spt.setInteger("numsar", 5);
		for(int i=0;i<5;++i){
			spt.setCompoundTag("sar_" + i, new ItemStack(TSGTheifarrows.taItemArrowTorchNether).writeToNBT(new NBTTagCompound()));
		}
		spt.setInteger("timer", 30);
		spt.setInteger("forever", 0);
		sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
		sps.setTagCompound(spt);

		CraftingManager.getInstance().addRecipe(
			sps,
			new Object[] {
				"III",
				"ISI",
				" D ",
				'I', new ItemStack(TSGTheifarrows.taItemArrowTorchNether),
				'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
				'D', new ItemStack(FCBetterThanWolves.fcItemDynamite)
			}
		);
		spt = new NBTTagCompound();
		spt.setInteger("numsar", 5);
		for(int i=0;i<5;++i){
			spt.setCompoundTag("sar_" + i, new ItemStack(TSGTheifarrows.taItemArrowTorchRedstone).writeToNBT(new NBTTagCompound()));
		}
		spt.setInteger("timer", 30);
		spt.setInteger("forever", 0);
		sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
		sps.setTagCompound(spt);

		CraftingManager.getInstance().addRecipe(
			sps,
			new Object[] {
				"III",
				"ISI",
				" D ",
				'I', new ItemStack(TSGTheifarrows.taItemArrowTorchRedstone),
				'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
				'D', new ItemStack(FCBetterThanWolves.fcItemDynamite)
			}
		);
		spt = new NBTTagCompound();
		spt.setInteger("numsar", 5);
		for(int i=0;i<5;++i){
			spt.setCompoundTag("sar_" + i, new ItemStack(TSGTheifarrows.taItemArrowRising).writeToNBT(new NBTTagCompound()));
		}
		spt.setInteger("timer", 30);
		spt.setInteger("forever", 0);
		sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
		sps.setTagCompound(spt);

		CraftingManager.getInstance().addRecipe(
			sps,
			new Object[] {
				"III",
				"ISI",
				" D ",
				'I', new ItemStack(TSGTheifarrows.taItemArrowRising),
				'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
				'D', new ItemStack(FCBetterThanWolves.fcItemDynamite)
			}
		);
		spt = new NBTTagCompound();
		spt.setInteger("numsar", 5);
		for(int i=0;i<5;++i){
			spt.setCompoundTag("sar_" + i, new ItemStack(TSGTheifarrows.taItemArrowAccelerator).writeToNBT(new NBTTagCompound()));
		}
		spt.setInteger("timer", 30);
		spt.setInteger("forever", 0);
		sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
		sps.setTagCompound(spt);

		CraftingManager.getInstance().addRecipe(
			sps,
			new Object[] {
				"III",
				"ISI",
				" D ",
				'I', new ItemStack(TSGTheifarrows.taItemArrowAccelerator),
				'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
				'D', new ItemStack(FCBetterThanWolves.fcItemDynamite)
			}
		);
		spt = new NBTTagCompound();
		spt.setInteger("numsar", 5);
		for(int i=0;i<5;++i){
			spt.setCompoundTag("sar_" + i, new ItemStack(TSGTheifarrows.taItemArrowOrange).writeToNBT(new NBTTagCompound()));
		}
		spt.setInteger("timer", 30);
		spt.setInteger("forever", 0);
		sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
		sps.setTagCompound(spt);

		CraftingManager.getInstance().addRecipe(
			sps,
			new Object[] {
				"III",
				"ISI",
				" D ",
				'I', new ItemStack(TSGTheifarrows.taItemArrowOrange),
				'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
				'D', new ItemStack(FCBetterThanWolves.fcItemDynamite)
			}
		);
		spt = new NBTTagCompound();
		spt.setInteger("numsar", 5);
		for(int i=0;i<5;++i){
			spt.setCompoundTag("sar_" + i, new ItemStack(TSGTheifarrows.taItemArrowDousing).writeToNBT(new NBTTagCompound()));
		}
		spt.setInteger("timer", 30);
		spt.setInteger("forever", 0);
		sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
		sps.setTagCompound(spt);

		CraftingManager.getInstance().addRecipe(
			sps,
			new Object[] {
				"III",
				"ISI",
				" D ",
				'I', new ItemStack(TSGTheifarrows.taItemArrowDousing),
				'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
				'D', new ItemStack(FCBetterThanWolves.fcItemDynamite)
			}
		);

	}
	public static void
	addBadRecipes(){
		NBTTagCompound spt = new NBTTagCompound();
		spt.setInteger("numsar", 5);
		for(int i=0;i<5;++i){
			spt.setCompoundTag("sar_" + i, new ItemStack(Item.arrow).writeToNBT(new NBTTagCompound()));
		}
		spt.setInteger("timer", 10);
		spt.setInteger("forever", 1);
		ItemStack sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
		sps.setTagCompound(spt);

		CraftingManager.getInstance().addRecipe(
			sps,
			new Object[]{
				"AAA",
				"ASA",
				"NDN",
				'A', new ItemStack(Item.arrow),
				'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
				'D', new ItemStack(FCBetterThanWolves.fcItemDynamite),
				'N', new ItemStack(Item.netherStar)
			}
		);

		spt = new NBTTagCompound();
		spt.setInteger("numsar", 5);
		for(int i=0;i<5;++i){
			spt.setCompoundTag("sar_" + i, new ItemStack(TSGTheifarrows.taItemArrowIgnite).writeToNBT(new NBTTagCompound()));
		}
		spt.setInteger("timer", 30);
		spt.setInteger("forever", 1);
		sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
		sps.setTagCompound(spt);

		CraftingManager.getInstance().addRecipe(
			sps,
			new Object[] {
				"III",
				"ISI",
				"NDN",
				'I', new ItemStack(TSGTheifarrows.taItemArrowIgnite),
				'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
				'D', new ItemStack(FCBetterThanWolves.fcItemDynamite),
				'N', new ItemStack(Item.netherStar)
			}
		);
		spt = new NBTTagCompound();
		spt.setInteger("numsar", 5);
		for(int i=0;i<5;++i){
			spt.setCompoundTag("sar_" + i, new ItemStack(TSGTheifarrows.taItemWarcrime).writeToNBT(new NBTTagCompound()));
		}
		spt.setInteger("timer", 40);
		spt.setInteger("forever", 0);
		sps = new ItemStack(TSGTheifarrows.taItemArrowSplitting);
		sps.setTagCompound(spt);

		CraftingManager.getInstance().addRecipe(
			sps,
			new Object[]{
				"AAA",
				"ASA",
				"NDN",
				'A', new ItemStack(TSGTheifarrows.taItemWarcrime),
				'S', new ItemStack(TSGTheifarrows.taItemArrowSplitting),
				'D', new ItemStack(FCBetterThanWolves.fcItemDynamite),
				'N', new ItemStack(Item.netherStar)
			}
		);
	}
}

/*
 * Copyright (c) SpaceToad, 2011-2012
 * http://www.mod-buildcraft.com
 * 
 * BuildCraft is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package buildcraft.core.fluids;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info/>
 */
public class SingleUseTank extends Tank {

	private Fluid acceptedFluid;

	public SingleUseTank(String name, int capacity) {
		super(name, capacity);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (resource == null)
			return 0;
		if (acceptedFluid == null)
			acceptedFluid = resource.getFluid();
		if (acceptedFluid != resource.getFluid())
			return 0;
		return super.fill(resource, doFill);
	}

	public void reset() {
		acceptedFluid = null;
	}
	
	public Fluid getAcceptedFluid(){
		return acceptedFluid;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (acceptedFluid != null)
			nbt.setString("acceptedFluid", acceptedFluid.getName());
		return nbt;
	}

	@Override
	public FluidTank readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		acceptedFluid = FluidRegistry.getFluid(nbt.getString("acceptedFluid"));
		return this;
	}
}

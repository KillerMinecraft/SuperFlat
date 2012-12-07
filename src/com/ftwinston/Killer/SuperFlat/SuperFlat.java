package com.ftwinston.Killer.SuperFlat;

import org.bukkit.World.Environment;
import org.bukkit.WorldType;

import com.ftwinston.Killer.Option;
import com.ftwinston.Killer.WorldConfig;

public class SuperFlat extends com.ftwinston.Killer.WorldOption
{
	public static final int classicFlat = 0, tunnelersDream = 1, waterWorld = 2, overworld = 3, snowyKingdom = 4, bottomlessPit = 5, desert = 6, redstoneReady = 7;
	
	@Override
	public Option[] setupOptions()
	{
		// hmm, these options should really be in a config list, if/once we can get it to generate based on the preset codes
		Option[] options = {
			new Option("Classic Flat", true),
			new Option("Tunnelers' Dream", false),
			new Option("Water World", false),
			new Option("Overworld", false),
			new Option("Snowy Kingdom", false),
			new Option("Bottomless Pit", false),
			new Option("Desert", false),
			new Option("Readstone Ready", false)
		};
		
		return options;
	}
	
	@Override
	public void toggleOption(int num)
	{
		super.toggleOption(num);
		Option.ensureOnlyOneEnabled(getOptions(), num, classicFlat, tunnelersDream, waterWorld, overworld, snowyKingdom, bottomlessPit, desert, redstoneReady);
	}
	
	@Override
	public void setupWorld(WorldConfig world, Runnable runWhenDone)
	{
		if ( world.getEnvironment() == Environment.NORMAL )
		{
			world.setWorldType(WorldType.FLAT);
			
			if ( getOption(tunnelersDream).isEnabled() )
				;
			else if ( getOption(waterWorld).isEnabled() )
				;
			else if ( getOption(overworld).isEnabled() )
				;
			else if ( getOption(snowyKingdom).isEnabled() )
				;
		}
		
		createWorld(world, runWhenDone);
	}
}

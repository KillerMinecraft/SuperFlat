package com.ftwinston.Killer.SuperFlat;

import org.bukkit.World.Environment;
import org.bukkit.WorldType;

import com.ftwinston.Killer.Option;
import com.ftwinston.Killer.WorldConfig;

public class SuperFlat extends com.ftwinston.Killer.WorldOption
{
	@Override
	public Option[] setupOptions()
	{
		int num = Plugin.getNumOptions();
		if ( num < 1 )
		{
			Option[] options = {
				new Option("Classic Flat", true)
			};
			
			return options;
		}
		else
		{
			Option[] options = new Option[num];
			
			for ( int i=0; i<num; i++ )
				options[i] = new Option(Plugin.getOptionName(i), i==0);
			
			return options;
		}
	}
	
	@Override
	public void toggleOption(int num)
	{
		super.toggleOption(num);
		
		int[] allOptions = new int[Plugin.getNumOptions()];
		for ( int i=0; i<allOptions.length; i++ )
			allOptions[i] = i;
		
		Option.ensureOnlyOneEnabled(getOptions(), num, allOptions);
	}
	
	@Override
	public void setupWorld(WorldConfig world, Runnable runWhenDone)
	{
		if ( world.getEnvironment() == Environment.NORMAL )
		{
			world.setWorldType(WorldType.FLAT);
			
			int numOptions = Plugin.getNumOptions();
			for ( int i=0; i<numOptions; i++ )
				if ( getOption(i).isEnabled() )
				{
					world.setGeneratorSettings(Plugin.getOptionGenerator(i));
					break;
				}
		}
		
		createWorld(world, runWhenDone);
	}
}

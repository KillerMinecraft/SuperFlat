package com.ftwinston.KillerMinecraft.Modules.SuperFlat;

import org.bukkit.World.Environment;
import org.bukkit.WorldType;

import com.ftwinston.KillerMinecraft.Option;
import com.ftwinston.KillerMinecraft.WorldConfig;
import com.ftwinston.KillerMinecraft.WorldGenerator;
import com.ftwinston.KillerMinecraft.Configuration.ToggleOption;

public class SuperFlat extends WorldGenerator
{
	ToggleOption[] options;
	
	@Override
	public Option[] setupOptions()
	{
		int num = Plugin.getNumOptions();
		if ( num < 1 )
			options = new ToggleOption[] { new ToggleOption("Classic Flat", true) };
		else
		{
			options = new ToggleOption[num];
			
			for ( int i=0; i<num; i++ )
				options[i] = new ToggleOption(Plugin.getOptionName(i), i==0);

			ToggleOption.ensureOnlyOneEnabled(options);
		}
		
		return options;
	}
	
	@Override
	public void setupWorld(WorldConfig world, Runnable runWhenDone)
	{
		if ( world.getEnvironment() == Environment.NORMAL )
		{
			world.setWorldType(WorldType.FLAT);
			
			for ( int i=0; i<options.length; i++ )
				if ( options[i].isEnabled() )
				{
					world.setGeneratorSettings(Plugin.getOptionGenerator(i));
					break;
				}
		}
		
		createWorld(world, runWhenDone);
	}
}

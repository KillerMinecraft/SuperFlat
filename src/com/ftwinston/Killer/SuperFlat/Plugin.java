package com.ftwinston.Killer.SuperFlat;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;

import com.ftwinston.Killer.Killer;
import com.ftwinston.Killer.WorldOption;
import com.ftwinston.Killer.WorldOptionPlugin;

public class Plugin extends WorldOptionPlugin
{
	public void onEnable()
	{
		loadOptions();
		Killer.registerWorldOption(this);
	}
	
	@Override
	public WorldOption createInstance()
	{
		return new SuperFlat();
	}
	
	private static Option[] options = null;
	
	private void loadOptions()
	{
		ConfigurationSection section = getConfig().getConfigurationSection("worlds");
		Map<String, Object> optionMap = section == null ? null : section.getValues(false);
		
		// if we don't have anything specified, add some defaults
		if ( optionMap == null || optionMap.size() == 0 )
		{
			optionMap = new LinkedHashMap<String, Object>();
			optionMap.put("Classic Flat", "2;7,2x3,2;1;village");
			optionMap.put("Tunnelers' Dream", "2;7,230x1,5x3,2;3;stronghold,biome_1,decoration,dungeon,mineshaft");
			optionMap.put("Water World", "2;7,5x1,5x3,5x12,90x9;1;biome_1,village");
			optionMap.put("Overworld", "2;7,59x1,3x3,2;1;stronghold,biome_1,village,decoration,dungeon,lake,mineshaft,lava_lake");
			optionMap.put("Snowy Kingdom", "2;7,59x1,3x3,2,78;12;biome_1,village");
			optionMap.put("Bottomless Pit", "2;2x4,3x3,2;1;biome_1,village");
			optionMap.put("Desert", "2;7,3x1,52x24,8x12;2;stronghold,biome_1,village,decoration,dungeon,mineshaft");
			optionMap.put("Redstone Ready", "2;7,3x1,52x24;2");
			
			getConfig().createSection("worlds", optionMap);
			saveConfig();
		}
		
		options = new Option[optionMap.size()];
		Iterator<Map.Entry<String, Object>> it = optionMap.entrySet().iterator();
		int num = 0;
		while ( it.hasNext() )
		{
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>)it.next();
			options[num++] = new Option(entry.getKey(), (String)entry.getValue());
		}
	}
	
	static int getNumOptions() { return options.length; }
	
	static String getOptionName(int num)
	{
		return options[num].getName();
	}
	
	static String getOptionGenerator(int num)
	{
		return options[num].getGenerator();
	}
	
	private class Option
	{
		public Option(String name, String generator)
		{
			this.name = name;
			this.generator = generator;
		}
		
		private String name, generator;
		String getName() { return name; }
		String getGenerator() { return generator; }
	}
}
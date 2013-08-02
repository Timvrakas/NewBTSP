



package net.btsp.profile;


public class Profile {
	public static final String DEFAULT_JRE_ARGUMENTS = "-Xmx1G";
	private static String name;
	private static String ram;
	private static BaseVersion base;
	private static ForgeVersion forge;
	private static ModPack pack;
	private static String additionalArgs;
	
public Profile(String name, BaseVersion base, ForgeVersion forge, ModPack pack){
	this.pack = pack;
	this.base = base;
	this.forge = forge;
	this.name = name;
	
}

public String getMainClass() {
	if(forge.getMainClass() != null)
		return forge.getMainClass();
	return base.getMainClass();
}

public String getMinecraftArguments() {
	if(forge.getMinecraftArguments() != null)
		return forge.getMinecraftArguments().concat(additionalArgs);
	return base.getMinecraftArguments().concat(additionalArgs);
}
public void setSubTypes(BaseVersion base, ForgeVersion forge, ModPack pack){
	this.pack = pack;
	this.base = base;
	this.forge = forge;
}
public ForgeVersion getForge(){
	return forge;
}
public BaseVersion getBase(){
	return base;
}
public ModPack getPack(){
	return pack;
}

}

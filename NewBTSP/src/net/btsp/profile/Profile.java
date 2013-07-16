package net.btsp.profile;


public class Profile {
	public static final String DEFAULT_JRE_ARGUMENTS = "-Xmx1G";
	private static BaseVersion base;
	private static ForgeVersion forge;
	private static ModPack pack;
	
public Profile(String name, BaseVersion base, ForgeVersion forge, ModPack pack){
	this.pack = pack;
	this.base = base;
	this.forge = forge;
}

}

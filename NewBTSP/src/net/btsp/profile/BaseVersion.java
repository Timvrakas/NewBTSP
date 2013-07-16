/*     */ package net.btsp.profile;
/*     */ 
/*     */ import java.io.File;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.launcher.OperatingSystem;
import net.minecraft.launcher.updater.download.Downloadable;

import com.sun.corba.se.impl.util.Version;

/*     */ public class BaseVersion
/*     */ {
/*     */   private String id;
/*     */   private Date time;
/*     */   private Date releaseTime;
/*     */   private ReleaseType type;
/*     */   private String minecraftArguments;
/*     */   private List<Library> libraries;
/*     */   private String mainClass;
/*     */   private int minimumLauncherVersion;
/*     */   private String incompatibilityReason;
/*     */   private List<Rule> rules;
/*     */ 
/*     */   public BaseVersion()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BaseVersion(String id, Date releaseTime, Date updateTime, ReleaseType type, String mainClass, String minecraftArguments)
/*     */   {
/*  28 */     if ((id == null) || (id.length() == 0)) throw new IllegalArgumentException("ID cannot be null or empty");
/*  29 */     if (releaseTime == null) throw new IllegalArgumentException("Release time cannot be null");
/*  30 */     if (updateTime == null) throw new IllegalArgumentException("Update time cannot be null");
/*  31 */     if (type == null) throw new IllegalArgumentException("Release type cannot be null");
/*  32 */     if ((mainClass == null) || (mainClass.length() == 0)) throw new IllegalArgumentException("Main class cannot be null or empty");
/*  33 */     if (minecraftArguments == null) throw new IllegalArgumentException("Process arguments cannot be null or empty");
/*     */ 
/*  35 */     this.id = id;
/*  36 */     this.releaseTime = releaseTime;
/*  37 */     this.time = updateTime;
/*  38 */     this.type = type;
/*  39 */     this.mainClass = mainClass;
/*  40 */     this.libraries = new ArrayList();
/*  41 */     this.minecraftArguments = minecraftArguments;
/*     */   }
/*     */ 
/*     */   public BaseVersion(BaseVersion version) {
/*  45 */     this(version.getId(), version.getReleaseTime(), version.getUpdatedTime(), version.getType(), version.getMainClass(), version.getMinecraftArguments());
/*     */   }
/*     */ 
/*     */ 
/*     */   public String getId()
/*     */   {
/*  54 */     return this.id;
/*     */   }
/*     */ 
/*     */   public ReleaseType getType()
/*     */   {
/*  59 */     return this.type;
/*     */   }
/*     */ 
/*     */   public Date getUpdatedTime()
/*     */   {
/*  64 */     return this.time;
/*     */   }
/*     */ 
/*     */   public Date getReleaseTime()
/*     */   {
/*  69 */     return this.releaseTime;
/*     */   }
/*     */ 
/*     */   public Collection<Library> getLibraries() {
/*  73 */     return this.libraries;
/*     */   }
/*     */ 
/*     */   public String getMainClass() {
/*  77 */     return this.mainClass;
/*     */   }
/*     */ 
/*     */   public void setUpdatedTime(Date time)
/*     */   {
/*  82 */     if (time == null) throw new IllegalArgumentException("Time cannot be null");
/*  83 */     this.time = time;
/*     */   }
/*     */ 
/*     */   public void setReleaseTime(Date time)
/*     */   {
/*  88 */     if (time == null) throw new IllegalArgumentException("Time cannot be null");
/*  89 */     this.releaseTime = time;
/*     */   }
/*     */ 
/*     */   public void setType(ReleaseType type)
/*     */   {
/*  94 */     if (type == null) throw new IllegalArgumentException("Release type cannot be null");
/*  95 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public void setMainClass(String mainClass) {
/*  99 */     if ((mainClass == null) || (mainClass.length() == 0)) throw new IllegalArgumentException("Main class cannot be null or empty");
/* 100 */     this.mainClass = mainClass;
/*     */   }
/*     */ 
/*     */   public Collection<Library> getRelevantLibraries() {
/* 104 */     List result = new ArrayList();
/*     */ 
/* 106 */     for (Library library : this.libraries) {
/* 107 */       if (library.appliesToCurrentEnvironment()) {
/* 108 */         result.add(library);
/*     */       }
/*     */     }
/*     */ 
/* 112 */     return result;
/*     */   }
/*     */ 
/*     */   public Collection<File> getClassPath(OperatingSystem os, File base) {
/* 116 */     Collection<Library> libraries = getRelevantLibraries();
/* 117 */     Collection result = new ArrayList();
/*     */ 
/* 119 */     for (Library library : libraries) {
/* 120 */       if (library.getNatives() == null) {
/* 121 */         result.add(new File(base, "libraries/" + library.getArtifactPath()));
/*     */       }
/*     */     }
/*     */ 
/* 125 */     result.add(new File(base, "versions/" + getId() + "/" + getId() + ".jar"));
/*     */ 
/* 127 */     return result;
/*     */   }
/*     */ 
/*     */   public Collection<String> getExtractFiles(OperatingSystem os) {
/* 131 */     Collection<Library> libraries = getRelevantLibraries();
/* 132 */     Collection result = new ArrayList();
/*     */ 
/* 134 */     for (Library library : libraries) {
/* 135 */       Map natives = library.getNatives();
/*     */ 
/* 137 */       if ((natives != null) && (natives.containsKey(os))) {
/* 138 */         result.add("libraries/" + library.getArtifactPath((String)natives.get(os)));
/*     */       }
/*     */     }
/*     */ 
/* 142 */     return result;
/*     */   }
/*     */ 
/*     */   public Set<String> getRequiredFiles(OperatingSystem os) {
/* 146 */     Set neededFiles = new HashSet();
/*     */ 
/* 148 */     for (Library library : getRelevantLibraries()) {
/* 149 */       if (library.getNatives() != null) {
/* 150 */         String natives = (String)library.getNatives().get(os);
/* 151 */         if (natives != null) neededFiles.add("libraries/" + library.getArtifactPath(natives)); 
/*     */       }
/* 153 */       else { neededFiles.add("libraries/" + library.getArtifactPath()); }
/*     */ 
/*     */     }
/*     */ 
/* 157 */     return neededFiles;
/*     */   }
/*     */ 
/*     */   public Set<Downloadable> getRequiredDownloadables(OperatingSystem os, Proxy proxy, File targetDirectory, boolean ignoreLocalFiles) throws MalformedURLException {
/* 161 */     Set neededFiles = new HashSet();
/*     */ 
/* 163 */     for (Library library : getRelevantLibraries()) {
/* 164 */       String file = null;
/*     */ 
/* 166 */       if (library.getNatives() != null) {
/* 167 */         String natives = (String)library.getNatives().get(os);
/* 168 */         if (natives != null)
/* 169 */           file = library.getArtifactPath(natives);
/*     */       }
/*     */       else {
/* 172 */         file = library.getArtifactPath();
/*     */       }
/*     */ 
/* 175 */       if (file != null) {
/* 176 */         URL url = new URL(library.getDownloadUrl() + file);
/* 177 */         neededFiles.add(new Downloadable(url, new File(targetDirectory, "libraries/" + file), ignoreLocalFiles));
/*     */       }
/*     */     }
/*     */ 
/* 181 */     return neededFiles;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 186 */     return "CompleteVersion{id='" + this.id + '\'' + ", time=" + this.time + ", type=" + this.type + ", libraries=" + this.libraries + ", mainClass='" + this.mainClass + '\'' + ", minimumLauncherVersion=" + this.minimumLauncherVersion + '}';
/*     */   }
/*     */ 
/*     */   public String getMinecraftArguments()
/*     */   {
/* 197 */     return this.minecraftArguments;
/*     */   }
/*     */ 
/*     */   public void setMinecraftArguments(String minecraftArguments) {
/* 201 */     if (minecraftArguments == null) throw new IllegalArgumentException("Process arguments cannot be null or empty");
/* 202 */     this.minecraftArguments = minecraftArguments;
/*     */   }
/*     */ 
/*     */   public int getMinimumLauncherVersion() {
/* 206 */     return this.minimumLauncherVersion;
/*     */   }
/*     */ 
/*     */   public void setMinimumLauncherVersion(int minimumLauncherVersion) {
/* 210 */     this.minimumLauncherVersion = minimumLauncherVersion;
/*     */   }
/*     */ 
/*     */   public boolean appliesToCurrentEnvironment() {
/* 214 */     if (this.rules == null) return true;
/* 215 */     Rule.Action lastAction = Rule.Action.DISALLOW;
/*     */ 
/* 217 */     for (Rule rule : this.rules) {
/* 218 */       Rule.Action action = rule.getAppliedAction();
/* 219 */       if (action != null) lastAction = action;
/*     */     }
/*     */ 
/* 222 */     return lastAction == Rule.Action.ALLOW;
/*     */   }
/*     */ 
/*     */   public String getIncompatibilityReason() {
/* 226 */     return this.incompatibilityReason;
/*     */   }
/*     */ }

/* Location:           /Users/timv/Library/Application Support/minecraft/launcher.jar
 * Qualified Name:     net.minecraft.launcher.versions.CompleteVersion
 * JD-Core Version:    0.6.2
 */
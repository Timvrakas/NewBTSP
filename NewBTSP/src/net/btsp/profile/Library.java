/*     */ package net.btsp.profile;
/*     */ 
/*     */ import java.util.EnumMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.launcher.OperatingSystem;
/*     */ 
/*     */ public class Library
/*     */ {
/*     */   private String name;
/*     */   private List<Rule> rules;
/*     */   private Map<OperatingSystem, String> natives;
/*     */   private ExtractRules extract;
/*     */   private String url;
/*     */ 
/*     */   public Library()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Library(String name)
/*     */   {
/*  20 */     if ((name == null) || (name.length() == 0)) throw new IllegalArgumentException("Library name cannot be null or empty");
/*  21 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getName() {
/*  25 */     return this.name;
/*     */   }
/*     */ 
/*     */   public Library addNative(OperatingSystem operatingSystem, String name) {
/*  29 */     if ((operatingSystem == null) || (!operatingSystem.isSupported())) throw new IllegalArgumentException("Cannot add native for unsupported OS");
/*  30 */     if ((name == null) || (name.length() == 0)) throw new IllegalArgumentException("Cannot add native for null or empty name");
/*  31 */     if (this.natives == null) this.natives = new EnumMap<OperatingSystem, String>(OperatingSystem.class);
/*  32 */     this.natives.put(operatingSystem, name);
/*  33 */     return this;
/*     */   }
/*     */ 
/*     */   public List<Rule> getRules() {
/*  37 */     return this.rules;
/*     */   }
/*     */ 
/*     */   public boolean appliesToCurrentEnvironment() {
/*  41 */     if (this.rules == null) return true;
/*  42 */     Rule.Action lastAction = Rule.Action.DISALLOW;
/*     */ 
/*  44 */     for (Rule rule : this.rules) {
/*  45 */       Rule.Action action = rule.getAppliedAction();
/*  46 */       if (action != null) lastAction = action;
/*     */     }
/*     */ 
/*  49 */     return lastAction == Rule.Action.ALLOW;
/*     */   }
/*     */ 
/*     */   public Map<OperatingSystem, String> getNatives() {
/*  53 */     return this.natives;
/*     */   }
/*     */ 
/*     */   public ExtractRules getExtractRules() {
/*  57 */     return this.extract;
/*     */   }
/*     */ 
/*     */   public Library setExtractRules(ExtractRules rules) {
/*  61 */     this.extract = rules;
/*  62 */     return this;
/*     */   }
/*     */ 
/*     */   public String getArtifactBaseDir() {
/*  66 */     if (this.name == null) throw new IllegalStateException("Cannot get artifact dir of empty/blank artifact");
/*  67 */     String[] parts = this.name.split(":", 3);
/*  68 */     return String.format("%s/%s/%s", new Object[] { parts[0].replaceAll("\\.", "/"), parts[1], parts[2] });
/*     */   }
/*     */ 
/*     */   public String getArtifactPath() {
/*  72 */     if (this.name == null) throw new IllegalStateException("Cannot get artifact path of empty/blank artifact");
/*  73 */     return String.format("%s/%s", new Object[] { getArtifactBaseDir(), getArtifactFilename() });
/*     */   }
/*     */ 
/*     */   public String getArtifactPath(String classifier) {
/*  77 */     if (this.name == null) throw new IllegalStateException("Cannot get artifact path of empty/blank artifact");
/*  78 */     return String.format("%s/%s", new Object[] { getArtifactBaseDir(), getArtifactFilename(classifier) });
/*     */   }
/*     */ 
/*     */   public String getArtifactFilename() {
/*  82 */     if (this.name == null) throw new IllegalStateException("Cannot get artifact filename of empty/blank artifact");
/*  83 */     String[] parts = this.name.split(":", 3);
/*  84 */     return String.format("%s-%s.jar", new Object[] { parts[1], parts[2] });
/*     */   }
/*     */ 
/*     */   public String getArtifactFilename(String classifier) {
/*  88 */     if (this.name == null) throw new IllegalStateException("Cannot get artifact filename of empty/blank artifact");
/*  89 */     String[] parts = this.name.split(":", 3);
/*  90 */     return String.format("%s-%s-%s.jar", new Object[] { parts[1], parts[2], classifier });
/*     */   }

/*     */   public String getDownloadUrl()
/*     */   {
/* 104 */     if (this.url != null) return this.url;
/* 105 */     return "https://s3.amazonaws.com/Minecraft.Download/libraries/";
/*     */   }
/*     */ }

/* Location:           /Users/timv/Library/Application Support/minecraft/launcher.jar
 * Qualified Name:     net.minecraft.launcher.versions.Library
 * JD-Core Version:    0.6.2
 */
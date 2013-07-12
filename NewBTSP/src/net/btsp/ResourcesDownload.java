package net.btsp;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.minecraft.launcher.updater.download.Downloadable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ResourcesDownload {

	private Set<Downloadable> getResourceFiles(File baseDirectory) {
		 Set<Downloadable> result = new HashSet<Downloadable>();
		  try{
		  URL resourceUrl = new URL("https://s3.amazonaws.com/Minecraft.Resources/");
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  DocumentBuilder db = dbf.newDocumentBuilder();
		  Document doc = db.parse(resourceUrl.openStream());
	      NodeList nodeLst = doc.getElementsByTagName("Contents");
		/*     */ 
		/* 266 */       long start = System.nanoTime();
		/* 267 */       for (int i = 0; i < nodeLst.getLength(); i++) {
		/* 268 */         Node node = nodeLst.item(i);
		/*     */ 
		/* 270 */         if (node.getNodeType() == 1) {
		/* 271 */           Element element = (Element)node;
		/* 272 */           String key = element.getElementsByTagName("Key").item(0).getChildNodes().item(0).getNodeValue();
		/* 273 */           String etag = element.getElementsByTagName("ETag") != null ? element.getElementsByTagName("ETag").item(0).getChildNodes().item(0).getNodeValue() : "-";
		/* 274 */           long size = Long.parseLong(element.getElementsByTagName("Size").item(0).getChildNodes().item(0).getNodeValue());
		/*     */ 
		/* 276 */           if (size > 0L) {
		/* 277 */             File file = new File(baseDirectory, "assets/" + key);
		/* 278 */             if (etag.length() > 1) {
		/* 279 */               etag = Downloadable.getEtag(etag);
		/* 280 */               if ((file.isFile()) && (file.length() == size)) {
		/* 281 */                 String localMd5 = Downloadable.getMD5(file);
		/* 282 */                 if (localMd5.equals(etag)) continue;
		/*     */               }
		/*     */             }
		/* 285 */             Downloadable downloadable = new Downloadable(new URL("https://s3.amazonaws.com/Minecraft.Resources/" + key), file, false);
		/* 286 */             downloadable.setExpectedSize(size);
		/* 287 */             result.add(downloadable);
		/*     */           }
		/*     */         }
		/*     */       }
		/* 291 */       long end = System.nanoTime();
		/* 292 */       long delta = end - start;
		/* 293 */       System.out.println("Delta time to compare resources: " + delta / 1000000L + " ms ");
		/*     */     } catch (Exception ex) {
		/* 295 */       ex.printStackTrace();
		/*     */     }
		/*     */ 
		/* 298 */     return result;
		/*     */   }
}

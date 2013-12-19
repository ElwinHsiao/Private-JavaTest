package test.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mediautil.image.jpeg.AbstractImageInfo;
import mediautil.image.jpeg.Exif;
import mediautil.image.jpeg.LLJTran;
import mediautil.image.jpeg.LLJTranException;
import net.sourceforge.jheader.App1Header;
import net.sourceforge.jheader.JpegHeaders;
import net.sourceforge.jheader.TagValue;

import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;
import org.apache.sanselan.common.IImageMetadata;
import org.apache.sanselan.formats.jpeg.JpegImageMetadata;
import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Integer ic = 2;
//		ic.to
//		String str = "Hello";
//		Charset.forName("UTF-8")
		
//		ByteBuffer bb = ByteBuffer.allocate(1024);
//		bb.putInt(-1);
//		byte[] bt = bb.array();
////		bb.clear();
////		bb.put(bt);
//		
//		byte b = -1;
//		int i = -1;
		
		
//		String.format("%x", bt)
//		System.out.println(Byte.toString(b));
//		System.out.println(Integer.toString(bb.get(0), 2));
		
		
//		String[] fileNms = new String[] { 
//				"E:/resource/play_xml/live_ch.xml",
//				"E:/resource/play_xml/vod_ch.xml",	
//				"E:/resource/play_xml/2012.xml",
//				"E:/resource/play_xml/2012.xml",
//		};
//		for (int i = 0; i < fileNms.length; ++i) {
//			File file = new File(fileNms[i]);
//			
//			FileInputStream fis = null;
//			try {
//				fis = new FileInputStream(file);
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			//String str = getFileMD5(file);
//			String str = getInputStreamMD5(fis);
//			
//			System.out.println(fileNms[i] + " MD5=" + str);			
//		}
		
//		System.out.println("KeyManagerFactory.getDefaultAlgorithm()=" + KeyManagerFactory.getDefaultAlgorithm());
		
//		String old = "a b c";
//		String newstr = old.replace(' ', '_');
//		System.out.println("old=" + old + "\nnew=" + newstr);
		
//		Thread thread = new Thread() {		
//			@Override
//			public void run() {
//				for (int i = 0; i < 100; ++i) {
//					System.out.println("child thread loop: " + (i+1));
////					try {
////						sleep(1000);
////					} catch (InterruptedException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
//				}
//			}
//		};
//		
////		thread.setDaemon(true);
//		thread.start();
//		try {
//			thread.join();
//			System.out.println("after join");
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		

//		System.out.println("main thread exit");
		
		Main mainClass = new Main();
//		mainClass.getDataTimeByFileName("/storage/sdcard0/DCIM/C360_2013-03-23-10-09-43-011.jpg");
//		mainClass.getDataTimeByFileName("/storage/sdcard0/DCIM/IMG_20130210_161320.jpg");
//		mainClass.exifStudy();
//		mainClass.dateTimeCalendarStudy();
//		mainClass.metadataExtractorStudy();
//		mainClass.repairPhotoTimeStudy();
//		File file = new File("D:\\My Documents\\My Pictures\\testtime\\");
//		System.out.println(file.listFiles()[0].getPath());
//		mainClass.studyStaitcGetSelf();
		try { 
	    	((A)new B()).process();
	    	System.out.print("Normal ");
	    } catch (Exception e) { 
	    	System.out.print("Exception ");
	    }  
	}
	
	static class A {  
		public void process() { 
			System.out.print("A ");
			throw new RuntimeException();
		}

	}
	static class B extends A {  
		public void process() {  
			System.out.print("B ");
		}
	}  

	
	private static final String thisClassName = Runtime.getRuntime().getClass().getName();
	private void studyStaitcGetSelf() {
//		System.out.println("className=" + thisClassName);
		getThisClassName();
	}
	
	public static void getThisClassName()
	{
	    // 方法1：通过SecurityManager的保护方法getClassContext()
	    String clazzName = new SecurityManager()
	    {
	        public String getClassName() 
	        {
	            return getClassContext()[1].getName();
	        }
	    }.getClassName();
	    System.out.println(clazzName);
	    // 方法2：通过Throwable的方法getStackTrace()
	    String clazzName2 = new Throwable().getStackTrace()[1].getClassName();
	    System.out.println(clazzName2);
	    // 方法3：通过分析匿名类名称()
	    String clazzName3 = new Object()    {
	        public String getClassName() 
	        {
	            String clazzName = this.getClass().getName();
	            return clazzName.substring(0, clazzName.lastIndexOf('$'));
	        }
	    }.getClassName();
	    System.out.println(clazzName3);

	    System.out.println(new Object() { }.getClass().getEnclosingClass().getName());
	    
	    System.out.println("thread=" +  ClassName.CALLED_CLASS_NAME());
	}
	
	public static class ClassName {
		public static String CALLED_CLASS_NAME() {
			return Thread.currentThread().getStackTrace()[2].getClassName();
		}
	}
	
	private void repairPhotoTimeStudy() {
		new RepairPhotoTime().debug();;
	}
	
	private void metadataExtractorStudy() {
		File file = new File("D:\\My Documents\\My Pictures\\testtime\\20081230151610654_have_origal_time.jpg");
//		File file = new File("D:\\My Documents\\My Pictures\\testtime\\IMG_20130505_155951-1.jpg");
		System.out.println("printExifByMetadataExtractor");
		printExifByMetadataExtractor(file);
//		System.out.println("\nprintExifByJHead");
//		printExifByJHead(file);
//		System.out.println("printExifByLLJTran");
//		printExifByLLJTran(file);
//		System.out.println("printExifBySanselan");
//		printExifBySanselan(file);
	}
	
	
	private void printExifBySanselan(File file) {
		IImageMetadata metadata = null;
        try {
            metadata = Sanselan.getMetadata(file);
        } catch (ImageReadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
//        if (metadata instanceof JpegImageMetadata) {
        	JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
            System.out.println("\nFile: " + file.getPath());
            
            TiffField field = jpegMetadata.findEXIFValue(TiffConstants.EXIF_TAG_DATE_TIME_ORIGINAL);
            if (field == null) {
                System.out.println("Not Found.");
            } else {
                System.out.println(field.getValueDescription());
            }
//        }
	}
	
	private void printExifByLLJTran(File file) {
		LLJTran llj = new LLJTran(file);
		try {
			llj.read(LLJTran.READ_INFO,true);
		} catch (LLJTranException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		llj.setComment("test jpeg commen");

//		llj.setAppx(0, LLJTran.dummyExifHeader, 0, LLJTran.dummyExifHeader.length, true);
		AbstractImageInfo imageInfo = llj.getImageInfo();
		
		Date date = new Date();
		System.out.println("getDataTimeOriginal=" + imageInfo.getDataTimeOriginalString() + " date=" + date);
		
//		if(imageInfo.getThumbnailLength() > 0)
//        {
//            System.out.println("Image already has a Thumbnail. Exitting..");
//            System.exit(1);
//        }

		int tag = Exif.DATETIME;
		if(imageInfo instanceof Exif) {
			Exif exif = (Exif) imageInfo;
			mediautil.image.jpeg.Entry entry = exif.getTagValue(tag, true);
//			Object object = exif.getAttribute(Exif.DATETIMEORIGINALSTRING);
//			System.out.println(object);
			System.out.println("instanceof Exif, entry=" + entry + " getValues=" + entry.getValue(0));
		} else {
			System.out.println("have not exif data");
			llj.addAppx(LLJTran.dummyExifHeader, 0,
                    LLJTran.dummyExifHeader.length, true);
			imageInfo = llj.getImageInfo(); // This would have changed
			Exif exif = (Exif) imageInfo;
			
			 
        
//			mediautil.image.jpeg.Entry entry = new mediautil.image.jpeg.Entry(Exif.ASCII);
			mediautil.image.jpeg.Entry entry = exif.getTagValue(tag, true);
			entry.setValue(0, "2013:12:12 13:13:13");		// format: yyyy:MM:dd HH:mm:ss
			exif.setTagValue(tag, -1, entry, true);
			llj.refreshAppx();  // Recreate Marker Data for changes done
			
			OutputStream out;
			try {
				File outFile = new File(file.getPath()+"_bak.jpg");
				outFile.createNewFile();
				out = new BufferedOutputStream(new FileOutputStream(outFile));
				llj.xferInfo(null, out, LLJTran.REPLACE, LLJTran.REPLACE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}
	
	private void printExifByMetadataExtractor(File file) {
		Metadata metadata = null;
		try {
			metadata = JpegMetadataReader.readMetadata(file);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		
		// obtain the Exif directory
//		ExifSubIFDDirectory directory = metadata.getDirectory(ExifSubIFDDirectory.class);
//
//		// query the tag's value
//		Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
//		System.out.println(date);
		
		
		for (Directory directory : metadata.getDirectories()) {
		    for (Tag tag : directory.getTags()) {
		        System.out.println("directory=" + directory.getClass() + " tag=" + tag);
		    }
		}
		
		
//		Directory exif = metadata.getDirectory(ExifIFD0Directory.class);	//ExifIFD0Directory、ExifInteropDirectory、ExifSubIFDDirectory、ExifThumbnailDirectory
//		Collection<Tag> tags = exif.getTags();
//		Iterator<Tag> iter = tags.iterator();
//		// 逐个遍历每个Tag
//		while (iter.hasNext()) {
//			Tag tag = (Tag) iter.next();
//			System.out.println(tag);
//		}
		
		
//		// 检查是否Tag中包含了图片属性-摘要中的作者 (xp)
//		if (exif.containsTag(ExifIFD0Directory.TAG_WIN_AUTHOR)) {
//			System.out.println("->Pic author is "
//					+ exif.getDescription(ExifIFD0Directory.TAG_WIN_AUTHOR));
//		}
//		// 检查是否Tag中包含了图片属性-摘要中的标题 (xp)
//		if (exif.containsTag(ExifIFD0Directory.TAG_WIN_TITLE)) {
//			System.out.println("->Pic title is "
//					+ exif.getDescription(ExifIFD0Directory.TAG_WIN_TITLE));
//		}
//		// 检查是否Tag中包含了图片属性-摘要中的主题 (xp)
//		if (exif.containsTag(ExifIFD0Directory.TAG_WIN_SUBJECT)) {
//			System.out.println("->Pic subject is "
//					+ exif.getDescription(ExifIFD0Directory.TAG_WIN_SUBJECT));
//		}
	}
	
	private void printExifByJHead(File file) {
		String filePath = file.getPath();
		JpegHeaders jpegHeaders;
		try {
			jpegHeaders = new JpegHeaders(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} 
		App1Header exifHeader = jpegHeaders.getApp1Header();

		// 遍历显示EXIF
		SortedMap<net.sourceforge.jheader.App1Header.Tag, TagValue> tags = exifHeader.getTags();
//		Map.Entry<Tag, TagValue> entry = tags.entrySet();
		for (Entry<net.sourceforge.jheader.App1Header.Tag, TagValue> entry: tags.entrySet()) {
			System.out.println(entry.getKey() + "[" + entry.getKey().name
					+ "]:" + entry.getValue());
		}

		// 修改EXIF的拍照日期
//		exifHeader.setValue(Tag.DATETIMEORIGINAL, getTimeByFileName(filename));
		// 保存
//		jpegHeaders.save(true);
	}
	
	private void dateTimeCalendarStudy() {
//		Date date = new Date();
//		System.out.println(date);
//		DateFormat df =  DateFormat.getDateInstance();
//		System.out.println(df.format(date));
//		df = DateFormat.getTimeInstance();
//		System.out.println(df.format(date));
//		df = DateFormat.getDateTimeInstance();
//		System.out.println(df.format(date));
//		df = new SimpleDateFormat();
//		System.out.println(df.format(date));
//		System.out.println(new SimpleDateFormat().toLocalizedPattern());
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(date));
//		System.out.println(date.getTime());
		
		
		String dateTime = "20130210 161320";
		
		DateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
		try {
			df.parse(dateTime);
		} catch (ParseException e) {
			throw new RuntimeException("parser file name fail, your pattern need to update");
		}
		
		System.out.println(df.getCalendar().getTime());
	}
	
	private void exifStudy() {
		new RepairPhotoTime().debug();
	}


	public static String getFileMD5(File file) {
	    if (!file.isFile()){
	      return null;
	    }
	    MessageDigest digest = null;
	    FileInputStream in=null;
	    byte buffer[] = new byte[1024];
	    int len;
	    try {
	      digest = MessageDigest.getInstance("MD5");
	      in = new FileInputStream(file);
	      while ((len = in.read(buffer, 0, 1024)) != -1) {
	        digest.update(buffer, 0, len);
	      }
	      in.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	    BigInteger bigInt = new BigInteger(1, digest.digest());
	    return bigInt.toString(16);
	  }
	
	public static String getInputStreamMD5(InputStream in) {
		if (in == null) {
			return null;
		}

	    MessageDigest digest = null;
	    byte buffer[] = new byte[1024];
	    int len;
	    try {

	    } catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	      try {
			digest = MessageDigest.getInstance("MD5");
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
	      } catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      	
	    BigInteger bigInt = new BigInteger(1, digest.digest());
//	    byte[] byte1 = bigInt.toByteArray();
	    return bigInt.toString(16);
	  }

	private static final long K = 1024;
	private static final long M = K * K;
	private static final long G = M * K;
	private static final long T = G * K;

	public static String convertToStringRepresentation(final long value){
	    final long[] dividers = new long[] { T, G, M, K, 1 };
	    final String[] units = new String[] { "TB", "GB", "MB", "KB", "B" };
	    if(value < 1)
	        throw new IllegalArgumentException("Invalid file size: " + value);
	    String result = null;
	    for(int i = 0; i < dividers.length; i++){
	        final long divider = dividers[i];
	        if(value >= divider){
	            result = format(value, divider, units[i]);
	            break;
	        }
	    }
	    return result;	}

	private static String format(final long value,
	    final long divider,
	    final String unit){
	    final double result =
	        divider > 1 ? (double) value / (double) divider : (double) value;
	    return new DecimalFormat("#,##0.#").format(result) + " " + unit;
	}
	
	public String hello() {
		return "hello";
	}
	
	public interface TestIf{
		void testPl();
	}
	
	public void matcherStudy() {
		Pattern pattern = Pattern.compile("\\d{3,5}");
		String charSequence = "123-34345-234-00";
		Matcher matcher = pattern.matcher(charSequence);
 
		//虽然匹配失败，但由于charSequence里面的"123"和pattern是匹配的,所以下次的匹配从位置4开始
		System.out.println(matcher.matches());
		//测试匹配位置
		matcher.find();
		System.out.println(matcher.start());
		
		//使用reset方法重置匹配位置
		matcher.reset();

		// 第一次find匹配以及匹配的目标和匹配的起始位置
		System.out.println(matcher.find());
		System.out.println(matcher.group() + " - " + matcher.start());
		
		//第二次find匹配以及匹配的目标和匹配的起始位置
		System.out.println(matcher.find());
		System.out.println(matcher.group() + " - " + matcher.start());

		// 第一次lookingAt匹配以及匹配的目标和匹配的起始位置
		System.out.println(matcher.lookingAt());
		System.out.println(matcher.group() + " - " + matcher.start());

		// 第二次lookingAt匹配以及匹配的目标和匹配的起始位置
		System.out.println(matcher.lookingAt());
		System.out.println(matcher.group() + " - " + matcher.start());
	}
}

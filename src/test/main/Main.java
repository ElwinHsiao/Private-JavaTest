package test.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.jheader.App1Header;
import net.sourceforge.jheader.App1Header.Tag;
import net.sourceforge.jheader.ExifFormatException;
import net.sourceforge.jheader.JpegFormatException;
import net.sourceforge.jheader.JpegHeaders;
import net.sourceforge.jheader.TagFormatException;
import net.sourceforge.jheader.TagValue;


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
		mainClass.exifStudy();
//		mainClass.dateTimeCalendarStudy();
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

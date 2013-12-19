package test.main;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import android.media.ExifInterface;
//import android.util.Log;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public class RepairPhotoTime {
	
	private static final String TAG = "RepairPhotoTime";
	
	public boolean needToRepair(File file) throws IOException {
		// evaluate the GPS date and time stamp
//		ExifInterface exif = null;
//		exif = new ExifInterface(file.getPath());
//		String item = exif.getAttribute(ExifInterface.TAG_GPS_DATESTAMP);
//		if (item == null || item.equals("")) {
//			Log.i(TAG, "evalute GPS date stamp failed!");
//			return false;
//		}
//		item = exif.getAttribute(ExifInterface.TAG_GPS_TIMESTAMP);
//		if (item == null || item.equals("")) {
//			Log.i(TAG, "evalute GPS time stamp failed!");
//			return false;
//		}
//		
//		// evaluate the Exif DateTimeOriginal
//		if (!evaluteDateTimeOriginal(file)) {
//			Log.i(TAG, "evalute Exif DateTimeOriginal failed!");
//			return false;
//		}
				
		return true;
	}

	private boolean evaluteDateTimeOriginal(File file) throws IOException {
		Metadata metadata = null;
		try {
			metadata = JpegMetadataReader.readMetadata(file);
		} catch (JpegProcessingException e) {
			System.out.println("metadata == null" + e);
			return false;
		}

		Directory directory = metadata.getDirectory(ExifSubIFDDirectory.class);	//ExifIFD0Directory、ExifInteropDirectory、ExifSubIFDDirectory、ExifThumbnailDirectory
		if (directory == null) {
			System.out.println("directory == null");
			return false;
		}
		
		Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
		if (date == null) {
			System.out.println("date == null");
			return false;
		}
		
		return true;
	}
	
	public long getFileModifiedTime(String filePath) {
		File f = new File(filePath);  
		return f.lastModified();  
	}
	
	public boolean setFileModifiedTime(String filePath, long time) {
		File f = new File(filePath);
		return f.setLastModified(time);
	}
	
	public Calendar getCaputureTimeByFileName(String filePath) {
		Calendar cal = tryMiuiPattern(filePath);
		if (cal != null) {
			return cal;
		}

		cal = tryC360Pattern(filePath);
		if (cal != null) {
			return cal;
		}
		
		System.err.println("have not matched!");
		return null;
	}
	
	private Calendar tryMiuiPattern(String fileName) {
		Pattern pattern = Pattern.compile("IMG_([0-9]{8})_([0-9]{6})");		// example: IMG_20130210_161320*.jpg
		Matcher matcher = pattern.matcher(fileName);
		if (matcher.find()) {
			String dateTime = matcher.group(1) + " " + matcher.group(2);
			System.out.println("group=" + matcher.group() + " dateTime=" + dateTime);
			
			DateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
			try {
				df.parse(dateTime);
			} catch (ParseException e) {
				throw new RuntimeException("parser file name fail, your pattern need to update");
			}
			
			return df.getCalendar();
		}
		
		return null;
	}
	
	private Calendar tryC360Pattern(String fileHame) {
		Pattern pattern = Pattern.compile("C360_([0-9-]{19})");		// example: C360_2013-02-10-16-13-49*.jpg
		Matcher matcher = pattern.matcher(fileHame);
		if (matcher.find()) {
			String dateTime = matcher.group(1);
			System.out.println("group=" + matcher.group() + " dateTime=" + dateTime);
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			try {
				df.parse(dateTime);
			} catch (ParseException e) {
				throw new RuntimeException("parser file name fail, pattern need to update");
			}

			return df.getCalendar();
		}
		
		return null;
	}
	
	
	/*************************************-test-*********************************************/
	private static final boolean DEBUG_MODE = true;
	public void debug() {
		if (!DEBUG_MODE) {
			throw new UnsupportedOperationException("this class is not in debug mode");
		}
		
		String filePath = "D:\\My Documents\\My Pictures\\testtime\\IMG_20130210_161320-new.jpg";
		File file = new File(filePath);
		
		filePath = file.getName();
		Calendar cal = getCaputureTimeByFileName(filePath);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("DEBUG-MODE: " + df.format(cal.getTime()));
		
		file = new File(filePath);
		filePath = file.getName();
		filePath = "D:\\My Documents\\My Pictures\\testtime\\C360_2013-02-10-16-13-49.jpg";
		cal = getCaputureTimeByFileName(filePath);
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("DEBUG-MODE: " + df.format(cal.getTime()));
		
		// test needToRepair
		System.out.println("DEBUG-MODE: test needToRepair start >>>>>>");
		file = new File("D:\\My Documents\\My Pictures\\testtime\\C360_2013-02-10-16-13-49.jpg");
		try {
			boolean result = needToRepair(file);
			System.out.println("needToRepair=" + result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("DEBUG-MODE: test needToRepair end <<<<<<");
		
	}
}

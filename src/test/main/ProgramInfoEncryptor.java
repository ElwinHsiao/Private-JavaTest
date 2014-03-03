package test.main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class ProgramInfoEncryptor {
	private static final String DEFAULT_ALGORITHM = "DES";

	private static final String[] FILES_IN = new String[] {
		"tvlist1.xml",
		"tvlist2.xml",
		"tvlist3.xml"
	};
	
	private static final String FILE_IN_DIR_NAME = ".";
	private static final String FILE_OUT_SUBDIR_NAME = "output_files";
	private static final String CONFIG_FILE_NAME = "serverlist.xml";

//	public ProgramInfoEncryptor() {
//	}

	public void encryptProgramInfos(String passwd) throws IOException {
//		if (passwd == null) {
//			
//		}
		for (int i = 0; i < FILES_IN.length; ++i) {
			File inputFile = new File(FILE_IN_DIR_NAME + "/" + FILES_IN[i]);
			File outputDir = new File(FILE_OUT_SUBDIR_NAME);
			outputDir.mkdir();
			File outputFile = new File(outputDir.getCanonicalPath() + "/" + inputFile.getName());
			System.out.println("inputFIle=" + inputFile.getCanonicalPath() + "\noutputFile=" + outputFile.getCanonicalPath());
			encryptProgramInfo(passwd, inputFile, outputFile);
		}
	}
	
	public void buildConfigFile(String passwd, String server) throws XMLStreamException, IOException {
		buildConfigFile(passwd, server, CONFIG_FILE_NAME);
	}
	
	public void encryptProgramInfo(String passwd, File inputFile, File outputFile) throws IOException {
		
		InputStream inputStream = new FileInputStream(inputFile);
		OutputStream outputStream = new FileOutputStream(outputFile);
		encryptProgramInfo(passwd, inputStream, outputStream);
		inputStream.close();
		outputStream.close();
	}
	
	public void encryptProgramInfo(String passwd, InputStream inputStream, OutputStream outputStream) throws IOException {
		byte[] data = readToByte(inputStream);
		if (data.length == 0) {
			System.out.println("The input stream is empty, nothing need to do!");
			return;
		}
		
		byte[] encryptedData = encryptData(passwd, data);
		
		
//		System.out.println("encryptData=" + Main.byteToHexString(encryptedData));
		outputStream.write(encryptedData);
	}
	
	private static byte[] readToByte(InputStream inputStream) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] byte1 = new byte[1];
		while (inputStream.read(byte1) != -1) {
			System.out.printf("%02X", byte1[0]);
			out.write(byte1);
//			chiper.update(byte1);
		}
		System.out.println("");
		return out.toByteArray();
	}

	public void buildConfigFile(String passwd, String server, String outputFile) throws XMLStreamException, IOException{
		System.out.println("configFile=" + new File(outputFile).getCanonicalPath());
		XMLOutputFactory xof =  XMLOutputFactory.newInstance();
		XMLStreamWriter xtw = null;
		xtw = xof.createXMLStreamWriter(new FileWriter(CONFIG_FILE_NAME));
		IndentXMLStreamWriter writer = new IndentXMLStreamWriter(xtw);
//		System.out.println(xof.getClass().getName() + " " + xtw.getClass().getName());

		writer.writeStartDocument("utf-8","1.0");
//		writer.setPrefix("prefix1", "http://www.w3.org/TR/REC-html40");
		
		writer.writeStartElement("serverlist");
//		writer.writeNamespace("ns1", "http://www.w3.org/TR/REC-html40");
		writer.writeStartElement("server");
		
		writer.writeStartElement("serverid");
		writer.writeText("SRV0001");
		writer.writeEndElement();
		
		writer.writeStartElement("name");
		writer.writeText("服务器1");
		writer.writeEndElement();
		
		writer.writeStartElement("password");
		writer.writeCData(passwd);
		writer.writeEndElement();
		
		for (String file: FILES_IN) {
			writer.writeStartElement("url");
			writer.writeAttribute("file",file);
			writer.writeCData("http://" + server + "/" + file);
			writer.writeEndElement();
		}
		
		writer.writeEndElement();
		writer.writeEndElement();
		
		writer.writeEndDocument();

		writer.flush();
		writer.close();
	}
	
	private Cipher getChiper(String passwd, int mode) {
		String algorithm = DEFAULT_ALGORITHM;
		SecretKeySpec key = new SecretKeySpec(passwd.getBytes(), algorithm);
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(DEFAULT_ALGORITHM);
			cipher.init(mode, key);
		} catch (InvalidKeyException e) {
			throw new IllegalArgumentException("The password is too short, at least 8 characters, currently is " + passwd.length(), e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("This machine does not support this algorithm: " + algorithm, e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException("Warp[NoSuchPaddingException]" + algorithm, e);
		}
		
		return cipher;
	}
	
	public byte[] encryptData(String passwd, byte[] data) {

		byte[] encryptedData;
		Cipher chiper = getChiper(passwd, Cipher.ENCRYPT_MODE);
		try {
			encryptedData = chiper.doFinal(data);
//			System.out.println("encryptData.length=" + encryptData.length);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException("inner error" + e);
		} catch (BadPaddingException e) {
			throw new RuntimeException("inner error" + e);
		}

		return encryptedData;
	}
	
	//test
	public byte[] decryptDES(String passwd, byte[] data) {
		Cipher chiper = getChiper(passwd, Cipher.DECRYPT_MODE);
		byte decryptedData[];
		try {
			decryptedData = chiper.doFinal(data);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException("input data format error: " + e);
		} catch (BadPaddingException e) {
			throw new RuntimeException("input data format error: " + e);
		}

		return decryptedData;
	}
	
	class IndentXMLStreamWriter {

		private XMLStreamWriter xtw;
		private String indent = "\n";
//		private int level;

		public IndentXMLStreamWriter(XMLStreamWriter xtw) {
			this.xtw = xtw;
		}

		public void writeStartDocument(String encoding, String version) throws XMLStreamException {
			xtw.writeStartDocument(encoding, version);
		}

		public void writeEndDocument() throws XMLStreamException {
			xtw.writeEndDocument();
		}

		public void writeStartElement(String localName) throws XMLStreamException {
			xtw.writeCharacters(indent);
			xtw.writeStartElement(localName);
			indent += "\t";
//			level++;
//			writeIndent();
		}

		public void writeEndElement() throws XMLStreamException {
			indent = indent.substring(0, indent.length()-1);
			xtw.writeCharacters(indent);
			xtw.writeEndElement();
		}

		public void writeText(String text) throws XMLStreamException {
			xtw.writeCharacters(indent);
			xtw.writeCharacters(text);
//			indent = indent.substring(0, indent.length()-1);
		}

		public void writeCData(String data) throws XMLStreamException {
			xtw.writeCharacters(indent);
			xtw.writeCData(data);
//			indent = indent.substring(0, indent.length()-1);
		}

		public void writeAttribute(String localName, String value) throws XMLStreamException {
			xtw.writeAttribute(localName, value);
//			xtw.writeCharacters(indent);
		}

		public void setPrefix(String prefix, String uri) throws XMLStreamException {
			xtw.setPrefix(prefix, uri);
		}

		public void close() throws XMLStreamException {
			xtw.close();
		}

		public void flush() throws XMLStreamException {
			xtw.flush();
		}
		
//		private void writeIndent() throws XMLStreamException {
//			char[] marginChar = new char[level+1];
//			Arrays.fill(marginChar, '\t');
//			marginChar[0] = '\n';
//			xtw.writeCharacters(marginChar, 0, marginChar.length);
//		}
		
	}
	
	public static void testEncrypt() {
		try {
//			System.out.println(System.getProperty("user.dir"));
			ProgramInfoEncryptor encryptor = new ProgramInfoEncryptor();
			encryptor.encryptProgramInfos("123456  ");
			
			// decrypt
			System.out.println("decrypting...");
			URL url = new URL("http://192.168.1.52/raw_files/tvlist1.xml");
			URLConnection conn = url.openConnection();  
	        InputStream inputStream = conn.getInputStream();
//			File file = new File(FILE_OUT_SUBDIR_NAME + "/test.xml");
//			InputStream inputStream = new FileInputStream(file);
			byte[] data = readToByte(inputStream);
			if (data.length == 0) {
				System.out.println("The input stream is empty, nothing need to do!");
				return;
			}
			byte[] decryptedData = encryptor.decryptDES("123456  ", data);
			FileOutputStream outfile = new FileOutputStream(FILE_OUT_SUBDIR_NAME + "/test_decrypted.xml");
			outfile.write(decryptedData);
			outfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testBuildConfig() {
		ProgramInfoEncryptor encryptor = new ProgramInfoEncryptor();
		try {
			encryptor.buildConfigFile("123456  ", "192.168.1.52/raw_files");
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

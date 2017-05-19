package com.bean;

import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Test {
	
	public static void main(String[] args) 
	{
		
		try {
//			System.out.println("IP:"+WebToolUtils.getLocalIP());
//			System.out.println("Name:"+WebToolUtils.getLocalHostName());
//			
//			System.out.println("IP1:"+WebToolUtils.getLocalIP1());
//			
//			System.out.println("mac:"+WebToolUtils.getMacAddr());
//			
//		    printHardwareAddresses();
			
			changeImge(new File("E:/image/11-123.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
     * * 转换图片 * * 
     */  
    public static void changeImge(File img) {  
        try {  
            Image image = ImageIO.read(img);  
            int srcH = image.getHeight(null);  
            int srcW = image.getWidth(null);  
            BufferedImage bufferedImage = new BufferedImage(srcW, srcH,BufferedImage.TYPE_3BYTE_BGR);  
            bufferedImage.getGraphics().drawImage(image, 0,0, srcW, srcH, null);  
            bufferedImage=new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY),null).filter (bufferedImage,null);   
            FileOutputStream fos = new FileOutputStream(img);  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);  
            encoder.encode(bufferedImage);  
            fos.close();  
             System.out.println("转换成功...");  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw new IllegalStateException("图片转换出错！", e);  
        }  
    }  
	

	public static void printHardwareAddresses() throws Exception {
	    if (System.getProperty("os.name").equals("Linux")) {
	
	        // Read all available device names
	        List<String> devices = new ArrayList<String>();
	        Pattern pattern = Pattern.compile("^ *(.*):");
	        try (FileReader reader = new FileReader("/proc/net/dev")) {
	            BufferedReader in = new BufferedReader(reader);
	            String line = null;
	            while( (line = in.readLine()) != null) {
	                Matcher m = pattern.matcher(line);
	                if (m.find()) {
	                    devices.add(m.group(1));
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	
	        // read the hardware address for each device
	        for (String device : devices) {
	            try (FileReader reader = new FileReader("/sys/class/net/" + device + "/address")) {
	                BufferedReader in = new BufferedReader(reader);
	                String addr = in.readLine();
	
	                System.out.println(String.format("%5s: %s", device, addr));
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	
	    } else {
	        // use standard API for Windows & Others (need to test on each platform, though!!)
	      
	    }
	
	}

}

import imagereader.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.color.*;


public class ImplementImageIO implements IImageIO
{
     private int bitcount = 0;
     public Image myRead(String filePath) {
       try {
         BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
         bis.skip(18);
         byte[] tmpw = new byte[4];
         byte[] tmph = new byte[4];
         bis.read(tmpw);
         bis.read(tmph);

         int width = byte2Int(tmpw);
         int height = byte2Int(tmph);
         

         bis.skip(2);
         bitcount = (int)(bis.read()  | bis.read() << 8);
         System.out.println(bitcount);
         int skipnum = (bitcount * width/8) % 4;
         if (skipnum != 0)
            skipnum = 4-skipnum;
         //int[] data = new int[height*width]; 
         bis.skip(24);

       if (bitcount == 24) {
         int[] data = new int[height*width]; 
         for (int i = height - 1; i >= 0; i--) {
              for (int j = 0; j < width; j++) {
                  int blue = bis.read();
                  int green = bis.read();
                  int red = bis.read();

                  Color c = new Color(red, green, blue);
                  data[i*width+j] = c.getRGB();
             }
               if (skipnum != 0)
                  bis.skip(skipnum);
          }
        return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, data, 0, width));

        } else if (bitcount == 8) {
          bis.skip(1024);
         // byte[] data = new byte[height*width]; 
          int[] data = new int[height*width]; 
          for (int i = height - 1; i >= 0; i--) {
              for (int j = 0; j < width; j++) {
                  int rgb = bis.read();
             
                  //bis.read(data,i*width, width);
		  Color c = new Color(rgb, rgb, rgb);
                  data[i*width+j] = c.getRGB();
              }

               if (skipnum != 0)
                  bis.skip(skipnum);
          }
       // ColorModel cm = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_GRAY),new int[]{8},false,false,Transparency.OPAQUE,DataBuffer.TYPE_BYTE);
       // return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, cm, data, 0, width));
        return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, data, 0, width));

        } else {
          return null;
        }

     } catch (Exception e) {
       e.printStackTrace();
     }
     return null;
     }

     public int byte2Int(byte[] tmp) {
           int t1 = tmp[3] & 0xff;
           int t2 = tmp[2] & 0xff;
           int t3 = tmp[1] & 0xff;
           int t4 = tmp[0] & 0xff;
           int num = t1 << 24 | t2 << 16 | t3 << 8 |t4;
           return num;
      }

     public Image myWrite(Image image, String filePath) {   
           try {
               BufferedImage bimage;
               if (bitcount == 8)
                  bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_BYTE_GRAY);
               else
                  bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
               Graphics2D g = bimage.createGraphics();
               g.drawImage(image, null, null);

               ImageIO.write(bimage, "bmp", new File(filePath));
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
           return null;
     }
}

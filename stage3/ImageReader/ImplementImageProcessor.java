import java.awt.*;
import javax.imageio.*;
import imagereader.*;
import java.io.*;
import javax.swing.*;
import java.awt.image.*;


public class ImplementImageProcessor implements IImageProcessor
{
      public Image showChanelR(Image sourceImage) {
           try {        
            return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), new colorFilter("red")));

           } catch (Exception e) {

           }
           return null;
      }

      public Image showChanelG(Image sourceImage) {
           try {        
            return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), new colorFilter("green")));

           } catch (Exception e) {

           }
           return null;
      }

      public Image showChanelB(Image sourceImage) {
           try {        
            return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), new colorFilter("blue")));

           } catch (Exception e) {

           }
           return null;
      }

      public Image showGray(Image sourceImage) {
           try {        
            return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), new colorFilter("gray")));

           } catch (Exception e) {

           }
           return null;
      }
}

class colorFilter extends RGBImageFilter
{
     private String m_color;
     public colorFilter(String s) {
            m_color = s;
            canFilterIndexColorModel = true;
     }

     public int filterRGB(int x, int y, int rgb) {
         if (m_color.equals("red")) {
               return (rgb & 0xffff0000);

         } else if (m_color.equals("blue")) {
               return (rgb & 0xff0000ff);       

         } else if (m_color.equals("green")) {
               return (rgb & 0xff00ff00);

         } else if (m_color.equals("gray")) {
               int r = (rgb & 0x00ff0000) >> 16;
               int g = (rgb & 0x0000ff00) >> 8;
               int b = (rgb & 0x000000ff);
               int l = (int)(0.299*r + 0.587*g + 0.114*b);
               return (0xff000000 | l << 16 | l << 8 | l);
         }
         return rgb;
     }
}

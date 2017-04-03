import imagereader.Runner;
public class ImageReaderRunner 
{
      public static void main(String[] args) {
           ImplementImageIO imageioer = new ImplementImageIO();
           ImplementImageProcessor processor = new ImplementImageProcessor();
           Runner.run(imageioer, processor);
      }
}

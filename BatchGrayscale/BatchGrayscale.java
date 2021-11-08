import edu.duke.*;
import java.io.*;
/**
 * Write a description of class BatchGrayscale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BatchGrayscale {
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            int avg = (inPixel.getRed()+inPixel.getGreen()+inPixel.getBlue())/3;
            pixel.setRed(avg);
            pixel.setGreen(avg);
            pixel.setBlue(avg);
        }
        return outImage;
    }
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource img = new ImageResource(f);
            ImageResource gray = makeGray(img);
            String iName = img.getFileName();
            String newName = "gray-" + iName;
            gray.setFileName(newName);
            //gray.setFileName(newName);
            gray.draw();
            gray.save();
            //gray.draw();
        }
    }
    public void testGray(){
        ImageResource img = new ImageResource();
        ImageResource gray = makeGray(img);
        gray.draw();
    }
}

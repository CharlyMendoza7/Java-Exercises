import edu.duke.*;
import java.io.*;
/**
 * Write a description of class imageInversion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class imageInversion {
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel p : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(p.getX(),p.getY());
            int newRed = 255 - inPixel.getRed();
            int newGreen = 255 - inPixel.getGreen();
            int newBlue = 255 - inPixel.getBlue();
            p.setRed(newRed);
            p.setGreen(newGreen);
            p.setBlue(newBlue);
        }
        return outImage;
    }
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource img = new ImageResource(f);
            String imgName = img.getFileName();
            String newName = "inverted-"+imgName;
            ImageResource inv = makeInversion(img);
            inv.setFileName(newName);
            inv.draw();
            inv.save();
        }
    }
    public void testInversion(){
        ImageResource img = new ImageResource();
        ImageResource inv = makeInversion(img);
        inv.draw();
    }
}

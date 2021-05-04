import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class imgAnalyzer {

    public static final String pathImg = "/home/deblazz/Documents/Tesina/test/media/pic.jpg";
    public static final String pathImg2 = "/home/deblazz/Documents/Tesina/test/media/pic2.jpg";
    public static final String outputCanvas = "/home/deblazz/Documents/Tesina/test/media/outputCanvas.png";


    public static BufferedImage openImage(String path) throws IOException {
        File img = new File(path);
        BufferedImage bufferedImage = ImageIO.read(img);
        return bufferedImage;
    }

    public static void compareImgs(BufferedImage img1, BufferedImage img2) throws IOException {
        int result[][] = new int[800][800];
        File canvas = new File(outputCanvas);
        BufferedImage bufferedCanvas = ImageIO.read(canvas);

        for(int row = 0; row < 800; row++){
            for(int col = 0; col < 800; col++){

                Color myColor = new Color(img1.getRGB(row, col));
                Color myColor2 = new Color(img2.getRGB(row, col));

                if(myColor.getGreen() == myColor2.getGreen() && myColor.getRed() == myColor2.getRed() && myColor.getBlue() == myColor2.getBlue()){
                    bufferedCanvas = writeToCanvas(row, col, 0, bufferedCanvas);
                }else{
                    bufferedCanvas = writeToCanvas(row, col, myColor2.getRGB(), bufferedCanvas);
                }
            }
        }
        ImageIO.write(bufferedCanvas, "PNG", canvas);
    }

    public static BufferedImage writeToCanvas(int row, int col, int rgb, BufferedImage bufferedCanvas){
         bufferedCanvas.setRGB(row, col, rgb);
         return bufferedCanvas;
    }


    public static void main(String[] args) throws IOException {
        BufferedImage img = openImage(pathImg);
        BufferedImage img2 = openImage(pathImg2);
        compareImgs(img, img2);




    }
}

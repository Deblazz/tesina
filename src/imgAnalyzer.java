import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class imgAnalyzer extends Thread {

    public static final String pathImg = "./media/pic.jpg";
    public static final String pathImg2 = "./media/pic2.jpg";


    public static BufferedImage openImage(String path) throws IOException {
        File img = new File(path);
        return ImageIO.read(img);
    }

    public static Boolean compareImgs(BufferedImage img1, BufferedImage img2, int minWidth, int maxWidth, int minHeight, int maxHeight, BufferedImage outputImage) throws IOException {

        Boolean thereAreDifferences = false;
        int widthImg2 = img2.getWidth();
        int heightImg2 = img2.getHeight();

        for (int y = 0; y < img1.getHeight(); y++) {
            for (int x = 0; x < img1.getWidth(); x++) {

                Color myColor = new Color(img1.getRGB(x, y));

                if (y < heightImg2 && x < widthImg2) {

                    // TODO: 05/05/21 wrap writeToCanvas in class
                    Color myColor2 = new Color(img2.getRGB(x, y));
                    if (isSimiliar(myColor2.getRed(), myColor.getRed()) && isSimiliar(myColor2.getGreen(), myColor.getGreen()) && isSimiliar(myColor2.getBlue(), myColor.getBlue())) {
                        outputImage = clearPixel(x, y, outputImage);
                    } else {
                        outputImage = writeToCanvas(x, y, myColor2.getRGB(), outputImage);
                        thereAreDifferences = true;
                    }

                } else {
                    outputImage = clearPixel(x, y, outputImage);
                }
            }
        }
        return thereAreDifferences ? true : null;
    }

    public static BufferedImage writeToCanvas(int x, int y, int rgb, BufferedImage bufferedCanvas) {
        bufferedCanvas.setRGB(x, y, rgb);
        return bufferedCanvas;
    }

    public static BufferedImage clearPixel(int x, int y, BufferedImage bufferedCanvas) {
        bufferedCanvas.setRGB(x, y, new Color(0, 0, 0, 0).getRGB());
        return bufferedCanvas;
    }

    public static Boolean isSimiliar(int firstColor, int secondColor) {
        if (firstColor <= (secondColor + 22) && firstColor >= (secondColor - 22))
            return true;
        else
            return false;
    }


    public static Boolean run(int minWidth, int maxWidth, int minHeight, int maxHeight, BufferedImage outputImage) throws IOException {
        BufferedImage img = openImage(pathImg);
        BufferedImage img2 = openImage(pathImg2);
        Boolean res = compareImgs(img, img2, minWidth, maxWidth, minHeight, maxHeight, outputImage);

        return res;
    }
}

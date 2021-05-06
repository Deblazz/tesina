import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class imgAnalyzer {

    public static final String pathImg = "/home/deblazz/Documents/tesina/media/pic.jpg";
    public static final String pathImg2 = "/home/deblazz/Documents/tesina/media/pic2.jpg";
    public static final String outputCanvas = "/home/deblazz/Documents/tesina/media/outputCanvas.png";


    public static BufferedImage openImage(String path) throws IOException {
        File img = new File(path);
        return ImageIO.read(img);
    }

    public static Boolean compareImgs(BufferedImage img1, BufferedImage img2) throws IOException {
        File canvas = new File(outputCanvas);
        BufferedImage bufferedCanvas = ImageIO.read(canvas);
        Boolean thereAreDifferences = false;
        int heightImg2 = img2.getHeight();
        int widthImg2 = img2.getWidth();

        for (int y = 0; y < img1.getHeight(); y++) {
            for (int x = 0; x < img1.getWidth(); x++) {

                Color myColor = new Color(img1.getRGB(x, y));

                if (y < heightImg2 && x < widthImg2) {

                    // TODO: 05/05/21 wrap writeToCanvas in class
                    Color myColor2 = new Color(img2.getRGB(x, y));
                    if (isSimiliar(myColor2.getRed(), myColor.getRed()) && isSimiliar(myColor2.getGreen(), myColor.getGreen()) && isSimiliar(myColor2.getBlue(), myColor.getBlue())) {
                        bufferedCanvas = clearPixel(x, y, bufferedCanvas);
                    } else {
                        bufferedCanvas = writeToCanvas(x, y, myColor2.getRGB(), bufferedCanvas);
                        thereAreDifferences = true;
                    }

                } else {
                    bufferedCanvas = clearPixel(x, y, bufferedCanvas);
                }
            }
        }
        ImageIO.write(bufferedCanvas, "PNG", canvas);
        return thereAreDifferences;
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


    public static void main(String[] args) throws IOException {
        BufferedImage img = openImage(pathImg);
        BufferedImage img2 = openImage(pathImg2);
        Boolean res = compareImgs(img, img2);

        if (res) {
            System.out.println("Le due immagini presentano differenze");
        } else {
            System.out.println("Le due immagini non presentano differenze");
        }
    }
}

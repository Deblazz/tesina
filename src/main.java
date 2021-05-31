import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class main {

    public static final String pathImg = "/home/deblazz/Documents/tesina/media/pic.jpg";
    public static final String pathImg2 = "/home/deblazz/Documents/tesina/media/pic2.jpg";

    public static void main(String[] args) throws IOException {
        BufferedImage img1 = openImage(pathImg);
        BufferedImage img2 = openImage(pathImg2);

        if ((img1.getWidth() * img1.getHeight()) > (img2.getWidth() * img2.getHeight())) {
            threadManager(img2, img1);
        } else {
            threadManager(img1, img2);
        }

    }

    public static void threadManager(BufferedImage smallerImage, BufferedImage largerImage) throws IOException {
        int width = smallerImage.getWidth();
        int height = smallerImage.getHeight();
        Boolean res = false;

        BufferedImage outputImage = new BufferedImage(largerImage.getWidth(), largerImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

        res = imgAnalyzer.run(0, width / 4, 0, height / 4, outputImage);
        res = imgAnalyzer.run(width / 4, width / 2, height / 4, height / 2, outputImage);
        res = imgAnalyzer.run(width / 2, width - width / 4, height / 2, height - height / 4, outputImage);
        res = imgAnalyzer.run(width - width / 4, width, height - height / 4, height, outputImage);

        File output = new File("./outputs/output.png");
        ImageIO.write(outputImage, "PNG", output);
        if (res) {
            System.out.println("Le due immagini presentano differenza");
        } else {
            System.out.println("Le due immagini non presentano differenza");
        }

    }

    public static BufferedImage openImage(String path) throws IOException {
        File img = new File(path);
        return ImageIO.read(img);
    }


}

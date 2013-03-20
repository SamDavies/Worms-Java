import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprites1 {

	public static BufferedImage[] walking() throws IOException {

		BufferedImage bigImg = ImageIO.read(new File("walking.png"));
		// The above line throws an checked IOException which must be caught.

		final int width = 228/3;
		final int height = 192/2;
		final int x = 0;
		final int y = 0;
		final int imagesi = 4;
		final int imagesj = 2;
		BufferedImage[] sprites = new BufferedImage[imagesi+imagesj];

		for(int j = 0; j < imagesj; j++)
		for (int i = 0; i < imagesi; i++) {
			sprites[i+j] = bigImg.getSubimage(x + i*width, y + i*height, width, height);
		}
		return sprites;
	}
}

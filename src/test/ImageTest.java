package test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImageTest {

	@Test
	public void test() {
		try {
			InputStream tankImage = this.getClass().getClassLoader().getResourceAsStream("images/bulletR.gif");
			BufferedImage image = ImageIO.read(tankImage);
			System.out.println(image.getClass());
			System.out.println(image.getHeight());
			System.out.println(image.getWidth());
			assertNotNull(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

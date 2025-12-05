/*
  ImageApp: 
 */
import java.awt.Color;

public class ImageApp
{
  public static void main(String[] args)
  {

    // use any file from the lib folder
    String pictureFile = "lib/beach.jpg";

    // Get an image, get 2d array of pixels, show a color of a pixel, and display the image
    Picture origImg = new Picture(pictureFile);
    Pixel[][] origPixels = origImg.getPixels2D();
    System.out.println(origPixels[0][0].getColor());
    origImg.explore();

    // Image #1 Using the original image and pixels, recolor an image by changing the RGB color of each Pixel
    Picture recoloredImg = new Picture(pictureFile);
    Pixel[][] recoloredPixels = recoloredImg.getPixels2D();

    // implemenation
    for (int row = 0; row < recoloredPixels.length; row++) {
      for (int col = 0; col < recoloredPixels[0].length; col++) {
        Pixel p = recoloredPixels[row][col];
        Color c = p.getColor();
        int red = c.getGreen();
        int green = c.getBlue();
        int blue = c.getRed();
        Color newColor = new Color(red, green, blue);
        p.setColor(newColor);
      }
    }
    recoloredImg.explore();

    // Image #2 Using the original image and pixels, create a photographic negative of the image
    Picture negImg = new Picture(pictureFile);
    Pixel[][] negPixels = negImg.getPixels2D();

    /* implementation */
    for (int row = 0; row < negPixels.length; row++) {
      for (int col = 0; col < negPixels[0].length; col++) {
        Pixel p = negPixels[row][col];
        Color c = p.getColor();
        int red = 255 - c.getRed();
        int green = 255 - c.getGreen();
        int blue = 255 - c.getBlue();
        Color newColor = new Color(red, green, blue);
        p.setColor(newColor);
      }
    }
    negImg.explore();

    // Image #3 Using the original image and pixels, create a grayscale version of the image
    Picture grayscaleImg = new Picture(pictureFile);
    Pixel[][] grayscalePixels = grayscaleImg.getPixels2D();

    /* implementation */
    for (int row = 0; row < grayscalePixels.length; row++) {
      for (int col = 0; col < grayscalePixels[0].length; col++) {
        Pixel p = grayscalePixels[row][col];
        Color c = p.getColor();
        int avg = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
        Color newColor = new Color(avg, avg, avg);
        p.setColor(newColor);
      }
    }
    grayscaleImg.explore();

    // Image #4 Using the original image and pixels, rotate it 180 degrees
    Picture upsidedownImage = new Picture(pictureFile);
    Pixel[][] upsideDownPixels = upsidedownImage.getPixels2D();
    
    /* to be implemented */  

    



    

    // Image #5 Using the original image and pixels, rotate image 90
    Picture rotateImg = new Picture(pictureFile);
    Pixel[][] rotatePixels = rotateImg.getPixels2D();

    /* to be implemented */


    // Image #6 Using the original image and pixels, rotate image -90
    Picture rotateImg2 = new Picture(pictureFile);
    Pixel[][] rotatePixels2 = rotateImg2.getPixels2D();

    /* to be implemented */
    







    // Final Image: Add a small image to a larger one, remove the white background

    /* implementation */
    Picture largeImg = new Picture("lib/beach.jpg");
    Picture smallImg = new Picture("lib2/balloon.png");
    Pixel[][] largePixels = largeImg.getPixels2D();
    Pixel[][] smallPixels = smallImg.getPixels2D();
    for (int row = 0; row < smallPixels.length; row++) {
      for (int col = 0; col < smallPixels[0].length; col++) {
        Pixel smallPixel = smallPixels[row][col];
        Pixel largePixel = largePixels[row + 100][col + 100];
        if (smallPixel.getColor().getRed() == 255 &&
            smallPixel.getColor().getGreen() == 255 &&
            smallPixel.getColor().getBlue() == 255) {
          continue; // skip white pixels
        }
        largePixel.setColor(smallPixel.getColor());
      }
    }
    largeImg.explore();



    // for testing  2D algorithms
    int[][] test1 = { { 1, 2, 3, 4 },
        { 5, 6, 7, 8 },
        { 9, 10, 11, 12 },
        { 13, 14, 15, 16 } };
    int[][] test2 = new int[4][4];


  }
}

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

    /* implementation */  

    int height = upsideDownPixels.length;
    int width = upsideDownPixels[0].length;

    // Create new picture for rotated result (same dimensions for 180°)
    Picture rotated180 = new Picture(width, height);
    Pixel[][] rotated180Pixels = rotated180.getPixels2D();

    // Get actual dimensions of rotated image (may differ from constructor params)
    int rotatedHeight = rotated180Pixels.length;
    int rotatedWidth = rotated180Pixels[0].length;

    // 180 degree rotation matrix: [[-1, 0], [0, -1]]
    // Inverse is the same for 180° rotation
    Matrix2by2 rot180Inverse = new Matrix2by2(-1, 0, 0, -1);

    // Center of the images
    double centerX = (width - 1) / 2.0;
    double centerY = (height - 1) / 2.0;
    double rotatedCenterX = (rotatedWidth - 1) / 2.0;
    double rotatedCenterY = (rotatedHeight - 1) / 2.0;

    // Map from destination to source (reverse mapping)
    for (int newRow = 0; newRow < rotatedHeight; newRow++) {
        for (int newCol = 0; newCol < rotatedWidth; newCol++) {
            // Translate destination pixel to origin
            double x = newCol - rotatedCenterX;
            double y = newRow - rotatedCenterY;
            
            // Apply inverse rotation to find source pixel
            Vector1by2 destPos = new Vector1by2(x, y);
            Vector1by2 sourcePos = Matrix2by2.multiply(destPos, rot180Inverse);
            
            // Translate back to image coordinates
            int sourceCol = (int) Math.round(sourcePos.getX() + centerX);
            int sourceRow = (int) Math.round(sourcePos.getY() + centerY);
            
            // Only copy if source pixel is within bounds (don't clamp!)
            if (sourceRow >= 0 && sourceRow < height && sourceCol >= 0 && sourceCol < width) {
                rotated180Pixels[newRow][newCol].setColor(upsideDownPixels[sourceRow][sourceCol].getColor());
            }
            // Pixels outside bounds remain as default (black or white)
        }
    }
    rotated180.explore();




    

  // Image #5 Using the original image and pixels, rotate image 90
  Picture rotateImg = new Picture(pictureFile);
  Pixel[][] rotatePixels = rotateImg.getPixels2D();

  /* implementation */
  height = rotatePixels.length;
  width = rotatePixels[0].length;

  // For 90° rotation, dimensions swap
  Picture rotated90CCW = new Picture(height, width);
  Pixel[][] rotated90CCWPixels = rotated90CCW.getPixels2D();
  int newHeight = rotated90CCWPixels.length;
  int newWidth = rotated90CCWPixels[0].length;

  // 90 degree counterclockwise: [[0, 1], [-1, 0]]
  // Inverse (90° clockwise): [[0, -1], [1, 0]]
  Matrix2by2 rot90CCWInverse = new Matrix2by2(0, 1, -1, 0);

  centerX = (width - 1) / 2.0;
  centerY = (height - 1) / 2.0;
  double newCenterX = (newWidth - 1) / 2.0;
  double newCenterY = (newHeight - 1) / 2.0;

  for (int newRow = 0; newRow < newHeight; newRow++) {
      for (int newCol = 0; newCol < newWidth; newCol++) {
          // Translate destination pixel to origin
          double x = newCol - newCenterX;
          double y = newRow - newCenterY;
          
          // Apply inverse rotation
          Vector1by2 destPos = new Vector1by2(x, y);
          Vector1by2 sourcePos = Matrix2by2.multiply(destPos, rot90CCWInverse);
          
          // Translate back to original image coordinates
          int sourceCol = (int) Math.round(sourcePos.getX() + centerX);
          int sourceRow = (int) Math.round(sourcePos.getY() + centerY);
          
          // Only copy if source pixel is within bounds
          if (sourceRow >= 0 && sourceRow < height && sourceCol >= 0 && sourceCol < width) {
              rotated90CCWPixels[newRow][newCol].setColor(rotatePixels[sourceRow][sourceCol].getColor());
          }
      }
  }
  rotated90CCW.explore();

  // Image #6 Using the original image and pixels, rotate image -90
  Picture rotateImg2 = new Picture(pictureFile);
  Pixel[][] rotatePixels2 = rotateImg2.getPixels2D();

  int heightCW = rotatePixels2.length;
  int widthCW = rotatePixels2[0].length;

  // For -90° rotation, dimensions swap
  Picture rotated90CW = new Picture(heightCW, widthCW);
  Pixel[][] rotated90CWPixels = rotated90CW.getPixels2D();
  int newHeightCW = rotated90CWPixels.length;
  int newWidthCW = rotated90CWPixels[0].length;

  // -90 degree (clockwise): [[0, -1], [1, 0]]
  // Inverse (90° counterclockwise): [[0, 1], [-1, 0]]
  Matrix2by2 rot90CWInverse = new Matrix2by2(0, -1, 1, 0);

  double centerXCW = (widthCW - 1) / 2.0;
  double centerYCW = (heightCW - 1) / 2.0;
  double newCenterXCW = (newWidthCW - 1) / 2.0;
  double newCenterYCW = (newHeightCW - 1) / 2.0;

  for (int newRowCW = 0; newRowCW < newHeightCW; newRowCW++) {
      for (int newColCW = 0; newColCW < newWidthCW; newColCW++) {
          // Translate destination pixel to origin
          double xCW = newColCW - newCenterXCW;
          double yCW = newRowCW - newCenterYCW;
          
          // Apply inverse rotation
          Vector1by2 destPosCW = new Vector1by2(xCW, yCW);
          Vector1by2 sourcePosCW = Matrix2by2.multiply(destPosCW, rot90CWInverse);
          
          // Translate back to original image coordinates
          int sourceColCW = (int) Math.round(sourcePosCW.getX() + centerXCW);
          int sourceRowCW = (int) Math.round(sourcePosCW.getY() + centerYCW);
          
          // Only copy if source pixel is within bounds
          if (sourceRowCW >= 0 && sourceRowCW < heightCW && sourceColCW >= 0 && sourceColCW < widthCW) {
              rotated90CWPixels[newRowCW][newColCW].setColor(rotatePixels2[sourceRowCW][sourceColCW].getColor());
          }
      }
  }
  rotated90CW.explore();







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

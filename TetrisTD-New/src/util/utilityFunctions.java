package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class utilityFunctions {
	public static float[] doubleToFloat(double[] doubleArr) {
		float[] retVal = new float[doubleArr.length];
		for (int i = 0; i < doubleArr.length; i++) {
			retVal[i] = Float.parseFloat(Double.toString(doubleArr[i]));
		}
		return retVal;
	}
	
	/**
	 *  Wraps the given string into a list of split lines based on the width
	 *  @author http://slick.javaunlimited.net/viewtopic.php?t=3778
	 *  @param text The text to be broken into lines
	 *  @param font The Bitmap font that determines width of characters
	 *  @param width The width of a line before breaking it.
	 */
    public static List<String> wrap(String text, BitmapFont font, int width) {
        //A less accurate but more efficient wrap would be to specify the max 
        //number of columns (e.g. using the width of the 'M' character or something). 
        //The below method will look nicer in the end, though.
        
        List<String> list = new ArrayList<String>();
        String str = text;
        String line = "";
        
        //we will go through adding characters, once we hit the max width
        //we will either split the line at the last space OR split the line
        //at the given char if no last space exists
        
        //while we still have text to check
        int i = 0;
        int lastSpace = -1;
        while (i<str.length()) {
            char c = str.charAt(i);
            if (Character.isWhitespace(c))
                lastSpace = i;
            
            //time to wrap 
            if (c=='\n' || font.getBounds(line + c).width > width) {
                //if we've hit a space recently, use that
                int split = lastSpace!=-1 ? lastSpace : i;
                int splitTrimmed = split;
                
                //if we are splitting by space, trim it off for the start of the
                //next line
                if (lastSpace!=-1 && split<str.length()-1) {
                   splitTrimmed++;
                }
                
                line = str.substring(0, split);
                str = str.substring(splitTrimmed);
                
                //add the line and reset our values
                list.add(line);
                line = "";
                i = 0;
                lastSpace = -1;
            } 
            //not time to wrap, just keep moving along
            else {
                line += c;
                i++;
            }
        }
        if (str.length()!=0)
            list.add(str);
        
        return list;
    }
    
	/**
	 * Takes in grid coordinates of a shape and returns flattened index representation.
	 * 3x3 grid is indexed as follows:
	 * 678
	 * 345
	 * 012
	 * @param shapeBody
	 * @return
	 */
    public static int[] flattenShape(float[] shapeBody, int fieldWidth) {
    	int[] retVal = new int[shapeBody.length/2];
		for (int i = 0; i < retVal.length; i++) {
			retVal[i] = (int)(shapeBody[2*i] + fieldWidth*shapeBody[2*i+1]);
		}
		return retVal;
    }
    
    /**
     * A helper method to produce drawn text on the screen. Always will segment into the specified widths per line.
     * 
     * @param game game to supply spriteBatch and font
     * @param text text to be wrapped
     * @param x x starting coordinate of the text
     * @param y y starting coordinate of the text
     * @param widthPerLine width per line before line break
     * @return final y after y has been decreased
     */
    public static int drawText(BitmapFont font, SpriteBatch batch, String text, int x, int y, int widthPerLine) {
		List<String> description = utilityFunctions.wrap(text, font, 150);
		Iterator<String> iter = description.iterator();
		while (iter.hasNext()) {
			font.draw(batch, iter.next(), 850, y);
			y -= font.getLineHeight();
		}
    	
    	return y;
    }
    
}
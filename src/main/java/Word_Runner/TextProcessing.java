package Word_Runner;

import java.lang.Math;
public class TextProcessing {

    public static String[] Splitter(String text){
        String[] formatted = text.split("\\s+");
        return formatted;
    }

    /** SpeedMap() method returns the time each word is gonna be displayed. Notice how here the reader is expected to speed up slighly
     * from the beginnning of the sentence to the end. The reader is expected to slow down a bit when encountering comma marks
     * @Acceleration - factor at which reader is gonna speed up when reading the sentence
     * @PunctuationAccent - factor of how much slower reader is gonna read the punctuation marks
     * @OverallSpeed - factor at which everything is scaled
     * @return speed map indicating the duration for which each word is displayed
     */
    public static double[] SpeedMap(String[] text, double Acceleration, double PunctuationAccent, double OverallSpeed){
        double[] speedmap = new double[text.length];
        double OverallAcceleration = 0;
        for(int i= 0; i < text.length;i++){
            //checking if there are any punctuation marks
            for(int j = 0; j < text[i].length();j++){
                char x = text[i].charAt(j);
                if(x == '!' || x == '?' || x == ',' || x == '—' || x == '.'){
                    //in case they are punctuation marks
                    speedmap[i] = Math.max((-OverallAcceleration + OverallSpeed)*PunctuationAccent, 0.2);
                    OverallAcceleration = 0;
                    break;
                }
                if(text[i].length() - j == 1){
                    //case with no punctuation marks
                    speedmap[i] = Math.max((OverallSpeed - OverallAcceleration),0.2);
                    OverallAcceleration += Acceleration;
                    break;
                }

            }
        }
        return speedmap;
  }

    public static void main(String[] args) {
        String example = "Doubling the number of U.S. exhibitors participating in HOFEX 2021, the USA Pavilion showcases a wide variety of sustainable food and beverage offerings including Alaskan seafood, Oregon Hazelnuts, Wisconsin Ginseng, Virginian specialty beverages, condiments and sauces, pecans, tart cherries, as well as wide range of products from the Southern and Western regions of the United States.  The USA Pavilion’s #DeliciousUSA kitchen will feature ongoing cooking demonstrations highlighting the versatility of these products with local Chef Andy Dark.\n" +
                "\n" +
                "Colin Crosby, Acting Consul General for the U.S. General Consulate Hong Kong and Macau, inaugurated the USA Pavilion, saying: “U.S. food and agricultural exporters value and appreciate the Hong Kong Market and are focused on deepening their relationships with city’s importers and consumers.  These partnerships are an important cornerstone in our bilateral relationship.”\n" +
                "\n" +
                "The United States is the third largest supplier of consumer-ready food products to Hong Kong’s $21 billion market.  The city is also a top export destination for U.S. premium products such as seafood, fresh fruit, edible tree nuts, and beef, and the number one market for specialty U.S. products like ginseng and eel.";
        String[] array = Splitter(example);
        double[] speedmap = SpeedMap(array, .01,1.5,0.3);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i] + " " +speedmap[i]);

        }
    }

}

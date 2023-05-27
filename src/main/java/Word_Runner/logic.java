package Word_Runner;

import com.sun.tools.javac.Main;

public class logic{
    public static double elapsedSeconds(double beginTime, double currentTime){
        return (currentTime - beginTime)/1000;
    }

    public static void WordRunner(String text, double Acceleration, double OvrSpeed, double PunctuationMark){
        String[] arrayText =  TextProcessing.Splitter(text);
        double[] speedmap = TextProcessing.SpeedMap(arrayText, Acceleration, PunctuationMark, OvrSpeed);
        double beginTime = System.currentTimeMillis();

        for(int i = 0; i < arrayText.length; i++){
            double currentTime = System.currentTimeMillis();
            if(elapsedSeconds(beginTime,currentTime) >= speedmap[i]){
                //action to be performed at specific timeframes
                beginTime = System.currentTimeMillis();
            }
            else{
                i -=1;
            }
        }
    }
    public static void main(String[] args){
        String text = "Being in the same folder only implies that the compiler will know where to look for the files. Being in the same package is required to not have to use fully specified class name.";
        WordRunner(text, 0.01,0.3,1.5);
    }
}


import java.io.*;
import javax.sound.sampled.*;

public class SpeechDetectionTest {

    public static void main(String[] args) {

        ByteArrayOutputStream byteArrayOutputStream;
        TargetDataLine targetDataLine;
        int count;
        // array of bytes
        byte tempBuffer[] = new byte[8000];
        int countzero, countdownTimer;
        // converting bytes into shorts
        short convert[] = new short[tempBuffer.length];

        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            countdownTimer = 0;
            while (true) {
                // describing the audio format
                AudioFormat audioFormat = new AudioFormat(8000.0F, 8, 1, true, false);
                // TargetDataLine subinterface of DataLine to capture the audio
                // making an DataLine.Info object to specify info about the line
                DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
                // TargetDataLine - input stream
                targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
                // opening the audio capturing
                targetDataLine.open(audioFormat);
                // initializing - starting the capturing
                targetDataLine.start();
                // reads from the buffer
                count = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
                // writes to the data line
                byteArrayOutputStream.write(tempBuffer, 0, count);
                try {
                    countzero = 0;
                    for (int i = 0; i < tempBuffer.length; i++) {
                        convert[i] = tempBuffer[i];
                        if (convert[i] == 0) {
                            countzero++;            // counts zeros
                        }


                    }
                    countdownTimer++;
                    System.out.println(countzero + " " + countdownTimer);

                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
                Thread.sleep(0);
                targetDataLine.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
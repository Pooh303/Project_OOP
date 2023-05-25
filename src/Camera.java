
//Import section
import com.github.sarxos.webcam.Webcam;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Camera implements MyWebCam, Runnable, WebCamStatus {

    private Result result;
    private String text;
    private boolean pause = true;
    private boolean status;
    private boolean isConnected;

    public String getStatus() {
        if (isConnected) {
            return "Webcam is connected.";
        } else {
            return "Webcam is disconnected.";
        }
    }

    public static void reportStatus(WebCamStatus webcam) {
        String status = webcam.getStatus();
        System.out.println("Webcam status: " + status);
    }

    private synchronized void checkPaused() {
        while (pause) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void pauseThread() {
        pause = true;
    }

    public synchronized void resumeThread() {
        pause = false;
        this.notify();
    }

    public void run() {
        //getwebcam by default cam
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        int i = 0;
        while (true) {
            checkPaused();
            try {
                ImageIO.write(webcam.getImage(), "PNG", new File("BarcodeCapture/Update.png"));
                InputStream barInputStream = new FileInputStream("BarcodeCapture/Update.png");
                BufferedImage barBufferedImage = ImageIO.read(barInputStream);
                LuminanceSource source = new BufferedImageLuminanceSource(barBufferedImage);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                Reader reader = new MultiFormatReader();
                result = reader.decode(bitmap);
                if (checked()) {
                    System.out.println(result.getText());
                    text = result.getText();
                    pause = true;
                    pauseThread();

                }
            } catch (IOException ex) {

            } catch (NotFoundException ex) {
//                try {
//                    Thread.sleep(1000);
//                    System.out.println("Time's Running " + ++i);
//                } catch (InterruptedException ex1) {
//                    Logger.getLogger(Camera.class.getName()).log(Level.SEVERE, null, ex1);
//                }
            } catch (ChecksumException ex) {

            } catch (FormatException ex) {

            }
        }
    }

    @Override
    public void OpenCam() {

    }

    @Override
    public boolean checked() {
        return isNumeric(result.getText());
    }

    public synchronized String getSN() {
        return this.text;
    }

    public boolean isActive() {
        return this.pause;
    }

    public void setSN(String text) {
        this.text = text;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean getBool() {
        return this.pause;
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

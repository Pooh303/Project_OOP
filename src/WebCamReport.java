
public class WebCamReport {

    public static void reportStatus(WebCamStatus webcam) {
        String status = webcam.getStatus();
        System.out.println("Webcam status: " + status);
    }
}

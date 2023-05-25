
class SonyWebcam implements WebCamStatus {

    private boolean isOn;

    public SonyWebcam(boolean isOn) {
        this.isOn = isOn;
    }

    @Override
    public String getStatus() {
        if (isOn) {
            return "Webcam is turned on.";
        } else {
            return "Webcam is turned off.";
        }
    }
}

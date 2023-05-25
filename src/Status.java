
class Status implements WebCamStatus {

    private boolean isOn;

    public Status(boolean isOn) {
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

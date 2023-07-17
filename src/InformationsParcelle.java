public class InformationsParcelle {
    private String adresse;
    private double superficie;
    private double latitude;
    private double longitude;
    private String descriptionRegion;

    public InformationsParcelle(String adresse, double superficie, double latitude, double longitude, String descriptionRegion) {
        this.adresse = adresse;
        this.superficie = superficie;
        this.latitude = latitude;
        this.longitude = longitude;
        this.descriptionRegion = descriptionRegion;
    }

    public InformationsParcelle() {

    }

    public String getAdresse() {
        return adresse;
    }

    public double getSuperficie() {
        return superficie;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDescriptionRegion() {
        return descriptionRegion;
    }

    @Override
    public String toString() {
        return "InformationsParcelle{" +
                "adresse='" + adresse + '\'' +
                ", superficie=" + superficie +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", descriptionRegion='" + descriptionRegion + '\'' +
                '}';
    }

    public void setAdresse(String adresse) {
    }

    public void setSuperficie(double superficie) {
    }

    public void setLatitude(double latitude) {
    }

    public void setLongitude(double longitude) {
    }

    public void setDescriptionRegion(String descriptionRegion) {
    }
}

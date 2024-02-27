import java.util.List;
import java.util.Objects;

public class Route {
    private String id;
    private Double distance;
    private int popularity;
    private boolean isFavorite;
    private List<String> locationPoints;

    public Route(String id, Double distance, int popularity, boolean isFavorite, List<String> locationPoints) {
        this.id = id;
        this.distance = distance;
        this.popularity = popularity;
        this.isFavorite = isFavorite;
        this.locationPoints = locationPoints;
    }

    public Route() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Route otherRoute = (Route) obj;

        List<String> otherLocationPoints = otherRoute.getLocationPoints();

        if (locationPoints.size() < 2 || otherLocationPoints.size() < 2) return false;

        return locationPoints.getFirst().equals(otherLocationPoints.getFirst())
                && locationPoints.getLast().equals(otherLocationPoints.getLast())
                && distance.equals(otherRoute.getDistance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationPoints.getFirst(), locationPoints.getLast(), locationPoints.size(), distance);
    }
    public int getLocationPointsSize(){
        return getLocationPoints().size();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
    public void updatePopularity(){
        this.popularity++;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public List<String> getLocationPoints() {
        return locationPoints;
    }

    public void setLocationPoints(List<String> locationPoints) {
        this.locationPoints = locationPoints;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id='" + id + '\'' +
                ", distance=" + distance +
                ", popularity=" + popularity +
                ", isFavorite=" + isFavorite +
                ", LocationPoints=" + locationPoints +
                '}' + "\n";
    }
}

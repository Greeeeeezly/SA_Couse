import java.util.*;

public class NavigatorImpl implements Navigator {
    MyHashSet<Route> routes = new MyHashSet<>();

    public void addRoute(Route route) {
        if(route.getLocationPointsSize()<2){
            System.out.println("В маршруте "+route.getId()+" слишком мало точек");
            return;
        }
        if (!contains(route)) {
            routes.add(route);
        }
    }

    @Override
    public void removeRoute(String routeId) {
        Route route = getRoute(routeId);
        if (route!=null) {
            routes.remove(route);
        }
    }
    @Override
    public Route getRoute(String routeId) {
        for (Route route : routes) {
            if (route.getId().equals(routeId)) {
                return route;
            }
        }
        return null;
    }

    @Override
    public boolean contains(Route route) {
        for (Route route1 : routes) {
            if (route1.equals(route)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return routes.size();
    }

    @Override
    public void chooseRoute(String routeId) {
        if (getRoute(routeId)!=null){
        getRoute(routeId).updatePopularity();
        }
        else System.out.println(routeId+" маршрут не существует");
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        MyArrayList<Route> searchRoutes = new MyArrayList<>();

        if (!startPoint.isEmpty() && !endPoint.isEmpty() && !startPoint.equals(endPoint)) {
            for (Route route : routes) {
                if (route.getLocationPoints().getFirst().equals(startPoint) && route.getLocationPoints().getLast().equals(endPoint)) {
                    searchRoutes.add(route);
                }
            }
        }

        List<Route> favoriteRoutes = new ArrayList<>();
        List<Route> nonFavoriteRoutes = new ArrayList<>();

        for (Route route : searchRoutes) {
            if (route.isFavorite()) {
                favoriteRoutes.add(route);
            } else {
                nonFavoriteRoutes.add(route);
            }
        }

        favoriteRoutes.sort(Comparator.comparingInt(Route::getLocationPointsSize).reversed()
                .thenComparingInt(Route::getPopularity).reversed());

        nonFavoriteRoutes.sort(Comparator.comparingInt(Route::getLocationPointsSize).reversed()
                .thenComparingInt(Route::getPopularity).reversed());

        favoriteRoutes.addAll(nonFavoriteRoutes);

        return favoriteRoutes;
    }


    private boolean notAStartPoint(String destinationPoint) {
        for (Route route : routes) {
            if (route.getLocationPoints().getFirst().equals(destinationPoint)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        MyArrayList<Route> favoriteRoutes = new MyArrayList<>();
        for (Route route : routes) {
            if (route.isFavorite() && notAStartPoint(destinationPoint)&&route.getLocationPoints().contains(destinationPoint)) {
                favoriteRoutes.add(route);
            }
        }
        favoriteRoutes.sort(Comparator.comparingDouble(Route::getDistance).reversed()
                .thenComparingInt(Route::getPopularity).reversed());
        MyArrayList<Route> sortedList=new MyArrayList<>(favoriteRoutes.size());
        for (int i = 0; i < favoriteRoutes.size(); i++) {
            sortedList.add(favoriteRoutes.get(i));
        }
        return sortedList;
    }

    @Override
    public Iterable<Route> getTop3Routes() {
        MyArrayList<Route> favoriteRoutes = new MyArrayList<>();
        for (Route route : routes) {
            favoriteRoutes.add(route);
        }
        favoriteRoutes.sort(Comparator.comparingInt(Route::getPopularity).reversed()
                .thenComparingDouble(Route::getDistance).thenComparingInt(Route::getLocationPointsSize));
        int numberOfRoutesToReturn = Math.min(5, favoriteRoutes.size());
        MyArrayList<Route> sortedList = new MyArrayList<>(numberOfRoutesToReturn);
        for (int i = 0; i < Math.min(5, favoriteRoutes.size()); i++) {
            sortedList.add(favoriteRoutes.get(i));
        }
        return sortedList;
    }
}
public interface Navigator {
    void addRoute(Route route);

    void removeRoute(String routeId);

    boolean contains(Route route);

    int size();

    Route getRoute(String routeId);

    void chooseRoute(String routeId);

    Iterable<Route>searchRoutes(String startPoint, String endPoint);

    Iterable<Route>getFavoriteRoutes(String destinationPoint);

    Iterable<Route>getTop3Routes();
}

/*	void addRoute(Route route) – добавляет маршрут в «Навигатор».
●	void removeRoute(String routeId) – удаляет маршрут из «Навигатора».
●	bool contains(Route route) – возвращает значение true, если маршрут найден.
●	int size() – возвращает общее количество маршрутов.
●	Route getRoute(String routeId) – возвращает маршрут по id.
●	void chooseRoute(String routeId) – увеличивает популярность данного маршрута на 1.
●	Iterable<Route> searchRoutes(String startPoint, String endPoint) – возвращает все маршруты, имеющие логический смысл, содержащие начальную и конечную точки. Результаты следует отсортировать по расстоянию между двумя точками (наименьшее количество точек между ними), а затем по популярности в порядке убывания. Избранные маршруты должны стоять на первом месте, независимо от расстояния или популярности.
Примечания:
•	Избранные маршруты также следует отсортировать по расстоянию и популярности в порядке убывания.
•	Если нет маршрутов, соответствующих точкам поиска – верните пустую коллекцию.
●	Iterable<Route> getFavoriteRoutes(String destinationPoint) – возвращает все маршруты, которые являются избранными и содержат данную точку назначения в качестве не начальной точки. Результаты следует отсортировать по расстоянию в порядке возрастания, а затем по популярности в порядке убывания.
Если избранных маршрутов нет — вернуть пустую коллекцию.
●	Iterable<Route> getTop3Routes() – возвращает первые 5 маршрутов по популярности в порядке убывания, затем по расстоянию в порядке возрастания и затем по количеству точек местоположения в порядке возрастания. Если маршрутов нет — верните пустую коллекцию.
*/
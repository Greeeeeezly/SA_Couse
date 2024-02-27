import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("NY","LA","SF");
        Route route1 = new Route("1", 2222.00, 1,true,list1);
        Route route9 = new Route("9", 2222.00, 2,true,list1);
        List<String> list2 = Arrays.asList("NY","LA");
        Route route2 = new Route("2", 2222.00, 5,true,list2);
        Route route3 = new Route("3", 3333.00, 1,false,list2);
        Route route4 = new Route("4", 222.00, 1,false,list2);
        Route route5 = new Route("5", 4444.00, 0,true,list2);
        List<String> list3 = Arrays.asList("SF","NJ");
        Route route7 =new Route("7",1555.00,2,false,list3);
        Route route8 = new Route("8",1555.00,2,false,list3);
        List<String> list4=Arrays.asList("NY","SF","LA");
        Route route10 = new Route("10", 44444.00, 1,true,list4);
        NavigatorImpl navigator = new NavigatorImpl();
        System.out.println("Возврат пустых коллекций");
        System.out.println(navigator.getTop3Routes());
        System.out.println(navigator.searchRoutes("NY","LA"));
        System.out.println(navigator.getFavoriteRoutes("LA"));

        navigator.addRoute(route1);
        navigator.addRoute(route2);
        navigator.addRoute(route3);
        navigator.addRoute(route4);
        navigator.addRoute(route5);
        navigator.addRoute(route7);
        navigator.addRoute(route8);
        navigator.addRoute(route9);
        navigator.addRoute(route10);

        System.out.println("Получение маршрута с индексом 1");
        System.out.println(navigator.getRoute("1"));
        System.out.println("Увеличение популярности маршрута 1");
        navigator.chooseRoute("1");
        System.out.println(navigator.getRoute("1"));
        System.out.println("Удаление маршрута");
        System.out.println("Количество маршрутов до удаления");
        System.out.println(navigator.size());
        navigator.removeRoute("1");
        System.out.println("Количество маршрутов после удаления");
        System.out.println(navigator.size());
        System.out.println("Проверка наличия маршрута 1");
        System.out.println(navigator.getRoute("1"));
        System.out.println(navigator.contains(route1));
        System.out.println("Проверка наличия маршрута 2");
        System.out.println(navigator.contains(route2));
        System.out.println("Поиск избранных маршрутов до LA");
        System.out.println(navigator.getFavoriteRoutes("LA"));
        System.out.println("Поиск маршрутов от NY до LA");
        System.out.println(navigator.searchRoutes("NY","LA"));
        System.out.println("Поиск топ-5 маршрутов по популярности");
        System.out.println(navigator.getTop3Routes());
        System.out.println("Маршрут 7: "+route7);
        System.out.println("Маршрут 8: "+route8);
        System.out.println("Проверка наличия маршрута 7");
        System.out.println(navigator.getRoute("7"));
        System.out.println("Проверка наличия маршрута 8");
        System.out.println(navigator.getRoute("8"));
    }
}

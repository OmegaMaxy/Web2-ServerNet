package ui.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Router {
    public String name;
    public String defaultRoute;
    public String notFoundRoute;
    private ArrayList<String[]> routes;

    public Router(String name, String defaultRoute, String notFoundRoute) {
        this.name = name;
        this.defaultRoute = defaultRoute;
        this.notFoundRoute = notFoundRoute;
        this.routes = new ArrayList<>();
    }

    public void consultRoute(Controller controller, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        String route = ( request.getParameter("route") != null ) ? request.getParameter("route") : this.defaultRoute;
        request.setAttribute("currentRoute", route);

        /*
        Cookie route_cookie = new Cookie("previousRoute", originRoute);
        route_cookie.setMaxAge(60*60*24);
        response.addCookie(route_cookie);

         */


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        System.out.println("[" + formatter.format(time) + "]-------------------------");
        System.out.println("currentRoute: " + request.getAttribute("currentRoute"));
        System.out.println("ROUTED: " + route);
        //Class<?> c = Class.forName("Controller");

        Method method = controller.getClass().getDeclaredMethod(this.findRoute(route), HttpServletRequest.class, HttpServletResponse.class);
        String filename;
        try {
            filename = (String) method.invoke(controller, request, response);
        } catch (InvocationTargetException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));

            Method new_method = controller.getClass().getDeclaredMethod("error_500", HttpServletRequest.class, HttpServletResponse.class);
            filename = (String) new_method.invoke(controller, request, response);
        }
        request.getRequestDispatcher(filename).forward(request,response);
    }

    public void addRoute(String route_name, String route_function) {
        this.routes.add(new String[]{route_name, route_function});
    }
    private String findRoute(String route) {
        String methodToExecute = this.notFoundRoute;
        for (String[] _route: routes) {
            if (route.equals(_route[0])) {
                methodToExecute = _route[1];
                break;
            }
        }
        return methodToExecute;
    }
}

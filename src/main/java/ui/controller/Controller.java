package ui.controller;


import domain.model.DomainException;
import domain.model.Server;
import domain.services.ServerDB;
import domain.seeders.ServerDBSeeder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    public final ServerDB db = new ServerDB();
    public HttpServletRequest request;
    public HttpServletResponse response;

    public ArrayList<String> errorMessages = new ArrayList<String>();
    public ArrayList<String> successMessages = new ArrayList<String>();
    public final Router router = new Router("Front Controller Router", "home", "error_404");


    public Controller() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            //new ServerFactory(db, 10);
            new ServerDBSeeder(this.db);
        } catch (IOException e) {
            e.printStackTrace();
        }
        router.addRoute("home", "home");
        router.addRoute("store", "store");
        router.addRoute("create", "create");
        router.addRoute("overview", "index");
        router.addRoute("view", "get");
        router.addRoute("search", "search");
        router.addRoute("update", "update");
        router.addRoute("edit", "edit");
        router.addRoute("deleteConfirm", "deleteConfirm");
        router.addRoute("delete", "delete");

        router.addRoute("admin-reload-server", "admin_reloadServer");
        router.addRoute("admin-clear", "admin_clear");
        router.addRoute("test", "test");

        router.addRoute("shop", "shop_index");
        router.addRoute("shop-view", "shop_view");
        router.addRoute("shop-addToBasket", "shop_addToBasket");
        router.addRoute("shop-removeFromBasket", "shop_removeFromBasket");
        router.addRoute("shop-deleteBasket", "shop_deleteBasket");
        router.addRoute("shop-basket", "shop_basket");
        router.addRoute("shop-nosession", "shop_nosession");

        router.addRoute("darkmode", "darkmode");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            requestMiddleware(request,response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            requestMiddleware(request,response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void requestMiddleware(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {

        this.errorMessages.clear();
        this.successMessages.clear();

        request.setAttribute("errorMessages", this.errorMessages);
        request.setAttribute("successMessages", this.successMessages);
        router.consultRoute(this, request, response);
    }

    public String create(HttpServletRequest request, HttpServletResponse response) {
        return "manage/create.jsp";
    }
    public String store(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if ( testServerValidityFails(request, "create") ) {
            return create(request, response);
        } else {
            int new_id = db.addServer(request.getParameter("serverName"), request.getParameter("serverLocation"), request.getParameter("amountOfServices"), request.getParameter("amountOfRAM"));
            if ( new_id == -1 ) {
                this.errorMessages.add("Could not create server, please check input.");
                return create(request, response);
            } else {
                request.setAttribute("id", String.valueOf(new_id));
                return get(request, response);
            }
        }

    }
    public String home(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("db", db);
        return "index.jsp";
    }
    public String search(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("db", db);
        if ( request.getParameter("is_searching") != null && request.getParameter("is_searching").equals("1") ) {
            if ( request.getParameter("query") != null && !request.getParameter("query").isEmpty()) {
                String query = request.getParameter("query");
                Cookie query_cookie = this.getCookie(request, "latest_query");

                if ( query_cookie != null ) {
                    if ( !query_cookie.getValue().equals(query) ) query_cookie.setValue(query);

                    response.addCookie(query_cookie);
                } else {
                    Cookie new_query_cookie = new Cookie("latest_query", query);
                    new_query_cookie.setMaxAge(60*30);
                    response.addCookie(new_query_cookie);
                }
                request.setAttribute("results", db.search(query));
            } else {
                this.errorMessages.add("Your query cannot be empty!");
            }
        }

        return "search.jsp";
    }
    public Cookie getCookie(HttpServletRequest request, String name) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }
    public String get(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("db", db);
        String serverid = (request.getParameter("id") == null) ? (String) request.getAttribute("id") : request.getParameter("id");
        if (serverid == null || serverid.isEmpty()) {
            this.errorMessages.add("This item does not exist.");
            return index(request, response);
        } else {
            if ( testServerExistence(serverid) ) return item_not_found(request, response);
            request.setAttribute("server", db.getByID(serverid));
        }

        return "manage/view.jsp";
    }
    public String index(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("servers", db.getServers());
        request.setAttribute("db", db);
        return "manage/overview.jsp";
    }
    public String edit(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("db", db);
        if ( testServerExistence(request.getParameter("id")) ) return item_not_found(request, response);
        request.setAttribute("server", db.getByID(request.getParameter("id")));
        return "manage/edit.jsp";
    }
    public String update(HttpServletRequest request, HttpServletResponse response) {
        if ( testServerValidityFails(request, "update") ) {
            request.setAttribute("id", request.getParameter("serverid"));
            return edit(request, response);
        } else {
            db.updateServer(request);
            request.setAttribute("id", request.getParameter("serverid"));
            return get(request, response);
        }
    }
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        if ( testServerExistence(request.getParameter("id")) ) return item_not_found(request, response);
        request.setAttribute("server_name", db.getByID(request.getParameter("id")).getName());
        request.setAttribute("serverid", request.getParameter("id"));
        return "manage/delete.jsp";
    }
    public String deleteConfirm(HttpServletRequest request, HttpServletResponse response) {
        try {
            db.removeByID(request.getParameter("id"));
            this.successMessages.add("Server deleted successfully.");
            return index(request, response);
        } catch (IOException ex) {
            this.errorMessages.add("Could not delete server nr. " + request.getParameter("id") + ".");
            return index(request, response);
        }

    }

    public boolean hasNoSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if ( !session.isNew() ) return true;
        System.out.println("passed isNew");
        try  {
           return session.getAttribute("basket") == null;
        } catch (IllegalStateException e) {
           return true;
        }
    }
    public HttpSession createIfHasNoSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setMaxInactiveInterval(60 * 30);
            session.setAttribute("basket", new ArrayList<Server>());
        }
        return session;
    }
    public String shop_index(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = this.createIfHasNoSession(request);
        request.setAttribute("servers", db.getServers());
        request.setAttribute("db", db);
        return "shop/index.jsp";
    }
    public String shop_view(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = this.createIfHasNoSession(request);
        request.setAttribute("db", db);

        if (request.getParameter("id") == null) {
            this.errorMessages.add("This item does not exist.");
            return shop_index(request, response);
        } else {
            if ( testServerExistence(request.getParameter("id")) ) return item_not_found(request, response);
            request.setAttribute("server", db.getByID(request.getParameter("id")));
        }

        return "shop/view.jsp";
    }
    public String shop_addToBasket(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session;
        try {
            session = this.createIfHasNoSession(request);
        } catch (Exception e) {
            return shop_deleteBasket(request, response);
        }


        try {
            ArrayList<Server> basket = (ArrayList<Server>) session.getAttribute("basket");
            if ( testServerExistence(request.getParameter("id")) ) return item_not_found(request, response);
            if ( !basket.contains(db.getByID(request.getParameter("id"))) ) {
                boolean hasInserted = basket.add(db.getByID(request.getParameter("id")));
                if (!hasInserted) {
                    this.errorMessages.add("Could not add item to basket, please try again!");
                } else {
                    this.successMessages.add("Item added to basket.");
                    session.setAttribute("basket", basket);
                }
            } else {
                this.errorMessages.add("Could not add item to basket, item is already in basket!");
            }

            return shop_index(request, response);
        } catch (Exception e) {
            this.errorMessages.add("Could not add item to basket because of the session, please try again!");
            return shop_deleteBasket(request, response);
        }

    }
    public String shop_removeFromBasket(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = this.createIfHasNoSession(request);

        ArrayList<Server> basket = (ArrayList<Server>) session.getAttribute("basket");
        if ( testServerExistence(request.getParameter("id")) ) return item_not_found(request, response);
        boolean hasBeenRemoved = basket.remove(db.getByID(request.getParameter("id")));
        if (!hasBeenRemoved) {
            this.errorMessages.add("Could not remove item from basket, please try again!");
        } else {
            this.successMessages.add("Item removed from basket.");
            session.setAttribute("basket", basket);
        }
        return shop_basket(request, response);
    }
    public String shop_deleteBasket(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = this.createIfHasNoSession(request);
        session.invalidate();

        this.successMessages.add("Session successfully refreshed!");
        return shop_index(request, response);
    }
    public String shop_basket(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = this.createIfHasNoSession(request);
        return "shop/basket.jsp";
    }
    public String shop_nosession(HttpServletRequest request, HttpServletResponse response) {
        return "shop/nosession.jsp";
    }

    public String darkmode(HttpServletRequest request, HttpServletResponse response) {

        String originRoute = request.getParameter("originRoute");
        String option = request.getParameter("option");

        String actual_option = (option.equals("dark")) ? "dark" : "light";

        Cookie cookie = this.getCookie(request, "darkmode_option");

        if ( cookie == null ) {
            Cookie new_cookie = new Cookie("darkmode_option", "light");
            new_cookie.setValue( actual_option );
            new_cookie.setMaxAge(60*60*24);
            response.addCookie(new_cookie);
        } else {
            cookie.setValue( actual_option );
            response.addCookie(cookie);
        }

        Cookie route_cookie = new Cookie("previousRoute", originRoute);
        route_cookie.setMaxAge(60*60*24);
        response.addCookie(route_cookie);

        try {
            this.successMessages.add("Changed to " + actual_option + "mode.");
            return (String) this.getClass().getDeclaredMethod(originRoute, HttpServletRequest.class, HttpServletResponse.class).invoke(this, request, response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException exc ) {
            this.errorMessages.add("Could not change darkmode setting.");
            return index(request, response);
        }
    }

    public String test(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("test_str", "test_obj");
        return "test.jsp";
    }
    public String admin_reloadServer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("command") != null && request.getParameter("command").equals("reload-server")) {
            db.reload();
            request.setAttribute("status", "Data has been refreshed!");
            this.successMessages.add("Server successfully reloaded");
        }
        request.setAttribute("amountOfServers", db.getServers().size());
        request.setAttribute("amountOfMBInUse", db.getMBInUse());
        return "admin/reload-server.jsp";
    }
    public String admin_clear(HttpServletRequest request, HttpServletResponse response) {
        this.db.clear();
        this.successMessages.add("Server successfully cleared");
        return "admin/reload-server.jsp";
    }
    public String error_404(HttpServletRequest request, HttpServletResponse response) {
        return "components/req/error_404.jsp";
    }
    public String item_not_found(HttpServletRequest request, HttpServletResponse response) {
        return "error_pages/item_not_found.jsp";
    }
    public String error_500(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.toString());
        return "error_pages/error_500.jsp";
    }

    public boolean testServerValidityFails(HttpServletRequest request, String mode) {
        String name = request.getParameter("serverName");
        String location = request.getParameter("serverLocation");
        String amountOfServices = request.getParameter("amountOfServices");
        String amountOfRAM = request.getParameter("amountOfRAM");
        boolean hasError = false;

        if ( amountOfServices.isEmpty() ) amountOfServices = "-1";
        if ( amountOfRAM.isEmpty() ) amountOfRAM = "-1";

        try {
            new Server(name, "test", 120, 120);
        } catch (DomainException e) {
            this.errorMessages.add(e.getMessage());
            hasError = true;
        }
        try {
            new Server("test", location, 120, 120);
        } catch (DomainException e) {
            this.errorMessages.add(e.getMessage());
            hasError = true;
        }
        try {
            new Server("test", "test", Integer.parseInt(amountOfServices), 120);
        } catch (DomainException e) {
            this.errorMessages.add(e.getMessage());
            hasError = true;
        }
        try {
            new Server("test", "test", 120, Integer.parseInt(amountOfRAM));
        } catch (DomainException e) {
            this.errorMessages.add(e.getMessage());
            hasError = true;
        }

        if (!mode.equals("update")) {
            // using this.getByName(name).size() != this.servers.size() below, because of initial size of zero
            if (db.getByName(name).size() > 0 && db.getByName(name).size() != db.getServers().size()) {
                this.errorMessages.add("The server with this name already exists.");
                hasError = true;
            }
        }

        return hasError;
    }
    public boolean testServerExistence(String id) {
        return db.getByID(id) == null;
    }
}


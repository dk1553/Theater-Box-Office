package rest.mvc;

import io.javalin.http.Context;
import rest.converters.ConvertToJsonService;

import java.util.Objects;

public class Controller {
    private static Model model;


    public Controller(Model model) {
        this.model = model;
    }


    public static void getEventList(Context context) throws Exception {
        String viewData = model.getEventList();
        context.json(viewData);
    }

    public static void getEvent(Context context) {
        String viewData = model.getEvent(context.pathParam("eventID"));
        context.json(viewData);

    }

    public static void getPerformanceList(Context context) throws Exception {
        String viewData = model.getPerformanceList();
        context.json(viewData);
    }

    public static void getPerformance(Context context) throws Exception {
        String viewData = model.getPerformance(context.pathParam("performanceName"));
        context.json(viewData);
    }

    public static void addPerformances(Context context) throws Exception {
        String viewData = ConvertToJsonService.loginMessage2jsonString();
        int viewStatus = 401;
        if ((context.cookie("role") != null) && (Objects.equals(context.cookie("role"), "admin"))) {
            viewData = model.addPerformances(context.body());
            viewStatus = 200;
        }
        context.status(viewStatus);
        context.json(viewData);
    }

    public static void addEvents(Context context) throws Exception {
        String viewData = ConvertToJsonService.loginMessage2jsonString();
        int viewStatus = 401;
        if ((context.cookie("role") != null) && (Objects.equals(context.cookie("role"), "admin"))) {
            viewData = model.addEvents(context.body());
            viewStatus = 200;
        }
        context.status(viewStatus);
        context.json(viewData);
    }

    public static void buyTicket(Context context) throws Exception {
        String viewData = model.buyTicket(context.body(), context.pathParam("ticketID"));
        context.status(200);
        context.json(viewData);
    }

    public static void loginAdmin(Context context) throws Exception {
        Boolean adminIsVerified = model.adminSignIn(context.body());
        if (adminIsVerified) {
            context.cookie("role", "admin");
        } else {
            logout(context);
        }
        String viewData = model.getLoginStatus(adminIsVerified);
        context.status(200);
        context.json(viewData);
    }

    public static void logout(Context context) {
        context.cookie("role", "null");
        String viewData = model.getLogoutStatus();
        context.status(200);
        context.json(viewData);
    }


    public static void buyYearTicket(Context context)throws Exception  {
        String viewData = model.buyYearTicket(context.body());
        context.status(200);
        context.json(viewData);
    }
}
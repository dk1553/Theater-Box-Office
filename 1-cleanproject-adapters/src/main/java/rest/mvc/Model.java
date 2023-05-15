package rest.mvc;

import Service.TheaterService;
import rest.converters.JsonService;
import mapper.*;
import org.json.JSONException;

public class Model {
    private static TheaterService service;

    private static EventMapper eventMapper;
    private static EventResourceMapper eventResourceMapper;
    private static PerformanceMapper performanceMapper;
    private static PerformanceResourceMapper performanceResourceMapper;
    private static TicketResourceMapper ticketResourceMapper;

    public Model(TheaterService theaterService) {
        service = theaterService;

        eventMapper = new EventMapper();
        performanceMapper = new PerformanceMapper();
        performanceResourceMapper = new PerformanceResourceMapper();
        eventResourceMapper = new EventResourceMapper();
        ticketResourceMapper = new TicketResourceMapper();
    }


    public String getEventList() throws Exception {
        String viewData = JsonService.eventResourceList2jsonString(eventResourceMapper.map(
                service.showProgramUseCase(), service.getTicketRepository()));

        return viewData;
    }

    public String getEvent(String eventID) {
        String viewData = JsonService.eventResource2jsonString(eventResourceMapper.map(
                service.showEventUseCase(eventID), service.getTicketRepository()));
        return viewData;

    }

    public String getPerformanceList() throws Exception {
        String viewData = JsonService.performanceResourceList2jsonString(performanceResourceMapper.map(
                service.showRepertoireUseCase()));
        return viewData;

    }

    public String getPerformance(String performanceName) throws Exception {
        String viewData =
                JsonService.performanceResource2jsonString(performanceResourceMapper.map(
                        service.showPerformanceUseCase(performanceName)));
        return viewData;
    }

    public String addPerformances(String json) throws Exception {
        Boolean status = service.updateRepertoireUseCase(
                performanceMapper.map(
                        JsonService.json2PerformanceList(json)));

        String viewData = JsonService.status2jsonString(status);
        return viewData;
    }

    public String addEvents(String json) throws Exception {
        Boolean status = service.updateTheaterProgramUseCase(
                eventMapper.mapNewObject(JsonService.json2EventResourceList(
                        json), service.getPerformanceRepository(), service.getHallRepository()));
        String viewData = JsonService.status2jsonString(status);
        return viewData;
    }

    public String buyTicket(String json, String ticketID) throws Exception {
        String[] username = JsonService.getUsername(json);
        String viewData =
                JsonService.boughtTicket2jsonString(ticketResourceMapper.map(
                        service.buyTicketUseCase(
                                ticketID, username[0], username[1])));

        return viewData;
    }

    public Boolean verifyAdmin(String json) throws JSONException {
        String[] cred = JsonService.getCredentials(json);
        return service.verifyAdmin(cred[0], cred[1]);
    }

    public String getLoginStatus(Boolean adminIsVerified){
       String viewData =JsonService.status2jsonString(adminIsVerified);
       return viewData;
    }

    public String getLogoutStatus() {
        String viewData = JsonService.status2jsonString(true);
        return viewData;
    }


    public String buyYearTicket(String json) throws Exception {
        String[] username = JsonService.getUsername(json);
        String viewData =
                JsonService.boughtTicket2jsonString(ticketResourceMapper.map(
                        service.buyYearTicketUseCase(
                                 username[0], username[1])));

        return viewData;
    }
}

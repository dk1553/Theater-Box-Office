package rest.mvc;

import Service.TheaterService;
import rest.converters.ConvertFromJsonService;
import rest.converters.ConvertToJsonService;
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
        String viewData = ConvertToJsonService.eventResourceList2jsonString(eventResourceMapper.map(
                service.showProgramUseCase(), service.getTicketRepository()));

        return viewData;
    }

    public String getEvent(String eventID) {
        String viewData = ConvertToJsonService.eventResource2jsonString(eventResourceMapper.map(
                service.showEventUseCase(eventID), service.getTicketRepository()));
        return viewData;

    }

    public String getPerformanceList() throws Exception {
        String viewData = ConvertToJsonService.performanceResourceList2jsonString(performanceResourceMapper.map(
                service.showRepertoireUseCase()));
        return viewData;

    }

    public String getPerformance(String performanceName) throws Exception {
        String viewData =
                ConvertToJsonService.performanceResource2jsonString(performanceResourceMapper.map(
                        service.showPerformanceUseCase(performanceName)));
        return viewData;
    }

    public String addPerformances(String json) throws Exception {
        Boolean status = service.updateRepertoireUseCase(
                performanceMapper.map(
                        ConvertFromJsonService.json2PerformanceList(json)));

        String viewData = ConvertToJsonService.status2jsonString(status);
        return viewData;
    }

    public String addEvents(String json) throws Exception {
        Boolean status = service.updateTheaterProgramUseCase(
                eventMapper.mapNewObject(ConvertFromJsonService.json2EventResourceList(
                        json), service.getPerformanceRepository(), service.getHallRepository()));
        String viewData = ConvertToJsonService.status2jsonString(status);
        return viewData;
    }

    public String buyTicket(String json, String ticketID) throws Exception {
        String[] username = ConvertFromJsonService.json2Username(json);
        String viewData =
                ConvertToJsonService.boughtTicket2jsonString(ticketResourceMapper.map(
                        service.buyTicketUseCase(
                                ticketID, username[0], username[1])));

        return viewData;
    }

    public Boolean adminSignIn(String json) throws JSONException {
        String[] cred = ConvertFromJsonService.json2Credentials(json);
        return service.verifyAdmin(cred[0], cred[1]);
    }

    public String getLoginStatus(Boolean adminIsVerified){
       String viewData = ConvertToJsonService.status2jsonString(adminIsVerified);
       return viewData;
    }

    public String getLogoutStatus() {
        String viewData = ConvertToJsonService.status2jsonString(true);
        return viewData;
    }


    public String buyYearTicket(String json) throws Exception {
        String[] username = ConvertFromJsonService.json2Username(json);
        String viewData =
                ConvertToJsonService.boughtTicket2jsonString(ticketResourceMapper.map(
                        service.buyYearTicketUseCase(
                                 username[0], username[1])));

        return viewData;
    }
}

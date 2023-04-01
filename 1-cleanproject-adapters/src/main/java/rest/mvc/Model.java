package rest.mvc;

import Service.TheaterService;
import rest.converters.GsonService;
import mapper.*;
import org.json.JSONException;

public class Model {
   private static TheaterService service;

   private static EventMapper eventMapper;
   private static EventResourceMapper eventResourceMapper;
   private static PerformanceMapper performanceMapper;
   private static PerformanceResourceMapper performanceResourceMapper;
   private static TicketResourceMapper ticketResourceMapper;

   public Model (TheaterService theaterService){
      this.service=theaterService;

      eventMapper = new EventMapper();
      performanceMapper = new PerformanceMapper();
      performanceResourceMapper = new PerformanceResourceMapper();
      eventResourceMapper = new EventResourceMapper();
      ticketResourceMapper = new TicketResourceMapper();
   }


   public String getEventList() throws Exception {
      String viewData= GsonService.eventResourceList2jsonString(eventResourceMapper.map(
              service.showProgramUseCase()));

      return viewData;
   }

   public String getEvent(String eventID) {
      String viewData= GsonService.eventResource2jsonString(eventResourceMapper.map(
              service.showEventUseCase(eventID)));
      return viewData;

   }

   public String getPerformanceList() throws Exception {
      String viewData= GsonService.performanceResourceList2jsonString(performanceResourceMapper.map(
              service.showRepertoireUseCase()));
      return viewData;

   }
   public String getPerformance(String performanceName) throws Exception {
      String viewData=
              GsonService.performanceResource2jsonString(performanceResourceMapper.map(
                      service.showPerformanceUseCase(performanceName)));
      return viewData;
   }

   public  Result addPerformances(String json) throws Exception {
      String viewData="";
      int viewStatus=200;
         Boolean status= service.updateRepertoireUseCase(
                 performanceMapper.map(
                         GsonService.json2PerformanceList(json)));

         viewData= GsonService.status2jsonString(status);


      return new Result(viewData, viewStatus);
   }

   public Result addEvents(String json) throws Exception {
         int viewStatus=200;
         Boolean status= service.updateTheaterProgramUseCase(
                 eventMapper.mapNewObject(GsonService.json2EventResourceList(
                        json),service.getPerformanceRepository(), service.getTheaterBuilding(), service.getTicketRepository()));
         String viewData= GsonService.status2jsonString(status);
      return new Result(viewData, viewStatus);
   }

   public String buyTicket(String json, String ticketID) throws Exception {
      String[] username=GsonService.getUsername(json);
      String viewData=
              GsonService.boughtTicket2jsonString(ticketResourceMapper.map(
                      service.buyTicketUseCase(
                              ticketID, username[0], username[1])));

      return viewData;
   }

   public  Boolean verifyAdmin(String json) throws JSONException {
      String[] creds=GsonService.getCredentials(json);
      return service.verifyAdmin(creds[0], creds[1]);
   }
   public  String getLoginStatus(Boolean adminIsVerified) throws JSONException {
      return  GsonService.status2jsonString(adminIsVerified);
   }

   public  String getLogoutStatus() {
      return GsonService.status2jsonString(true);
   }
}

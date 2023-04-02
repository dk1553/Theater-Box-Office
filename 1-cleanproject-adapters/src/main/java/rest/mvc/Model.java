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

   public Model (TheaterService theaterService){
      this.service=theaterService;

      eventMapper = new EventMapper();
      performanceMapper = new PerformanceMapper();
      performanceResourceMapper = new PerformanceResourceMapper();
      eventResourceMapper = new EventResourceMapper();
      ticketResourceMapper = new TicketResourceMapper();
   }


   public String getEventList() throws Exception {
      String viewData= JsonService.eventResourceList2jsonString(eventResourceMapper.map(
              service.showProgramUseCase()));

      return viewData;
   }

   public String getEvent(String eventID) {
      String viewData= JsonService.eventResource2jsonString(eventResourceMapper.map(
              service.showEventUseCase(eventID)));
      return viewData;

   }

   public String getPerformanceList() throws Exception {
      String viewData= JsonService.performanceResourceList2jsonString(performanceResourceMapper.map(
              service.showRepertoireUseCase()));
      return viewData;

   }
   public String getPerformance(String performanceName) throws Exception {
      String viewData=
              JsonService.performanceResource2jsonString(performanceResourceMapper.map(
                      service.showPerformanceUseCase(performanceName)));
      return viewData;
   }

   public  Result addPerformances(String json) throws Exception {
      String viewData="";
      int viewStatus=200;
         Boolean status= service.updateRepertoireUseCase(
                 performanceMapper.map(
                         JsonService.json2PerformanceList(json)));

         viewData= JsonService.status2jsonString(status);


      return new Result(viewData, viewStatus);
   }

   public Result addEvents(String json) throws Exception {
         int viewStatus=200;
         Boolean status= service.updateTheaterProgramUseCase(
                 eventMapper.mapNewObject(JsonService.json2EventResourceList(
                        json),service.getPerformanceRepository(), service.getTheaterBuilding(), service.getTicketRepository()));
         String viewData= JsonService.status2jsonString(status);
      return new Result(viewData, viewStatus);
   }

   public String buyTicket(String json, String ticketID) throws Exception {
      String[] username= JsonService.getUsername(json);
      String viewData=
              JsonService.boughtTicket2jsonString(ticketResourceMapper.map(
                      service.buyTicketUseCase(
                              ticketID, username[0], username[1])));

      return viewData;
   }

   public  Boolean verifyAdmin(String json) throws JSONException {
      String[] creds= JsonService.getCredentials(json);
      return service.verifyAdmin(creds[0], creds[1]);
   }
   public  String getLoginStatus(Boolean adminIsVerified) throws JSONException {
      return  JsonService.status2jsonString(adminIsVerified);
   }

   public  String getLogoutStatus() {
      return JsonService.status2jsonString(true);
   }
}

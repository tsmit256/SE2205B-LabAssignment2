import java.lang.*;

public class QueueSimulator{
  public enum Event { ARRIVAL, DEPARTURE };
  private double currTime;
  private double arrivalRate;
  private double serviceTime;
  private double timeForNextArrival;
  private double timeForNextDeparture;
  private double totalSimTime;
  LinkedListQueue<Data> buffer = new LinkedListQueue<Data>();
  LinkedListQueue<Data> eventQueue = new LinkedListQueue<Data>();
  private Event e;
  
  public double getRandTime(double arrivalRate){
    double num, time1, max=1, min=0, randNUM;
    randNUM= Math.random();
    time1= (-1/arrivalRate) * (Math.log(1-randNUM));
    return time1;
  }

  public QueueSimulator(double aR, double servT, double simT){
    currTime = 0;
    arrivalRate = aR;
    serviceTime = servT;
    totalSimTime = simT;
    timeForNextArrival = getRandTime(arrivalRate);
    timeForNextDeparture = serviceTime + timeForNextArrival;
  }

  public double calcAverageWaitingTime(){
    double sumOfTime = 0;
    int divisor = eventQueue.size();

    while(!(eventQueue.isEmpty())){
      sumOfTime = sumOfTime + eventQueue.first().getDepartureTime() - eventQueue.first().getArrivalTime();
      eventQueue.dequeue();
    }
    return sumOfTime/divisor;
  }
  
  public double runSimulation(){
    //loop stops once the time has reached the total simulation time
    while(currTime < totalSimTime){

        //if the queue is empty, then arrival must be first
        if(buffer.isEmpty()){
          //The following line ensures that the timeForNextDeparture is also reset when the queue is empty
          timeForNextDeparture = timeForNextArrival + serviceTime;
          e = Event.ARRIVAL;
        }
        //if the arrival time is less than departure time, arrival will come first
        else if(timeForNextArrival < timeForNextDeparture){
          e = Event.ARRIVAL;
        }
        else{
          e = Event.DEPARTURE;
        }

        //handles the two cases of either arrival or departure
        switch(e){
          case ARRIVAL:
            currTime = timeForNextArrival;
            Data packet = new Data();
            packet.setArrivalTime(currTime);
            buffer.enqueue(packet);
            timeForNextArrival = currTime + getRandTime(arrivalRate);
            break;

          case DEPARTURE:
            currTime = timeForNextDeparture;
            buffer.first().setDepartureTime(currTime);
            eventQueue.enqueue(buffer.dequeue());
            timeForNextDeparture = currTime + serviceTime;
            break;
        }
    }
    return calcAverageWaitingTime();
  }

}







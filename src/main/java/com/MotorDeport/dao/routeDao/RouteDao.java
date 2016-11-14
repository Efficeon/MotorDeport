package com.MotorDeport.dao.routeDao;

import com.MotorDeport.exception.ExceptionRouteSelectionNotFound;
import com.MotorDeport.View.ConsoleHelper;
import com.MotorDeport.model.route.Route;

import java.io.*;
import java.util.*;

public class RouteDao {
    private List<Route> routers = new LinkedList<>();
    public Route createRoute(String pointOfDeparture, String pointOfDestination) throws IOException, ClassNotFoundException {

        if (getDatabaseRouters()!=null) {
            routers = getDatabaseRouters();
        }
        int routingNumber = getRoutingNumber();

        Route route;
        if(isExistenceRouteInDatabase(pointOfDeparture, pointOfDestination)) {
            route = getRouteFromBase(pointOfDeparture, pointOfDestination);
        } else {
            route = new Route(pointOfDeparture, pointOfDestination, routingNumber);
            routers.add(route);
            addToDatabaseRoute();
        }
        return route;
    }

    public int getRoutingNumber() throws IOException, ClassNotFoundException {
        if (getDatabaseRouters()!=null) {
            routers = getDatabaseRouters();
        }
        return routers.size()+1;
    }

    private Route getRouteFromBase(String pointOfDeparture, String pointOfDestination) throws IOException, ClassNotFoundException {

        if (getDatabaseRouters() != null) {
            routers = getDatabaseRouters();
        }
            for (Route route : routers) {
                if (route.getPointOfDeparture().equals(pointOfDeparture) &&
                    route.getPointOfDestination().equals(pointOfDestination)){
                    return route;
                }
            }
        return null;
    }

    public Route getRouteByNumber(int routingNumber) throws IOException, ClassNotFoundException {
        routers = getDatabaseRouters();
        for (Route route : routers){
            if (route.getRoutingNumber() == routingNumber) return route;
        }
        return null;
        //TODO //дописать исключение
    }

    public void addToDatabaseRoute() throws IOException {
        ObjectOutputStream routeOutputStream = new ObjectOutputStream(new FileOutputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/route/Route.dat"));
        routeOutputStream.writeObject(routers);
        routeOutputStream.close();
    }

    public List<Route> getDatabaseRouters() throws ClassNotFoundException, IOException {
        try (ObjectInputStream routeInputStream = new ObjectInputStream(new FileInputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/route/Route.dat"))){
            return (List<Route>) routeInputStream.readObject();
        } catch (EOFException e){
            //ConsoleHelper.writeMessage("Ошибка работы с файлом \"Route.dat\" (EOFException)");
            return null;
        }
    }

    public void showDatabaseRoute() throws IOException, ClassNotFoundException {
        try {
           routers = getDatabaseRouters();
               for (Route route : routers) {
               ConsoleHelper.writeMessage(route.toString());
           }
        } catch (NullPointerException e){
            ConsoleHelper.writeMessage("\nДанные отсутствуют\n");
        }

    }

    public Route routeSelection(int routeNumber) throws IOException, ClassNotFoundException, ExceptionRouteSelectionNotFound {
        routers = getDatabaseRouters();
        Route routeSelection = null;
        for (Route route : routers){
            if(routeNumber == route.getRoutingNumber()){
                routeSelection = route;
                break;
            }
        }
        return routeSelection;
    }

    public boolean isExistenceRouteInDatabase(String pointOfDeparture, String pointOfDestination) throws IOException, ClassNotFoundException {
        if (getDatabaseRouters() != null) {
            routers = getDatabaseRouters();
            for (Route route : routers) {
                if (route.getPointOfDeparture().equals(pointOfDeparture) &&
                        route.getPointOfDestination().equals(pointOfDestination)) {
                    return true;
                }
            }
        }
        return false;
    }
}

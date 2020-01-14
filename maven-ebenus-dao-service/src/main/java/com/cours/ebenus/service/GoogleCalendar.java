package com.cours.ebenus.service;


import com.cours.ebenus.utils.Constants;
import com.cours.ebenus.utils.EventPlace;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


public class GoogleCalendar {
    protected static final String APPLICATION_NAME = "Parking app";
    protected static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    //    static java.io.File CREDENTIALS_FILE_PATH = new java.io.File(path);
    protected static Calendar service;

    public static List<EventPlace> listEvent;

    private GoogleCalendar() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        GoogleCredentials credential = GoogleCredentials.fromStream(new FileInputStream(Constants.CREDENTIALS_FILE_PATH)).createScoped(SCOPES);
        credential.refreshIfExpired();
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credential);

        service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, requestInitializer)
                .setApplicationName(APPLICATION_NAME)
                .build();

        listEvent = new ArrayList<>();
    }

    private static class SingletonHolder {
        private static GoogleCalendar instance;

        static {
            try {
                instance = new GoogleCalendar();
            } catch (IOException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

    }

    public static GoogleCalendar getInstance() {
        return SingletonHolder.instance;
    }

    public static Event newEvent() {
        Event event = new Event();
        event.setSummary("03;raphael@gmail.com");
        DateTime start = new DateTime("2020-01-15T09:00:00+01:00");
        event.setStart(new EventDateTime().setDateTime(start));
        DateTime end = new DateTime("2020-01-16T10:00:00+01:00");
        event.setEnd(new EventDateTime().setDateTime(end));
        return event;
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        GoogleCalendar.getInstance();
//        deleteEvent("sk5o210cfov6s7r7viuvftnj3s");
//        Event event = newEvent();
//        addEvent(event);
        checkCal(10);
        System.out.println(isBooked(3));
    }

    /**
     * @param : Objet Calendar
     * @param : Integer, le nombre d'event à afficher.
     * @function : checkCal
     * @details : Affiche les N events du cal.
     */
    public static void checkCal(int nb) throws IOException, GeneralSecurityException {
        //recup les evenements des trois prochains jours
        Date now1 = new Date();
        LocalDateTime localDateTime = now1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusDays(3);
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        DateTime dateTime = new DateTime(date);


        //call à l'api google calendar
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list(Constants.CALENDAR_ID)
                .setMaxResults(nb)
                .setTimeMin(now)
                .setTimeMax(dateTime)
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                EventPlace place_event = new EventPlace();
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                String[] eventInfo = event.getSummary().split(";");
                int idParking = Integer.parseInt((eventInfo[0]));
                String userEmail = eventInfo[1];
                place_event.setParkingRoom(idParking);
                place_event.setUserEmail(userEmail);
                place_event.setDateBook(start);
                place_event.setIdEvent(event.getId());
                //add à la static listevent
                listEvent.add(place_event);
                System.out.println(idParking);
                System.out.println(userEmail);
                System.out.printf("%s (%s)\n %s", event.getSummary(), start, event.getId());
            }
        }
    }

    /**
     * @param : id de l'event
     * @Objet : deleteEvent
     * @details : supprime un event
     */
    public static void deleteEvent(String eventID) throws IOException {
        service.events().delete(Constants.CALENDAR_ID, eventID).execute();
    }

    /**
     * @param event : id de l'event
     * @throws IOException
     * @details : add un event
     */
    public static void addEvent(Event event) throws IOException {
        service.events().insert(Constants.CALENDAR_ID, event).setSendNotifications(true).execute();
    }

    /**
     *
     * @param place
     * @return true if isBooked // if free return false
     */
    public static boolean isBooked(int place) {
        EventPlace e = listEvent.stream()
                .filter(event -> event.getParkingRoom() == place)
                .findAny().orElse(null);

        return e !=null;
    }
}

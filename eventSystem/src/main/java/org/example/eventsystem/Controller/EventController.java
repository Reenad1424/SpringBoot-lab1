package org.example.eventsystem.Controller;

import org.example.eventsystem.ApiResponse.ApiResponse;
import org.example.eventsystem.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/events")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();

    //1-Added new Event
    @PostMapping("/add")
    public ApiResponse addEvent(@RequestBody Event event) {
        events.add(event);
        return new ApiResponse("Event added successfully");
    }

    //2-display all Events
    @GetMapping("/get")
    public ArrayList<Event> displayAllEvent() {
        return events;
    }

    //3-Update Event
    @PutMapping("/update/{index}")
    public ApiResponse updateEvent(@PathVariable int index, @RequestBody Event event) {
        if (index >= 0 && index < events.size()) {
            events.set(index, event);
            return new ApiResponse("Event updated successfully");
        }
        return new ApiResponse("Index not found");
    }

    //4-Delete Event by id
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteEvent(@PathVariable String id) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId().equalsIgnoreCase(id)) {
                events.remove(i);
                return new ApiResponse("Event deleted successfully");
            }
        }
        return new ApiResponse("Event not found");
    }

    //5-change capacity of specific event
    @PutMapping("/changeCapacity/{id}/{capacity}")
    public ApiResponse changeCapacity(@PathVariable String id, @PathVariable int capacity) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId().equalsIgnoreCase(id)) {
                events.get(i).setCapacity(capacity);
                return new ApiResponse("Capacity updated successfully");
            }
        }
        return new ApiResponse("Event not found");
    }

    //6-search event by id
    @GetMapping("/search/{id}")
    public Event searchEvent(@PathVariable String id) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId().equalsIgnoreCase(id)) {
                return events.get(i);
            }
        }
        return null;
    }
}


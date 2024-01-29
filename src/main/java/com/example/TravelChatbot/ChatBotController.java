package com.example.TravelChatbot;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ChatBotController {


    private final Map<String,String> botResponse;
    public  ChatBotController(){
        botResponse=new HashMap<>();
        botResponse.put("hii","Hii how can i help you");
        botResponse.put("hello","Hello how can i help you");
        botResponse.put("thanks","You are welcome");
        botResponse.put("help","""
                Here are some things I can help with. Please choose one:
                1. Weather
                2. Flight schedules and bookings
                3. how can i book a hotel
                4. What are the must-try cuisines
                5.What are the best hotels
                """);

        botResponse.put("Weather","The current weather is sunny 25°C.");
        botResponse.put("Flight schedules and bookings"," Sure! Here are some upcoming " +
       " Flight 123 - Departure: 10:00 AM, Arrival: 1:00 PM"+
        "Flight 456 - Departure: 2:00 PM, Arrival: 5:00 PM");
        botResponse.put("how can i book a hotel","Booking.com");
        botResponse.put("What are the must-try cuisines","Local Dish and " +
                "Traditional Delight");
        botResponse.put("What are the best hotels","Lux Hotel - 5-star" +
                " rating Comfort Suites - Great for families");


    }


    @PostMapping("/processUserInput")
    public Map<String,String> getBotResponse(@RequestBody Map<String,String>request){
        String userInput = request.get("userInput");
        String response = botResponse.getOrDefault(userInput,"Im sorry,I didnt understand");
        Map<String,String>botResponse=new HashMap<>();
        System.out.println(response);
        botResponse.put("response",response);
        System.out.println(botResponse);
        return botResponse;

    }
}

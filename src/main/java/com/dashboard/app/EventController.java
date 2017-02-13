package com.dashboard.app;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dashboard.app.events.JsonSerializer;

@Controller
public class EventController {

	@RequestMapping(value = "/fetchEvents", method = RequestMethod.GET)
	public @ResponseBody String returnEvents(){
		try {
			return JsonSerializer.fetchEvents();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error occured";
		}
	}
}

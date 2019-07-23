package com.myshop;

import com.myshop.utils.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

	@RequestMapping("/hola")
	public String sayHi() {
		return "Hola!";
	}
}

package com.project.fawry.Controller;

import com.project.fawry.Bslogic.Authentication_Bsl;
import com.project.fawry.Models.BaseUser;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AutenticationController {
	
	private Authentication_Bsl authenticationBsl;
	public AutenticationController(Authentication_Bsl authenticationBsl)
	{
		this.authenticationBsl = authenticationBsl;
	}

	@PostMapping(value = "/signUp")
	public String signUp(@RequestBody BaseUser baseUser) {
		return authenticationBsl.add(baseUser);
	}
	@PostMapping(value = "/logIn/{username}/{password}")
	public String logIn(@PathVariable String username, @PathVariable String password) {
		return authenticationBsl.log(username,password);
	}


	public Authentication_Bsl getAuthenticationBsl() {
		return authenticationBsl;
	}

	public void setAuthenticationBsl(Authentication_Bsl authenticationBsl) {
		this.authenticationBsl = authenticationBsl;
	}
}

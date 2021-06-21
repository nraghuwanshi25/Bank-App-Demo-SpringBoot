package com.bank.app;

import java.util.Collection;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.core.EngageNonFatalException;
import com.bank.app.core.EngageResponse;
import com.bank.app.security.AuthentictRequest;
import com.bank.app.security.AuthentictResponse;
import com.bank.app.security.JwtUtil;
import com.bank.app.security.MyUserDetailService;

@RestController
@CrossOrigin("*")
//@RequestMapping("/test")
public class TestController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TestService testService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private MyUserDetailService myUserDetailService;

	private static Logger logger = Logger.getLogger(TestController.class);

	@GetMapping("/show")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<EngageResponse> showMes() {
		logger.info("showMes called");
		EngageResponse engageResponse = new EngageResponse();

		engageResponse.setData("hello");
		engageResponse.setMessage("hello");
		// testService.name();
		logger.info("showMes response hi new");
		return new ResponseEntity<EngageResponse>(engageResponse, HttpStatus.OK);

	}

	@GetMapping("/hello")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> helloMes() {
		logger.info("helloMes response");
		System.out.println("helloMes callle d");
		return new ResponseEntity<String>("hello world", HttpStatus.OK);

	}
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<AuthentictResponse> showMes(@RequestBody @Valid AuthentictRequest authentictRequest) {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentictRequest.getUserName(),
					authentictRequest.getPassword()));
		} catch (BadCredentialsException e) {
			// TODO: handle exceptionthrow
			throw new EngageNonFatalException("Invalid Credential");
		}
		final UserDetails loadUserByUsername = myUserDetailService.loadUserByUsername(authentictRequest.getUserName());
		final String generateToken = jwtUtil.generateToken(loadUserByUsername);
		AuthentictResponse authentictResponse = new AuthentictResponse(generateToken);
		Collection<? extends GrantedAuthority> authorities = loadUserByUsername.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			System.out.println(grantedAuthority.getAuthority());
		}
		return new ResponseEntity<AuthentictResponse>(authentictResponse, HttpStatus.OK);
	}

}

package simplePage.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import simplePage.text.Text;
import simplePage.text.TextService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
@Data
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private TextService textService;

	// create new text By user
	@PostMapping("/user/newtext")
	@ResponseStatus(HttpStatus.CREATED)
	private <T extends Object> void createText(@RequestBody Text text) {
		textService.addNewText(text);
	}

	// get all users
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserForClient> giveAllUsers() {
		return userService.receiveAllUsers();
	}

	// get single user by userId
	@GetMapping("/singleUser/{userId}")
	public User getUserById(@PathVariable("userId") final Long userId) {
		return userService.getOneUserById(userId);
	}

	// create new user
	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createUser(@RequestBody final User user) {
		userService.addNewUser(user);

	}

	// delete user
	@RequestMapping(path = "/user/delete/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUserFromDatabase(@PathVariable final Long userId) {
		userService.deleteUser(userId);
	}
}

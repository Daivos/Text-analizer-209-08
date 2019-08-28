package simplePage.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;

@Transactional
@Service
@Data
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserForClient> receiveAllUsers() {
		List<User> usersFromDatabase = userRepository.findAll();
		List<UserForClient> usersForClient = usersFromDatabase.stream().map((user) -> {
			UserForClient userForClient = new UserForClient();
			userForClient.setUserId(user.getUserId());
			userForClient.setUserName(user.getUserName());
			return userForClient;
		}).collect(Collectors.toList());
		return usersForClient;
	}

	public User getOneUserById(long userId) {
		return userRepository.findOne(userId);
	}

	public void addNewUser(User user) {
		userRepository.save(user);

	}

	public void deleteAll() {
		userRepository.deleteAll();

	}

	public void deleteUser(Long userId) {
		userRepository.delete(userId);
	}

}

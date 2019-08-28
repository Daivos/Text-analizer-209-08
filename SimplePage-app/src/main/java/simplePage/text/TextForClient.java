package simplePage.text;

import lombok.Data;
import simplePage.user.User;

@Data
public class TextForClient {

	private long textId;
	private String textField;
	private User user;
}

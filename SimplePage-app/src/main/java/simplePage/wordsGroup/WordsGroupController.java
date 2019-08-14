package simplePage.wordsGroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
@Data
public class WordsGroupController {

	@Autowired
	private WordsGroupService wordsGroupService;

	// get all wordsGroups
	@RequestMapping(value = "/wordsGroups", method = RequestMethod.GET)
	public List<WordsGroupForClient> giveAllWordsGroups() {
		return wordsGroupService.receiveAllWordsGroups();
	}

	// get all wordsGroups by textId
	@RequestMapping(value = "/wordsGroupsByTextId/{textId}", method = RequestMethod.GET)
	public List<WordsGroupForClient> getWordsGroupsByTextId(@PathVariable(value = "textId") long textId) {
		return wordsGroupService.receiveWordsGroupsByTextId(textId);
	}

	// get one wordsGroup
	@RequestMapping(value = "/singleWordsGroup/{wordsGroupId}", method = RequestMethod.GET)
	public WordsGroupForClient giveSingleWordsGroup(@PathVariable("wordsGroupId") final Long wordsGroupId) {
		return wordsGroupService.receiveWordsGroupInfo(wordsGroupId);
	}
}

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

	// // create new wordsGroup
	// @RequestMapping(value = "/newWordsGroup/{ownerId}/{textId}", method =
	// RequestMethod.POST)
	// @ResponseStatus(HttpStatus.CREATED)
	// public void createWordsGroup(@RequestBody final WordsGroup wordsGroup,
	// @PathVariable(value = "ownerId") long ownerId, @PathVariable(value =
	// "textId") long textId) {
	// wordsGroupService.addNewWordsGroup(wordsGroup, textId);
	// }
	//
	// // update wordsGroup
	// @RequestMapping(path =
	// "/singleWordsGroup/update/{wordsGroupId}/{ownerId}/{textId}", method =
	// RequestMethod.PUT)
	// @ResponseStatus(HttpStatus.CREATED)
	// public void updateExistingWordsGroup(@RequestBody final WordsGroup
	// wordsGroup,
	// @PathVariable final Long wordsGroupId, @PathVariable(value = "ownerId") long
	// ownerId,
	// @PathVariable(value = "textId") long textId) {
	// wordsGroupService.updateWordsGroup(wordsGroup, wordsGroupId, ownerId,
	// textId);
	// }
	//
	// // delete wordsGroup
	// @RequestMapping(path = "/wordsGroup/delete/{wordsGroupId}", method =
	// RequestMethod.DELETE)
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	// p public void updateExistingWordsGroup(@RequestBody final WordsGroup
	// wordsGroup,
	// @PathVariable final Long wordsGroupId, @PathVariable(value = "ownerId") long
	// ownerId,
	// @PathVariable(value = "textId") long textId) {
	// wordsGroupService.updateWordsGroup(wordsGroup, wordsGroupId, ownerId,
	// textId);
	// }
	//
	// // delete wordsGroup
	// @RequestMapping(path = "/wordsGroup/delete/{wordsGroupId}", method =
	// RequestMethod.DELETE)
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	// public void deleteWordsGroupFromDatabase(@PathVariable final Long
	// wordsGroupId) {
	// wordsGroupService.deleteWordsGroup(wordsGroupId);
	// }ublic void deleteWordsGroupFromDatabase(@PathVariable final Long
	// wordsGroupId) {
	// wordsGroupService.deleteWordsGroup(wordsGroupId);
	// }
	//
	// public WordsGroupService getWordsGroupService() {
	// return wordsGroupService;
	// }
	//
	// public void setWordsGroupService(WordsGroupService wordsGroupService) {
	// this.wordsGroupService = wordsGroupService;
	// }
}

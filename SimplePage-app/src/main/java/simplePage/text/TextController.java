package simplePage.text;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import simplePage.wordsGroup.WordsGroupService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
@Data
public class TextController {

	@Autowired
	private TextService textService;
	@Autowired
	private WordsGroupService wordsGroupService;

	// public TextService getTextService() {
	// return textService;
	// }
	//
	// public void setTextService(TextService textService) {
	// this.textService = textService;
	// }

	// get all texts
	@RequestMapping(value = "/texts", method = RequestMethod.GET)
	public List<TextForClient> giveAllTexts() {
		return textService.receiveAllTexts();
	}

	// get single text by textId
	@GetMapping("/singleText/{textId}")
	public Text getTextById(@PathVariable("textId") final Long textId) {
		return textService.getOneTextById(textId);
	}

	// create new text
	@RequestMapping(value = "/newText", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createText(@RequestBody final Text text) {
		// wordsGroupService.deleteAll();
		// textService.deleteAll();
		textService.addNewText(text);
		textService.generateWordsGroups(text);
	}

	// // update text
	// @RequestMapping(path = "/text/update/{textId}", method = RequestMethod.PUT)
	// @ResponseStatus(HttpStatus.CREATED)
	// public void updateExistingText(@RequestBody final Text text, @PathVariable
	// final Long textId) {
	// // wordsGroupService.deleteAll();
	// // textService.deleteAll();
	// textService.updateText(text, textId);
	// textService.generateWordsGroups(text);
	// }

	// delete text
	@RequestMapping(path = "/text/delete/{textId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTextFromDatabase(@PathVariable final Long textId) {
		textService.deleteText(textId);
	}
}

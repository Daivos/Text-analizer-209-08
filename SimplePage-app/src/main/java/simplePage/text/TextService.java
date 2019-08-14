package simplePage.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import simplePage.wordsGroup.WordsGroup;
import simplePage.wordsGroup.WordsGroupService;

@Transactional
@Service
@Data
public class TextService {

	@Autowired
	private TextRepository textRepository;
	@Autowired
	private WordsGroupService wordsGroupService;

	public List<TextForClient> receiveAllTexts() {
		List<Text> textsFromDatabase = textRepository.findAll();
		List<TextForClient> textsForClient = textsFromDatabase.stream().map((text) -> {
			TextForClient txt = new TextForClient();
			txt.setTextId(text.getTextId());
			txt.setTextField(text.getTextField());
			return txt;
		}).collect(Collectors.toList());
		return textsForClient;
	}

	public Text getOneTextById(long textId) {
		return textRepository.findOne(textId);
	}

	public void addNewText(Text text) {
		textRepository.save(text);

	}

	public void deleteAll() {
		textRepository.deleteAll();

	}

	public void deleteText(Long textId) {
		textRepository.delete(textId);
	}

	public void generateWordsGroups(Text text) {
		// Split text to words array
		String[] words = text.getTextField().split("\\s+");
		// To check for a non-word character before blindly performing a replacement
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].replaceAll("[^\\w]", "");
		}
		// Make map of strings with same letter at the end, sorting them based on
		// alphabetical order
		Set<String> letters = new HashSet<String>();
		Map<String, String> wordsByLastLetter = new HashMap<String, String>();
		Map<String, Integer> wordsAmountByLastLetter = new HashMap<String, Integer>();
		for (int i = 0; i < words.length; i++) {
			String reverseWordFirstLetter = (new StringBuilder(words[i]).reverse()).toString().substring(0, 1)
					.toLowerCase();
			if (!wordsByLastLetter.containsKey(reverseWordFirstLetter)) {
				wordsAmountByLastLetter.put(reverseWordFirstLetter, 1);
				wordsByLastLetter.put(reverseWordFirstLetter, words[i]);
				letters.add(reverseWordFirstLetter);
			} else {
				wordsByLastLetter.put(reverseWordFirstLetter,
						wordsByLastLetter.get(reverseWordFirstLetter) + ", " + words[i]);
				wordsAmountByLastLetter.put(reverseWordFirstLetter,
						wordsAmountByLastLetter.get(reverseWordFirstLetter) + 1);
			}
		}
		List<String> listOfLetters = new ArrayList<String>(letters);
		Collections.sort(listOfLetters);

		// Displaying the strings with same letter at the end, sorting them based on
		// alphabetical order
		System.out.println(" Words with the same letter at the end: ");
		for (int i = 0; i <= letters.size() - 1; i++) {
			WordsGroup wordsGroup = new WordsGroup();
			wordsGroup.setLetter(listOfLetters.get(i));
			wordsGroup.setWordsAmount(wordsAmountByLastLetter.get(listOfLetters.get(i)));
			wordsGroup.setWords(wordsByLastLetter.get(listOfLetters.get(i)));
			wordsGroupService.addNewWordsGroup(wordsGroup, text.getTextId());
		}

	}
}

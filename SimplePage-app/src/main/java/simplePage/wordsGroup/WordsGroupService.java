package simplePage.wordsGroup;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import simplePage.text.Text;
import simplePage.text.TextRepository;

@Transactional
@Service
@Data
public class WordsGroupService {

	@Autowired
	private WordsGroupRepository wordsGroupRepository;

	@Autowired
	private TextRepository textRepository;

	public List<WordsGroupForClient> receiveAllWordsGroups() {
		List<WordsGroup> wordsGroupsFromDatabase = getWordsGroupRepository().findAll();
		List<WordsGroupForClient> wordsGroupsForClient = wordsGroupsFromDatabase.stream().map((wordsGroup) -> {
			WordsGroupForClient wGrp = new WordsGroupForClient();
			wGrp.setWordsGroupId(wordsGroup.getWordsGroupId());
			wGrp.setLetter(wordsGroup.getLetter());
			wGrp.setWordsAmount(wordsGroup.getWordsAmount());
			wGrp.setFullText(wordsGroup.getText().getTextField());
			wGrp.setWords(wordsGroup.getWords());
			return wGrp;
		}).collect(Collectors.toList());
		return wordsGroupsForClient;
	}

	public List<WordsGroupForClient> receiveWordsGroupsByTextId(long textId) {
		List<WordsGroup> wordsGroupsFromDatabase = getWordsGroupRepository().findAll().stream()
				.filter(wg -> wg.getText().getTextId() == textId).collect(Collectors.toList());
		List<WordsGroupForClient> wordsGroupsByTextIdForClient = wordsGroupsFromDatabase.stream().map((wordsGroup) -> {
			WordsGroupForClient wGrp = new WordsGroupForClient();
			wGrp.setWordsGroupId(wordsGroup.getWordsGroupId());
			wGrp.setLetter(wordsGroup.getLetter());
			wGrp.setWordsAmount(wordsGroup.getWordsAmount());
			wGrp.setWords(wordsGroup.getWords());
			wGrp.setFullText(wordsGroup.getText().getTextField());
			return wGrp;
		}).collect(Collectors.toList());
		return wordsGroupsByTextIdForClient;
	}

	public WordsGroupForClient receiveWordsGroupInfo(long wordsGroupId) {
		WordsGroup wordsGroup = wordsGroupRepository.findByWordsGroupId(wordsGroupId);
		WordsGroupForClient wGrp = new WordsGroupForClient();
		wGrp.setWordsGroupId(wordsGroup.getWordsGroupId());
		wGrp.setLetter(wordsGroup.getLetter());
		wGrp.setWordsAmount(wordsGroup.getWordsAmount());
		wGrp.setWords(wordsGroup.getWords());
		wGrp.setFullText(wordsGroup.getText().getTextField());
		return wGrp;
	}

	public void addNewWordsGroup(WordsGroup wordsGroup, long textId) {
		wordsGroup.setLetter(wordsGroup.getLetter());
		wordsGroup.setWordsAmount(wordsGroup.getWordsAmount());
		wordsGroup.setWords(wordsGroup.getWords());
		Text text = textRepository.findByTextId(textId);
		wordsGroup.setText(text);
		wordsGroupRepository.save(wordsGroup);
	}

}

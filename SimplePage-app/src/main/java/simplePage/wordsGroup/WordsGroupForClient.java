package simplePage.wordsGroup;

import lombok.Data;

@Data
public class WordsGroupForClient {

	private long wordsGroupId;
	private String letter;
	private Integer wordsAmount;
	private String words;
	private String fullText;

}

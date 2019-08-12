package simplePage.mappers;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import simplePage.text.Text;
import simplePage.wordsGroup.WordsGroup;
import simplePage.wordsGroup.WordsGroupForClient;

@Mapper(componentModel = "spring", uses = { Text.class })
public interface WordsGroupMapper {

	@Mappings({ @Mapping(target = "fullText", expression = "java(text.getTextField)"), })
	WordsGroupForClient wordsGroupToClient(WordsGroup wordsGroup);

	@InheritConfiguration
	List<WordsGroupForClient> wordsGroupToClient(List<WordsGroup> wordsGroup);

}

package simplePage.wordsGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordsGroupRepository extends JpaRepository<WordsGroup, Long> {

	WordsGroup findByWordsGroupId(long wordsGroupId);

}

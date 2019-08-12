package simplePage.text;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import simplePage.wordsGroup.WordsGroup;

@Entity
@Table(name = "TEXT")
@EqualsAndHashCode(exclude = { "wordsGroups" })
@PrimaryKeyJoinColumn(name = "textId")
@Getter
@Setter
public class Text {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private long textId;
	@Column(columnDefinition = "CLOB")
	private String textField;

	// @JsonManagedReference(value = "text")
	@OneToMany(mappedBy = "text", cascade = CascadeType.ALL)
	private List<WordsGroup> wordsGroups;

	// public List<WordsGroup> getWordsGroups() {
	// return wordsGroups;
	// }
	//
	// public void setWordsGroups(List<WordsGroup> wordsGroups) {
	// this.wordsGroups = wordsGroups;
	// }
	//
	// public long getTextId() {
	// return textId;
	// }
	//
	// public void setTextId(long textId) {
	// this.textId = textId;
	// }
	//
	// public String getTextField() {
	// return textField;
	// }
	//
	// public void setTextField(String textField) {
	// this.textField = textField;
	// }

}

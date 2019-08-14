package simplePage.wordsGroup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import simplePage.text.Text;

@Entity
@Table(name = "WORDS_GROUP")
@EqualsAndHashCode(exclude = { "text" })
@PrimaryKeyJoinColumn(name = "wordsGroupId")
@Getter
@Setter
public class WordsGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private long wordsGroupId;
	private String letter;
	private Integer wordsAmount;
	private String words;

	@ManyToOne
	@JsonBackReference(value = "textId")
	@JoinColumn(name = "textId")
	private Text text;

}

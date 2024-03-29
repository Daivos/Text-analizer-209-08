package simplePage.text;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import simplePage.user.User;
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

	@ManyToOne
	@JsonBackReference(value = "userId")
	@JoinColumn(name = "userId")
	private User user;

}

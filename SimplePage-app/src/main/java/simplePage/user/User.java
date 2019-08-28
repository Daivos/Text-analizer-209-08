package simplePage.user;

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
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import simplePage.text.Text;

@Entity
@Table(name = "USER")
@EqualsAndHashCode(exclude = { "texts" })
@PrimaryKeyJoinColumn(name = "userId")
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private long userId;

	@NotNull
	private String userName;
	//
	// @NotNull
	// private String password;

	// @JsonManagedReference(value = "user")
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Text> texts;

}

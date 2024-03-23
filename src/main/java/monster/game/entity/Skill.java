package monster.game.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long skillId;

	private String skill;

	/*
	 * Each of many skills can belong to many monsters. Monsters should persist if
	 * skills are deleted, and skills should persist if monsters are deleted.
	 */
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "skills", cascade = CascadeType.PERSIST)
	private Set<Monster> monsters = new HashSet<>();

}

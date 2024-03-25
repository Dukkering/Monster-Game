package monster.game.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data

public class Monster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long monsterId;

	private String name;
	private String breed;


	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "trainer_id", nullable = false)
	private Trainer trainer;

	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "monster_skill", joinColumns = @JoinColumn(name = "monster_id"), 
		inverseJoinColumns = @JoinColumn(name = "skill_id"))

	private Set<Skill> skills = new HashSet<>();
}

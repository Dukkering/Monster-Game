package monster.game.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data

public class Trainer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long trainerId;

	private String trainerName;

	/*
	 * There is One Trainer to many Monsters, which are kept in a set. If the
	 * trainer is removed, all associated monsters should also be removed.
	 */
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Monster> monsters = new HashSet<>();
}

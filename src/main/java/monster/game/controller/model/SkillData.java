package monster.game.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import monster.game.entity.Monster;
import monster.game.entity.Skill;

@Data
@NoArgsConstructor
public class SkillData {

	private Long skillId;
	private String skillName;
	private Set<MonstersResponse> monsters = new HashSet<>();

		public SkillData(Skill skill) {
			skillId = skill.getSkillId();
			skillName = skill.getSkillName();
			
			for (Monster monster : skill.getMonsters()) {
				monsters.add(new MonstersResponse(monster));
			}
		}
		
		@Data
		@NoArgsConstructor
		public static class MonstersResponse {
			private long monsterId;
			private String name;
			private String breed;
			
			public MonstersResponse(Monster monster) {
				this.monsterId = monster.getMonsterId();
				this.name = monster.getName();
				this.breed = monster.getBreed();
			}
		}
}

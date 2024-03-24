package monster.game.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import monster.game.entity.Monster;
import monster.game.entity.Skill;

@Data
@NoArgsConstructor
public class MonsterData {
	private Long monsterId;
	private String name;
	private String breed;
	private Set<SkillsResponse> skills = new HashSet<>();
	
		public MonsterData(Monster monster) {
			monsterId = monster.getMonsterId();
			name = monster.getName();
			breed = monster.getBreed();
			
			for (Skill skill : monster.getSkills() ) {
				skills.add(new SkillsResponse(skill));
			}
		}
	
		@Data
		@NoArgsConstructor
		
		public static class SkillsResponse {
			private long skillId;
			private String skill;
			
			public SkillsResponse(Skill skill) {
				skillId = skill.getSkillId();
				this.skill = skill.getSkill();
			}
		}
}

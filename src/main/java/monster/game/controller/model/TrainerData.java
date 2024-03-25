package monster.game.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import monster.game.entity.Monster;
import monster.game.entity.Skill;
import monster.game.entity.Trainer;

@Data
@NoArgsConstructor
public class TrainerData {
	

	private Long trainerId;
	private String trainerName;
	private Set<MonsterResponse> monsters = new HashSet<>();
	
	public TrainerData(Trainer trainer) {
		trainerId = trainer.getTrainerId();
		trainerName = trainer.getTrainerName();
		
		for(Monster monster : trainer.getMonsters()) {
			monsters.add(new MonsterResponse(monster));
		}
	}


	@Data
	@NoArgsConstructor
	static class MonsterResponse {
		private Long monsterId;
		private String name;
		private String breed;
		private Set<String> skills = new HashSet<>();
		
		MonsterResponse(Monster monster) {
			monsterId = monster.getMonsterId();
			name = monster.getName();
			breed = monster.getBreed();
			
			for(Skill skill : monster.getSkills()) {
				skills.add(skill.getSkillName());
			}
			
			
		}
	}
}

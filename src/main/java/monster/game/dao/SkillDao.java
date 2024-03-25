package monster.game.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import monster.game.controller.model.MonsterData.SkillsResponse;
import monster.game.entity.Skill;

public interface SkillDao extends JpaRepository<Skill, Long> {



//	Set<Skill> findAllBySkillId2(Set<Skill> skills);

	Set<Skill> findAllBySkillId(Set<SkillsResponse> skills);

}

package monster.game.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import monster.game.entity.Skill;
import monster.game.controller.model.MonsterData;

public interface SkillDao extends JpaRepository<Skill, Long> {

	/*
	 * Had to make MonsterData.SkillsResponse public for the Dao to access this
	 */
	Set<Skill> findAllBySkillIn(Set<MonsterData.SkillsResponse> skills);

}

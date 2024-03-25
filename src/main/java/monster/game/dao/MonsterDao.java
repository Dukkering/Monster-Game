package monster.game.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import monster.game.controller.model.SkillData;
import monster.game.entity.Monster;


public interface MonsterDao extends JpaRepository<Monster, Long> {

	Set<Monster> findAllByMonsterId(Long monsterId);
	
}

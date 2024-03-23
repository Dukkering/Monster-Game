package monster.game.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import monster.game.entity.Skill;

public interface SkillDao extends JpaRepository<Skill, Long> {

	Set<Skill> findAllBySkillIn(Set<SkillsResponse> skills);

}

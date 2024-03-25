package monster.game.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import monster.game.entity.Skill;

public interface SkillDao extends JpaRepository<Skill, Long> {

}

package monster.game.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import monster.game.entity.Monster;


public interface MonsterDao extends JpaRepository<Monster, Long> {

}

package monster.game.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import monster.game.entity.Trainer;

public interface TrainerDao extends JpaRepository<Trainer, Long> {

}

package monster.game.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import monster.game.controller.model.MonsterData;
import monster.game.controller.model.TrainerData;
import monster.game.dao.MonsterDao;
import monster.game.dao.SkillDao;
import monster.game.dao.TrainerDao;
import monster.game.entity.Skill;
import monster.game.entity.Trainer;

@Service
public class MonsterGameService {

	@Autowired
	private TrainerDao trainerDao;

	@Autowired
	private MonsterDao monsterDao;

	@Autowired
	private SkillDao skillDao;

	@Transactional(readOnly = false)
	public TrainerData saveTrainer(TrainerData trainerData) {
		Long trainerId = trainerData.getTrainerId();
		Trainer trainer = findOrCreateTrainer(trainerId);

		setFieldsInTrainer(trainer, trainerData);
		return new TrainerData(trainerDao.save(trainer));

	}

	private void setFieldsInTrainer(Trainer trainer, TrainerData trainerData) {
		trainer.setTrainerName(trainerData.getTrainerName());
	}

	private Trainer findOrCreateTrainer(Long trainerId) {
		Trainer trainer;

		if (Objects.isNull(trainerId)) {
			trainer = new Trainer();
		} else {
			trainer = findTrainerById(trainerId);
		}

		return trainer;
	}

	private Trainer findTrainerById(Long trainerId) {
		return trainerDao.findById(trainerId)
				.orElseThrow(() -> new NoSuchElementException("Trainer with ID=" + trainerId + " was not found."));
	}

	@Transactional(readOnly = true)
	public List<TrainerData> retrieveAllTrainers() {

		//@formatter:off
		return trainerDao.findAll()
		.stream()
		.map(cont -> new TrainerData(cont))
		.toList();
		//@formatter:on
	}

	@Transactional(readOnly = true)
	public TrainerData retrieveTrainerById(Long trainerId) {
		Trainer trainer = findTrainerById(trainerId);
		return new TrainerData(trainer);
	}

	@Transactional(readOnly = false)
	public void deleteTrainerById(Long trainerId) {
		Trainer trainer = findTrainerById(trainerId);
		trainerDao.delete(trainer);
	}

	@Transactional(readOnly = false)
	public MonsterData saveMonster(Long trainerId, MonsterData monsterData) {
		/*
		 * Defines the Trainer
		 */
		Trainer trainer = findTrainerById(trainerId);
		
		/*
		 * Finds all the skills to be associated with a given monster
		 */
		Set<Skill> skills = skillDao.findAllBySkillIn(monsterData.getSkills());
	}

	
}

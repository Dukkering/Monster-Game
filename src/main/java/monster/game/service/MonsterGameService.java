package monster.game.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import monster.game.controller.model.MonsterData;
import monster.game.controller.model.SkillData;
import monster.game.controller.model.TrainerData;
import monster.game.dao.MonsterDao;
import monster.game.dao.SkillDao;
import monster.game.dao.TrainerDao;
import monster.game.entity.Monster;
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
		 * Creates the monster Object and sets the fields
		 */
		Monster monster = findOrCreateMonster(monsterData.getMonsterId());
		
		
		setMonsterFields(monster, monsterData);

		/*
		 * Sets the Trainer for the monster, and puts the monster in the Trainer's list
		 * of monsters.
		 */
		monster.setTrainer(trainer);
		trainer.getMonsters().add(monster);


		Monster dbMonster = monsterDao.save(monster);
		return new MonsterData(dbMonster);
	}

	/*
	 * Conversion from Monster Data Object to Monster Object
	 */
	private void setMonsterFields(Monster monster, MonsterData monsterData) {
		monster.setName(monsterData.getName());
		monster.setBreed(monsterData.getBreed());
		monster.setMonsterId(monsterData.getMonsterId());

	}

	/*
	 * Finds or creates a monster; Searches if it's null, creates it if it is, else
	 * pulls it by the ID.
	 */
	private Monster findOrCreateMonster(Long monsterId) {
		Monster monster;

		if (Objects.isNull(monsterId)) {
			monster = new Monster();
		} else {
			monster = findMonsterById(monsterId);
		}
		return monster;
	}

	private Monster findMonsterById(Long monsterId) {
		return monsterDao.findById(monsterId)
				.orElseThrow(() -> new NoSuchElementException("Monster with ID=" + monsterId + " does not exist."));
	}

	@Transactional(readOnly = true)
	public MonsterData retrieveMonsterById(Long trainerId, Long monsterId) {
		findTrainerById(trainerId);
		Monster monster = findMonsterById(monsterId);

		if (monster.getTrainer().getTrainerId() != trainerId) {
			throw new IllegalStateException(
					"Monster with ID=" + monsterId + " is not owned by trainer with ID=" + trainerId);
		}

		return new MonsterData(monster);
	}

	@Transactional(readOnly = false)
	public void deleteMonsterById(Long trainerId, Long monsterId) {
		findTrainerById(trainerId);
		Monster monster = findMonsterById(monsterId);

		if (monster.getTrainer().getTrainerId() != trainerId) {
			throw new IllegalStateException(
					"Monster with ID=" + monsterId + " is not owned by trainer with ID=" + trainerId);
		}

		monsterDao.delete(monster);

	}

	@Transactional(readOnly = false)
	public SkillData saveSkill(Long trainerId, Long monsterId, SkillData skillData) {
		Monster monster = findMonsterById(monsterId);
		
		Skill skill = findOrCreateSkill(monsterId, skillData.getSkillId());
		
		setSkillFields(skill, skillData);
		
		
		skill.getMonsters().add(monster);
		monster.getSkills().add(skill);
		
		Skill dbSkill = skillDao.save(skill);
		
		return new SkillData(dbSkill);

	}
	
	private Skill findOrCreateSkill(Long monsterId, Long skillId) {
		Skill skill;

		if (Objects.isNull(skillId)) {
			skill = new Skill();
		} else {
			skill = findSkillById(monsterId, skillId);
		}
		return skill;
	}

	private Skill findSkillById(Long monsterId, Long skillId) {
	return skillDao.findById(skillId).orElseThrow(() -> new NoSuchElementException(
			"Skill with ID=" + skillId + " is not known by monster ID=" + monsterId + "."));
	}

	private void setSkillFields(Skill skill, SkillData skillData) {
		skill.setSkillId(skillData.getSkillId());
		skill.setSkillName(skillData.getSkillName());	
	}

	public void deleteSkillById(Long trainerId, Long monsterId, Long skillId) {
		findTrainerById(trainerId);
		findMonsterById(monsterId);
		Skill skill = findSkillById(monsterId, skillId);
		
		skillDao.delete(skill);
	}

	@Transactional(readOnly = true)
	public List<MonsterData> retrieveAllMonsters() {

			//@formatter:off
			return monsterDao.findAll()
			.stream()
			.map(cont -> new MonsterData(cont))
			.toList();
			//@formatter:on
		}
	}
	





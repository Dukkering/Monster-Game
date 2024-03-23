package monster.game.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import monster.game.controller.model.MonsterData;
import monster.game.controller.model.TrainerData;
import monster.game.service.MonsterGameService;

@RestController
@RequestMapping("/monster_game")
@Slf4j
public class MonsterGameController {
	@Autowired
	private MonsterGameService monsterGameService;

	/*
	 * Post function to add a Trainer; Returns a Created code of 201 when successful
	 */
	@PostMapping("/trainer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TrainerData saveTrainer(@RequestBody TrainerData trainerData) {
		log.info("Creating new Trainer {}", trainerData);
		return monsterGameService.saveTrainer(trainerData);
	}
	
	/*
	 * Retrieves all Trainers
	 */
	@GetMapping("/trainer")
	public List<TrainerData> retrieveAllTrainers() {
		log.info("Retrieve all Trainers");
		return monsterGameService.retrieveAllTrainers();
	}
	
	/*
	 * Retrieves a specific Trainer by ID
	 */
	@GetMapping("trainer/{trainerId}")
	public TrainerData retrieverTrainerById(@PathVariable Long trainerId) {
		log.info("Retrieving Trainer with ID={}", trainerId);
		return monsterGameService.retrieveTrainerById(trainerId);
	}
	
	/*
	 * Catches and prevents any attempts to delete all Trainers
	 */
	@DeleteMapping("/trainer/")
	public void deleteAllTrainers() {
		log.info("Attempting to delete all Trainers.");
		throw new UnsupportedOperationException("Deleting all contributors is not allowed.");
	}
	
	/*
	 * Deletes a specific Trainer by the specified ID
	 */
	@DeleteMapping("/trainer/{trainerId}")
	public Map<String, String> deleteTrainerById(@PathVariable Long trainerId){
		log.info("Deleting Trainer with an ID={}", trainerId);
		monsterGameService.deleteTrainerById(trainerId);
		return Map.of("message", "Deletion of Trainer with ID=" 
		+ trainerId+ " was successful.");
	}
	
	/*
	 * Creates a monster, tied to a specific trainer
	 */
	@PostMapping("/trainer/{trainerId}/monster")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MonsterData insertMonster(@PathVariable Long trainerId, @RequestBody MonsterData monsterData) {
		log.info("Creating Monster {} for Trainer with ID={}", monsterData, trainerId);
		return monsterGameService.saveMonster(trainerId, monsterData);
	}
}

































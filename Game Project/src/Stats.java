//actually, we should put these in another map, but stats aren't so important right now, so we can do it later

public class Stats {
	
	int maxHP;
	int currentHP;
	int maxMP;
	int currentMP;
	
	int Strength; 		//determines character's effectiveness with physical attacks
	int Intelligence;	//determines character's effectiveness with magical attacks
	int Endurance;		//determines character's effectiveness against physical and magical attacks
	int Speed;			//determines character's evasiveness and placement in the combat queue
	int Luck;			//if we even use this, we can figure out what we want it to affect
	
	int Attack;			//strength + weapon's attack modifier
	int Defense;		//endurance + armor's defense modifier
}

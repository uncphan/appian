package main.firefighters;

import java.util.ArrayList;
import java.util.List;

import main.api.Building;
import main.api.City;
import main.api.CityNode;
import main.api.FireDispatch;
import main.api.Firefighter;
import main.api.exceptions.NoFireFoundException;

public class FireDispatchImpl implements FireDispatch {

  private List<Firefighter> fireFighters;
  private City city;

  public FireDispatchImpl(City city) {
    this.city = city;
  }

  @Override
  public void setFirefighters(int numFirefighters) {
    ArrayList<Firefighter> fireFighters = new ArrayList<Firefighter>(numFirefighters);
    for (int i = 0; i < numFirefighters; i++) {
    	FirefighterImpl f = new FirefighterImpl(city.getFireStation());
    	fireFighters.add(f);
    }
    this.fireFighters = fireFighters;
  }

  @Override
  public List<Firefighter> getFirefighters() {
    return this.fireFighters;
  }

  @Override
  public void dispatchFirefighers(CityNode... burningBuildings) {
    for (CityNode building : burningBuildings) {
    	FirefighterImpl fighter = getClosestFireFighter(building);
    	fighter.setLocation(building);
    	Building b = city.getBuilding(building);
    	try {
			b.extinguishFire();
		} catch (NoFireFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
  }

  private FirefighterImpl getClosestFireFighter(CityNode building) {
	FirefighterImpl closestFighter = (FirefighterImpl) getFirefighters().get(0);
	int closestDistance = closestFighter.calculateDistance(building);
	for (Firefighter f : getFirefighters()) {
		int currentDistance = ((FirefighterImpl)f).calculateDistance(building);
		if (currentDistance<closestDistance) {
			closestDistance = currentDistance;
			closestFighter = (FirefighterImpl) f;
		}
	}
	return closestFighter;
  }
}

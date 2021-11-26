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

  /**
   * @inheritDoc
   * sets the fire fighters and puts them at the fire station
   */
  @Override
  public void setFirefighters(int numFirefighters) {
    ArrayList<Firefighter> fireFighters = new ArrayList<Firefighter>(numFirefighters);
    for (int i = 0; i < numFirefighters; i++) {
    	FirefighterImpl f = new FirefighterImpl(city.getFireStation());
    	fireFighters.add(f);
    }
    this.fireFighters = fireFighters;
  }

  /**
   * @inheritDoc
   */
  @Override
  public List<Firefighter> getFirefighters() {
    return this.fireFighters;
  }

  /**
   * @inheritDoc
   */
  @Override
  public void dispatchFirefighers(CityNode... burningBuildings) {
	if (burningBuildings != null && city != null) {
	    for (CityNode node : burningBuildings) {
	    	Building b = city.getBuilding(node);
	    	//don't do this if you don't need to put out a fire
	    	if (b != null && b.isBurning() && !b.isFireproof()) {
		    	FirefighterImpl fighter = getClosestFireFighter(node);
		    	fighter.setLocation(node);
		    	try {
					b.extinguishFire();
				} catch (NoFireFoundException e) {
					CityNode location = b.getLocation();
					if (location != null) {
						System.out.println("No fire found for building at coordinates: "+location.getX() +" , "+location.getY());
					}
					e.printStackTrace();
				}
	    	}
	    }
	}
  }

  /**
   * Get the closest fire fighter to a given building
   * @param building
   * @return the closest fire fighter
   */
  private FirefighterImpl getClosestFireFighter(CityNode building) {
	FirefighterImpl closestFighter = (FirefighterImpl) getFirefighters().get(0);
	int closestDistance = closestFighter.calculateDistance(building);
	for (Firefighter f : getFirefighters()) {
		FirefighterImpl currentFighter = (FirefighterImpl) f;
		int currentDistance = currentFighter.calculateDistance(building);
		if (currentDistance<closestDistance) {
			closestDistance = currentDistance;
			closestFighter = currentFighter;
		}
	}
	return closestFighter;
  }
}

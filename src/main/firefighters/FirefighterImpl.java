package main.firefighters;

import main.api.Building;
import main.api.CityNode;
import main.api.Firefighter;

public class FirefighterImpl implements Firefighter {
  private int distance;
  private CityNode location;
  
  FirefighterImpl(Building fireStation) {
	  setFireStation(fireStation);
	  setDistance(0);
  }
  
  /**
   * @inheritDoc
   * @return {@link CityNode} of the fire fighter
   */
  @Override
  public CityNode getLocation() {
    return location;
  }

  /**
   * @inheritDoc
   * sets the total distance traveled
   */
  @Override
  public int distanceTraveled() {
    return this.distance;
  }

  private void setFireStation(Building fireStation) {
	  this.location = fireStation.getLocation();
  }

  private int getDistance() {
    return this.distance;
  }
  
  private void setDistance(int distance) {
	  this.distance = distance;
  }
  
  /**
   * Sets the current location for the fire fighter and adds the distance to get there to the total distance
   *
   * @param building
   */
  public void setLocation(CityNode building) {
	  //add the change in location distance to the fire fighters overall distance
	  setDistance(getDistance() + calculateDistance(building));
	  this.location = building;
  }

  /**
   * calculates the distance to a building from the fire fighters current location
   *
   * @param building
   */
  public int calculateDistance(CityNode building) {
	//taxicab geometry, distance = |x1-x2| + |y1-y2|
	return Math.abs(getLocation().getX()-building.getX()) + Math.abs(getLocation().getY()-building.getY());
  }
}

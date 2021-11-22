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
  
  @Override
  public CityNode getLocation() {
    return location;
  }

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
  
  public void setLocation(CityNode building) {
	  setDistance(getDistance() + calculateDistance(building));
	  this.location = building;
  }

  public int calculateDistance(CityNode building) {
	return Math.abs(getLocation().getX()-building.getX()) + Math.abs(getLocation().getY()-building.getY());
  }
}

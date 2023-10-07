package project2;


import java.lang.*;
/**
 * This class represents a Location. 
 * It uses a single representation:
 * - State and County (state, county) in which each component is represented by a string
 * Optionally, the Location object can store latitude, longitude and elevation of the location
 * 
 * @author Alessandro Landi
 *
 */

public class Location implements Comparable<Location> {

    private String state;
    private String county;
    private double latitude = 0;
    private double longitude = 0;
    private int elevation = 0;

	/**
	 * Constructs a new Location object with specified state and county. 
	 * @param state and county string to be used for this Location
	 * @throws IllegalArgumentException  if state and or county parameter is invalid 
	 */

    public Location( String state, String county) throws IllegalArgumentException{
        //validates if parameters equal null or are blank
        if(state == ""|| state == null) {
            throw new IllegalArgumentException("Invalid string for state: blank or null ");
        }
        if(county == ""|| county == null) {
            throw new IllegalArgumentException("Invalid string for state: blank or null");
        }

        this.state = state;
        this.county = county;

    }

    /**
	 * Returns the string state representing this Location object.
	 * @return the state of this Location object.
	 */

    public String getState(){
        return state;
    }

    /**
	 * Returns the string county representing this Location object.
	 * @return the county of this Location object.
	 */

    public String getCounty(){
        return county;
    }

    /**
	 * Returns the double latitude representing this Location object.
	 * @return the latitude of this Location object.
	 */

    public double getLatitude(){
        return latitude;
    }

	/**
	 * Validates and sets the latitude for this Location object. 
	 * @param latitude double value to be examined and set. 
	 * @throws IllegalArgumentException if the latitude is invalid 
	 */

    public void setLatitude( double latitude ) throws IllegalArgumentException{
        
        //validate the correct parameters of the latitude
        if(latitude < -90 || latitude > 90 ){
            throw new IllegalArgumentException("A valid latitude is in the range of from -90 to +90 inclusive");
        }

        this.latitude = latitude;
    }

    /**
	 * Returns the double longitude representing this Location object.
	 * @return the longitude of this Location object.
	 */
    public double getLongitude(){
        return longitude;
    }


	/**
	 * Validates and sets the longitude for this Location object. 
	 * @param longitude double value to be examined and set. 
	 * @throws IllegalArgumentException if the longitude is invalid 
	 */

    public void setLongitude( double longitude) throws IllegalArgumentException{
        if(longitude < -180 || longitude > 180 ){
            throw new IllegalArgumentException("A valid longitude is in the range of from -180 to +180 inclusive.");
        }

        this.longitude = longitude;
    }

    /**
	 * Returns the int elevation representing this Location object.
	 * @return the elevation of this Location object.
	 */

    public int getElevation(){
        return elevation;
    }

	/**
	 * Sets the elevation for this Location object. 
	 * @param elevation int value to be examined and set. 
	 * @throws IllegalArgumentException if the longitude is invalid 
	 */

    public void setElevation( int elevation ){

        this.elevation = elevation;
    }



	/**
	 * Returns the string representation of this Location.
	 * @returns the string representation of this Location object 
	 */
    @Override
    public String toString(){
        return this.county + ", " + this.state + "\n" + String.format("%.6f", this.latitude ) + ", " + String.format("%.6f", this.longitude) + ", " + this.elevation;
    }


	/**
	 * Indicates whether some object obj is "equal to" this one. 
	 * To Location objects are considered equal if their state, county, longitude, latitude and elevation are the same 
	 * (ignoring the case of strings). 
	 * @return true if this object is the same as the obj argument; false otherwise.
	 */
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Location)){
            return false;
        }
        Location other = (Location) obj;

        if( this.state.equalsIgnoreCase(other.state)){
            if (this.county.equalsIgnoreCase(other.county)){
                if(this.longitude == other.longitude){
                    if(this.latitude == other.latitude){
                        if(this.elevation == other.elevation){
                            return true;
                        }
                    }
                }
            }
        }

        return false;

    }


	/** Compares this object with the specified object for order. 
	 * @param o the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
	 */
    @Override
    public int compareTo(Location o) {
        
        if( this.county.compareToIgnoreCase(o.getCounty()) == 0){
            if ( this.state.compareToIgnoreCase(o.getState()) > 0){
                return 1;
            }
            else if ( this.state.compareToIgnoreCase(o.getState()) < 0){
                return -1;
            }
            if ( this.state.compareToIgnoreCase(o.getState()) == 0){
                if ( Double.compare(this.getLongitude(), o.getLongitude()) > 0){
                    return 1;
                }
                else if ( Double.compare(this.getLongitude(), o.getLongitude()) < 0){
                    return -1;
                }
                if(Double.compare(this.getLongitude(), o.getLongitude()) == 0){
                    if ( Double.compare(this.getLatitude(), o.getLatitude()) > 0){
                        return 1;
                    }
                    else if ( Double.compare(this.getLatitude(), o.getLatitude()) < 0){
                        return -1;
                    }
                    if(Double.compare(this.getLatitude(), o.getLatitude()) == 0){
                        if ( Double.compare(this.getElevation(), o.getElevation()) > 0){
                            return 1;
                        }
                        else if ( Double.compare(this.getElevation(), o.getElevation()) < 0){
                            return -1;
                        }
                        else if(Double.compare(this.getElevation(), o.getElevation()) == 0){

                            return 0;
                        }
                    }
                }
            }
        }
        if (this.county.compareToIgnoreCase(o.getCounty()) > 0) {
            return 1;
        }
        else if (this.county.compareToIgnoreCase(o.getCounty()) < 0) {
            return -1;
        }

        return 0;
    }
}

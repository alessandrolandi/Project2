package project2;

/**
 * This class represents a Feature. 
 * It uses a single representation:
 * - Feature_Name, Feature_Class and the Location (featureName, featureClass, featureLocation) in which featureName and featureClass are 
 * represented by a string and featureLocation is represented by an object Location
 * 
 * 
 * @author Alessandro Landi
 *
 */


public class Feature implements Comparable<Feature> {

    private String featureName;
    private String featureClass;
    private Location featureLocation;

    /**
	 * Constructs a new Feature object with specified featureName, featureClass and featureLocation. 
	 * @param string featureName, string featureClass and object Location featureLocation to be used for this Location
	 * @throws IllegalArgumentException  if featureName, featureClass and or featureLocation parameter is invalid 
	 */

    public Feature (String featureName, String featureClass, Location featureLocation) throws IllegalArgumentException{
        //validates if parameters equal null or are blank
        if(featureName == ""|| featureName == null) {
            throw new IllegalArgumentException("Invalid string for featureName: blank or null");
        }
        if(featureClass == ""|| featureClass == null) {
            throw new IllegalArgumentException("Invalid string for featureClass: blank or null");
        }
        if(featureLocation == null  ){
            throw new IllegalArgumentException("Invalid featureLocation: null");
        }

        this.featureName = featureName;
        this.featureClass = featureClass;
        this.featureLocation = featureLocation;

    }
    /**
	 * Returns the string featureName representing this Feature object.
	 * @return the featureName of this Feature object.
	 */

    public String getFeatureName(){
        return featureName;
    }

    /**
	 * Returns the string featureClass representing this Feature object.
	 * @return the featureClass of this Feature object.
	 */

    public String getFeatureClass(){
        return featureClass;
    }

    /**
	 * Returns the object featureLocation representing this Feature object.
	 * @return the featureLocation of this Feature object.
	 */

    public Location getFeatureLocation(){
        return featureLocation;
    }


	/**
	 * Indicates whether some object obj is "equal to" this one. 
	 * To Feature objects are considered equal if their featureName, featureClass, and featureLocation are the same 
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
        if(!(obj instanceof Feature)){
            return false;
        }
        Feature other = (Feature) obj;

        if( this.getFeatureName().equalsIgnoreCase(other.getFeatureName())){
            if (this.getFeatureClass().equalsIgnoreCase(other.getFeatureClass())){
                if(this.getFeatureLocation().equals(other.getFeatureLocation())){
                    return true;
                }
            }
        }

        return false;

    }


	/**
	 * Returns the string representation of this Feature.
	 * @returns the string representation of this Feature object 
	 */
    @Override
    public String toString(){
        return this.featureName + ", " + this.featureClass+ "\n" + featureLocation.toString();

    }


	/** Compares this object with the specified object for order. 
	 * @param o the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
	 */
    @Override
    public int compareTo(Feature o) {
        if ( this.featureName.compareToIgnoreCase(o.getFeatureName()) == 0){
            if(this.featureLocation.compareTo(o.getFeatureLocation()) > 0){
                return 1;
            }
            else if(this.featureLocation.compareTo(o.getFeatureLocation()) < 0){
                return -1;
            }
            if( this.featureLocation.compareTo(o.getFeatureLocation())== 0){
                if(this.featureClass.compareToIgnoreCase(o.getFeatureClass()) > 0){
                    return 1;
                }
                else if(this.featureClass.compareToIgnoreCase(o.getFeatureClass()) < 0){
                    return -1;
                }
                if(this.featureClass.compareToIgnoreCase(o.getFeatureClass())== 0){
                    return 0;
                }
            }
        }

        if(this.featureName.compareToIgnoreCase(o.getFeatureName()) > 0){
            return 1;
        }
        else if(this.featureName.compareToIgnoreCase(o.getFeatureName()) < 0){
            return -1;
        }

        return 0;

    }
}

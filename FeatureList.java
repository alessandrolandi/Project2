package project2;

import java.util.ArrayList;

/**
 * FeatureList class is used to store a collection of Feature objects. 
 * This class inherits all of its properties from an ArrayList<Feature>. It 
 * adds Feature-specific functions that allow search by featureName, featureClass 
 *and by state (of the featureLocation)
 *  
 * 
 * This class does not store Feature objects in any particular order. 
 * 
 * @author Alessandro Landi
 *
 */

public class FeatureList extends ArrayList<Feature> {
    
    //Default constructor that creates and empty feature list;
    public FeatureList(){
        
    }
	/**
	 * Search through the list of features for an object matching 
	 * the given featureName. 
	 * @param featureName the name of the feature for which to search 
	 * @return the reference to a matching Feature object in the list, or
	 * null if the matching Feature is not found  
	 */

    public FeatureList getByName ( String keyword ) throws IllegalArgumentException{
        
        //validates if parameter equal null or are blank
        if(keyword == "" || keyword == null){
            throw new IllegalArgumentException("Invalid keyword: blank or null");
        }

        FeatureList list = new FeatureList();

        for ( Feature f : this ){
            String name = f.getFeatureName();
             //validates if name equal null or are blank
            if( name == null || name == ""){
                throw new IllegalArgumentException("Invalid keyword: blank or null");
            }

            if(name.toLowerCase().contains(keyword.toLowerCase())){
                list.add(f);
                
            }

        }
        if(list.size() == 0){
            return null;
        }
        return list;
    }

	/**
	 * Search through the list of features for an object matching 
	 * the given featureClass. 
	 * @param featureClass the class of the feature for which to search 
	 * @return the reference to a matching Feature object in the list, or
	 * null if the matching Feature is not found  
	 */

    public FeatureList getByClass ( String keyword ) throws IllegalArgumentException{
         //validates if parameters equal null or are blank
        if(keyword == "" || keyword == null){
            throw new IllegalArgumentException("Invalid keyword: blank or null");
        }
        FeatureList list = new FeatureList();


        for ( Feature f : this ){
            String c = f.getFeatureClass();
             //validates if class equal null or are blank
            if( c == null || c == ""){
                throw new IllegalArgumentException("Invalid featureClass: blank or null");
            }

            if(c.toLowerCase().contains(keyword.toLowerCase())){
                list.add(f);
            }

        }
        if(list.size() == 0){
            return null;
        }
        return list;
    }

	/**
	 * Search through the list of features for an object matching 
	 * the given state of the featureLocation. 
	 * @param state the name of the feature for which to search 
	 * @return the reference to a matching Feature object in the list, or
	 * null if the matching Feature is not found  
	 */

    public FeatureList getByState ( String keyword ) throws IllegalArgumentException{
    //validates if parameter equal null or are blank
        if(keyword == "" || keyword == null){
            throw new IllegalArgumentException("Invalid keyword: blank or null");
        }
        if(keyword.length() != 2){
            return null;
        }
        
        FeatureList list = new FeatureList();


        for ( Feature f : this ){
            String s = f.getFeatureLocation().getState();
             //validates if state equal null or are blank
            if( s == null || s == ""){
                throw new IllegalArgumentException("Invalid state: blank or null");
            }

            if(s.toLowerCase().equals(keyword.toLowerCase())){
                list.add(f);
            }

        }
        if(list.size() == 0){
            return null;
        }
        return list;
    }


}

package project2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Collections;
import java.lang.*;

/**
 * This class is the program performing the search for specific Features.
 * The program is interactive. 
 * When the program is executed the name of the input file containing the list of all the named 
 * Features is provided as the program's single command line argument. The data in this file 
 * serves as a database of all the named Features. 
 * In the interactive part, the user can enter three different parameters: 
 * name desired featureName
 * name desired featureName class desired featureClass
 * name desired featureName state desired State
 * name desire featureName class desired featureClass state desired State or 
 * name desired featureName state desired State class desired featureClass
 * the program responds by printing the featureName, featureClass, State, County, Latitude, Longitude and Elevation
 * 
 * @author Alessandro Landi
 *
 */

public class EveryPlaceHasAName {

/**
 * Splits the given line of a pipe-delimited file according to | characters.
 * @author Joanna Klukowska
 * @param textLine	a line of text to be parsed
 * @return the array containing words (or empty strings) from between | characters
 */

public static String [] splitInputLine(String textLine){

	if (textLine == null ) return null;

	String [] entries = null;

	entries = textLine.split("\\|");

	return entries;
}

    public static void main(String[] args) throws IllegalAccessException{
		
        //verify that the command line argument exists 
        if(args.length == 0){
            System.err.println("Usage Error: No Command Line Argument Specified.");
            System.exit(1);
        }

        //verify that command line argument contains a name of an existing file 
       
        File dataFile = new File(args[0]);

        if(!dataFile.exists()){
            System.err.println("Error: the file with such name does not exist.");
            System.exit(1);
        }
        if(!dataFile.canRead()){
            System.err.println("Error: the file with such name does not exist.");
            System.exit(1);
        }

		//open the file for reading
        Scanner inData = null;

        try {
            inData = new Scanner (dataFile);
        } catch (FileNotFoundException e){
            System.err.println("Error: the file with such name does not exist.");

            System.exit(1);
        }

        //read the content of the file and save the data in a list of named Features

        FeatureList list = new FeatureList();
        String line = null;
        Scanner parseLine = null;
        String[] data = null;

        String featureID = null;
        String featureName = null;
        String featureClass = null;
        String state = null;
        String county = null;
        double latitude = 0;
        double longitude = 0;
        int elevation = 0;
        Feature currentFeature = null;
        Location currentLocation = null;
        
        inData.nextLine();

        while(inData.hasNextLine()){
            try{
                line = inData.nextLine();
                data = splitInputLine(line);
                try{

                featureID = data[0];
                featureName = data[1];
                featureClass = data[2];
                state = data[3];
                county = data[5];

                }catch(ArrayIndexOutOfBoundsException e){
                System.err.println(line);

                }
                //if latitude, longitude, or elevation in the featurelist are null returns 0
                //catches any sort of error caused by the index of the feature and or converting a
                //a string into a double or int and returns a 0 
                try {
                    String lat = null;
                    lat = data[9];
                    latitude = Double.parseDouble(lat);

                } catch(ArrayIndexOutOfBoundsException e){
                    latitude = 0;
                } catch(NumberFormatException e){
                    latitude = 0;
                } catch(NullPointerException e){
                    latitude = 0;
                }
                try {
                    String longit = null;
                    longit = data[10];
                    longitude = Double.parseDouble(longit);
                    
                } catch(ArrayIndexOutOfBoundsException e){
                    longitude = 0;

                } catch(NumberFormatException e){
                    longitude = 0;
                } catch(NullPointerException e){
                    longitude = 0;
                }
                try{
                    String elev = null;
                    elev = data[16];
                    elevation = Integer.parseInt(elev);
                    
                }catch(ArrayIndexOutOfBoundsException e){
                    elevation = 0;

                } catch(NumberFormatException e){
                    elevation = 0;
                } catch(NullPointerException e){
                    elevation = 0;
                }

            } catch (NoSuchElementException ex){
                //caused by an incomplete or miss-formatted line in the input file
                System.err.println(line);
                continue;
            }
            try {
                currentLocation = new Location (state, county);
                currentLocation.setLongitude(longitude);
                currentLocation.setLatitude(latitude);
                currentLocation.setElevation(elevation);
                currentFeature = new Feature ( featureName, featureClass, currentLocation);
                list.add ( currentFeature );

            } catch(IllegalArgumentException ex){
                //ignore this exception and skip to the next line 

            }



        }

        //Sorts the featureList
        Collections.sort(list);

        //interactive mode

        String userValue = "";
        String [] array = null;
        Scanner userInput = new Scanner(System.in);


        do{
            System.out.println("Enter your search query:");
            //get value of from the user 
            userValue = userInput.nextLine();
            while(userValue == " "|| userValue == null){
                //validates if userinput is blank or null
                System.out.println("This is not a valid query. Try again.");
                System.out.println("");
                userValue = userInput.nextLine();
            }
            System.out.println("");
            // splits the user input into a String array
            array = userValue.split(" ");
            
            String first = array[0];

            //validates if userinput is not quit and evaluates which parameters the user inputted
            if(!first.equalsIgnoreCase("quit")){
                if(array.length == 2 && first.equalsIgnoreCase("name")){
                    String name = array[1];

                    FeatureList arr = new FeatureList();
                    arr = list.getByName(name);
                    if(arr == null){
                        //validates if the featureList is null
                        System.out.println("No matches found. Try again.");
                        System.out.println("");
                        continue;
                    }
                    for(Feature f: arr){
                        System.out.println(f.toString() + "\n\n" + "-----------");


                    }

                    System.out.println("");


                } else if(array.length == 4 && first.equalsIgnoreCase("name") && array[2].equalsIgnoreCase("state") ){
                    String name = array[1];
                    String State = array[3];

                    FeatureList arr = new FeatureList();
                    arr = list.getByName(name);
                    arr = arr.getByState(State);
                    if(arr == null){
                        //validates if the featureList is null
                        System.out.println("No matches found. Try again.");
                        System.out.println("");
                        continue;
                    }
                    for(Feature f: arr){
                        System.out.println(f.toString() + "\n\n" + "-----------");


                    }

                    System.out.println("");

                } else if(array.length == 4 && first.equalsIgnoreCase("name") && array[2].equalsIgnoreCase("class") ){
                    String name = array[1];
                    String Class = array[3];

                    FeatureList arr = new FeatureList();
                    arr = list.getByName(name);
                    arr = arr.getByClass(Class);
                    if(arr == null){
                        //validates if the featureList is null
                        System.out.println("No matches found. Try again.");
                        System.out.println("");
                        continue;
                    }
                    for(Feature f: arr){
                        System.out.println(f.toString() + "\n\n" + "-----------");


                    }

                    System.out.println(" ");


                }else if(array.length == 6 && first.equalsIgnoreCase("name") && array[2].equalsIgnoreCase("state") && array[4].equalsIgnoreCase("class")){
                    String name = array[1];
                    String State= array[3];
                    String Class = array[5];

                    FeatureList arr = new FeatureList();
                    arr = list.getByName(name);
                    arr = arr.getByState(State);
                    arr = arr.getByClass(Class);

                    if(arr == null){
                        //validates if the featureList is null
                        System.out.println("No matches found. Try again.");
                        System.out.println("");
                        continue;
                    }

                    for(Feature f: arr){
                        System.out.println(f.toString() + "\n\n" + "-----------");


                    }

                    System.out.println("");

                } else if(array.length == 6 && first.equalsIgnoreCase("name") && array[2].equalsIgnoreCase("class") && array[4].equalsIgnoreCase("state")){
                    String name = array[1];
                    String Class= array[3];
                    String State = array[5];

                    FeatureList arr = new FeatureList();
                    arr = list.getByName(name);
                    arr = arr.getByClass(Class);
                    arr = arr.getByState(State);

                    if(arr == null){
                        //validates if the featureList is null
                        System.out.println("No matches found. Try again.");
                        System.out.println("");
                        continue;
                    }

                    for(Feature f: arr){
                        System.out.println(f.toString() + "\n\n" + "-----------");


                    }

                    System.out.println("");



                }
                else{
                    System.out.println("This is not a valid query. Try again.");
                    System.out.println("");
                }

            }

        } while (!userValue.equalsIgnoreCase("quit"));

        userInput.close();
    }

}

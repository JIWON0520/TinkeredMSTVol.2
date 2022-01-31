package inputprocessers;

public class Consts {
	public static int NUMOFTERMINALS=5000;
	public static int NUMOFPARTITIONS=12;
	public static int NUMOFPORTALS=5;
	public static String FILENAME="";
	public static String FILEPATH="";
	
	public static void setFILENAME(String FILENAME) {
		Consts.FILENAME=FILENAME;
	}
	
	public static void setNUMOFTERMINALS(int NUMOFTERMINALS) {
		Consts.NUMOFTERMINALS=NUMOFTERMINALS;
	}
	
	public static void setNUMOFPARTITIONS(int NUMOFPARTITIONS) {
		Consts.NUMOFPARTITIONS=NUMOFPARTITIONS;
		
	}
	public static void setNUMOFPORTALS(int NUMOFPORTALS) {
		Consts.NUMOFPORTALS=NUMOFPORTALS;
	}
	
	public static void setFILEPATH(String FILEPATH) {
		Consts.FILEPATH=FILEPATH;
	}
}


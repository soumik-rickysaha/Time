package Time.TimeTests;

import java.io.File;

/**
 * Hello world!
 *
 */
public class Utilities {
	String TimeFolderPath = "D:\\Time All Question Papers";
	String Aimcat = TimeFolderPath + "\\AIMCATS";
	String Samcats = TimeFolderPath + "\\SAMCATS";
	String Omets = TimeFolderPath + "\\OMETS";
	String AimOmets = TimeFolderPath + "\\AIMOMETS";
	File Fileobj;

	public String createFolder(String strTestName, String TargetLocation) {
		String Fullpath = null;
		String Fullpath1 = "w";


		
		switch (TargetLocation.toUpperCase()) {

		case "AIMCAT":
			Fullpath = TimeFolderPath + Aimcat;
			break;
		case "SAMCATS":
			Fullpath = TimeFolderPath + Samcats;
			break;
		case "OMETS":
			Fullpath = TimeFolderPath + Omets;
			break;
		case "AIMOMETS":
			Fullpath = TimeFolderPath + AimOmets;
			break;
		default:
			System.out.println("issue in path select case");
			break;
		}

		// create Directory with testname
		Fileobj = new File(Fullpath + "\\" + strTestName);
		if (!Fileobj.exists()) {
			Fileobj.mkdir();
			
		}
		return Fullpath + "\\" + strTestName;

	}
}

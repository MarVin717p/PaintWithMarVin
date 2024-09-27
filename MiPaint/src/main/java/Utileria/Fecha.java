package Utileria;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fecha {
	public static final String DATA_FORMAT_NOW = "dd/MM/yyyy HH:mm:ss";
	
	public static String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}
	
	public static String now(String dataFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
		return sdf.format(cal.getTime());
	}
}
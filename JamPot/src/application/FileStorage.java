package application;

import java.io.Serializable;
import java.util.ArrayList;

public class FileStorage implements Serializable {
	private static final long serialVersionUID = -1255147395157085942L;
	public ArrayList<MotionPattern> data;

	public FileStorage(ArrayList<MotionPattern> newData) {
		data = newData;
	}

}

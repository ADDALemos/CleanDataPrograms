import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ajax.common.SequencePrinter;
import ajax.functions.annotations.ExportClass;
import ajax.functions.annotations.ExportFunction;

@ExportClass(path = "merge")
public class MergeLessons {

	@ExportFunction
	public static Space mergeLessons(Object[] clusterId, Object[] roomName, Object[] roomID, Object[] lesson, Object[] lessonID, Object[] maxC, Object[] currentC, Object[] typeT, Object[] start, Object[] endDate){
		Space ret = new Space();
		int correct = 0;
		
		for (int i = 0; i < lesson.length; i++){
			
			if(roomName[i] != null){
				correct = i;
				break;
			}
		}
		
		ret.roomID = (String) roomID[correct];
		ret.roomName = (String) roomName[correct];
		ret.lesson = (String) lesson[correct];
		ret.typeT = (String) typeT[correct];
		ret.currentC = (int) currentC[correct];
		ret.maxC = (int) maxC[correct];
		ret.start = (String) start[correct];
		ret.endDate = (String) endDate[correct];
		ret.lectureID = (String) lessonID[correct];
		
		return ret;
	}
	
	}

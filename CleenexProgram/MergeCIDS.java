import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ajax.common.SequencePrinter;
import ajax.functions.annotations.ExportClass;
import ajax.functions.annotations.ExportFunction;

@ExportClass(path = "merge")
public class MergeCIDS {

	@ExportFunction
	public static CidsPubAuthor mergeTeamAuthor(Object[] clusterId, Object[] authorid, Object[] firstname, Object[] lastname){
		CidsPubAuthor ret = new CidsPubAuthor();
		int correct = 0;
		
		for (int i = 0; i < authorid.length; i++){
			int clu = (Integer) clusterId[i];
			int auth = (Integer) authorid[i];
			
			if(clu != auth){
				correct = i;
				break;
			}
		}
		
		ret.authorid = (Integer) authorid[correct];
		ret.firstname = (String) firstname[correct];
		ret.lastname = (String) lastname[correct];
		
		return ret;
	}
	
	@ExportFunction
	public static CidsPubAuthor mergeAuthors(Object[] clusterId, Object[] authorid, Object[] firstname, Object[] lastname){
		CidsPubAuthor ret = new CidsPubAuthor();
		int correct = 0;
		
		
		for (int i = 0; i < authorid.length; i++){
			if((((String)firstname[i])+((String)lastname[i])).length() >= (((String)firstname[correct])+((String)lastname[correct])).length()){
				correct=i;
			}
		}
		
		ret.authorid = (Integer) authorid[correct];
		ret.firstname = (String) firstname[correct];
		ret.lastname = (String) lastname[correct];
		
		return ret;
	}

	@ExportFunction
	public static String mergeAuthorName(String firstname, String lastname){
		return lastname + ", " + firstname;
	}

	@ExportFunction
	public static String mergeAuthorList(Object[] names){
		List<String> l = new LinkedList<String>();
		for (int i = 0; i < names.length; i++) {
			l.add((String) names[i]);
		}
		
		return new SequencePrinter("%1$s", " and ").mapToString(l);
	}

	@ExportFunction
public static CidsTeamAuthorCoauthor mergeTeamCoauthorship(Object[] clusterid, Object[] teamid, Object[] tauthorid, Object[] authorid){
		
		CidsTeamAuthorCoauthor ret = new CidsTeamAuthorCoauthor();
		
		List<Integer> itauthorid = new ArrayList<Integer>(tauthorid.length);
		for (int i = 0; i < tauthorid.length; i++) {
			itauthorid.add((Integer) tauthorid[i]);
		}
		
		int pos = itauthorid.indexOf((Integer) authorid[0]);
		
		if(pos != -1){
			ret.authorid = (Integer) authorid[pos];
			ret.teamid = (Integer) teamid[pos];
		}

		return ret;	
	}

	@ExportFunction
public static CidsPublication mergePublication(Object[] gsid, Object[] title, Object[] year){
	
	CidsPublication ret = new CidsPublication();
	
	BigInteger minGsid = new BigInteger((String)gsid[0]);
	
	for (int i = 1; i < gsid.length; i++) {
		minGsid = minGsid.min(new BigInteger((String)gsid[i]));
	}	
	ret.gsid = minGsid.toString();
	
	String top = "";
	for (int i = 0; i < title.length; i++) {
		String cur = (String) title[i];
		if(cur.length() > top.length())
			top = cur;
	}
	ret.title = top;
	
	for (int i = 0; i < year.length; i++) {
		String yr = (String) year[i];
		if(ret.year == null){
			ret.year = yr; continue;
		}
		if(yr == null) continue;
		if(!ret.year.equals(yr)){
			ret.year = null;
			break;
		}
	}
	
	return ret;
}

}

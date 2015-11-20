package examples.google;

public class GoogleSample2 {

	public static void main(String [] args) {
		String S = "dir1\n"
				+ " dir11\n"
				+ " dir12\n"
				+ "  picture.jpeg\n"
				+ "  dir121\n"
				+ "  file1.txt\n"
				+ "  dir3\n"
				+ "   file3.txt\n"
				+ "   dir5\n"
				+ "    longpicture.png\n"
				+ "  dir4\n"
				+ "dir2\n"
				+ " file2.gif\n";
		System.out.println(solution(S));
	}
	
	public static int solution(String S) {;
		String longestPath = "";
		String currentPath = "";
		String [] parts = S.split("\n");
		int lastLevel = -1;
		for(int i = 0; i < parts.length; i++) {
			String part = parts[i];
			int level = part.lastIndexOf(' ')+1;
			part = part.replaceAll("\\s+", "");
			if(level > lastLevel) {
				currentPath += "/" + part;
			} else if(level < lastLevel) {
				for(int dif = (lastLevel - level); dif > 0; dif--) {
					currentPath = currentPath.substring(0, currentPath.lastIndexOf('/'));
				}
				if(level == 0) {
					currentPath = "/" + part;
				} else {
					currentPath = currentPath.substring(0, currentPath.lastIndexOf('/'));
					currentPath += "/" + part;	
				}
			} else {
				currentPath = currentPath.substring(0, currentPath.lastIndexOf('/'));
				currentPath += "/" + part;
			}
			lastLevel = level;
			
			if(currentPath.contains(".jpeg") ||
					currentPath.contains(".png") ||
					currentPath.contains(".gif") &&
					currentPath.length() > longestPath.length()) {
				longestPath = currentPath;
			}
			System.out.println(currentPath);
		}
		return longestPath.length();
	}
}

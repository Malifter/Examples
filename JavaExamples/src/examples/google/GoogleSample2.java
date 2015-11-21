package examples.google;

public class GoogleSample2 {

	public static void main(String [] args) {
		String S = "dir1\n"
				+ " dir11\n"
				+ " dir12\n"
				+ "  picturepicturepicturepicturepicture.jpeg\n"
				+ "  dir121\n"
				+ "  file1.txt\n"
				+ "  dir3\n"
				+ "   file3.txt\n"
				+ "   dir5\n"
				+ "    longpicture.png\n"
				+ "  dir4\n"
				+ "dir2\n"
				+ " longpicturelongpicture.gif\n";
		System.out.println(solution3(S));
	}
	
	// O(n) if we solve it backwards because now we don't have to backtrack at all
	public static int solution3(String S) {
		int max = 0;
		String [] parts = S.split("\n");
		max = solution3Recursive(parts, parts.length, parts.length-1, "");
		return max;
	}
	
	public static int solution3Recursive(String [] parts, int lastLevel, int i, String path) {
		if(i == -1) return 0;
		String part = parts[i];
		int level = part.lastIndexOf(' ') + 1;
		part = part.replaceAll("\\s+", "");
		int max = 0;
		if(level > lastLevel) {
			path = "/" + part;
		} else if(level < lastLevel) {
			path = "/" + part + path;
			
		} else if((part.contains(".jpeg") ||
				part.contains(".png") ||
				part.contains(".gif")) &&
				part.length()+1 > path.length()) {
			path = "/" + part;
		}
		System.out.println(path);
		max = Math.max(path.length(), solution3Recursive(parts, level, i-1, path));
		return max;
	}
	
	// O(h*n) time complexity
	public static int solution2(String S) {
		int max = 0;
		String [] parts = S.split("\n");
		max = solution2Recursive(parts, -1, 0, "");
		return max;
	}
	
	public static int solution2Recursive(String [] parts, int lastLevel, int i, String path) {
		if(i == parts.length) return 0;
		String part = parts[i];
		int level = part.lastIndexOf(' ') + 1;
		part = part.replaceAll("\\s+", "");
		int max = 0;
		if(level > lastLevel) {
			path += "/" + part;
		} else if(level < lastLevel) {
			for(int dif = (lastLevel - level); dif > 0; dif--) {
				path = path.substring(0, path.lastIndexOf('/'));
			}
			if(level == 0) {
				path = "/" + part;
			} else {
				path = path.substring(0, path.lastIndexOf('/'));
				path += "/" + part;	
			}
		} else {
			path = path.substring(0, path.lastIndexOf('/'));
			path += "/" + part;
		}
		System.out.println(path);
		max = Math.max(max, solution2Recursive(parts, level, i+1, path));
		if((path.contains(".jpeg") ||
				path.contains(".png") ||
				path.contains(".gif") &&
				level >= lastLevel)) {
			return Math.max(max, path.length());
		}
		return max;
	}
	
	// O(h*n) time complexity
	public static int solution1(String S) {;
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
			
			if((currentPath.contains(".jpeg") ||
					currentPath.contains(".png") ||
					currentPath.contains(".gif")) &&
					currentPath.length() > longestPath.length()) {
				longestPath = currentPath;
			}
			System.out.println(currentPath);
		}
		return longestPath.length();
	}
}

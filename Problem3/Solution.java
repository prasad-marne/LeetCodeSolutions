import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
	private int lengthOfLongestSubstring(String s) {
		return findSubStringsWoRepeatingChars(s);
	}

	private Integer findSubStringsWoRepeatingChars(String s) {
		HashMap<String, Integer> arr = new LinkedHashMap<>();
		StringBuilder sub = new StringBuilder();
		boolean isNew;
		HashSet<String> substring = new HashSet<>();
		for (int i = 0; i < s.length(); i++) {
			String temp = Character.toString(s.charAt(i));
			isNew = substring.add(temp);
			if (!isNew) {
				arr.put(sub.toString(), sub.length());
				i = i - ((sub.length() - 1) - sub.lastIndexOf(temp));
				i--;
				substring.clear();
				sub.replace(0, sub.length(), "");
			} else {
				sub.append(temp);
			}
		}
		arr.put(sub.toString(), sub.length());

		LinkedHashMap<String, Integer> result = arr.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));

		System.out.println(result);

		return result.entrySet().stream().findFirst().get().getValue();
	}

	public static void main(String[] args) {
		String s = "abcabcbb";
		String s2 = "bbbbb";
		String s3 = "pwwkew";
		Solution solution = new Solution();
		System.out.println(solution.lengthOfLongestSubstring(s));
		System.out.println(solution.lengthOfLongestSubstring(s2));
		System.out.println(solution.lengthOfLongestSubstring(s3));
	}
}
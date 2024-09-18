import java.util.HashMap;
import java.util.Map;

class Dp_Solution {
    private Map<String, Boolean> memo = new HashMap<>();
    
    public boolean isMatch(String s, String p) {
        return isMatchHelper(s, p, 0, 0);
    }

    public void clearMemo() {
        memo.clear(); 
    }

    private boolean isMatchHelper(String s, String p, int i, int j) {
        String key = i + "," + j;
   
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (j == p.length()) {
            return i == s.length();
        }

        boolean firstMatch = (i < s.length() && 
                              (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));

        boolean result;
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            result = isMatchHelper(s, p, i, j + 2) || 
                     (firstMatch && isMatchHelper(s, p, i + 1, j));
        } else {
            result = firstMatch && isMatchHelper(s, p, i + 1, j + 1);
        }

        memo.put(key, result);
        return result;
    }
}

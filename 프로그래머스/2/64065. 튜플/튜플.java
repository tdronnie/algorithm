import java.util.*;
class Solution {
    public int[] solution(String s) {
        HashSet<Integer> hs = new HashSet<>();
        List<Integer> arr = new ArrayList<>();
        
        s = s.replace("{{", "");
        s = s.replace("}}", "");
        s = s.replace("},{", " ");
        
        StringTokenizer st = new StringTokenizer(s);
        String[] strs = new String[st.countTokens()];
        int i=0;
        
        while(st.hasMoreTokens()){
            strs[i++] = st.nextToken();
        }
        
        Arrays.sort(strs, (s1, s2) -> s1.length() - s2.length());
        
        for(String nums : strs){
            String[] splits = nums.split(",");
            for(String numStr : splits){
                int n = Integer.parseInt(numStr);
                if(hs.contains(n)) continue;
                hs.add(n);
                arr.add(n);
            }
        }
        
        int[] result = new int[arr.size()];
        for(i=0; i<arr.size(); i++){
            result[i] = arr.get(i);
        }
        return result;
    }
}
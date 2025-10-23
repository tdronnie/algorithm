class Solution {
    public int solution(String skill, String[] skill_trees) {
        
        int possible = 0;
        
        for(int i=0; i<skill_trees.length; i++){
            int no=0;
            boolean impossible = false;
            for(int j=0; j< skill_trees[i].length(); j++){
                char c = skill_trees[i].charAt(j);
                
                if(skill.indexOf(c) == -1) continue;
                if(no != skill.indexOf(c)){
                    impossible = true;
                    break;
                }
                
                no++;
            }
            
            if(!impossible){
                possible++;
            }
        }
        
        return possible;
    }
}
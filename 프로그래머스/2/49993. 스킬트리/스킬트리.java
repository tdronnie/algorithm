/*
한 문자열 안에 다른 문자열이 순서대로 들어가는지
선행 스킬(알파벳)을 배우면 no++, 타겟 문자열을 순회하면서 스킬트리 안에 있는 스킬이라면 현재 no와 실제 해당 스킬순서가 맞는지 확인
*/
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
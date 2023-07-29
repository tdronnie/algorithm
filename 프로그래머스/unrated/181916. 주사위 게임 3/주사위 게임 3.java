class Solution {
    public int solution(int a, int b, int c, int d) {
        int[] dice = new int[7]; //주사위 나온 개수 체크 배열

        //주사위 나온 숫자인덱스 카운트
        dice[a]++;
        dice[b]++;
        dice[c]++;
        dice[d]++;

        int min=7;
        int diff=1;
        for(int i=1; i< dice.length; i++){

            if(dice[i] == 1)
                min = Math.min(min, i);
            if(dice[i] == 4) { //모두 같은 숫자
                return 1111*i;
            }
            else if(dice[i] == 3){ //3 주사위 같은 숫자
                for (int j = 1; j < dice.length; j++) {
                    if(dice[j] == 1)
                        return (10 * i + j) * (10 * i + j);
                }
            }

            else if(dice[i] == 2){ //2주사위 같은 숫자
                for (int j = 1; j < dice.length; j++) {
                    if(j != i && dice[j] == 2) //다른 2주사위 같은 숫자
                        return (i+j)*(Math.abs(i-j));
                    if(dice[j] == 1)
                        diff *= j;
                }
                return diff;
            }
        }
        return min;
    }
}
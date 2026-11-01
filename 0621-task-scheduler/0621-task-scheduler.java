class Solution {
public int leastInterval(char[] tasks, int n) {
int[] freq=new int[26];

    for(char task : tasks) {
        freq[task-'A']++;
    }
    int maxFreq=0;
    for(int count : freq) {
        maxFreq =Math.max(maxFreq,count);
    }
    int countMax=0;
    for(int count : freq) {
        if(count==maxFreq) {
            countMax++;
        }
    }
    int result=(maxFreq-1)*(n+1)+countMax;
    return Math.max(tasks.length,result);
}
}

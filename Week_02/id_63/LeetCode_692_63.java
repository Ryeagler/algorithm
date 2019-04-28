/*
https://leetcode-cn.com/problems/top-k-frequent-words/
692. 前K个高频单词
给一非空的单词列表，返回前 k 个出现次数最多的单词。
题目描述
返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
示例 1：

输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
输出: ["i", "love"]
解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
    注意，按字母顺序 "i" 在 "love" 之前。

 

示例 2：

输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
输出: ["the", "is", "sunny", "day"]
解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
    出现次数依次为 4, 3, 2 和 1 次。

 

注意：

    假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
    输入的单词均由小写字母组成。

扩展练习：

    尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
*/
/*
时间不够，来不及用堆或者优先级队列。
*/
class Solution {
  static class WordFrequent {
    String word;
    int frequent;

    public WordFrequent(String word, int frequent) {
      this.word = word;
      this.frequent = frequent;
    }
  }

  public List<String> topKFrequent(String[] words, int k) {
    java.util.HashMap<String, Integer> map = new java.util.HashMap<>();
    for (int i = 0; i < words.length; i++) {
      Integer count = map.get(words[i]);
      if (count == null) {
        count = 0;
      }
      count ++;
      map.put(words[i], count);
    }
    java.util.List<WordFrequent> wordFrequents = new java.util.ArrayList<>();
    for (String word : map.keySet()) {
        wordFrequents.add(new WordFrequent(word, map.get(word)));
    }
    Collections.sort(wordFrequents, new Comparator<WordFrequent>() {
      @Override
      public int compare(WordFrequent o1, WordFrequent o2) {
        return o1.word.compareTo(o2.word);
      }
    });
    Collections.sort(wordFrequents, new Comparator<WordFrequent>() {
      @Override
      public int compare(WordFrequent o1, WordFrequent o2) {
        if (o1.frequent == o2.frequent) {
          return 0;
        } else if (o1.frequent > o2.frequent) {
          return -1;
        } else {
          return 1;
        }
      }
    });
    java.util.List<String> result = new java.util.ArrayList<>(k);
    for (int i = 0; i < k && i < map.size(); i++) {
      result.add(wordFrequents.get(i).word);
    }
    return result;
  }
}

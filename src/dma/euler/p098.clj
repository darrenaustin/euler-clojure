(ns dma.euler.p098
  (:use dma.euler.numeric)
  (:use clojure.math.combinatorics))

(defn has-repeating-digits? [n]
  (not= (count (distinct (str n)))
        (count (str n))))

(defn anagrams [words]
  (filter #(< 1 (count %))
          (vals (group-by sort words))))

(defn num-digit-sub [w1 w2 n]
  (number (map (zipmap w1 (str n)) w2)))

(defn square-anagram-pair [w1 w2 squares]
  (let [square? (set squares)]
    (some (fn [s1] 
            (let [s2 (num-digit-sub w1 w2 s1)]
              (if (square? s2)
                (max s1 s2))))
          squares)))

(defn max-square-pair [words squares]
  (let [matches (keep identity (map #(square-anagram-pair (first %) (second %) squares)
                                    (combinations words 2)))]
    (when (seq matches)
      (apply max matches))))

(defn solution {:answer 18769} []
  (let [words     (re-seq #"\w+" (slurp "data/words.txt"))
        anagrams  (reverse (sort-by #(count (first %)) (anagrams words)))
        max-chars (count (ffirst anagrams))
        squares   (group-by num-digits
                            (remove has-repeating-digits?
                                    (take-while-< (exp 10 max-chars) (map square (range)))))]
    (first
     (keep identity
           (map #(max-square-pair % (squares (count (first %)))) anagrams)))))


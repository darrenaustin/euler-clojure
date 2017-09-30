(ns euler.p042
  (:use [euler numeric util]))

(defn word-value [w]
  (sum (map #(inc (- (int %) (int \A))) w)))

(defn solution {:answer 162} []
  (let [words (words-from "data/words.txt")
        max-word-size (apply max (map count words))
        triangles (into #{} (take-while-< (inc (* max-word-size (word-value "Z"))) (triangle-nums)))]
    (count (filter triangles (map word-value words)))))





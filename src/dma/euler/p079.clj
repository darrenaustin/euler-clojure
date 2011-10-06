(ns dma.euler.p079
  (:use dma.euler.numeric)
  (:use clojure.set))

;; Solved this on paper, but thought it would be fun to programmically solve it.

(defn in-front-map [keys]
  (loop [keys keys in-front {}]
    (if (seq keys)
      (recur (rest keys) (assoc in-front (first keys) (set (rest keys))))
      in-front)))

(defn solution {:answer 73162890} []
  (let [logins (map #(digits (read-string %)) (.split (slurp "data/keylog.txt") "\n"))]
    (number
     (reverse
      (map first (sort-by #(count (second %))
                          (reduce #(merge-with union % (in-front-map %2))
                                  {} logins)))))))

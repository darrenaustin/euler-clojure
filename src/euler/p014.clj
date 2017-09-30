(ns euler.p014
  (:use euler.numeric))

(defn collatz-count [n]
  (loop [c 1 n n]
    (cond (<= n 1) c
          (even? n) (recur (inc c) (/ n 2))
          :else (recur (inc c) (inc (* 3 n))))))

(defn solution {:answer 837799} []
  (first (reduce #(if (> (second %2) (second %1)) %2 %1)
                 (map list (range) (map collatz-count (range 0 1000000))))))

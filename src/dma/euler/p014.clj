(ns dma.euler.p014
  (:use dma.euler.numeric clojure.contrib.seq-utils))

(defn collatz-count [n]
  (loop [c 1 n n]
    (cond (<= n 1) c
          (even? n) (recur (inc c) (/ n 2))
          :else (recur (inc c) (inc (* 3 n))))))

(defn solution {:answer 837799} []
  (first (reduce #(if (> (second %2) (second %1)) %2 %1)
                 (indexed (map collatz-count (range 0 1000000))))))

(ns dma.euler.p053
  (:use dma.euler.numeric))

(def fact (memoize factorial))

(defn num-combos [n r]
  (/ (fact n) (* (fact r) (fact (- n r)))))

(defn solution {:answer 4075} []
  (count
   (for [n (range 23 101)
         r (range 1 n)
         :let [c (num-combos n r)]
         :when (> c 1000000)]
     c)))

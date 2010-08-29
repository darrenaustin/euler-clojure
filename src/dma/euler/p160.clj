(ns dma.euler.p160
  (:use dma.euler.numeric))

(defn trim-trailing-zeros [n]
  (if (div? n 10)
    (recur (/ n 10))
    (rem n 100000)))

(defn f [n]
  (reduce (fn [tf x] (trim-trailing-zeros (* tf x))) (range 2 (inc n))))

(defn solution {:answer nil} []
  (f 1000000000000))

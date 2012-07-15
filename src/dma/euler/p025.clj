(ns dma.euler.p025
  (:use dma.euler.numeric))

(defn solution {:answer 4782} []
  (ffirst (filter (fn [[i f]] (>= (num-digits f) 1000)) (map list (range) (fibs)))))

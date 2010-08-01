(ns dma.euler.p001
  (:use dma.euler.numeric))

(defn solution {:answer 233168} []
  (sum (filter #(or (div? % 3) (div? % 5)) (range 1000))))

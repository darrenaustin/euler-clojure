(ns dma.euler.p021
  (:use dma.euler.numeric))

(defn solution {:answer 31626} []
  (sum (filter amicable? (range 10000))))




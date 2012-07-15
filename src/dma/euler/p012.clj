(ns dma.euler.p012
  (:use dma.euler.numeric))

(defn solution {:answer 76576500} []
  (first (filter #(< 500 (count (divisors %))) (triangle-nums))))




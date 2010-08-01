(ns dma.euler.p016
  (:use dma.euler.numeric))

(defn solution {:answer 1366} []
  (sum (digits (exp 2 1000))))




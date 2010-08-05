(ns dma.euler.p036
  (:use dma.euler.numeric))

(defn solution {:answer 872187} []
  (sum (for [x (range 1 (inc 1000000))
             :when (and (palindrome? x) (palindrome? (Integer/toString x 2)))]
         x)))

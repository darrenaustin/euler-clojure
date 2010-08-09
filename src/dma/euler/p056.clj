(ns dma.euler.p056
  (:use dma.euler.numeric clojure.contrib.math))

(defn solution {:answer 972} []
  (apply max
         (for [a (range 1 101), b (range 1 101)]
           (sum (digits (expt a b))))))

(ns dma.euler.p048
  (:use dma.euler.numeric clojure.math.numeric-tower))

(defn solution {:answer 9110846700} []
  (rem (sum (map #(expt % %) (range 1 1001))) 10000000000))


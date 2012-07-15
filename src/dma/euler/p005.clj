(ns dma.euler.p005
  (:use clojure.math.numeric-tower))

(defn solution {:answer 232792560} []
  (reduce lcm (range 1 21)))

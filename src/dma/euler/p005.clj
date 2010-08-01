(ns dma.euler.p005
  (:use clojure.contrib.math))

(defn solution {:answer 232792560} []
  (reduce lcm (range 1 21)))

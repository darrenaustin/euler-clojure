(ns euler.p005
  (:use euler.numeric))

(defn solution {:answer 232792560} []
  (reduce lcm (range 1 21)))

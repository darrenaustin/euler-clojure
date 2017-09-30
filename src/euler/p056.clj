(ns euler.p056
  (:use euler.numeric))

(defn solution {:answer 972} []
  (apply max
         (for [a (range 1 101), b (range 1 101)]
           (sum (digits (exp a b))))))

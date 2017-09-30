(ns euler.p048
  (:use euler.numeric))

(defn solution {:answer 9110846700} []
  (rem (sum (map #(exp % %) (range 1 1001))) 10000000000))

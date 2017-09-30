(ns euler.p016
  (:use euler.numeric))

(defn solution {:answer 1366} []
  (sum (digits (exp 2 1000))))




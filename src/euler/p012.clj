(ns euler.p012
  (:use euler.numeric))

(defn solution {:answer 76576500} []
  (first (filter #(< 500 (count (divisors %))) (triangle-nums))))



